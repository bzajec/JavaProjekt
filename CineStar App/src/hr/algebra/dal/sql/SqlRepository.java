/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Film;
import hr.algebra.models.Genre;
import hr.algebra.models.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class SqlRepository implements Repository {

    private static final String ID_FILM = "ID_Film";
    private static final String ORIGINAL_TITLE = "Original_Title";
    private static final String TITLE = "Title";
    private static final String RUNNING_TIME = "Running_Time";
    private static final String YEAR = "Release_Year";
    private static final String RATING = "Rating";
    private static final String PICTURE_PATH = "Picture_Path";
    private static final String PUBLISHED_DATE = "Publish_Date";
    private static final String DESCRIPTION = "Film_Description";
    private static final String ID_FILM_DIRECTOR = "ID_Film_Director";
    private static final String FILM_DIRECTOR_FIRST_NAME = "Director_First_Name";
    private static final String FILM_DIRECTOR_LAST_NAME = "Director_Last_Name";
    private static final String ID_ACTOR = "ID_Actor";
    private static final String ACTOR_FIRST_NAME = "Actor_First_Name";
    private static final String ACTOR_LAST_NAME = "Actor_Last_Name";
    private static final String GENRE_NAME = "Ganre_Name";
    private static final String ID_GENRE = "ID_Ganre";
    private static final String ID_USER = "ID_User";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Pass";
    private static final String FIRST_NAME = "First_Name";
    private static final String LAST_NAME = "Last_Name";
    private static final String EMAIL = "Email";

    private static final String CREATE_USER = "{ CALL uspCreateUser (?, ?,?,?, ?, ?) }";
    private static final String CREATE_ADMIN_USER = "{ CALL uspCreateAdminUser (?, ?,?,?, ?, ?) }";
    private static final String CHECK_LOGIN = "{ CALL uspCheckLoginValidity ( ?) }";
    private static final String GET_IDUSER = "{ CALL uspGetIDUser (?, ?) }";
    private static final String HAS_ADMIN_RIGHTS = "{ CALL uspHasAdminPrivilege ( ?) }";
    private static final String GET_USER = "{ CALL uspGetUser (?) }";
    private static final String CREATE_FILM_DIRECTOR = "{ CALL uspCreateNewFilmDirector (?, ?, ?) }";
    private static final String SELECT_ALL_FILM_DIRECTORS = "{ CALL uspSelectAllDirectors () }";
    private static final String GET_ID_FILM_DIRECTOR = "{ CALL uspGetIDFilmDirector (?,?, ?) }";
    private static final String CREATE_GENRE = "{ CALL uspCreateGenre (?,?) }";
    private static final String GET_ID_GENRE = "{ CALL uspGetIDGenre (?, ?) }";
    private static final String GET_ALL_GENRES = "{ CALL uspSelectAllGenres () }";
    private static final String GET_GENRES_FOR_FILM = "{ CALL uspGetAllGenresForFilm (?) }";
    private static final String GET_ALL_ACTORS = "{ CALL uspGetAllActors () }";
    private static final String CREATE_ACTOR = "{ CALL uspCreateActor (?,?,?) }";
    private static final String GET_ID_ACTOR = "{ CALL uspGetIDActor (?,?, ?) }";
    private static final String GET_ACTORS_FOR_FILM = "{ CALL uspGetAllActorsForFilm (?) }";
    private static final String GET_ALL_FILMS = "{ CALL uspGetAllFilms () }";
    private static final String CREATE_FILM = "{ CALL uspCreateFilm (?, ?, ?, ?, ?, ?,?, ?, ?) }";
    private static final String UPDATE_FILM = "{ CALL uspUpdateFilm (?, ?, ?, ?, ?, ?,?, ?, ?) }";
    private static final String NEW_GENRE_FILM_ENTRY = "{ CALL uspNewGenreFilmEntry (?,?) }";
    private static final String NEW_ACTOR_FILM_ENTRY = "{ CALL uspNewActorFilmEntry (?,?) }";
    private static final String NEW_FDIRECTOR_FILM_ENTRY = "{ CALL uspNewFDFilmEntry (?,?) }";
    private static final String DELETE_FD_FILM_ENTRIES = "{ CALL uspdeleteFilmDirectorsForFilm (?) }";
    private static final String DELETE_GENRE_FILM_ENTRIES = "{ CALL uspdeleteGenresForFilm (?) }";
    private static final String DELETE_ACTOR_FILM_ENTRIES = "{ CALL uspdeleteActorsForFilm (?) }";
    private static final String DELETE_ALL = "{ CALL uspDeleteAllData () }";
    private static final String SELECT_FILM = "{ CALL uspSelectFilm (?)}";
    private static final String SELECT_DIRECTORS_FILM ="{ CALL uspSelectDirectorsFilm (?) }";
    private static final String CREATE_MOVIE_ACTOR = "{ CALL uspCreateNewActorFilm (?,?,?) }";
    private static final String CREATE_MOVIE_DIRECTOR = "{ CALL uspCreateNewDirectorFilm (?,?,?) }";
    private static final String DELETE_FILM = "{ CALL uspDeleteFilm ( ? ) }";
    
            
            
    @Override
    public int uspCreateNewDirector(Director fd) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_FILM_DIRECTOR)) {

            stmt.setString(1, fd.getFirstName());
            stmt.setString(2, fd.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }

    }

    @Override
    public List<Director> uspSelectAllDirectors() throws Exception {

        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ALL_FILM_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                directors.add(new Director(
                        Integer.parseInt(rs.getString(ID_FILM_DIRECTOR)),
                        rs.getString(FILM_DIRECTOR_FIRST_NAME),
                        rs.getString(FILM_DIRECTOR_LAST_NAME)));
//                        Integer.parseInt(rs.getString(rs.getMetaData().getColumnName(1))),
//                        rs.getString(rs.getMetaData().getColumnName(2)),
//                        rs.getString(rs.getMetaData().getColumnName(3))));

            }
        }
        return directors;
    }

    @Override
    public int uspCreateUser(User user) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.executeUpdate();
            int id = stmt.getInt(6);
            return id;

        }
    }


    @Override
    public String uspCheckLoginValidity(String username) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_LOGIN)) {

            stmt.setString(1, username);
            stmt.registerOutParameter(2, Types.LONGNVARCHAR);

            stmt.executeUpdate();
            return stmt.getNString(2);
        }

    }

    @Override
    public int uspGetIDUser(String username) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_IDUSER)) {

            stmt.setString(1, username);
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(2);
        }

    }

    @Override
    public boolean uspHasAdminPrivilege(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(HAS_ADMIN_RIGHTS)) {

            stmt.setInt(1, id);
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getBoolean(2);
        }

    }

    @Override
    public Optional<User> uspGetUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        Connection con = null;
        User user = null;
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(GET_USER);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        id,
                        rs.getString(USERNAME),
                        rs.getString(PASSWORD),
                        rs.getString(EMAIL),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME));
            }
            con.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<Film> uspGetAllFilms() throws Exception {

        List<Film> films = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ALL_FILMS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                films.add(new Film(
                        Integer.parseInt(rs.getString(ID_FILM)),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getString(TITLE),
                        Integer.parseInt(rs.getString(RUNNING_TIME)),
                        Integer.parseInt(rs.getString(YEAR)),
                        Integer.parseInt(rs.getString(RATING)),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), DateTimeFormatter.ISO_DATE_TIME),
                        rs.getString(DESCRIPTION)
                ));

            }
        }

        return films;
    }

    @Override
    public List<Actor> uspGetAllActorsForFilm(int id) throws Exception {

        List<Actor> actorsForFilm = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(GET_ACTORS_FOR_FILM);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actorsForFilm.add(new Actor(
                        Integer.parseInt(rs.getString(ID_ACTOR)),
                        rs.getString(ACTOR_FIRST_NAME),
                        rs.getString(ACTOR_LAST_NAME)));
            }
            con.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return actorsForFilm;
    }

    @Override
    public List<Genre> uspGetAllGenresForFilm(int id) throws Exception {

        List<Genre> genresForFilm = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(GET_GENRES_FOR_FILM);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                genresForFilm.add(new Genre(
                        Integer.parseInt(rs.getString(ID_GENRE)),
                        rs.getString(GENRE_NAME)));
            }
            con.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return genresForFilm;

    }

    @Override
    public int uspCreateActor(Actor actor) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }

    }
    
    

    @Override
    public int uspCreateFilm(Film film) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_FILM)) {

            stmt.setString(1, film.getOriginalTitle());
            stmt.setString(2, film.getTitle());
            stmt.setInt(3, film.getRunningTime());
            stmt.setInt(4, film.getYear());
            stmt.setInt(5, film.getRating());
            stmt.setString(6, film.getPicturePath());
            stmt.setString(7, film.getPublishedDate().format(DateTimeFormatter.ISO_DATE_TIME));
            stmt.setString(8, film.getDescription());

            stmt.registerOutParameter(9, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(9);
        }
    }

    @Override
    public int uspGetIDDirector(Director fd) throws SQLException {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ID_FILM_DIRECTOR)) {

            stmt.setString(1, fd.getFirstName());
            stmt.setString(2, fd.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public List<Actor> uspGetAllActors() throws SQLException {

        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ALL_ACTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actors.add(new Actor(
                        Integer.parseInt(rs.getString(ID_ACTOR)),
                        rs.getString(ACTOR_FIRST_NAME),
                        rs.getString(ACTOR_LAST_NAME)));
            }
        }
        return actors;

    }

    @Override
    public int uspCreateAdminUser(User user) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ADMIN_USER)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.executeUpdate();
            int id = stmt.getInt(6);
            return id;

        }
    }

    @Override
    public int uspCreateGenre(Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {

            stmt.setString(1, genre.getGenreName());

            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    @Override
    public void uspDeleteAllData() throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL)) {

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Genre> uspSelectAllGenres() throws SQLException {

        List<Genre> allGenres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ALL_GENRES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                allGenres.add(
                        new Genre(rs.getString(GENRE_NAME)));
            }
        }
        return allGenres;

    }

    @Override
    public Integer uspGetIDGenre(Genre g) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ID_GENRE)) {

            stmt.setString(1, g.getGenreName());
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(2);
        }

    }

    @Override
    public Integer uspGetIDActor(Actor actor) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ID_ACTOR)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void uspNewGenreFilmEntry(Integer x, int idFilm) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(NEW_GENRE_FILM_ENTRY)) {

            stmt.setInt(1, x);
            stmt.setInt(2, idFilm);

            stmt.executeUpdate();
        }

    }

    @Override
    public void uspNewActorFilmEntry(Integer y, int idFilm) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(NEW_ACTOR_FILM_ENTRY)) {

            stmt.setInt(1, y);
            stmt.setInt(2, idFilm);

            stmt.executeUpdate();
        }

    }

    @Override
    public void uspNewFDFilmEntry(Integer z, int idFilm) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(NEW_FDIRECTOR_FILM_ENTRY)) {

            stmt.setInt(1, z);
            stmt.setInt(2, idFilm);

            stmt.executeUpdate();
        }

    }

    @Override
    public void uspUpdateFilm(int id, Film film) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_FILM)) {

            stmt.setString(1, film.getOriginalTitle());
            stmt.setString(2, film.getTitle());
            stmt.setInt(3, film.getRunningTime());
            stmt.setInt(4, film.getYear());
            stmt.setInt(5, film.getRating());
            stmt.setString(6, film.getPicturePath());
            stmt.setString(7, film.getPublishedDate().format(DateTimeFormatter.BASIC_ISO_DATE));
            stmt.setString(8, film.getDescription());
            stmt.setInt(9, id);

            stmt.executeUpdate();
        }

    }

    @Override
    public void uspdeleteGenresForFilm(int idFilm) throws Exception {
 DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENRE_FILM_ENTRIES)) {

            stmt.setInt(1, idFilm);
            
            stmt.executeUpdate();
        }    }

    @Override
    public void uspdeleteActorsForFilm(int idFilm) throws Exception {
 DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR_FILM_ENTRIES)) {

            stmt.setInt(1, idFilm);
            
            stmt.executeUpdate();
        }    }

    @Override
    public void uspdeleteDirectorsForFilm(int idFilm) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FD_FILM_ENTRIES)) {

            stmt.setInt(1, idFilm);
            
            stmt.executeUpdate();
        }

    }

    @Override
    public Optional<Film> uspSelectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_FILM)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Film(
                            rs.getInt(ID_FILM),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getString(TITLE),
                            rs.getInt(RUNNING_TIME),
                            rs.getInt(YEAR),
                            rs.getInt(RATING),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), DateTimeFormatter.ISO_DATE_TIME),
                            rs.getString(DESCRIPTION)
                            ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> uspSelectDirectorsFilm(Film film) throws Exception {
         List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS_FILM);
                
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                directors.add(new Director(
                        rs.getInt(ID_FILM_DIRECTOR),
                        rs.getString(FILM_DIRECTOR_FIRST_NAME),
                        rs.getString(FILM_DIRECTOR_LAST_NAME)
                ));
            }
        }
        return directors;
    }

    @Override
    public void uspCreateNewActorFilm(Film film, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {
            stmt.setInt(1, actor.getId());
            stmt.setInt(2, film.getId());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Actor> uspSelectActorsInFilm(Film film) throws Exception {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("select distinct Actor.Actor_First_Name, Actor.Actor_Last_Name from ActorFilm inner join Actor on ActorFilm.ActorID = ID_Actor where FilmId = " + film.getId());
            ResultSet result = statement.executeQuery();
            List<Actor> actors = new ArrayList<>();

            while (result.next()) {

                actors.add(new Actor(result.getString(ACTOR_FIRST_NAME), result.getString(ACTOR_LAST_NAME)));
            }
            return actors;
        } catch (SQLException sQLException) {
            System.out.println("Kaj smo rekli! Nikam ti neides!");
            return null;
        }
    }

    @Override
    public void uspDeleteFilm(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FILM)) {
            
                stmt.setInt(1, id);
            
                stmt.executeUpdate();
        } 
    }

    @Override
    public void uspCreateNewDirectorFilm(Film film, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_DIRECTOR)) {
            stmt.setInt(1, director.getId());
            stmt.setInt(2, film.getId());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Director> uspSelectDirectorsInFilm(Film film) throws Exception {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("select distinct Director.Director_First_Name, Director.Director_Last_Name from DirectorFilm inner join Director on DirectorFilm.DirectorID = ID_Film_Director where FilmId = " + film.getId());
            ResultSet result = statement.executeQuery();
            List<Director> directors = new ArrayList<>();

            while (result.next()) {
                directors.add(new Director(result.getString(FILM_DIRECTOR_FIRST_NAME), result.getString(FILM_DIRECTOR_LAST_NAME)));
            }
            return directors;
        } catch (SQLException sQLException) {
            System.out.println("Reka sam nemože!");
            return null;
        }
    }

    
}

