package sn.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import sn.model.enums.LikeType;
import javax.persistence;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "likes")
public class Like {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "person_id")
 private  Person person;

@Column(name = "item_id")
 private  long itemId;

@Enumerated(EnumType.STRING)
 private  LikeType likeType;

@CreationTimestamp
@Column(columnDefinition = "timestamp with time zone")
 private  LocalDateTime time;

public Like() {
}public Like(Person person, long itemId, LikeType likeType) {
    this.person = person;
    this.itemId = itemId;
    this.likeType = likeType;
}
}