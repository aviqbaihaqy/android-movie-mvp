package com.elifbyte.difavorite.myfavoritemovie;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.elifbyte.difavorite.myfavoritemovie.adapter.FavoriteAdapter;
import com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract;

import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.CONTENT_URI;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.FavoriteColumn.TITLE;
import static com.elifbyte.difavorite.myfavoritemovie.db.DatabaseContract.getColumnString;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener  {


    private FavoriteAdapter favoriteAdapter;

    private final int LOAD_MOVIE_ID = 110;
    private ListView lvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoriteAdapter = new FavoriteAdapter(this, null, true);
        lvMovie = (ListView) findViewById(R.id.lv_movie);
        lvMovie.setAdapter(favoriteAdapter);
        lvMovie.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(LOAD_MOVIE_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_MOVIE_ID, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d("contentURI", String.valueOf(CONTENT_URI));
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        favoriteAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        favoriteAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor cursor = (Cursor) favoriteAdapter.getItem(i);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn._ID));
        Log.d("ItemClick", String.valueOf(id));

        Toast.makeText(getApplicationContext(), getColumnString(cursor, TITLE),
                Toast.LENGTH_LONG).show();
    }
}
