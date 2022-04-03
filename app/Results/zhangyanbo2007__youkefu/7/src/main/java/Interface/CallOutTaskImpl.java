package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CallOutTask;
public class CallOutTaskImpl implements CallOutTask{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public void setName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))
    .queryParam("name",name)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setBatid(String batid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBatid"))
    .queryParam("batid",batid)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))
    .queryParam("orgi",orgi)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setExectype(String exectype){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExectype"))
    .queryParam("exectype",exectype)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setFilterid(String filterid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilterid"))
    .queryParam("filterid",filterid)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setActid(String actid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActid"))
    .queryParam("actid",actid)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setExecnum(int execnum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExecnum"))
    .queryParam("execnum",execnum)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setOrgan(String organ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgan"))
    .queryParam("organ",organ)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setCreatetime(Date createtime){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))
    .queryParam("createtime",createtime)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setNamenum(int namenum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNamenum"))
    .queryParam("namenum",namenum)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setNotassigned(int notassigned){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNotassigned"))
    .queryParam("notassigned",notassigned)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setAssigned(int assigned){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssigned"))
    .queryParam("assigned",assigned)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setAssignedorgan(int assignedorgan){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignedorgan"))
    .queryParam("assignedorgan",assignedorgan)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setAssignedai(int assignedai){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignedai"))
    .queryParam("assignedai",assignedai)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setAssignedforecast(int assignedforecast){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignedforecast"))
    .queryParam("assignedforecast",assignedforecast)
;
  restTemplate.put(builder.toUriString(), null);
}


public int getNamenum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNamenum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public void setRenum(int renum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRenum"))
    .queryParam("renum",renum)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setReorgannum(int reorgannum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReorgannum"))
    .queryParam("reorgannum",reorgannum)
;
  restTemplate.put(builder.toUriString(), null);
}


public String getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getAssigned(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAssigned"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getNotassigned(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNotassigned"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getAssignedorgan(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAssignedorgan"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getAssignedai(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAssignedai"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getAssignedforecast(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAssignedforecast"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getRenum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRenum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getReorgannum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getReorgannum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}