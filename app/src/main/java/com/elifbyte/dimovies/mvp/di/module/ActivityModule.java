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

import com.elifbyte.dimovies.mvp.data.network.model.BlogResponse;
import com.elifbyte.dimovies.mvp.data.network.model.OpenSourceResponse;
import com.elifbyte.dimovies.mvp.ui.about.AboutMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.about.AboutPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedPagerAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourcePresenter;
import com.elifbyte.dimovies.mvp.ui.login.LoginMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.login.LoginPresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogPresenter;
import com.elifbyte.dimovies.mvp.ui.splash.SplashMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.splash.SplashPresenter;
import com.elifbyte.dimovies.mvp.utils.rx.AppSchedulerProvider;
import com.elifbyte.dimovies.mvp.utils.rx.SchedulerProvider;
import com.elifbyte.dimovies.mvp.data.network.model.BlogResponse;
import com.elifbyte.dimovies.mvp.data.network.model.OpenSourceResponse;
import com.elifbyte.dimovies.mvp.di.ActivityContext;
import com.elifbyte.dimovies.mvp.di.PerActivity;
import com.elifbyte.dimovies.mvp.ui.about.AboutMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.about.AboutMvpView;
import com.elifbyte.dimovies.mvp.ui.about.AboutPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedMvpView;
import com.elifbyte.dimovies.mvp.ui.feed.FeedPagerAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.FeedPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogMvpView;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceAdapter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceMvpView;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourcePresenter;
import com.elifbyte.dimovies.mvp.ui.login.LoginMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.login.LoginMvpView;
import com.elifbyte.dimovies.mvp.ui.login.LoginPresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.MainMvpView;
import com.elifbyte.dimovies.mvp.ui.main.MainPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogMvpPresenter;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogMvpView;
import com.elifbyte.dimovies.mvp.ui.main.rating.RatingDialogPresenter;
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
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
