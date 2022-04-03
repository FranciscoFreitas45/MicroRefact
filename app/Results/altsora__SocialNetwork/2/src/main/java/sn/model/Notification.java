package sn.model;
 import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence;
import java.time.LocalDateTime;
import sn.Request.PersonRequest;
import sn.Request.Impl.PersonRequestImpl;
import sn.DTO.Person;
@Data
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "type_id", nullable = false)
 private  NotificationType type;

@CreationTimestamp
@Column(name = "sent_time", columnDefinition = "timestamp with time zone")
 private  LocalDateTime sentTime;

@Column(name = "entity_id", nullable = false)
 private  long entityId;

 private  String info;

@Transient
 private  Person toWhom;

 private  String contact;

@Column(name = "is_readed", nullable = false)
 private  boolean isReaded;

@Column(name = "id")
 private long id;

@Transient
 private PersonRequest personrequest = new PersonRequestImpl();;


}