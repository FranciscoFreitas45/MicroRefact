public class Error {

 private  String errorCode;

 private  String errorMessage;

 private RestTemplate restTemplate = new RestTemplate();


public String getErrorCode(){
    return errorCode;
}


public String getErrorMessage(){
    return errorMessage;
}


public void setErrorMessage(String errorMessage){
    this.errorMessage = errorMessage;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrorMessage"));

.queryParam("errorMessage",errorMessage);
restTemplate.put(builder.toUriString(),null);


}