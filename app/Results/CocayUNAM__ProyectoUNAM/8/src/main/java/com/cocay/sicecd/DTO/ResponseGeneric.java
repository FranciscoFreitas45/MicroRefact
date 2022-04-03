package com.cocay.sicecd.DTO;
 public class ResponseGeneric {

 private  String cdMensaje;

 private  String nbMensaje;

 private  Object response;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getNbMensaje(){
    return nbMensaje;
}


public Object getResponse(){
    return response;
}


public String getCdMensaje(){
    return cdMensaje;
}


public void setCdMensaje(String cdMensaje){
    this.cdMensaje = cdMensaje;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCdMensaje"))

.queryParam("cdMensaje",cdMensaje)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNbMensaje(String nbMensaje){
    this.nbMensaje = nbMensaje;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNbMensaje"))

.queryParam("nbMensaje",nbMensaje)
;
restTemplate.put(builder.toUriString(),null);
}


public void setResponse(Object response){
    this.response = response;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setResponse"))

.queryParam("response",response)
;
restTemplate.put(builder.toUriString(),null);
}


}