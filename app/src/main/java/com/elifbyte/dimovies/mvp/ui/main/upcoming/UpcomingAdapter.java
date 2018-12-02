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

package com.elifbyte.dimovies.mvp.ui.main.upcoming;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elifbyte.dimovies.mvp.R;
import com.elifbyte.dimovies.mvp.data.network.model.MovieResultsItem;
import com.elifbyte.dimovies.mvp.ui.base.BaseViewHolder;
import com.elifbyte.dimovies.mvp.ui.main.detail.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Janisharali on 25-05-2017.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<MovieResultsItem> mMovieResponseList;

    public UpcomingAdapter(List<MovieResultsItem> movieResponseList) {
        mMovieResponseList = movieResponseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMovieResponseList != null && mMovieResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mMovieResponseList != null && mMovieResponseList.size() > 0) {
            return mMovieResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<MovieResultsItem> movieList) {
        mMovieResponseList.addAll(movieList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cover_image_view)
        ImageView coverImageView;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        @BindView(R.id.author_text_view)
        TextView authorTextView;

        @BindView(R.id.date_text_view)
        TextView dateTextView;

        @BindView(R.id.content_text_view)
        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            contentTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final MovieResultsItem movie = mMovieResponseList.get(position);

            if (movie.getPosterPath() != null) {
                Glide.with(itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                        .asBitmap()
                        .centerCrop()
                        .into(coverImageView);
            }

            if (movie.getTitle() != null) {
                titleTextView.setText(movie.getTitle());
            }

            if (movie.getOriginalTitle() != null) {
                authorTextView.setText(movie.getOriginalTitle());
            }

            if (movie.getReleaseDate() != null) {
                dateTextView.setText(movie.getReleaseDate());
            }

            if (movie.getOverview() != null) {
                contentTextView.setText(movie.getOverview());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);

                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onRepoEmptyViewRetryClick();
        }
    }
}
