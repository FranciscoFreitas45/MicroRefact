public class Error {

 private  String errorCode;

 private  String errorMessage;


public void setErrorMessage(String errorMessage){
    this.errorMessage = errorMessage;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrorMessage"));

.queryParam("errorMessage",errorMessage);
restTemplate.put(builder.toUriString(),null);


public String getErrorCode(){
    return errorCode;
}


public void setErrorCode(String errorCode){
    this.errorCode = errorCode;
}


public String getErrorMessage(){
    return errorMessage;
}


}