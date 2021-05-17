import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_EMPTY)
public class ExtAjaxResponse {

 private  boolean success;

 private  String msg;

 private  Map<String,String> map;


public String getMsg(){
    return msg;
}


public Map<String,String> getMap(){
    return map;
}


public boolean isSuccess(){
    return success;
}


}