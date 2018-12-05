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

package com.elifbyte.dimovies3.mvp.ui.main.upcoming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elifbyte.dimovies3.mvp.R;
import com.elifbyte.dimovies3.mvp.data.network.model.MovieResultsItem;
import com.elifbyte.dimovies3.mvp.di.component.ActivityComponent;
import com.elifbyte.dimovies3.mvp.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by janisharali on 25/05/17.
 */

public class UpcomingFragment extends BaseFragment implements
        UpcomingMvpView, UpcomingAdapter.Callback {

    private static final String TAG = "UpcomingFragment";

    @Inject
    UpcomingMvpPresenter<UpcomingMvpView> mPresenter;

    @Inject
    UpcomingAdapter mUpcomingAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.movie_recycler_view)
    RecyclerView mRecyclerView;

    public static UpcomingFragment newInstance() {
        Bundle args = new Bundle();
        UpcomingFragment fragment = new UpcomingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mUpcomingAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUpcomingAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void onRepoEmptyViewRetryClick() {

    }

    @Override
    public void updateMovie(List<MovieResultsItem> movieList) {
        mUpcomingAdapter.addItems(movieList);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
