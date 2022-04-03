package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence;
@Entity
@Table(name = "FaceRecognition")
@Getter
@Setter
public class FaceRecognition {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "fr_img_id")
 private  long frImgId;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "frame_name")
 private  VidFrame vidFrame;

@Column(name = "fr_img_name")
@NotNull
 private  String frImgName;

@Column(name = "fr_img_path")
@NotNull
 private  String frImgPath;

@Column(name = "cluster_id")
 private  long clusterId;

@Column(name = "flag")
 private  int flag;


}