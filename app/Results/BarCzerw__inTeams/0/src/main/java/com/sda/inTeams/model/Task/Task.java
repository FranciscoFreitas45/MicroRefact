package com.sda.inTeams.model.Task;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.User.User;
import lombok;
import javax.persistence;
import java.util.Set;
import com.sda.inTeams.Request.UserRequest;
import com.sda.inTeams.Request.Impl.UserRequestImpl;
import com.sda.inTeams.DTO.User;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Indexable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private  String description;

@Enumerated(EnumType.STRING)
 private  TaskStatus status;

@ManyToOne
 private  Project project;

@Transient
 private  User userResponsible;

@OneToMany(mappedBy = "task")
@EqualsAndHashCode.Exclude
@ToString.Exclude
 private  Set<Comment> comments;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}