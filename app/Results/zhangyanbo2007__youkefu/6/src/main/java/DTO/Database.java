package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class Database implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String address;

 private  String lastcrawl;

 private  String account;

 private  String password;

 private  String attachment;

 private  int port;

 private  String databasetype;

 private  String databasename;

 private  String connectparam;

 private  String encoding;

 private  String databaseurl;

 private  String driverclazz;

 private  String configure;

 private  String secureconf;

 private  String previewtemplet;

 private  String listblocktemplet;

 private  String sqldialect;

 private  String orgi;

 private  String jndiname;

 private  String jndiparam;

 private  String connctiontype;

 private  String doctype;

 private  String secure;

 private  Date lastupdate;

 private  String taskname;

 private  String taskplan;

 private  String taskstatus;

 private  String tasktype;

 private  Date createtime;

 private  String userid;

 private  String groupid;

 private  String createuser;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getName(){
    return name;
}


public String getListblocktemplet(){
    return listblocktemplet;
}


public String getTaskstatus(){
    return taskstatus;
}


public String getDatabasetype(){
    return databasetype;
}


public String getEncoding(){
    return encoding;
}


public String getSqldialect(){
    return sqldialect;
}


public String getCode(){
    return code;
}


public String getAttachment(){
    return attachment;
}


@Transient
public String getType(){
    return "database";
}


public String getConnectparam(){
    return connectparam;
}


public String getDriverclazz(){
    return driverclazz;
}


public String getLastcrawl(){
    return lastcrawl;
}


public String getTasktype(){
    return tasktype;
}


public String getSecureconf(){
    return secureconf;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getConfigure(){
    return configure;
}


public String getConnctiontype(){
    return connctiontype;
}


public String getTaskplan(){
    return taskplan;
}


public String getPreviewtemplet(){
    return previewtemplet;
}


public String getCreateuser(){
    return createuser;
}


public Date getCreatetime(){
    return createtime;
}


public String getJndiparam(){
    return jndiparam;
}


public String getAccount(){
    return account;
}


public String getAddress(){
    return address;
}


public String getUserid(){
    return userid;
}


public String getDatabasename(){
    return databasename;
}


public String getDatabaseurl(){
    return databaseurl;
}


public String getGroupid(){
    return groupid;
}


public String getJndiname(){
    return jndiname;
}


public Date getLastupdate(){
    return lastupdate;
}


public String getPassword(){
    return password;
}


public String getDoctype(){
    return doctype;
}


public int getPort(){
    return port;
}


public String getTaskname(){
    return taskname;
}


public String getOrgi(){
    return orgi;
}


public String getSecure(){
    return secure;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreateuser(String createuser){
    this.createuser = createuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateuser"))

.queryParam("createuser",createuser)
;
restTemplate.put(builder.toUriString(),null);
}


}