package org.sdrc.childinfo.domain;
 import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "template_upload_meta")
public class TemplateUploadMeta {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "template_upload_meta_id_pk")
 private  Integer templateUploadMetaId;

@Column(name = "date_of_upload")
 private  Timestamp dateOfUpload;

@Column(name = "file_name", length = 100)
 private  String fileName;

@Column(name = "user_ip")
 private  String userIp;

@Column(name = "data_provided_by")
 private  String dataProvidedBy;


public void setFileName(String fileName){
    this.fileName = fileName;
}


public Integer getTemplateUploadMetaId(){
    return templateUploadMetaId;
}


public void setUserIp(String userIp){
    this.userIp = userIp;
}


public void setDateOfUpload(Timestamp dateOfUpload){
    this.dateOfUpload = dateOfUpload;
}


public String getDataProvidedBy(){
    return dataProvidedBy;
}


public String getUserIp(){
    return userIp;
}


public void setTemplateUploadMetaId(Integer templateUploadMetaId){
    this.templateUploadMetaId = templateUploadMetaId;
}


public Timestamp getDateOfUpload(){
    return dateOfUpload;
}


public String getFileName(){
    return fileName;
}


public void setDataProvidedBy(String dataProvidedBy){
    this.dataProvidedBy = dataProvidedBy;
}


}