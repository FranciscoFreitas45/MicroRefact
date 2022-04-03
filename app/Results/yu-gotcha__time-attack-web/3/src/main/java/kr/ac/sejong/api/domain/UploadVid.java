package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import kr.ac.sejong.api.Request.UserRequest;
import kr.ac.sejong.api.Request.Impl.UserRequestImpl;
import kr.ac.sejong.api.DTO.User;
import kr.ac.sejong.api.Request.UploadRequest;
import kr.ac.sejong.api.Request.Impl.UploadRequestImpl;
import kr.ac.sejong.api.DTO.Upload;
@Entity
@Table(name = "UploadVid")
@Getter
@Setter
public class UploadVid {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "up_vid_id")
 private  long upVidId;

@Column(name = "up_vid_name")
@NotNull
 private  String upVidName;

@Column(name = "up_vid_saved_name")
@NotNull
 private  String upVidSavedName;

@Column(name = "up_vid_path")
 private  String upVidPath;

@Transient
 private  User vidUpUser;

@Transient
 private  Upload upload;

@OneToMany(mappedBy = "upVid")
@Column(name = "vid_frame")
 private  List<VidFrame> vidFrameList;

@Column(name = "userId")
 private long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "upId")
 private long upId;

@Transient
 private UploadRequest uploadrequest = new UploadRequestImpl();;


}