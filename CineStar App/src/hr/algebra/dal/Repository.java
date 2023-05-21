/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Film;
import hr.algebra.models.Genre;
import hr.algebra.models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bruno
 */
public interface Repository {
    int uspCreateUser(User user) throws Exception;
    String uspCheckLoginValidity(String username) throws Exception;
    int uspGetIDUser(String username) throws Exception;
    boolean uspHasAdminPrivilege(int id) throws Exception;
    Optional<User> uspGetUser(int id) throws Exception;
    public int uspCreateAdminUser(User user)throws Exception;
    int uspCreateNewDirector(Director fd) throws Exception;
    List<Director> uspSelectAllDirectors() throws Exception;
    public int uspGetIDDirector(Director fd)throws Exception;
    int uspCreateActor(Actor actor) throws Exception;
    public Integer uspGetIDActor(Actor actor)throws Exception;
    int uspCreateFilm(Film film) throws Exception;
    List<Film> uspGetAllFilms() throws Exception;
    public List<Actor> uspGetAllActors()throws Exception;
    public List<Actor> uspGetAllActorsForFilm(int id)throws Exception;
    public List<Genre> uspGetAllGenresForFilm(int id)throws Exception;
    public int uspCreateGenre(Genre genre)throws Exception;
    public List<Genre> uspSelectAllGenres() throws Exception;
    public Integer uspGetIDGenre(Genre g)throws Exception;
    public void uspDeleteAllData() throws Exception;
    public void uspNewGenreFilmEntry(Integer x, int idFilm)throws Exception;
    public void uspNewActorFilmEntry(Integer y, int idFilm)throws Exception;
    public void uspNewFDFilmEntry(Integer z, int idFilm)throws Exception;
    public void uspUpdateFilm(int id, Film newFilm) throws Exception;
    public void uspdeleteGenresForFilm(int idFilm) throws Exception;
    public void uspdeleteActorsForFilm(int idFilm) throws Exception;
    public void uspdeleteDirectorsForFilm(int idFilm) throws Exception;
    public Optional<Film> uspSelectMovie(int id) throws Exception;
    public List<Director> uspSelectDirectorsFilm(Film film) throws Exception;
    void uspCreateNewActorFilm(Film film, Actor actor) throws Exception;
    List<Actor> uspSelectActorsInFilm(Film movie) throws Exception;
    void uspDeleteFilm(int id) throws Exception;

    void uspCreateNewDirectorFilm(Film film, Director director) throws Exception;

    List<Director> uspSelectDirectorsInFilm(Film film) throws Exception;
    
}
