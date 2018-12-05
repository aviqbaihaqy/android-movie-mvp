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

package com.elifbyte.dimovies3.mvp.ui.main.detail;

import com.elifbyte.dimovies3.mvp.R;
import com.elifbyte.dimovies3.mvp.data.DataManager;
import com.elifbyte.dimovies3.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies3.mvp.ui.base.BasePresenter;
import com.elifbyte.dimovies3.mvp.ui.base.MvpView;
import com.elifbyte.dimovies3.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by janisharali on 25/05/17.
 */

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V> implements
        DetailMvpPresenter<V> {

    private static final String TAG = "DetailPresenter";

    @Inject
    public DetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void checkIfExist(Long id) {
        getCompositeDisposable().add(getDataManager()
                .getFavoriteById(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Favorite>() {
                    @Override
                    public void accept(Favorite favorite) {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (favorite != null) {
                            getMvpView().updateFabFavorite(true);
                        } else {
                            getMvpView().updateFabFavorite(false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().updateFabFavorite(false);
//                        getMvpView().onError(R.string.some_error);
                    }
                })

        );
    }

    @Override
    public void setFavoriteMovie(final Favorite favorite) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .saveFavorite(favorite)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().updateFabFavorite(true);
                        getMvpView().showMessage(favorite.getTitle() + " Add to Favorite");
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        getMvpView().hideLoading();
                    }
                }));

    }

    @Override
    public void removeFavoriteMovie(final Favorite favorite) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .deleteFavorite(favorite)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().updateFabFavorite(false);
                        getMvpView().showMessage(favorite.getTitle() + " Removed from Favorite");
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        getMvpView().hideLoading();
                    }
                }));
    }
}
