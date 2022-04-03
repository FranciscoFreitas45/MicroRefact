package net.shangtech.weixin.controller;
 import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.util.EncoderUtils;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class WeixinController extends BaseController{

@Autowired
 private  SysUserService sysUserService;


@RequestMapping("/{appid}")
public String main(HttpServletRequest request,HttpServletResponse response,String appid){
    SysUser user = sysUserService.findByAppid(appid);
    if (!check(request, user)) {
    }
    String echostr = request.getParameter("echostr");
    if (StringUtils.isNotBlank(echostr)) {
        out(response, echostr);
        return null;
    }
    return null;
}


public boolean check(HttpServletRequest request,SysUser user){
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)) {
        return false;
    }
    String[] array = { user.getToken(), timestamp, nonce };
    Arrays.sort(array);
    String result = array[0] + array[1] + array[2];
    String _result = EncoderUtils.SHA1(result);
    if (_result.equals(signature)) {
        return true;
    }
    return false;
}


}