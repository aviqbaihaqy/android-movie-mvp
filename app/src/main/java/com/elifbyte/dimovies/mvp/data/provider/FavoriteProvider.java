package com.elifbyte.dimovies.mvp.data.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elifbyte.dimovies.mvp.data.db.DbOpenHelper;
import com.elifbyte.dimovies.mvp.data.db.model.DaoSession;
import com.elifbyte.dimovies.mvp.data.db.model.FavoriteDao;

import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

public class FavoriteProvider extends ContentProvider {

    public static final String AUTHORITY = "com.elifbyte.dimovies.mvp";
    public static final String BASE_PATH = "favorite";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE  + "/" + BASE_PATH;

    private static final String TABLENAME = FavoriteDao.TABLENAME;
    private static final String PK = FavoriteDao.Properties.Id.columnName;

    private static final int FAVORITE_DIR = 1; //0;
    private static final int FAVORITE_ID = 2; //1;

    private static final UriMatcher sURIMatcher;

    static {
        sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, FAVORITE_DIR);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FAVORITE_ID);
    }

    /**
     * This must be set from outside, it's recommended to do this inside your Application object.
     * Subject to change (static isn't nice).
     */
    public static DaoSession daoSession;
    DbOpenHelper dbOpenHelper;

    @Override
    public boolean onCreate() {
        DaoLog.d("Content Provider started: " + CONTENT_URI);
//        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
//        daoSession = ((MvpApp) getApplication()).getDaoSession();
//        daoSession = (new DataManager()).getDaoSession();
        return true;
    }

    protected Database getDatabase() {
        if(daoSession == null) {
            throw new IllegalStateException("DaoSession must be set during content provider is active");
        }
        return daoSession.getDatabase();
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case FAVORITE_DIR:
                queryBuilder.setTables(TABLENAME);
                break;
            case FAVORITE_ID:
                queryBuilder.setTables(TABLENAME);
                queryBuilder.appendWhere(PK + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Database db = getDatabase();
        Cursor cursor = queryBuilder.query(((StandardDatabase) db).getSQLiteDatabase(), projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public final String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case FAVORITE_DIR:
                return CONTENT_TYPE;
            case FAVORITE_ID:
                return CONTENT_ITEM_TYPE;
            default :
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }
}
