package com.webapp.DTO;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class TrainingSession {

 private  Long id;

 private  String name;

 private  Set<Word> words;

 private  List<Result> results;

 private  User user;

 private  Long userId;

public TrainingSession(User user, String name) {
    this.user = user;
    this.name = name;
    userId = user.getId();
}public TrainingSession() {
}
public String getName(){
    return name;
}


public List<Result> getResults(){
    return results;
}


public Long getId(){
    return id;
}


public Set<Word> getWords(){
    return words;
}


public Long getUserId(){
    return userId;
}


}