package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence;
@Entity
@Table(name = "ResultSection")
@Getter
@Setter
public class ResultSection {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "section_id")
 private  long sectionId;

@Column(name = "start")
@NotNull
 private  String start;

@Column(name = "end")
@NotNull
 private  String end;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "upload_id")
 private  Upload upload;

@Column(name = "img_path")
 private  String imgPath;


}