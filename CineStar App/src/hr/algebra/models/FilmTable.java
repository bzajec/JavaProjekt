/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Bruno
 */
public class FilmTable extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Original title", "Film Director", "Genre", "Running time", "Year", "Rating", "Actors"};
    private List<Film> films;

    public FilmTable(List<Film> simuliramGetFilmovi) {
        this.films = simuliramGetFilmovi;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public int getRowCount() {
        return films.size();
    }

    @Override
    public int getColumnCount() {
        return Film.class.getDeclaredFields().length - 11;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return films.get(rowIndex).getId();
            case 1:
                return films.get(rowIndex).getOriginalTitle();
            case 2:
                return films.get(rowIndex).getDirectors();
            case 3:
                return films.get(rowIndex).getGenres();
            case 4:
                return films.get(rowIndex).getRunningTime();
            case 5:
                return films.get(rowIndex).getYear();

            case 6:
                return films.get(rowIndex).getRating();

            case 7:
                return films.get(rowIndex).getActors();

            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
     public static void setFilmsTableColumnWidthNormalView(JTable tblFilms) {
        //0 id, 1- orig title, 2 - film direcotr, 3-genre, 4-running time, 5 -year, 6 rating, 7 actors
        TableColumnModel columnModel = tblFilms.getColumnModel();

        TableColumn idColumn = columnModel.getColumn(0);
        idColumn.setPreferredWidth(110);
        idColumn.setResizable(true);

        TableColumn originalTitleColumn = columnModel.getColumn(1);
        originalTitleColumn.setPreferredWidth(270);
        originalTitleColumn.setResizable(true);
        TableColumn directorColumn = columnModel.getColumn(2);
        directorColumn.setPreferredWidth(400);
                directorColumn.setResizable(true);


        TableColumn genreColumn = columnModel.getColumn(3);
        genreColumn.setPreferredWidth(300);
        TableColumn runningTimeColumn = columnModel.getColumn(4);
        runningTimeColumn.setPreferredWidth(250);
        TableColumn yearColumn = columnModel.getColumn(5);
        yearColumn.setPreferredWidth(120);
        TableColumn ratingColumn = columnModel.getColumn(6);
        ratingColumn.setPreferredWidth(120);

        TableColumn columnActors = columnModel.getColumn(7);
        columnActors.setPreferredWidth(1000);
    }
     
       @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class; 
            case 4:
                return Integer.class; 
            case 5:
                return Integer.class; 
            case 6:
                return Integer.class; 
        }
        return super.getColumnClass(columnIndex); 
    }
}
