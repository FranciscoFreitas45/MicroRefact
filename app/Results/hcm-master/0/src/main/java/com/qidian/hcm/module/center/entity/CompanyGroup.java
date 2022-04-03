package com.qidian.hcm.module.center.entity;
 import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "company_group")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CompanyGroup implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "name")
 private  String name;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "create_time", nullable = false, updatable = false)
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@UpdateTimestamp
@Column(name = "update_time", nullable = true, insertable = false)
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
 private  Date updateTime;


}