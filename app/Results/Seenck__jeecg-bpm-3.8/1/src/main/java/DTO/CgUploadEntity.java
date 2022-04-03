package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
public class CgUploadEntity extends TSAttachment{

 private  String cgformName;

 private  String cgformId;

 private  String cgformField;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Column(name = "CGFORM_NAME", nullable = false, length = 100)
public String getCgformName(){
    return cgformName;
}


@Column(name = "CGFORM_FIELD", nullable = false, length = 100)
public String getCgformField(){
    return cgformField;
}


@Column(name = "CGFORM_ID", nullable = false, length = 36)
public String getCgformId(){
    return cgformId;
}


public void setCgformId(String cgformId){
    this.cgformId = cgformId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCgformId"))

.queryParam("cgformId",cgformId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCgformName(String cgformName){
    this.cgformName = cgformName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCgformName"))

.queryParam("cgformName",cgformName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCgformField(String cgformField){
    this.cgformField = cgformField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCgformField"))

.queryParam("cgformField",cgformField)
;
restTemplate.put(builder.toUriString(),null);
}


}