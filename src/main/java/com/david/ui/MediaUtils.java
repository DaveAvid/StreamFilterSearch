package com.david.ui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

public class MediaUtils {

    /**
     * Method populates an ArrayList of type MediaType
     * <p>
     * NetFlixMovies.json and NetFlixTVShows.json are two strings passed into the method and are split into an array of
     * String between two curly braces { } and added to either a object of type TelevisionShow or Movie and then
     * added to a master List
     * <p>
     * @param movieFileName String movieFileName must exist for method to run
     * @param tvFileName String tvFileName must exist for method to run
     * @return List of type MediaType    Master list of all movies and television shows on NetFlix
     * @throws IOException if the file cannot be read
     */
    public static List<MediaType> populateMediaList(String movieFileName, String tvFileName) throws IOException {
        List<MediaType> media = new ArrayList<>();
        String myJsonMovies = readFile(movieFileName);
        String myJsonTV = readFile(tvFileName);
        String[] stuffBetweenCurlyBracesMovies = substringsBetween(myJsonMovies, "{", "}");
        String[] stuffBetweenCurlyBracesTelevision = substringsBetween(myJsonTV, "{", "}");

        for (String entry : stuffBetweenCurlyBracesTelevision) {
            TelevisionShow tvEntry = new TelevisionShow();
            populateMediaEntry(tvEntry, entry);
            media.add(tvEntry);
        }
        for (String entry : stuffBetweenCurlyBracesMovies) {
            Movie movieEntry = new Movie();
            populateMediaEntry(movieEntry, entry);
            media.add(movieEntry);
        }
        return media;
    }

    /**
     * Method populates the object of type MediaType called mediaEntry
     *
     * @param mediaEntry           mediaEntry is an object of type MediaType representing objects of both Movie and TelevisionShow
     * @param fieldToParseFromJson fieldToParseFromJson is a String which represents which field is to be parsed and operated on within this method
     * @throws IOException if entry cannot be read
     */
    public static void populateMediaEntry(MediaType mediaEntry, String fieldToParseFromJson) throws IOException {
        mediaEntry.setTitle(getField(fieldToParseFromJson, "title"));
        mediaEntry.setType(getField(fieldToParseFromJson, "type"));
        mediaEntry.setYear(getField(fieldToParseFromJson, "titlereleased"));
        mediaEntry.setImdb(getField(fieldToParseFromJson, "imdb"));
        mediaEntry.setMpaaRating(getField(fieldToParseFromJson, "rating"));
        mediaEntry.setRunTime(getField(fieldToParseFromJson, "runtime"));
        mediaEntry.setReleaseDate(getField(fieldToParseFromJson, "date_released"));
        mediaEntry.setDescription(getField(fieldToParseFromJson, "description"));
        String[] actors = getFieldArray(fieldToParseFromJson, "actors");
        for (String actor : actors) {
            Actor parsedPerson = createActorFromName(actor);
            mediaEntry.addActor(parsedPerson);
        }
        String[] directors = getFieldArray(fieldToParseFromJson, "director");
        for (String director : directors) {
            Director parsedPerson = createDirectorFromName(director);
            mediaEntry.addDirector(parsedPerson);
        }
        String[] genres = getFieldArrayGenre(fieldToParseFromJson, "category");
        for (String genre : genres) {
            mediaEntry.addGenre(genre);
        }
    }

    /**
     * This method splits and sets the first name and last name of a Person of type Actor
     *
     * @param actor actor is a String parsed from the JSON file, there must exist an object of type Actor
     * @return Actor newActor is an object of type Actor with firstName and lastName
     * @throws ArrayIndexOutOfBoundsException if array cannot be split
     */
    public static Actor createActorFromName(String actor) throws ArrayIndexOutOfBoundsException {
        Actor newActor = new Actor();
        actor = chomp(actor);
        String[] splitName = split(actor);
        newActor.setFirstName(splitName[0]);
        if (splitName.length > 2) {
            newActor.setLastName(splitName[1] + " " + splitName[2]);
        } else if (splitName.length == 2) {
            newActor.setLastName(splitName[1]);
        }
        return newActor;
    }

    /**
     * This method splits and sets the first name and last name of a Person of type Director
     *
     * @param director director is a String parsed from the JSON file there must exist an object of type Director
     * @return Director Director is an object of type Director with firstName and lastName
     * @throws ArrayIndexOutOfBoundsException if array cannot be split
     */
    public static Director createDirectorFromName(String director) throws ArrayIndexOutOfBoundsException {
        Director newDirector = new Director();
        director = chomp(director);
        String[] splitName = split(director);
        newDirector.setFirstName(splitName[0]);
        if (splitName.length > 2) {
            newDirector.setLastName(splitName[1] + " " + splitName[2]);
        } else if (splitName.length == 2) {
            newDirector.setLastName(splitName[1]);
        }
        return newDirector;
    }

    /**
     * Method to process a String set between an open and close constrain set within the logic.
     * Uses StringUtils and StringEscapeUtils to separate String into pieces and to convert any escaped
     * unicode to original characters
     *
     * @param record    String record is the String being passed in to be separated and processed
     * @param fieldName String fieldName corresponds to the variable to which the value that is parsed will be assigned
     *                  inside the object
     * @return String value to be added to the object of type MediaType
     */
    public static String getField(String record, String fieldName) {
        String foundRecord = substringBetween(record, "\"" + fieldName + "\":\"", "\"");
        String replaceTenSlash = replace(foundRecord, "\\/10", "");
        String replaceMinutes = replace(replaceTenSlash,"minutes","");
        String unEscape = StringEscapeUtils.unescapeJava(replaceMinutes);

        return unEscape;
    }

    /**
     * Method to process a String set between an open and close constrain set within the logic.
     * Uses StringUtils and StringEscapeUtils to separate String into pieces and to convert any escaped
     * unicode to original characters and returns an array of String
     *
     * @param record    String record is the String being passed in to be separated and processed
     * @param fieldName String fieldName corresponds to the variable to which the value that is parsed will be assigned
     *                  inside the object
     * @return String[]   An array of string values that are split to be added to the  List actors or List directors of type Person
     *
     *
     */
    public static String[] getFieldArray(String record, String fieldName) {
        String foundRecord = substringBetween(record, "\"" + fieldName + "\":\"", "\"");
        String unEscape = StringEscapeUtils.unescapeJava(foundRecord);
        String replaceNull = replace(unEscape, "null", "");
        String[] split = split(replaceNull, ",");
        return split;
    }

    /**
     * Method to process a String set between an open and close constrain set within the logic specifically for genres.
     * Uses StringUtils and StringEscapeUtils to separate String into pieces and to convert any escaped
     * unicode to original characters and returns an array of String
     * <p>
     * Method also replaces pluralized categories and replaces them with singular tense as well as removes unnecessary
     * and redundant descriptive words
     *
     *
     * @param record    String record is the String being passed in to be separated and processed
     * @param fieldName String fieldName corresponds to the variable to which the value that is parsed will be assigned
     *                  inside the object
     * @return String[] An array of string values that are split
     */
    public static String[] getFieldArrayGenre(String record, String fieldName) {
        String foundRecord = substringBetween(record, "\"" + fieldName + "\":\"", "\"");
        foundRecord = remove(foundRecord, "\\n");
        String removeStringMovies = remove(foundRecord, "Movies");
        String removeStringAnd = remove(removeStringMovies, "&");
        String replaceComedies = replace(removeStringAnd, "Comedies", "Comedy");
        String replaceDramas = replace(replaceComedies, "Dramas", "Drama");
        String removeTV = remove(replaceDramas, "TV");
        String removeShows = remove(removeTV, "Shows");
        String replaceRomantic = replace(removeShows, "Romantic", "Romance");
        String replaceMusicals = replace(replaceRomantic, "Musicals", "Musical");
        String replaceThrillers = replace(replaceMusicals, "Thrillers", "Thriller");
        String removeFeatures = remove(replaceThrillers, "Features");
        String replaceMysteries = replace(removeFeatures, "Mysteries", "Mystery");
        String replaceDocumentaries = replace(replaceMysteries, "Documentaries", "Documentary");
        String removeSeries = remove(replaceDocumentaries, "Series");
        String[] split = split(removeSeries);
        return split;
    }

    /**
     * Method to read in any file to be parsed by the preceding methods
     *
     * @param fileName String fileName representing the name of the file to be passed into the method
     * @return String Returns a String representation of the file that was taken in by the method
     * @throws IOException if file cannot be read
     */
    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        return FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
    }
}
