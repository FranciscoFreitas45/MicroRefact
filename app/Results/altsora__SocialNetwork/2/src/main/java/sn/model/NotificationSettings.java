package sn.model;
 import lombok;
import javax.persistence;
import sn.Request.PersonRequest;
import sn.Request.Impl.PersonRequestImpl;
import sn.DTO.Person;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_settings")
public class NotificationSettings {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@Transient
 private  Person owner;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "notification_type_id", nullable = false)
 private  NotificationType type;

 private  boolean enable;

@Column(name = "id")
 private long id;

@Transient
 private PersonRequest personrequest = new PersonRequestImpl();;


}