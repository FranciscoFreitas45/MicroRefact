package main.model;
 import lombok.Getter;
import lombok.Setter;
import javax.persistence;
@Entity
@Table(name = "tag2post")
@Getter
@Setter
public class TagToPost {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "post_id", nullable = false)
 private  int postId;

@Column(name = "tag_id", nullable = false)
 private  int tagId;


}