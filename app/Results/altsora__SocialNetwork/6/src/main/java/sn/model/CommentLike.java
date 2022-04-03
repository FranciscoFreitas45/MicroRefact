package sn.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.time.LocalDateTime;
@Entity
@Table(name = "comment_likes")
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CommentLike {

 private  long id;

 private  LocalDateTime time;

 private  Person person;

 private  Comment comment;


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "person_id")
public Person getPerson(){
    return person;
}


@Column(name = "time", nullable = false)
public LocalDateTime getTime(){
    return time;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long getId(){
    return id;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "comment_id")
public Comment getComment(){
    return comment;
}


}