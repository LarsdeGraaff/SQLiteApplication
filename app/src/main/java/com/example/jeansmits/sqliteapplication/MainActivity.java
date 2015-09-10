package com.example.jeansmits.sqliteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.r_view_movies);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);




        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations (Create Read Update Delete)
         * */
        // add Movies
        db.addMovie(new Movie("Android Application Development Cookbook", "Wei Meng Lee"));
        db.addMovie(new Movie("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy"));
        db.addMovie(new Movie("Learn Android App Development", "Wallace Jackson"));

        // get all movies
        List<Movie> list = db.getAllMovies();

        MovieAdapter movieAdapter= new MovieAdapter(list,this);
        recyclerView.setAdapter(movieAdapter);

        // delete one movie
        //db.delete(list.get(0));

        // get all movies
        //db.getAllMovies();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
