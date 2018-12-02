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

package com.elifbyte.dimovies.mvp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.data.network.model.MovieResultsItem;
import com.elifbyte.dimovies.mvp.di.ActivityContext;
import com.elifbyte.dimovies.mvp.di.PerActivity;
import com.elifbyte.dimovies.mvp.ui.about.AboutMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.about.AboutMvpView;
import com.elifbyte.dimovies.mvp.ui.about.AboutPresenter;
import com.elifbyte.dimovies.mvp.ui.favorite.FavoriteAdapter;
import com.elifbyte.dimovies.mvp.ui.favorite.FavoriteMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.favorite.FavoriteMvpView;
import com.elifbyte.dimovies.mvp.ui.favorite.FavoritePresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainMvpView;
import com.elifbyte.dimovies.mvp.ui.main.MainPagerAdapter;
import com.elifbyte.dimovies.mvp.ui.main.MainPresenter;
import com.elifbyte.dimovies.mvp.ui.main.detail.DetailMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.detail.DetailMvpView;
import com.elifbyte.dimovies.mvp.ui.main.detail.DetailPresenter;
import com.elifbyte.dimovies.mvp.ui.main.now.NowAdapter;
import com.elifbyte.dimovies.mvp.ui.main.now.NowMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.now.NowMvpView;
import com.elifbyte.dimovies.mvp.ui.main.now.NowPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogMvpView;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogPresenter;
import com.elifbyte.dimovies.mvp.ui.main.search.SearchAdapter;
import com.elifbyte.dimovies.mvp.ui.main.search.SearchMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.search.SearchMvpView;
import com.elifbyte.dimovies.mvp.ui.main.search.SearchPresenter;
import com.elifbyte.dimovies.mvp.ui.main.upcoming.UpcomingAdapter;
import com.elifbyte.dimovies.mvp.ui.main.upcoming.UpcomingMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.upcoming.UpcomingMvpView;
import com.elifbyte.dimovies.mvp.ui.main.upcoming.UpcomingPresenter;
import com.elifbyte.dimovies.mvp.ui.splash.SplashMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.splash.SplashMvpView;
import com.elifbyte.dimovies.mvp.ui.splash.SplashPresenter;
import com.elifbyte.dimovies.mvp.utils.rx.AppSchedulerProvider;
import com.elifbyte.dimovies.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    NowMvpPresenter<NowMvpView> provideNowMvpPresenter(
            NowPresenter<NowMvpView> presenter) {
        return presenter;
    }

    @Provides
    UpcomingMvpPresenter<UpcomingMvpView> provideUpcomingPresenter(
            UpcomingPresenter<UpcomingMvpView> presenter) {
        return presenter;
    }

    @Provides
    NowAdapter provideNowAdapter() {
        return new NowAdapter(new ArrayList<MovieResultsItem>());
    }

    @Provides
    UpcomingAdapter provideUpcomingAdapter() {
        return new UpcomingAdapter(new ArrayList<MovieResultsItem>());
    }

    @Provides
    MainPagerAdapter provideMainPagerAdapter(AppCompatActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    DetailMvpPresenter<DetailMvpView> provideDetailMvpPresenter(
            DetailPresenter<DetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    SearchMvpPresenter<SearchMvpView> provideSearchMvpPresenter(
            SearchPresenter<SearchMvpView> presenter) {
        return presenter;
    }

    @Provides
    SearchAdapter provideSearchAdapter() {
        return new SearchAdapter(new ArrayList<MovieResultsItem>());
    }

    @Provides
    FavoriteMvpPresenter<FavoriteMvpView> provideFavoriteMvpPresenter(
            FavoritePresenter<FavoriteMvpView> presenter) {
        return presenter;
    }

    @Provides
    FavoriteAdapter provideFavoriteAdapter() {
        return new FavoriteAdapter(new ArrayList<Favorite>());
    }

}
