package kr.ac.sejong.api.domain;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import kr.ac.sejong.api.Request.UploadRequest;
import kr.ac.sejong.api.Request.Impl.UploadRequestImpl;
import kr.ac.sejong.api.DTO.Upload;
@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
 private  long userId;

@Column(name = "user_name")
@NotNull
 private  String userName;

@Column(name = "user_login_id")
@NotNull
 private  String loginId;

@Column(name = "user_login_pw")
@NotNull
 private  String loginPw;

@Transient
 private  List<Upload> uploadList;

@Transient
 private UploadRequest uploadrequest = new UploadRequestImpl();;


}