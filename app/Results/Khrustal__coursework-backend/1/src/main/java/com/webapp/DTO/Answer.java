package com.webapp.DTO;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class Answer {

 private  Long id;

 private  Word word;

 private  String answer;

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


public Word getWord(){
    return word;
}


public Long getId(){
    return id;
}


public Boolean getCorrect(){
    return isCorrect;
}


}