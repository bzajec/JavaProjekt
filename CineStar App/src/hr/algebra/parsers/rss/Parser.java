/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import hr.algebra.dal.Repository;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Film;
import hr.algebra.models.Genre;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.ParserUtils;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 *
 * @author Bruno
 */
public class Parser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final Random RANDOM = new Random();

    Repository repository;

    public static List<Film> parseFilms() throws IOException, XMLStreamException {
        List<Film> films = new ArrayList<>();
        HttpURLConnection connection = UrlConnectionFactory.getHttpUrlConnection(RSS_URL, TIMEOUT, REQUEST_METHOD);

        XMLEventReader reader = ParserFactory.createStaxParser(connection.getInputStream());
        Film film = null;
        StartElement startElement = null;
        Optional<TagType> tagType = Optional.empty();

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:

                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    tagType = TagType.from(qName);
                    if (tagType.isPresent() && TagType.ITEM == tagType.get()) {
                        film = new Film();
                        films.add(film);
                    }

                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent()) {

                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();

                        switch (tagType.get()) {

                            case TITLE:
                                if (film != null && !data.isEmpty()) {

                                    film.setTitle(data);

                                }
                                break;
                            case PUB_DATE:
                                if (film != null && !data.isEmpty()) {
                                        LocalDateTime pubDateDB = LocalDateTime.parse(data, DATE_FORMATTER);
                                        film.setPublishedDate(pubDateDB);
                                        
                                }
                                break;

                            case DESCRIPTION:
                                if (film != null && !data.isEmpty()) {

                                    Document doc = Jsoup.parse(data);
                                    String mydata = doc.toString();

                                    String temp = doc.wholeText();

                                    film.setDescription(temp);

                                }
                                break;
                            case ORIGINAL_TITLE:
                                if (film != null && !data.isEmpty()) {

                                    film.setOriginalTitle(data);

                                }
                            case FILM_DIRECTOR:

                                if (film != null && !data.isEmpty()) {

                                    film.setDirectorsAsString(data);
                                    List<Director> all = Director.getAllDirectorsForAFilm(data);
                                    film.setDirectors(all);
                                }
                                break;
                            case ACTORS:
                                if (film != null && !data.isEmpty()) {
                                    film.setActorsAsString(data);
                                    List<Actor> allActorsForAFilm = Actor.getAllActorsForAFilm(data);

                                    film.setActors(allActorsForAFilm);

                                }
                                break;
                            case RUNNING_TIME:
                                if (film != null && !data.isEmpty()) {

                                    film.setRunningTime(Integer.parseInt(data));
                                }
                                break;
                            case YEAR:
                                if (film != null && !data.isEmpty()) {

                                    film.setYear(Integer.parseInt(data));
                                }
                                break;
                            case GENRE:
                                if (film != null && !data.isEmpty()) {
                                    List<String> genres = ParserUtils.parseStringToList(data);
                                    List allGenres = Genre.createGenresFromList(genres);
                                    film.setGenres(allGenres);
                                }
                                break;
                            case POSTER:
                                if (film != null && !data.isEmpty()) {
                                    handlePicture(film, data);
                                }
                                break;
                            case RATING:
                                if (film != null && !data.isEmpty()) {

                                    film.setRating(Integer.parseInt(data));
                                }
                                break;
                        }
                    }

                    break;
            }
        }

        return films;

    }

    private static void handlePicture(Film film, String pictureUrl) throws IOException {

        pictureUrl = pictureUrl.replaceAll("https", "https");
        String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        String pictureName = Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName;

        FileUtils.copyFromUrl(pictureUrl, localPicturePath);
        film.setPicturePath(localPicturePath);
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_TITLE("orignaziv"),
        FILM_DIRECTOR("redatelj"),
        ACTORS("glumci"),
        RUNNING_TIME("trajanje"),
        YEAR("godina"),
        GENRE("zanr"),
        POSTER("plakat"),
        RATING("rating");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }

    }

}
