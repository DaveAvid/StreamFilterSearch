package com.david.ui;

import java.util.ArrayList;

public class TelevisionShow implements MediaType {
    private String title;
    private String releaseDate;
    private ArrayList<String> genres = new ArrayList<String>();
    private String runTime;
    private String description;
    private String type;
    private String imdb;
    private String mpaaRating;
    private ArrayList<Person> actors = new ArrayList<Person>();
    private ArrayList<Person> directors = new ArrayList<Person>();
    private String year;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getRunTime() {
        return runTime;
    }

    @Override
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImdb() {
        return imdb;
    }

    @Override
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public String getMpaaRating() {
        return mpaaRating;
    }

    @Override
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public ArrayList<Person> getActors() {
        return actors;
    }

    @Override
    public void addActor(Actor actor) {
        actors.add(actor);
    }

    @Override
    public ArrayList<Person> getDirectors() {
        return directors;
    }

    @Override
    public void addDirector(Director director) {
        directors.add(director);
    }

    @Override
    public ArrayList<String> getGenres() {
        return genres;
    }

    @Override
    public void addGenre(String genre) {
        genres.add(genre);
    }


    @Override
    public String toString() {
        return "-----------------------------------" + "\n" +
                "Title: " + title + "\n" +
                "Media Type: " + type + "\n" +
                "Year: " + year + "\n" +
                "Mpaa Rating: " + mpaaRating + "\n" +
                "Actors: " + actors + "\n" +
                "Director: " + directors + "\n" +
                "Genre: " + genres + "\n" +
                "Imdb Rating: " + imdb + "\n" +
                "RunTime: " + runTime + "\n" +
                "Release Date: " + releaseDate + "\n" +
                "Description: " + description + "\n" +
                "----------------------------------" + "\n";
    }
}
