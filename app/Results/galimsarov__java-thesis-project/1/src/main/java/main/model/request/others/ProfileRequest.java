package main.model.request.others;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;
@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileRequest extends EmailRequest{

 private  String name;

 private  String password;

 private  int removePhoto;

 private  MultipartFile photo;


}