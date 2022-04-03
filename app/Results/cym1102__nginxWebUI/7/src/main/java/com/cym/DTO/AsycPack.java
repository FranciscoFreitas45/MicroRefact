package com.cym.DTO;
 import java.util.List;
import com.cym.model.Basic;
import com.cym.model.Http;
import com.cym.model.Location;
import com.cym.model.Param;
import com.cym.model.Password;
import com.cym.model.Server;
import com.cym.model.Stream;
import com.cym.model.Template;
import com.cym.model.Upstream;
import com.cym.model.UpstreamServer;
public class AsycPack {

 private List<Basic> basicList;

 private List<Http> httpList;

 private List<Server> serverList;

 private List<Location> locationList;

 private List<Upstream> upstreamList;

 private List<UpstreamServer> upstreamServerList;

 private List<Stream> streamList;

 private List<Template> templateList;

 private List<Param> paramList;

 private List<Password> passwordList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public List<Template> getTemplateList(){
    return templateList;
}


public List<Server> getServerList(){
    return serverList;
}


public List<Stream> getStreamList(){
    return streamList;
}


public List<Password> getPasswordList(){
    return passwordList;
}


public List<Basic> getBasicList(){
    return basicList;
}


public List<Param> getParamList(){
    return paramList;
}


public List<Upstream> getUpstreamList(){
    return upstreamList;
}


public List<UpstreamServer> getUpstreamServerList(){
    return upstreamServerList;
}


public List<Http> getHttpList(){
    return httpList;
}


public List<Location> getLocationList(){
    return locationList;
}


public void setBasicList(List<Basic> basicList){
    this.basicList = basicList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBasicList"))

.queryParam("basicList",basicList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHttpList(List<Http> httpList){
    this.httpList = httpList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHttpList"))

.queryParam("httpList",httpList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setServerList(List<Server> serverList){
    this.serverList = serverList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setServerList"))

.queryParam("serverList",serverList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLocationList(List<Location> locationList){
    this.locationList = locationList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLocationList"))

.queryParam("locationList",locationList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPasswordList(List<Password> passwordList){
    this.passwordList = passwordList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPasswordList"))

.queryParam("passwordList",passwordList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpstreamList(List<Upstream> upstreamList){
    this.upstreamList = upstreamList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpstreamList"))

.queryParam("upstreamList",upstreamList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpstreamServerList(List<UpstreamServer> upstreamServerList){
    this.upstreamServerList = upstreamServerList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpstreamServerList"))

.queryParam("upstreamServerList",upstreamServerList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStreamList(List<Stream> streamList){
    this.streamList = streamList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStreamList"))

.queryParam("streamList",streamList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTemplateList(List<Template> templateList){
    this.templateList = templateList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTemplateList"))

.queryParam("templateList",templateList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParamList(List<Param> paramList){
    this.paramList = paramList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParamList"))

.queryParam("paramList",paramList)
;
restTemplate.put(builder.toUriString(),null);
}


}