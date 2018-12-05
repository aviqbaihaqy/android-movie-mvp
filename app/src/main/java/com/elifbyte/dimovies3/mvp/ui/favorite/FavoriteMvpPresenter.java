package com.elifbyte.dimovies3.mvp.ui.favorite;

import com.elifbyte.dimovies3.mvp.ui.base.MvpPresenter;

public interface FavoriteMvpPresenter<V extends FavoriteMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
