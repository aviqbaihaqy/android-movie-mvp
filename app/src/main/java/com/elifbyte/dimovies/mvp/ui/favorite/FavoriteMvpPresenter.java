package com.elifbyte.dimovies.mvp.ui.favorite;

import com.elifbyte.dimovies.mvp.ui.base.MvpPresenter;

public interface FavoriteMvpPresenter<V extends FavoriteMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
