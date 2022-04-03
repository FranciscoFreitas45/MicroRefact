package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class EkmKnowledgeTypeAuth {

 private  long serialVersionUID;

 private  String id;

 private  String userid;

 private  String knowledgetypeid;

 private  String knowledgebaseid;

 private  String auth;

 private  boolean view;

 private  boolean cover;

 private  String organ;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://41";


public String getAuth(){
    return auth;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getKnowledgetypeid(){
    return knowledgetypeid;
}


public String getOrgan(){
    return organ;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public String getKnowledgebaseid(){
    return knowledgebaseid;
}


public String getCreater(){
    return creater;
}


public String getUserid(){
    return userid;
}


public boolean isView(){
    return view;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isView"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isCover(){
    return cover;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isCover"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}