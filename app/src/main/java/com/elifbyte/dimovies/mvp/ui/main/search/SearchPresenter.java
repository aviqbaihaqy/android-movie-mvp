/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.elifbyte.dimovies.mvp.ui.main.search;

import android.support.v7.widget.SearchView;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.elifbyte.dimovies.mvp.data.DataManager;
import com.elifbyte.dimovies.mvp.data.network.model.MovieResponse;
import com.elifbyte.dimovies.mvp.ui.base.BasePresenter;
import com.elifbyte.dimovies.mvp.utils.rx.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by janisharali on 25/05/17.
 */

public class SearchPresenter<V extends SearchMvpView> extends BasePresenter<V> implements
        SearchMvpPresenter<V> {

    private static final String TAG = "SearchPresenter";
    private PublishSubject<String> publishSubject = PublishSubject.create();

    @Inject
    public SearchPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getResultsBasedOnQuery(SearchView searchView) {

        getObservableQuery(searchView)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if (s.equals("")) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .debounce(3, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMapSingle(new Function<String, SingleSource<MovieResponse>>() {
                    @Override
                    public SingleSource<MovieResponse> apply(String s) throws Exception {
                        return getDataManager()
                                .getSearchApiCall(s)
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

    }

    private Observable<String> getObservableQuery(SearchView searchView) {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit" + query);
                getMvpView().showLoading();
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange" + newText);
//                getMvpView().showLoading();
//                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;

    }

    public DisposableObserver<MovieResponse> getObserver() {
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG, "onNext " + movieResponse.getTotalResults());

                if (movieResponse != null && movieResponse.getResults() != null) {
                    getMvpView().updateMovie(movieResponse.getResults());
                }
                getMvpView().hideLoading();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();

                if (!isViewAttached()) {
                    return;
                }

                getMvpView().hideLoading();

                // handle the error here
                if (e instanceof ANError) {
                    ANError anError = (ANError) e;
                    handleApiError(anError);
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
                getMvpView().hideLoading();
            }
        };
    }


}
