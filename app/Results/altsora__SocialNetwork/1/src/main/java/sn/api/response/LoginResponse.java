package sn.api.response;
 import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginResponse extends AbstractResponse{

 private  String error;

 private  long timestamp;

 private  PersonResponse data;


}