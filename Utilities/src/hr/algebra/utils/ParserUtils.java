/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ParserUtils {
    
    public static List<String> parseStringToList(String allActors) {
        List<String> actors = new ArrayList<>();
        List<String> asList = Arrays.asList(allActors.split(","));
        asList.forEach(a -> actors.add(a.trim()));

        return actors;
    }
}
