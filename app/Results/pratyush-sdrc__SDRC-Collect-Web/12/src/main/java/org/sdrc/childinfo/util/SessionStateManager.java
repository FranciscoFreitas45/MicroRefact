package org.sdrc.childinfo.util;
 import java.util.List;
import javax.servlet.http.HttpSession;
import org.sdrc.childinfo.model.Error;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
@Scope(value = "singleton")
public class SessionStateManager implements StateManager{

public SessionStateManager() {
    System.out.println("In session state constructor");
}
@SuppressWarnings("unchecked")
@Override
public List<Error> getError(){
    return (List<Error>) (session().getAttribute(Constants.ERROR_LIST));
}


@Override
public Object getValue(String key){
    return session().getAttribute(key);
}


@Override
public void setError(List<Error> errModels){
    if (getError() != null && !errModels.isEmpty()) {
        List<Error> errorList = getError();
        errorList.addAll(errModels);
    } else {
        setValue(Constants.ERROR_LIST, errModels);
    }
}


public HttpSession session(){
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return attr.getRequest().getSession(true);
}


@Override
public void setValue(String key,Object value){
    session().setAttribute(key, value);
}


}