package com.elifbyte.difavorite.myfavoritemovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.elifbyte.difavorite.myfavoritemovie.R;

import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.OVERVIEW;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.POSTER_PATH;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.RELEASE_DATE;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.TITLE;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.getColumnString;

public class FavoriteAdapter extends CursorAdapter {

    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_favorite, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if (cursor != null){
            TextView tvTitTextView = (TextView)view.findViewById(R.id.title_text_view);
            TextView tvDateTextView = (TextView)view.findViewById(R.id.date_text_view);
            TextView tvContentTextView = (TextView)view.findViewById(R.id.content_text_view);
            ImageView coverImageView = (ImageView) view.findViewById(R.id.cover_image_view);

            Glide.with(context)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w500/" + getColumnString(cursor, POSTER_PATH))
                    .apply(new RequestOptions().centerCrop())
                    .into(coverImageView);

            tvTitTextView.setText(getColumnString(cursor,TITLE));
            tvDateTextView.setText(getColumnString(cursor,RELEASE_DATE));
            tvContentTextView.setText(getColumnString(cursor,OVERVIEW));
        }

    }
}
