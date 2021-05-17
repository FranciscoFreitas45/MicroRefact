import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
public class BadRequestAlertException extends AbstractThrowableProblem {

 private  long serialVersionUID;

 private  String entityName;

 private  String errorKey;


public String getEntityName(){
    return entityName;
}


public String getErrorKey(){
    return errorKey;
}


public Map<String,Object> getAlertParameters(String entityName,String errorKey){
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("message", "error." + errorKey);
    parameters.put("params", entityName);
    return parameters;
}


}