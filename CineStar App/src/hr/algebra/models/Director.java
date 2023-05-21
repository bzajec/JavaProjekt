/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import hr.algebra.utils.ParserUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Director extends Person
{
   
    public Director(String fullName) {
      super(fullName);
    }

    public Director() {
    }

    public Director(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Director(String ime, String prezime) {
      super(ime, prezime);
    }

    public static Director parseStringToDirector (String data) {

        String first = new String();
        String last = new String();
        String[] temp = data.split(" ");
        for (int i = 0; i < temp.length; i++) {
            if (!temp[0].isEmpty()) {
                first = temp[0];
            }

            if (i >= 1 && !temp[i].isEmpty()) {

                last += temp[i];
                last += " ";
            }
        }
        
        return new Director(first, last);

    }
   
     public static List<Director> getAllDirectorsForAFilm(String data) {
        
        List<Director> allForOneFilm = new ArrayList<>();

        List<String> tempPersonAsStrings = ParserUtils.parseStringToList(data);

        tempPersonAsStrings.forEach(
                a -> {
            Director fd = Director.parseStringToDirector(a);
                    allForOneFilm.add(fd);
                });
        return allForOneFilm;
    }
}
