package com.webapp.models;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Answer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
@OneToOne
 private  Word word;

@NotBlank
@Size(max = 120)
 private  String answer;

@NotBlank
 private  Boolean isCorrect;

public Answer(@NotBlank Word word, @NotBlank String answer, @NotBlank Boolean isCorrect) {
    this.word = word;
    this.answer = answer;
    this.isCorrect = isCorrect;
}public Answer() {
}
public String getAnswer(){
    return answer;
}


public void setCorrect(Boolean correct){
    isCorrect = correct;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public Word getWord(){
    return word;
}


public void setId(Long id){
    this.id = id;
}


public boolean containsWord(Long id){
    return word.getId().equals(id);
}


public Long getId(){
    return id;
}


public void setWord(Word word){
    this.word = word;
}


public Boolean getCorrect(){
    return isCorrect;
}


}