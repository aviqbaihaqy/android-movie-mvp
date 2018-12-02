package com.elifbyte.dimovies.mvp.ui.favorite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elifbyte.dimovies.mvp.R;
import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.di.component.ActivityComponent;
import com.elifbyte.dimovies.mvp.ui.base.BaseDialog;
import com.elifbyte.dimovies.mvp.ui.base.BaseFragment;
import com.elifbyte.dimovies.mvp.ui.main.now.NowFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteFragment extends BaseFragment implements FavoriteMvpView, FavoriteAdapter.Callback {

    public static final String TAG = "FavoriteFragment";

    @Inject
    FavoritePresenter<FavoriteMvpView> mPresenter;

    @Inject
    FavoriteAdapter mFavoriteAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.movie_recycler_view)
    RecyclerView mRecyclerView;

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mFavoriteAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFavoriteAdapter);
        mPresenter.onViewPrepared();
    }

    @Override
    public void onEmptyViewRetryClick() {

    }

    @Override
    public void updatFavorite(List<Favorite> favoriteList) {

        mFavoriteAdapter.addItems(favoriteList);
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
