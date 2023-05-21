/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Genre {

    public static List<Genre> createGenresFromList(List<String> listOfGenres) {
        List<Genre> genres = new ArrayList<>();
        listOfGenres.forEach((g)
                -> {
            genres.add(new Genre(g));

        }
        );

        return genres;

    }
    int id;
    String genreName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }

}
