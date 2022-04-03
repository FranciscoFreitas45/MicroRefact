package com.qidian.hcm.module.authorization.entity;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "role_menu")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class RoleMenu implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "role_id")
 private  Long roleId;

@Column(name = "menu_id")
 private  Long menuId;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "role_id", insertable = false, updatable = false)
 private  Role role;

public RoleMenu(Long roleId, Long menuId) {
    this.roleId = roleId;
    this.menuId = menuId;
}
}