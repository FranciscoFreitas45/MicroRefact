package gov.cdc.nccdphp.domain;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import javax.persistence;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import gov.cdc.nccdphp.Request.DivisionRequest;
import gov.cdc.nccdphp.Request.Impl.DivisionRequestImpl;
import gov.cdc.nccdphp.DTO.Division;
import gov.cdc.nccdphp.Request.ManagerRequest;
import gov.cdc.nccdphp.Request.Impl.ManagerRequestImpl;
import gov.cdc.nccdphp.DTO.Manager;
@Entity
@Data
@ToString(exclude = "deployments")
@EqualsAndHashCode(exclude = "deployments")
public class Project {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@Transient
 private  Division division;

@NotNull
@Size(min = 2, max = 255)
 private  String name;

@Column(unique = true)
@Size(min = 2, max = 6)
 private  String abbreviation;

@Column(unique = true)
@Max(99999)
 private  Integer escNumber;

@Transient
 private  Manager manager;

@OneToMany(mappedBy = "project")
 private  List<Deployment> deployments;

@Column(name = "id")
 private Integer id;

@Transient
 private DivisionRequest divisionrequest = new DivisionRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private ManagerRequest managerrequest = new ManagerRequestImpl();;


public void addDeployment(Deployment deployment){
    if (deployments == null) {
        deployments = new ArrayList<Deployment>();
    }
    this.deployments.add(deployment);
    deployment.setProject(this);
}


}