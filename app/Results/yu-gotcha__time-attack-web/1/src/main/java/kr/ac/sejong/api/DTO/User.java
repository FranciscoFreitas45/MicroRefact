package kr.ac.sejong.api.DTO;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import kr.ac.sejong.api.Request.UploadRequest;
import kr.ac.sejong.api.Request.Impl.UploadRequestImpl;
import kr.ac.sejong.api.DTO.Upload;
public class User {

 private  long userId;

 private  String userName;

 private  String loginId;

 private  String loginPw;

 private  List<Upload> uploadList;


}