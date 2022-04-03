package com.cym.ext;
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


public void setHttpList(List<Http> httpList){
    this.httpList = httpList;
}


public void setUpstreamServerList(List<UpstreamServer> upstreamServerList){
    this.upstreamServerList = upstreamServerList;
}


public void setStreamList(List<Stream> streamList){
    this.streamList = streamList;
}


public void setLocationList(List<Location> locationList){
    this.locationList = locationList;
}


public List<Template> getTemplateList(){
    return templateList;
}


public void setServerList(List<Server> serverList){
    this.serverList = serverList;
}


public List<Server> getServerList(){
    return serverList;
}


public void setTemplateList(List<Template> templateList){
    this.templateList = templateList;
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


public void setUpstreamList(List<Upstream> upstreamList){
    this.upstreamList = upstreamList;
}


public void setBasicList(List<Basic> basicList){
    this.basicList = basicList;
}


public List<Location> getLocationList(){
    return locationList;
}


public void setParamList(List<Param> paramList){
    this.paramList = paramList;
}


public void setPasswordList(List<Password> passwordList){
    this.passwordList = passwordList;
}


}