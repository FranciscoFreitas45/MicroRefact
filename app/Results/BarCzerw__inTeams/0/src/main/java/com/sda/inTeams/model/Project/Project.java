package com.sda.inTeams.model.Project;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Team.Team;
import lombok;
import javax.persistence;
import java.util.Set;
import com.sda.inTeams.Request.TeamRequest;
import com.sda.inTeams.Request.Impl.TeamRequestImpl;
import com.sda.inTeams.DTO.Team;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Indexable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private  String name;

@Transient
 private  Team projectOwner;

@Enumerated(EnumType.STRING)
 private  ProjectStatus status;

@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
@EqualsAndHashCode.Exclude
@ToString.Exclude
 private  Set<Task> tasks;

@Column(name = "id")
 private Long id;

@Transient
 private TeamRequest teamrequest = new TeamRequestImpl();;


}