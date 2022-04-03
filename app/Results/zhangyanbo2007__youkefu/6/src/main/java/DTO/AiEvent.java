package DTO;
 public class AiEvent {

 private  long id;

 private  UserEvent event;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public UserEvent getEvent(){
    return event;
}


public long getId(){
    return id;
}


public void setEvent(UserEvent event){
    this.event = event;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEvent"))

.queryParam("event",event)
;
restTemplate.put(builder.toUriString(),null);
}


}