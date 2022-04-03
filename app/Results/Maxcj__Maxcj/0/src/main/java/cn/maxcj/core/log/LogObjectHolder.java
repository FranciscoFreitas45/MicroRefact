package cn.maxcj.core.log;
 import cn.stylefeng.roses.core.util.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import java.io.Serializable;
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable{

 private  Object object;


public void set(Object obj){
    this.object = obj;
}


public Object get(){
    return object;
}


public LogObjectHolder me(){
    return SpringContextHolder.getBean(LogObjectHolder.class);
}


}