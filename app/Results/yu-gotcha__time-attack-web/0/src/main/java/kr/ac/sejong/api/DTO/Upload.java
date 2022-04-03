package kr.ac.sejong.api.DTO;
 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class Upload {

 private  long upId;

 public  UploadImg uploadImg;

 public  UploadVid uploadVid;

 private  long resultClusterId;

 private  List<ResultSection> sectionList;

 private  User user;

 private  int uploading;

 private  int processing;

 private  int faceCount;


}