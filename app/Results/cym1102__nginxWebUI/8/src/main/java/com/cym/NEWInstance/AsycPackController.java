package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AsycPackController {

 private AsycPack asycpack;

 private AsycPack asycpack;


@PutMapping
("/setBasicList")
public void setBasicList(@RequestParam(name = "basicList") List<Basic> basicList){
asycpack.setBasicList(basicList);
}


@PutMapping
("/setHttpList")
public void setHttpList(@RequestParam(name = "httpList") List<Http> httpList){
asycpack.setHttpList(httpList);
}


@PutMapping
("/setServerList")
public void setServerList(@RequestParam(name = "serverList") List<Server> serverList){
asycpack.setServerList(serverList);
}


@PutMapping
("/setLocationList")
public void setLocationList(@RequestParam(name = "locationList") List<Location> locationList){
asycpack.setLocationList(locationList);
}


@PutMapping
("/setPasswordList")
public void setPasswordList(@RequestParam(name = "passwordList") List<Password> passwordList){
asycpack.setPasswordList(passwordList);
}


@PutMapping
("/setUpstreamList")
public void setUpstreamList(@RequestParam(name = "upstreamList") List<Upstream> upstreamList){
asycpack.setUpstreamList(upstreamList);
}


@PutMapping
("/setUpstreamServerList")
public void setUpstreamServerList(@RequestParam(name = "upstreamServerList") List<UpstreamServer> upstreamServerList){
asycpack.setUpstreamServerList(upstreamServerList);
}


@PutMapping
("/setStreamList")
public void setStreamList(@RequestParam(name = "streamList") List<Stream> streamList){
asycpack.setStreamList(streamList);
}


@PutMapping
("/setTemplateList")
public void setTemplateList(@RequestParam(name = "templateList") List<Template> templateList){
asycpack.setTemplateList(templateList);
}


@PutMapping
("/setParamList")
public void setParamList(@RequestParam(name = "paramList") List<Param> paramList){
asycpack.setParamList(paramList);
}


}