package com.elifbyte.difavorite.myfavoritemovie.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract;

import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.getColumnInt;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.getColumnLong;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.getColumnString;

public class FavoriteItem implements Parcelable {

    private int id;
    private Long movieId;
    private String title;
    private String overview;
    private String posterPath;
    private String releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeValue(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
    }

    public FavoriteItem() {
    }

    public FavoriteItem(int id, Long movieId, String title, String overview, String posterPath, String releaseDate) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    public FavoriteItem(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.FavoriteColumn._ID);
        this.movieId = getColumnLong(cursor, DatabaseContract.FavoriteColumn.MOVIE_ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteColumn.TITLE);
        this.overview= getColumnString(cursor, DatabaseContract.FavoriteColumn.OVERVIEW);
        this.releaseDate= getColumnString(cursor, DatabaseContract.FavoriteColumn.RELEASE_DATE);
        this.posterPath= getColumnString(cursor, DatabaseContract.FavoriteColumn.POSTER_PATH);
    }

    protected FavoriteItem(Parcel in) {
        this.id = in.readInt();
        this.movieId = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Creator<FavoriteItem> CREATOR = new Creator<FavoriteItem>() {
        @Override
        public FavoriteItem createFromParcel(Parcel source) {
            return new FavoriteItem(source);
        }

        @Override
        public FavoriteItem[] newArray(int size) {
            return new FavoriteItem[size];
        }
    };
}
