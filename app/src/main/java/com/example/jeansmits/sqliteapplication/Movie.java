package com.example.jeansmits.sqliteapplication;

/**
 * Created by jeansmits on 9/09/15.
 */
public class Movie {
    private int movieId;
    private String title;
    private String director;

    public Movie(int movieId, String title, String director) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
    }

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
