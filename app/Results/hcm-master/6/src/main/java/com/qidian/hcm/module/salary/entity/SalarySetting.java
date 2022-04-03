package com.qidian.hcm.module.salary.entity;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "salary_setting")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class SalarySetting implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "setting_key")
 private  String key;

@Column(name = "setting_value")
 private  String value;

@Column(name = "remark")
 private  String remark;

public SalarySetting(String name, String key, String value) {
    this.name = name;
    this.key = key;
    this.value = value;
}
}