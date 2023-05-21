/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

/**
 *
 * @author Bruno
 */
public class User extends Person {

    private String username;
    private String password;
    private String email;

    public User(int id, String username, String password, String email, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String firstName, String lastName) {

        super(firstName, lastName);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getFullName() {
        return super.getFullName(); 
    }
}
