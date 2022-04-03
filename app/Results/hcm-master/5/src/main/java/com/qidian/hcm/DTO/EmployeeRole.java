package com.qidian.hcm.DTO;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
public class EmployeeRole implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Long employeeId;

 private  Long roleId;

 private  Role role;

public EmployeeRole(Long employeeId, Long roleId) {
    this.employeeId = employeeId;
    this.roleId = roleId;
}
}