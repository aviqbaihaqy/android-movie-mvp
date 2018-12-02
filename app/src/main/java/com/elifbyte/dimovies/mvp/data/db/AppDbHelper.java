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

package com.elifbyte.dimovies.mvp.data.db;

import com.elifbyte.dimovies.mvp.data.db.model.DaoMaster;
import com.elifbyte.dimovies.mvp.data.db.model.DaoSession;
import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.data.db.model.User;
import com.elifbyte.dimovies.mvp.data.provider.FavoriteProvider;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
        FavoriteProvider.daoSession = mDaoSession;
    }

    @Override
    public Observable<Long> insertFavorite(final Favorite favorite) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() {
                return mDaoSession.getFavoriteDao().insert(favorite);
            }
        });
    }

    @Override
    public Observable<List<Favorite>> getAllFavorite() {
        return Observable.fromCallable(new Callable<List<Favorite>>() {
            @Override
            public List<Favorite> call() {
                return mDaoSession.getFavoriteDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> saveFavorite(final Favorite favorite) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                mDaoSession.getFavoriteDao().insertInTx(favorite);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveFavoriteList(final List<Favorite> favoriteList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                mDaoSession.getFavoriteDao().insertInTx(favoriteList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> isFavoriteEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return !(mDaoSession.getFavoriteDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

}
