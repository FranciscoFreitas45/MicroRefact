package main.model.response.ids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthUserResp extends IdNamePhotoResp{

 private  String email;

 private  boolean moderation;

 private  int moderationCount;

 private  boolean settings;


}