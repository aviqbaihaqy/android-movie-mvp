package com.elifbyte.dimovies3.mvp.ui.favorite;

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
import com.elifbyte.dimovies3.mvp.R;
import com.elifbyte.dimovies3.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies3.mvp.data.network.model.MovieResultsItem;
import com.elifbyte.dimovies3.mvp.ui.base.BaseViewHolder;
import com.elifbyte.dimovies3.mvp.ui.main.detail.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Favorite> mMovieFavorite;
    private Callback mCallback;

    public FavoriteAdapter(List<Favorite> mMovieFavorite) {
        this.mMovieFavorite = mMovieFavorite;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_view, parent, false)
                );

            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false)
                );

        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mMovieFavorite != null && mMovieFavorite.size() > 0) {
            return mMovieFavorite.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMovieFavorite != null && mMovieFavorite.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public void addItems(List<Favorite> favoriteList) {
        mMovieFavorite.addAll(favoriteList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
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

            final Favorite favorite = mMovieFavorite.get(position);

            if (favorite.getPosterPath() != null) {
                Glide.with(itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w500/" + favorite.getPosterPath())
                        .asBitmap()
                        .centerCrop()
                        .into(coverImageView);
            }

            if (favorite.getTitle() != null) {
                titleTextView.setText(favorite.getTitle());
            }

            if (favorite.getOriginalTitle() != null) {
                authorTextView.setText(favorite.getOriginalTitle());
            }

            if (favorite.getReleaseDate() != null) {
                dateTextView.setText(favorite.getReleaseDate());
            }

            if (favorite.getOverview() != null) {
                contentTextView.setText(favorite.getOverview());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), favorite.getTitle(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    MovieResultsItem movie = new MovieResultsItem();
                    movie.setId(favorite.getId().intValue());
                    movie.setTitle(favorite.getTitle());
                    movie.setOverview(favorite.getOverview());
                    movie.setBackdropPath(favorite.getPosterPath());

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
                mCallback.onEmptyViewRetryClick();
        }
    }
}
