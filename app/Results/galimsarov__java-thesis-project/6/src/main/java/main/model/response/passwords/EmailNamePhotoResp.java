package main.model.response.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailNamePhotoResp extends PasswordResponse{

 private  String email;

 private  String photo;

 private  String name;


}