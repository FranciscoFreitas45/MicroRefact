package main.model;
 import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "post_comments")
@Getter
@Setter
@EqualsAndHashCode
public class PostComment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "parent_id")
 private  Integer parentId;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "time", nullable = false)
 private  Date time;

@Column(columnDefinition = "TEXT", name = "text", nullable = false)
 private  String text;

@ManyToOne(fetch = FetchType.LAZY)
 private  Post post;

@ManyToOne(fetch = FetchType.LAZY)
 private  User user;


}