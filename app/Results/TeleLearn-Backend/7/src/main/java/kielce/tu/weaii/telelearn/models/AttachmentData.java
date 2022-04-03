package kielce.tu.weaii.telelearn.models;
 import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import java.io.Serializable;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "ATTACHMENTS_DATA")
@Getter
@Setter
public class AttachmentData implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
 private  Long id;

@ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
@JoinColumn(nullable = false, name = "ATTACHMENT_ID")
 private  Attachment attachment;

@Column(nullable = false)
@Lob
 private  byte[] data;


}