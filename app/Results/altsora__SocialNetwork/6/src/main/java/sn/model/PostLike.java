package sn.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.time.LocalDateTime;
@Entity
@Table(name = "post_likes")
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class PostLike {

 private  long id;

 private  LocalDateTime time;

 private  Person person;

 private  Post post;


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "person_id")
public Person getPerson(){
    return person;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "post_id")
public Post getPost(){
    return post;
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


}