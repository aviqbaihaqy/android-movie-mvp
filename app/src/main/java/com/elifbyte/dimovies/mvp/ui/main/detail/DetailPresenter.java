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

package com.elifbyte.dimovies.mvp.ui.main.detail;

import android.widget.Toast;

import com.elifbyte.dimovies.mvp.R;
import com.elifbyte.dimovies.mvp.data.DataManager;
import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.ui.base.BasePresenter;
import com.elifbyte.dimovies.mvp.ui.base.MvpView;
import com.elifbyte.dimovies.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by janisharali on 25/05/17.
 */

public class DetailPresenter<V extends MvpView> extends BasePresenter<V> implements
        DetailMvpPresenter<V> {

    private static final String TAG = "DetailPresenter";

    @Inject
    public DetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onFabFavoriteClick(Favorite favorite) {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .saveFavorite(favorite)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        getMvpView().hideLoading();
                    }
                }));

    }
}
