package com.qidian.hcm.module.authorization.entity;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "employee_role")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EmployeeRole implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
@NotNull(message = "员工ID不能为空！")
 private  Long employeeId;

@Column(name = "role_id")
 private  Long roleId;

@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "role_id", insertable = false, updatable = false)
 private  Role role;

public EmployeeRole(Long employeeId, Long roleId) {
    this.employeeId = employeeId;
    this.roleId = roleId;
}
}