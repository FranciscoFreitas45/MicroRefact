package com.qidian.hcm.module.authorization.entity;
 import com.qidian.hcm.common.enums.YesNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "role")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Role implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "super_admin")
@Enumerated(value = EnumType.ORDINAL)
 private  YesNo superAdmin;

@Column(name = "created_time")
@CreationTimestamp
 private  Date createdTime;

@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
 public  List<RoleMenu> roleMenus;

@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
 public  List<RolePermission> rolePermissionList;

@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
 public  List<EmployeeRole> employeeRoles;

public Role(String name) {
    this.name = name;
}
}