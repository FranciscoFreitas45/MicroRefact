package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
public class QuickReply {

 private  String id;

 private  String title;

 private  String content;

 private  String type;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String cate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getContent(){
    return content;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public String getCate(){
    return cate;
}


public String getOrgi(){
    return orgi;
}


public String getCreater(){
    return creater;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
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


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


}