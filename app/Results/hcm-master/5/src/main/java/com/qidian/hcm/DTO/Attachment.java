package com.qidian.hcm.DTO;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
public class Attachment implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String originName;

 private  Long fileId;

 private  String fileNameOnOss;

 private  Date createTime;

 private  Date updateTime;

 private  String fileType;

 private  Long fileSize;


}