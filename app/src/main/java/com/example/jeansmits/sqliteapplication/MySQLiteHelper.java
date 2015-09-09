package com.example.jeansmits.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jeansmits on 9/09/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {


    // Movies table name
    private static final String TABLE_MOVIES = "movies";

    // Movies Table Columns names
    private static final String KEY_ID = "movieId";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DIRECTOR = "director";

    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_DIRECTOR};

    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "movieDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL statement to create movie table
        String CREATE_MOVIE_TABLE = "CREATE TABLE movies ( " +
                "movieId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "director TEXT )";

        // create movies table
        db.execSQL(CREATE_MOVIE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older movies table if existed
        db.execSQL("DROP TABLE IF EXISTS movies");

        // create fresh movies table
        this.onCreate(db);
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    public void addMovie(Movie movie) {

        //for logging
        Log.d("addMovie", movie.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle()); // get title
        values.put(KEY_DIRECTOR, movie.getDirector()); // get author

        // 3. insert
        db.insert(TABLE_MOVIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

    }

    public Movie getMovie(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_MOVIES, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build movie object
        Movie movie = new Movie();
        movie.setMovieId(Integer.parseInt(cursor.getString(0)));
        movie.setTitle(cursor.getString(1));
        movie.setDirector(cursor.getString(2));

        Log.d("getMovie(" + id + ")", movie.toString());

        // 5. return movie
        return movie;

    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_MOVIES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Movie movie = null;
        if (cursor.moveToFirst()) {
            do {
                movie = new Movie();
                movie.setMovieId(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setDirector(cursor.getString(2));

                // Add book to movies
                movies.add(movie);
            } while (cursor.moveToNext());
        }

        Log.d("getAllMovies()", movies.toString());

        // return movies

        return movies;
    }

    public int update(Movie movie) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", movie.getTitle()); // get title
        values.put("director", movie.getDirector()); // get author

        // 3. updating row
        int i = db.update(TABLE_MOVIES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(movie.getMovieId()) }); //selection args

        // 4. close
        db.close();

        return i;


    }

    public void delete(Movie movie) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_MOVIES,
                KEY_ID + " = ?",
                new String[]{String.valueOf(movie.getMovieId())});

        // 3. close
        db.close();

        Log.d("deleteBook", movie.toString());

    }

}
