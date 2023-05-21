/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Bruno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Film implements Comparable<Film> {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    public static final DateTimeFormatter DATE_FORMATTER_DATABASE = DateTimeFormatter.ISO_DATE_TIME;

    @XmlAttribute
    private int id;
    
    @XmlElement(name = "title")
    private String title;
    
    private LocalDateTime publishedDate;
    private String publishedDateDB;
    private String description;
    
    @XmlElement(name = "originaltitle")
    private String originalTitle;
    private String director;
    private int runningTime;
    private String genreAsString;
    private int year;
    private int rating;
    private String picturePath;
    private List<Actor> actors;
    private List<Director> directors;
    private List<Genre> genres;
    private String actorsAsString;
    private String directorsAsString;

    public Film() {
    }

    public Film(int id, String title, String originalTitle) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
    }

    
    
    public String getGenreAsString() {
        return genreAsString;
    }

    public void setGenreAsString(String genreAsString) {
        this.genreAsString = genreAsString;

    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;

    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDirectorsAsString() {
        return directorsAsString;
    }

    public void setDirectorsAsString(String directorsAsString) {
        this.directorsAsString = directorsAsString;
    }

    public Film(String title, LocalDateTime publishedDate, String publishedDateDB, String description, String originalTitle, String director, int runningTime, String genreAsString, int year, int rating, String picturePath, List<Actor> actors, List<Director> directors, List<Genre> genres, String actorsAsString, String directorsAsString) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.publishedDateDB = publishedDateDB;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.genreAsString = genreAsString;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
        this.actorsAsString = actorsAsString;
        this.directorsAsString = directorsAsString;
    }

    public Film(int id, String originalTitle, String title, int runningTime, int year, int rating, String picturePath, LocalDateTime publishedDate, String description) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.title = title;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.description = description;
    }
    public Film(int id, String originalTitle, String title, int runningTime, int year, int rating, String picturePath, String publishedDate, String description) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.title = title;

        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.publishedDateDB = publishedDate;
        this.description = description;
    }

    public Film(String originalTitle, String title, int runningTime, int year, int rating, String picturePath, LocalDateTime publishedDate, String description) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.description = description;
    }
    public Film(String originalTitle, String title, int runningTime, int year, int rating, String picturePath, LocalDateTime publishedDate, String description, List<Genre> genres, List<Actor> actors, List<Director> directors) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.description = description;
        this.genres= genres;
        this.actors=actors;
        this.directors=directors;
    }

       public Film(String originalTitle, String title, int runningTime, int year, int rating, String picturePath, String publisheddatedb, String description) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.publishedDateDB = publisheddatedb;
        this.description = description;
    }
    public Film(String title, LocalDateTime publishedDate, String publishedDateDB, String description, String originalTitle, String director, int runningTime, int year, int rating, String picturePath, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.publishedDateDB = publishedDateDB;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Film(int id, String title, LocalDateTime publishedDate, String publishedDateDB, String description, String originalTitle, String director, int runningTime, int year, int rating, String picturePath, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.publishedDateDB = publishedDateDB;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Film(int id, String title, LocalDateTime publishedDate, String publishedDateDB, String description, String originalTitle, String director, int runningTime, String genreAsString, int year, int rating, String picturePath, List<Actor> actors, List<Director> directors, List<Genre> genres, String actorsAsString, String directorsAsString) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.publishedDateDB = publishedDateDB;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.genreAsString = genreAsString;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
        this.actorsAsString = actorsAsString;
        this.directorsAsString = directorsAsString;
    }

    public Film(String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, List<Actor> actors) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.genreAsString = genre;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
    }

    public Film(String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, String actorsasString) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.genreAsString = genre;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actorsAsString = actorsasString;
    }

    public Film(String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, List<Actor> actors, String actorsasString) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = director;
        this.runningTime = runningTime;
        this.genreAsString = genre;
        this.year = year;
        this.rating = rating;
        this.picturePath = picturePath;
        this.actors = actors;
        this.actorsAsString = actorsasString;
    }

    public Film(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, List<Actor> actors) {

        this(title, publishedDate, description, originalTitle, director, runningTime, genre, year, rating, picturePath, actors);
        this.id = id;

    }

    public Film(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, String actorsAsString) {

        this(title, publishedDate, description, originalTitle, director, runningTime, genre, year, rating, picturePath, actorsAsString);
        this.id = id;

    }

    public Film(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, String director, int runningTime, String genre, int year, int rating, String picturePath, List<Actor> actors, String actorsAsString) {

        this(title, publishedDate, description, originalTitle, director, runningTime, genre, year, rating, picturePath, actors, actorsAsString);
        this.id = id;

    }

    public String getActorsAsString() {
        return actorsAsString;
    }

    public void setActorsAsString(String actorsAsString) {
        this.actorsAsString = actorsAsString;
    }

    public int getId() {
        return id;
    }

    public String getPublishedDateDB() {
        return publishedDateDB;
    }

    public void setPublishedDateDB(String publishedDateDB) {
        this.publishedDateDB = publishedDateDB;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getGenre() {
        return genreAsString;
    }

    public void setGenre(String genre) {
        this.genreAsString = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return id + " - " + title + ", origininal title: " + originalTitle;
    }

    public static void filmsToTextFile(List<Film> films) throws IOException, XMLStreamException, XMLStreamException {
        try (FileWriter fileWriter = new FileWriter("output.txt")) {

            for (Film f : films) {
                fileWriter.write(f.toString() + System.lineSeparator());
                fileWriter.write("Actors for film: " + f.getOriginalTitle() + System.lineSeparator() + f.getActorsAsString() + System.lineSeparator());
            }

        }

    }

    @Override
    public int compareTo(Film o) {
        return Integer.compare(id, o.id);
    }
}
