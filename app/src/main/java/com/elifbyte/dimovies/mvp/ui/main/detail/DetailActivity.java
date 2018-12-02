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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elifbyte.dimovies.mvp.R;
import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.data.network.model.MovieResultsItem;
import com.elifbyte.dimovies.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by janisharali on 25/05/17.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {

    public static String EXTRA_MOVIE = "extra_movie";

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tvMovieTitle)
    TextView movieTitle;

    @BindView(R.id.tvMovieReleaseDate)
    TextView movieReleaseDate;

    @BindView(R.id.tvMovieOverview)
    TextView movieOverview;

    @BindView(R.id.ivMoviePoster)
    ImageView moviePoster;

    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFavorite;

    String title;
    private MovieResultsItem movie;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String releaseDate = "Release Date: " + movie.getReleaseDate();
        title = movie.getTitle();

        if (movie != null) {
            movieTitle.setText(title);
            movieReleaseDate.setText(releaseDate);
            movieOverview.setText(movie.getOverview());
            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                    .into(moviePoster);
        }

        this.onClickFabFavorite();

        setUp();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickFabFavorite() {
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //fixme: java.lang.ClassCastException: com.elifbyte.dimovies.mvp.data.network.model.MovieResultsItem cannot be cast to com.elifbyte.dimovies.mvp.data.db.model.Favorite
//                Favorite favorite = getIntent().getParcelableExtra(EXTRA_MOVIE);

                Favorite favorite = new Favorite();
                favorite.setMovieId(movie.getId());
                favorite.setTitle(movie.getTitle());
                favorite.setReleaseDate(movie.getReleaseDate());
                favorite.setOverview(movie.getOverview());
                favorite.setPosterPath(movie.getPosterPath());

                mPresenter.onFabFavoriteClick(favorite);

                Toast.makeText(getApplicationContext(), title + " add to favorite",
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
