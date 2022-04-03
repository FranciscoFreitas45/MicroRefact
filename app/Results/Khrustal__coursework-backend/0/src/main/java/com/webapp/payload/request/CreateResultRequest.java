package com.webapp.payload.request;
 import com.webapp.models.Word;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
public class CreateResultRequest {

@NotBlank
 private  Long sessionId;

@NotBlank
 private  ArrayList<Word> words;

@NotBlank
 private  ArrayList<String> answers;


public void setSessionId(Long sessionId){
    this.sessionId = sessionId;
}


public ArrayList<String> getAnswers(){
    return answers;
}


public void setWords(ArrayList<Word> words){
    this.words = words;
}


public Long getSessionId(){
    return sessionId;
}


public ArrayList<Word> getWords(){
    return words;
}


public void setAnswers(ArrayList<String> answers){
    this.answers = answers;
}


}