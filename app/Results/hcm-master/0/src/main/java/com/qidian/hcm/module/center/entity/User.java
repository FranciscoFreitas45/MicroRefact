package com.qidian.hcm.module.center.entity;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.common.constants.RegularConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "user")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank(message = "用户名不能为空")
@Column(name = "username")
 private  String username;

@Column(name = "password")
 private  String password;

@Column(name = "status")
 private  Integer status;

@Email(message = "必须符合邮件格式")
@Column(name = "email")
 private  String email;

@NotBlank
@Length(min = 11, max = 11, message = "手机号长度必须为11位")
@Pattern(regexp = RegularConstant.MOBILE_REG, message = "请输入正确的手机号！")
@Column(name = "phone")
 private  String phone;

@Column(name = "group_id")
 private  Long groupId;

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

public User(String username, String password, String phone, Long groupId) {
    this.username = username;
    this.password = password;
    this.phone = phone;
    this.groupId = groupId;
}public User(String username, String phone, Long groupId) {
    this.username = username;
    this.phone = phone;
    this.groupId = groupId;
}
}