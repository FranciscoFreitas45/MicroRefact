package com.qidian.hcm.module.authorization.entity;
 import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.EffectType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "role_permission")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class RolePermission implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "role_id")
 private  Long roleId;

@Column(name = "menu_code")
@Enumerated(value = EnumType.STRING)
 private  MenuCode menuCode;

@Column(name = "resource")
 private  String resource;

@Column(name = "action")
@Enumerated(value = EnumType.STRING)
 private  ActionType action;

@Column(name = "conditions")
 private  String conditions;

@Column(name = "effect")
@Enumerated(value = EnumType.STRING)
 private  EffectType effect;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "role_id", insertable = false, updatable = false)
 private  Role role;

public RolePermission(Long roleId, MenuCode menuCode, String resource, ActionType action, String conditions) {
    this.roleId = roleId;
    this.menuCode = menuCode;
    this.resource = resource;
    this.action = action;
    this.conditions = conditions;
    this.effect = EffectType.allow;
}
}