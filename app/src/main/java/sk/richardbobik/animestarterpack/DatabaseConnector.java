package sk.richardbobik.animestarterpack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector extends SQLiteOpenHelper {
    public static final String ANIME_TABLE = "ANIME_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PICTURE = "PICTURE";
    public static final String COLUMN_SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
    public static final String COLUMN_LONG_DESCRIPTION = "LONG_DESCRIPTION";
    public static final String COLUMN_TAG = "TAG";
    public static final String COLUMN_VIEWED_STATUS = "VIEWED_STATUS";


    public DatabaseConnector(@Nullable Context context) {
        super(context, "AnimeStarterPack.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ANIME_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PICTURE + " TEXT, " + COLUMN_SHORT_DESCRIPTION + " TEXT, " + COLUMN_LONG_DESCRIPTION + " TEXT, " + COLUMN_TAG + " TEXT, " + COLUMN_VIEWED_STATUS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Show show) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, show.getName());
        cv.put(COLUMN_PICTURE, show.getPicture());
        cv.put(COLUMN_SHORT_DESCRIPTION, show.getShortDescription());
        cv.put(COLUMN_LONG_DESCRIPTION, show.getLongDescription());
        cv.put(COLUMN_TAG, show.getTag());
        cv.put(COLUMN_VIEWED_STATUS, show.getViewedStatus());


        long insert = db.insert(ANIME_TABLE, null, cv);

        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ANIME_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void updateOne(String row_id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_VIEWED_STATUS, status);

        db.update(ANIME_TABLE, cv, COLUMN_NAME+" = ?", new String[]{row_id});

        db.close();
    }

    public List<Show> getAllData() {
        List<Show> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ANIME_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int showID = cursor.getInt(0);
                String name = cursor.getString(1);
                String picture = cursor.getString(2);
                String shortDescription = cursor.getString(3);
                String longDescription = cursor.getString(4);
                String tag = cursor.getString(5);
                String viewedStatus = cursor.getString(6);

                Show testShow2 = new Show(showID,name, picture, shortDescription, longDescription, tag, viewedStatus);
                returnList.add(testShow2);

            } while (cursor.moveToNext());
        }
        else {
            //Don't add anything in the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Show getShowById(int id) {
        ArrayList<Show> returnList = (ArrayList<Show>) getAllData();
        for (Show s : returnList) {
            if (s.getId() == id) {
                return s;
            }
            
        }
        return null;
    }



}
