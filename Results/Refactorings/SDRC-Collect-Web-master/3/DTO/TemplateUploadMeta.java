import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class TemplateUploadMeta {

 private  Integer templateUploadMetaId;

 private  Timestamp dateOfUpload;

 private  String fileName;

 private  String userIp;

 private  String dataProvidedBy;

 private RestTemplate restTemplate = new RestTemplate();


public Integer getTemplateUploadMetaId(){
    return templateUploadMetaId;
}


public String getDataProvidedBy(){
    return dataProvidedBy;
}


public String getUserIp(){
    return userIp;
}


public Timestamp getDateOfUpload(){
    return dateOfUpload;
}


public String getFileName(){
    return fileName;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ templateUploadMetaId).concat("/setFileName"));

.queryParam("fileName",fileName);
restTemplate.put(builder.toUriString(),null);


public void setDateOfUpload(Timestamp dateOfUpload){
    this.dateOfUpload = dateOfUpload;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ templateUploadMetaId).concat("/setDateOfUpload"));

.queryParam("dateOfUpload",dateOfUpload);
restTemplate.put(builder.toUriString(),null);


public void setUserIp(String userIp){
    this.userIp = userIp;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ templateUploadMetaId).concat("/setUserIp"));

.queryParam("userIp",userIp);
restTemplate.put(builder.toUriString(),null);


public void setDataProvidedBy(String dataProvidedBy){
    this.dataProvidedBy = dataProvidedBy;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ templateUploadMetaId).concat("/setDataProvidedBy"));

.queryParam("dataProvidedBy",dataProvidedBy);
restTemplate.put(builder.toUriString(),null);


}