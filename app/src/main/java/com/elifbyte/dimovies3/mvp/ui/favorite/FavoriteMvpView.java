package com.elifbyte.dimovies3.mvp.ui.favorite;

import com.elifbyte.dimovies3.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies3.mvp.ui.base.MvpView;

import java.util.List;

public interface FavoriteMvpView extends MvpView {

    void updatFavorite(List<Favorite> favoriteList);
}
