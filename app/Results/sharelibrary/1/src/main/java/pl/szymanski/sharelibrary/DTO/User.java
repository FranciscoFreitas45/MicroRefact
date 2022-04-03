package pl.szymanski.sharelibrary.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.CascadeType.*;


public class User {

    private Long id;
    
    private String email;
 
    private char[] password;
   
    private String username;

  
    private String name;

    private String surname;

   
    private List<UserBook> books;

    private Coordinates coordinates;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
