package com.qidian.hcm.module.employee.entity;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "attachment")
@Getter
@Setter
@ApiModel(value = "attachment")
public class Attachment implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "origin_name")
 private  String originName;

@Column(name = "file_id")
 private  Long fileId;

@Column(name = "oss_filename")
 private  String fileNameOnOss;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "create_time", nullable = false, updatable = false)
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@UpdateTimestamp
@Column(name = "update_time", nullable = true, insertable = false)
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date updateTime;

@Column(name = "file_type")
 private  String fileType;

@Column(name = "file_size")
 private  Long fileSize;


}