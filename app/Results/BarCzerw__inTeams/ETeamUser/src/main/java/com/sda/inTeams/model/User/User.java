package com.sda.inTeams.model.User;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Team.Team;
import lombok;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import com.sda.inTeams.Request.TeamRequest;
import com.sda.inTeams.Request.Impl.TeamRequestImpl;
import com.sda.inTeams.DTO.Team;
import com.sda.inTeams.Request.TaskRequest;
import com.sda.inTeams.Request.Impl.TaskRequestImpl;
import com.sda.inTeams.DTO.Task;
import com.sda.inTeams.Request.CommentRequest;
import com.sda.inTeams.Request.Impl.CommentRequestImpl;
import com.sda.inTeams.DTO.Comment;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails,Indexable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private  String username;

 private  String nonHashedPassword;

 private  String password;

 private  String firstName;

 private  String lastName;

 private  String uniqueInvitationId;

 private  boolean accountNonExpired;

 private  boolean accountNonLocked;

 private  boolean credentialsNonExpired;

 private  boolean enabled;

@ManyToMany(fetch = FetchType.EAGER)
 private  Set<AccountRole> roles;

@Transient
 private  Set<Team> teamsOwned;

@ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
@EqualsAndHashCode.Exclude
@ToString.Exclude
 private  Set<Team> teams;

@Transient
 private  Set<Task> taskResponsibleFor;

@Transient
 private  Set<Comment> commentsCreated;

@Transient
 private TeamRequest teamrequest = new TeamRequestImpl();;

@Transient
 private TaskRequest taskrequest = new TaskRequestImpl();;

@Transient
 private CommentRequest commentrequest = new CommentRequestImpl();;


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return roles.stream().map(accountRole -> new SimpleGrantedAuthority(accountRole.getName())).collect(Collectors.toList());
}


}