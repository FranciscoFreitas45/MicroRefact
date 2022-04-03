package com.webapp.models;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.webapp.Request.ResultRequest;
import com.webapp.Request.Impl.ResultRequestImpl;
import com.webapp.DTO.Result;
import com.webapp.Request.UserRequest;
import com.webapp.Request.Impl.UserRequestImpl;
import com.webapp.DTO.User;
@Entity
@Table(name = "training_session")
public class TrainingSession {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
@Size(max = 120)
 private  String name;

@ManyToMany
 private  Set<Word> words;

@Transient
 private  List<Result> results;

@Transient
 private  User user;

@Column(name = "user_id")
 private  Long userId;

@Transient
 private ResultRequest resultrequest = new ResultRequestImpl();;

@Column(name = "idPV9E")
 private Long idPV9E;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public TrainingSession(User user, String name) {
    this.user = user;
    this.name = name;
    userId = user.getId();
}public TrainingSession() {
}
public void setName(String name){
    this.name = name;
}


public void removeWord(Long wordId){
    words.removeIf(word -> word.getId().equals(wordId));
}


public String getName(){
    return name;
}


public List<Result> getResults(){
  this.results = resultrequest.getResults(this.id);
return this.results;
}}



public boolean containsWord(Long wordId){
    for (Word word : words) {
        if (word.getId().equals(wordId))
            return true;
    }
    return false;
}


public Long getId(){
    return id;
}


public void setWords(Set<Word> words){
    this.words = words;
}


public void setResults(List<Result> results){
resultrequest.setResults(results,this.id);
 this.results = results;
}



public Set<Word> getWords(){
    return words;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}