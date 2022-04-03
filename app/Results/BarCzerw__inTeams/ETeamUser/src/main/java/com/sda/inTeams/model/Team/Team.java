package com.sda.inTeams.model.Team;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.User.User;
import lombok;
import javax.persistence;
import java.util.Set;
import java.util.UUID;
import com.sda.inTeams.Request.ProjectRequest;
import com.sda.inTeams.Request.Impl.ProjectRequestImpl;
import com.sda.inTeams.DTO.Project;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Indexable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private  String uniqueRegisterId;

 private  String name;

@ManyToOne()
@EqualsAndHashCode.Exclude
@ToString.Exclude
 private  User teamOwner;

@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
@EqualsAndHashCode.Exclude
@ToString.Exclude
 private  Set<User> members;

@Transient
 private  Set<Project> projects;

@Transient
 private ProjectRequest projectrequest = new ProjectRequestImpl();;


}