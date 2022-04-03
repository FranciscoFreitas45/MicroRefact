package sn.api.response;
 import lombok.Getter;
import lombok.Setter;
import java.util.Map;
@Getter
@Setter
public class StorageResponse extends AbstractResponse{

 private  boolean success;

 private  Map<String,Object> response;


}