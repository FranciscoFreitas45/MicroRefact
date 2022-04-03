package main.model;
 import lombok;
import javax.persistence;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
public class Tag {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "name", nullable = false, unique = true)
 private  String name;

@ManyToMany(mappedBy = "tagSet")
 private  Set<Post> postSet;


public void addPost(Post post){
    postSet.add(post);
    post.getTagSet().add(this);
}


public void removePost(Post post){
    postSet.remove(post);
    post.getTagSet().remove(this);
}


}