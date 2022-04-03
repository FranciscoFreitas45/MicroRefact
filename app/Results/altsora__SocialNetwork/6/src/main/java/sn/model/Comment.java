package sn.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sn.model.enums.LikeType;
import sn.service.LikeService;
import javax.persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "comments")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "children" })
public class Comment {

 private  long id;

 private  Comment parent;

 private  Post post;

 private  LocalDateTime time;

 private  Person author;

 private  String text;

 private  boolean isBlocked;

 private  Set<Comment> children;


@JsonManagedReference
@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
public Set<Comment> getChildren(){
    return children;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "parent_id")
public Comment getParent(){
    return parent;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "post_id")
public Post getPost(){
    return post;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "author_id")
public Person getAuthor(){
    return author;
}


@Column(name = "comment_time", nullable = false)
public LocalDateTime getTime(){
    return time;
}


@Column(name = "comment_text", nullable = false)
public String getText(){
    return text;
}


@Column(name = "is_blocked")
public boolean isBlocked(){
    return isBlocked;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long getId(){
    return id;
}


}