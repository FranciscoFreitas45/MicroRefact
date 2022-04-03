package com.qidian.hcm.module.authorization.entity;
 import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.MenuType;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "menu")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Menu implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "code")
@Enumerated(value = EnumType.STRING)
 private  MenuCode code;

@Column(name = "parent_id")
 private  Long parentId;

@Column(name = "idx")
 private  Integer idx;

@Column(name = "type")
@Enumerated(value = EnumType.STRING)
 private  MenuType type;

@Column(name = "platform")
@Enumerated(value = EnumType.STRING)
 private  PlatformType platform;


}