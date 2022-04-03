package com.example.steam.DTO;
 public class RankScoreValue {

 private  T value;

 private  long score;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

public RankScoreValue() {
}
public T getValue(){
    return value;
}


public long getScore(){
    return score;
}


public void setScore(long score){
    this.score = score;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setScore"))

.queryParam("score",score)
;
restTemplate.put(builder.toUriString(),null);
}


public void setValue(T value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}