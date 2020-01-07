package com.david.ui;

import java.util.ArrayList;

public interface MediaType {
	/**
	 * Returns the title of the TV show or Movie.
	 *
	 * @return title Title of respective media type (Television Show or Movie)
	 */
	public String getTitle();
	/**
	 * Sets the title of a Movie or Television Show.
	 *
	 * @param title title of respective media type to set(Television Show or Movie)
	 */
	public void setTitle(String title); 
	/**
	 * Returns the date a title was added to the streaming service Netflix.
	 *
	 * @return releaseDate The date a Movie or Television show was release ON NetFlix, NOT when it first was released in theaters
	 */
	public String getReleaseDate();
	/**
	 * Sets the date a Movie or Television Show was released on Netflix.
	 *
	 * @param releaseDate releaseDate of respective media type to set(Television Show or Movie)
	 */
	public void setReleaseDate(String releaseDate);

	/**
	 * Returns the runtime of a Television Show or Movie
	 *
	 * @return runTime The runtime of a TV show or Movie in minutes
	 */
	public String getRunTime();
	/**
	 * Sets the runtime of a Television Show or Movie
	 *
	 * @param runTime Runtime in minutes of a Television Show or Movie
	 */
	public void setRunTime(String runTime);
	/**
	 * Returns the description of a Television Show or Movie
	 *
	 * @return description The short description of a Movie or Television Show
	 */
	public String getDescription();
	/**
	 * Sets the description of a Television Show or Movie
	 *
	 * @param description description of a Television Show or Movie
	 */
	public void setDescription(String description);
	/**
	 * Returns the IMDB score of a Television Show or Movie
	 *
	 * @return imdb Score of a movie or TV show from the popular site IMDB.com
	 */
	public String getImdb();
	/**
	 * Sets the IMDB score of a Television Show or Movie
	 *
	 * @param imdb imdb score of a Television Show or Movie
	 */
	public void setImdb(String imdb);
	/**
	 * Returns the MPAA Rating of a Movie or the TV-Parental Rating of a Television Show
	 *
	 * @return mpaaRating Parental rating for a movie or tv show
	 */
	public String getMpaaRating();
	/**
	 * Sets the MPAA Rating of a Movie or the TV-Parental Rating of a Television Show
	 *
	 * @param mpaaRating mpaaRating of a Movie or TV-Parental rating of a Television Show
	 */
	public void setMpaaRating(String mpaaRating);
	/**
	 * Returns the year a movie or television show first was released
	 *
	 * @return year Year a movie or tv show was first released
	 */
	public String getYear();

	/**
	 * Sets the year a title was first released in the world, NOT when it was released to Netflix.
	 *
	 * @param year String year
	 */
	public void setYear(String year);

	/**
	 *	Returns whether a title is of Movie or TV format
	 *
	 * @return type Either of type Movie or type Television Show
	 */
	public String getType();

	/**
	 * Sets what type a title is, either Movie or TV
	 *
	 * @param type String type
	 */
	public void setType(String type);
	/**
	 * Returns an ArrayList of type Person of actors in a Movie or Television Show
	 *
	 * @return ArrayList List of actors of type Person
	 */
	public ArrayList<Person> getActors();
	/**
	 * Adds an actor of type Person to the ArrayList of type Person
	 *
	 * @param actor actor is of type Person
	 */
	public void addActor(Actor actor);
	/**
	 * Returns an ArrayList of type Person of directors in a Movie or Television Show
	 *
	 * @return ArrayList List of directors of type Person
	 */
	public ArrayList<Person> getDirectors();
	/**
	 * Adds an director of type Person to the ArrayList of type Person
	 *
	 * @param director director is of type Person
	 */
	public void addDirector(Director director);
	/**
	 * Returns a list of genres of type String in a Movie or Television Show
	 *
	 * @return ArrayList List of genres of type String
	 */
	public ArrayList<String> getGenres();
	/**
	 * Adds a genre(s) of type String to an ArrayList of type String
	 *
	 * @param genre genre is of type String
	 */
	public void addGenre(String genre);


	
	
}
