package kr.ac.sejong.api.DTO;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
public class UploadImg {

 private  long upImgId;

 private  String upImgName;

 private  String upImgSavedName;

 private  long upImgFaceId;

 private  String upImgPath;

 private  User imgUpUser;

 private  Upload upload;


}