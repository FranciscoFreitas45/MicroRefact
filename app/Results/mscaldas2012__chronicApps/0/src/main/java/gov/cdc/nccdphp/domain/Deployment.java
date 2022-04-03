package gov.cdc.nccdphp.domain;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
@Entity
@Data
public class Deployment {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@ManyToOne
@JoinColumn(name = "PROJECT_ID", nullable = false)
 private  Project project;

@NotNull
 private  EnumEnvironmentName environment;

 private  String envOtherDescription;

 private  EnumDeploymentLifeCycle lifecycle;

 private  Date dueDate;

@NotNull
 private  String url;

 private  String projectVersion;


}