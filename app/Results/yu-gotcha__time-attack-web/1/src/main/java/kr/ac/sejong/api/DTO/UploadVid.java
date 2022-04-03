package kr.ac.sejong.api.DTO;
 import com.sun.istack.NotNull;
import lombok;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class UploadVid {

 private  long upVidId;

 private  String upVidName;

 private  String upVidSavedName;

 private  String upVidPath;

 private  User vidUpUser;

 private  Upload upload;

 private  List<VidFrame> vidFrameList;


}