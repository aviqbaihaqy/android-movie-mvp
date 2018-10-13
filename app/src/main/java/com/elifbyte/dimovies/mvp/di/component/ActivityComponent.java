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

package com.elifbyte.dimovies.mvp.di.component;

import com.elifbyte.dimovies.mvp.ui.about.AboutFragment;
import com.elifbyte.dimovies.mvp.ui.feed.FeedActivity;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogFragment;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceFragment;
import com.elifbyte.dimovies.mvp.ui.login.LoginActivity;
import com.elifbyte.dimovies.mvp.ui.main.MainActivity;
import com.elifbyte.dimovies.mvp.ui.main.now.NowFragment;
import com.elifbyte.dimovies.mvp.ui.main.rating.RateUsDialog;
import com.elifbyte.dimovies.mvp.ui.main.upcoming.UpcomingFragment;
import com.elifbyte.dimovies.mvp.ui.splash.SplashActivity;
import com.elifbyte.dimovies.mvp.di.PerActivity;
import com.elifbyte.dimovies.mvp.di.module.ActivityModule;
import com.elifbyte.dimovies.mvp.ui.about.AboutFragment;
import com.elifbyte.dimovies.mvp.ui.feed.FeedActivity;
import com.elifbyte.dimovies.mvp.ui.feed.blogs.BlogFragment;
import com.elifbyte.dimovies.mvp.ui.feed.opensource.OpenSourceFragment;
import com.elifbyte.dimovies.mvp.ui.login.LoginActivity;
import com.elifbyte.dimovies.mvp.ui.main.MainActivity;
import com.elifbyte.dimovies.mvp.ui.main.rating.RateUsDialog;
import com.elifbyte.dimovies.mvp.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(NowFragment fragment);

    void inject(UpcomingFragment fragment);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(FeedActivity activity);

    void inject(AboutFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(BlogFragment fragment);

    void inject(RateUsDialog dialog);


}
