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
public   class Person {
    
    
   
    private int id;
    private String firstName;
    private String lastName;
    private String fullname ;
    public Person() {
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        fullname = firstName.concat(" ").concat(lastName); 
    }

    public Person(String fullname) {
        this.fullname = fullname;
    }

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        fullname = firstName.concat(" ").concat(lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

   public String getFullName(){
       return fullname;
   }
   public void setFullName(String fullname){
       this.fullname=fullname;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public static Person parseStringToPerson (String data) {

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
        
        return new Person(first, last);

    }
    
}
