package kr.ac.sejong.api.DTO;
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

 private long upImgId;

 private UploadImgRequest uploadimgrequest = new UploadImgRequestImpl();;

 private long upVidId;

 private UploadVidRequest uploadvidrequest = new UploadVidRequestImpl();;

 private long userId;


}