package cn.maxcj.DTO;
 import java.io.Serializable;
import java.util.List;
public class ShiroUser implements Serializable{

 private  long serialVersionUID;

 public  Integer id;

 public  String account;

 public  String name;

 public  Integer deptId;

 public  List<Integer> roleList;

 public  String deptName;

 public  List<String> roleNames;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public List<String> getRoleNames(){
    return roleNames;
}


public String getName(){
    return name;
}


public Integer getDeptId(){
    return deptId;
}


public Integer getId(){
    return id;
}


public String getDeptName(){
    return deptName;
}


public String getAccount(){
    return account;
}


public List<Integer> getRoleList(){
    return roleList;
}


public void setId(Integer id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAccount(String account){
    this.account = account;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccount"))

.queryParam("account",account)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDeptId(Integer deptId){
    this.deptId = deptId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDeptId"))

.queryParam("deptId",deptId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDeptName(String deptName){
    this.deptName = deptName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDeptName"))

.queryParam("deptName",deptName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoleList(List<Integer> roleList){
    this.roleList = roleList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoleList"))

.queryParam("roleList",roleList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoleNames(List<String> roleNames){
    this.roleNames = roleNames;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoleNames"))

.queryParam("roleNames",roleNames)
;
restTemplate.put(builder.toUriString(),null);
}


}