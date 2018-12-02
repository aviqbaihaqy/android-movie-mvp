package com.elifbyte.dimovies.mvp.ui.favorite;

import com.elifbyte.dimovies.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies.mvp.ui.base.MvpView;

import java.util.List;

public interface FavoriteMvpView extends MvpView {

    void updatFavorite(List<Favorite> favoriteList);
}
