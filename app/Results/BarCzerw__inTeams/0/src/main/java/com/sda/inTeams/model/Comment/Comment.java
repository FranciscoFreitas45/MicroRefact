package com.sda.inTeams.model.Comment;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import com.sda.inTeams.Request.UserRequest;
import com.sda.inTeams.Request.Impl.UserRequestImpl;
import com.sda.inTeams.DTO.User;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Indexable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private  String text;

@Transient
 private  User creator;

@Enumerated(EnumType.STRING)
 private  CommentType type;

@ManyToOne(fetch = FetchType.EAGER)
 private  Task task;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}