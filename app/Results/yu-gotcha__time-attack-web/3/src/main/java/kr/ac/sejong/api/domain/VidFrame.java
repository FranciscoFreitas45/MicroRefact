package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "VidFrame")
@Getter
@Setter
public class VidFrame {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "frame_id")
 private  long frameId;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "up_vid_id")
 private  UploadVid upVid;

@Column(name = "frame_name")
@NotNull
 private  String frameName;

@Column(name = "frame_no")
@NotNull
 private  long frameNo;

@Column(name = "frame_path")
@NotNull
 private  long framePath;

@OneToMany(mappedBy = "vidFrame")
@Column(name = "face_recognition")
 private  List<FaceRecognition> faceRecognitionList;


}