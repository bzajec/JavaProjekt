/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.ArrayList;
import java.util.List;
import hr.algebra.utils.ParserUtils;

/**
 *
 * @author Bruno
 */
public class Actor extends Person implements Comparable<Actor>{

    public Actor() {
    }

    public Actor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Actor(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

 public static List<Actor> getAllActorsForAFilm(String data) {
        
        List<Actor> allActorsForOneFilm = new ArrayList<>();
        List<String> tempPersonAsStrings = ParserUtils.parseStringToList(data);
        tempPersonAsStrings.forEach(
                a -> {
            Actor actor = Actor.parseStringToActor(a);
                    allActorsForOneFilm.add(actor);
                });
        return allActorsForOneFilm;
    }
 
   public static Actor parseStringToActor(String data) {
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
        
        return new Actor(first, last);

    }

      @Override
    public int compareTo(Actor o) {

        return this.getFullName().compareTo(o.getFullName());

    }
}
