package kr.ac.sejong.api.domain;
 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import kr.ac.sejong.api.Request.UploadImgRequest;
import kr.ac.sejong.api.Request.Impl.UploadImgRequestImpl;
import kr.ac.sejong.api.DTO.UploadImg;
import kr.ac.sejong.api.Request.UploadVidRequest;
import kr.ac.sejong.api.Request.Impl.UploadVidRequestImpl;
import kr.ac.sejong.api.DTO.UploadVid;
import kr.ac.sejong.api.Request.UserRequest;
import kr.ac.sejong.api.Request.Impl.UserRequestImpl;
import kr.ac.sejong.api.DTO.User;
@Entity
@Table(name = "Upload")
@Getter
@Setter
public class Upload {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "up_id")
 private  long upId;

@Transient
 public  UploadImg uploadImg;

@Transient
 public  UploadVid uploadVid;

@Column(name = "result_cluster_id")
 private  long resultClusterId;

@OneToMany(mappedBy = "upload", fetch = FetchType.EAGER)
@Column(name = "resule_section")
 private  List<ResultSection> sectionList;

@Transient
 private  User user;

@Column(name = "uploading")
 private  int uploading;

@Column(name = "processing")
 private  int processing;

@Column(name = "face_count")
 private  int faceCount;

@Column(name = "upImgId")
 private long upImgId;

@Transient
 private UploadImgRequest uploadimgrequest = new UploadImgRequestImpl();;

@Column(name = "upVidId")
 private long upVidId;

@Transient
 private UploadVidRequest uploadvidrequest = new UploadVidRequestImpl();;

@Column(name = "userId")
 private long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}