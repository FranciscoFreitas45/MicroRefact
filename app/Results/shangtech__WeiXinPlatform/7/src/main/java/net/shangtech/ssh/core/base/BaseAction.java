package net.shangtech.ssh.core.base;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class BaseAction extends BaseMVCimplements ServletResponseAware,ServletRequestAware{

 protected  HttpServletRequest request;

 protected  HttpServletResponse response;

 protected  Integer id;

 public  String SUCCESS;

 public  String GET;

 public  String POST;


public boolean isGet(){
    return GET.equals(request.getMethod());
}


public void prepare(){
    this.id = super.getId();
}


@Override
public HttpServletRequest getRequest(){
    return this.request;
}


@Override
public HttpServletResponse getResponse(){
    return this.response;
}


public void setServletResponse(HttpServletResponse response){
    this.response = response;
}


public void setServletRequest(HttpServletRequest request){
    this.request = request;
}


}