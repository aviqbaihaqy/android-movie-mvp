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

package com.elifbyte.dimovies.mvp.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "favorite")
public class Favorite implements Parcelable {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("movieId")
    @Property(nameInDb = "movieId")
    private int movieId;

    @Expose
    @SerializedName("overview")
    @Property(nameInDb = "overview")
    private String overview;

    @Expose
    @SerializedName("originalLanguage")
    @Property(nameInDb = "originalLanguage")
    private String originalLanguage;

    @Expose
    @SerializedName("originalTitle")
    @Property(nameInDb = "originalTitle")
    private String originalTitle;

    @Expose
    @SerializedName("video")
    @Property(nameInDb = "video")
    private boolean video;

    @Expose
    @SerializedName("title")
    @Property(nameInDb = "title")
    private String title;

//    @Property(nameInDb = "genreIds")
//    **@Convert(converter = GreenConverter.class, columnType = String.class)
//    private List<Integer> genreIds;**

    @Expose
    @SerializedName("posterPath")
    @Property(nameInDb = "posterPath")
    private String posterPath;

    @Expose
    @SerializedName("backdropPath")
    @Property(nameInDb = "backdropPath")
    private String backdropPath;

    @Expose
    @SerializedName("releaseDate")
    @Property(nameInDb = "releaseDate")
    private String releaseDate;

    @Expose
    @SerializedName("voteAverage")
    @Property(nameInDb = "voteAverage")
    private double voteAverage;

    @Expose
    @SerializedName("popularity")
    @Property(nameInDb = "popularity")
    private double popularity;

    @Expose
    @SerializedName("adult")
    @Property(nameInDb = "adult")
    private boolean adult;

    @Expose
    @SerializedName("voteCount")
    @Property(nameInDb = "voteCount")
    private int voteCount;

    @Expose
    @SerializedName("createdAt")
    @Property(nameInDb = "created_at")
    private String createdAt;

    @Expose
    @SerializedName("updatedAt")
    @Property(nameInDb = "updated_at")
    private String updatedAt;

    @Generated(hash = 632410198)
    public Favorite(Long id, int movieId, String overview, String originalLanguage, String originalTitle, boolean video, String title, String posterPath, String backdropPath, String releaseDate, double voteAverage, double popularity, boolean adult, int voteCount, String createdAt, String updatedAt) {
        this.id = id;
        this.movieId = movieId;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.video = video;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.adult = adult;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 459811785)
    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.movieId);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeDouble(this.popularity);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.voteCount);

    }

    public boolean getVideo() {
        return this.video;
    }

    public boolean getAdult() {
        return this.adult;
    }

    protected Favorite(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.movieId = in.readInt();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.video = in.readByte() != 0;
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();
        this.adult = in.readByte() != 0;
        this.voteCount = in.readInt();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}