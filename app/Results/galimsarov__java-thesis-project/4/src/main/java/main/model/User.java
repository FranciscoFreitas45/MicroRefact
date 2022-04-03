package main.model;
 import lombok;
import org.hibernate.annotations.Type;
import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = { "postList", "postVoteList", "postCommentList" })
@ToString
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(columnDefinition = "TINYINT", name = "is_moderator", nullable = false)
@Type(type = "org.hibernate.type.NumericBooleanType")
 private  boolean isModerator;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "reg_time", nullable = false)
 private  Date regTime;

@Column(name = "name", nullable = false)
 private  String name;

@Column(name = "email", nullable = false)
 private  String email;

@Column(name = "password", nullable = false)
 private  String password;

@Column(name = "code")
 private  String code;

@Column(columnDefinition = "TEXT", name = "photo")
 private  String photo;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<Post> postList;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<PostVote> postVoteList;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<PostComment> postCommentList;


public void addPostVote(PostVote postVote){
    postVoteList.add(postVote);
    postVote.setUser(this);
}


public void removePostVote(PostVote postVote){
    postVoteList.remove(postVote);
    postVote.setUser(null);
}


public void addPost(Post post){
    postList.add(post);
    post.setUser(this);
}


public void removePostComment(PostComment postComment){
    postCommentList.remove(postComment);
    postComment.setUser(null);
}


public void removePost(Post post){
    postList.remove(post);
    post.setUser(null);
}


public void addPostComment(PostComment postComment){
    postCommentList.add(postComment);
    postComment.setUser(this);
}


}