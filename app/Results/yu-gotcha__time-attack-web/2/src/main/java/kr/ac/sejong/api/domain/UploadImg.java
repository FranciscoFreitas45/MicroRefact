package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import kr.ac.sejong.api.Request.UserRequest;
import kr.ac.sejong.api.Request.Impl.UserRequestImpl;
import kr.ac.sejong.api.DTO.User;
import kr.ac.sejong.api.Request.UploadRequest;
import kr.ac.sejong.api.Request.Impl.UploadRequestImpl;
import kr.ac.sejong.api.DTO.Upload;
@Entity
@Table(name = "UploadImg")
@Getter
@Setter
public class UploadImg {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "up_img_id")
 private  long upImgId;

@Column(name = "up_img_name")
@NotNull
 private  String upImgName;

@Column(name = "up_img_saved_name")
@NotNull
 private  String upImgSavedName;

@Column(name = "up_img_face_id")
 private  long upImgFaceId;

@Column(name = "up_img_path")
@NotNull
 private  String upImgPath;

@Transient
 private  User imgUpUser;

@Transient
 private  Upload upload;

@Column(name = "userId")
 private long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "upId")
 private long upId;

@Transient
 private UploadRequest uploadrequest = new UploadRequestImpl();;


}