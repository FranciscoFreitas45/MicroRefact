package net.shangtech.weixin.sys.action;
 import net.shangtech.ssh.core.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
@Results({ @Result(name = "service", location = "user/service.jsp") })
@Namespace("/sys")
public class ServiceAction extends BaseAction{


public String execute(){
    return "service";
}


}