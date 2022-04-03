package com.netease.util;
 import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
public class CommonServiceUtil {


public void getBaseURL(HttpServletRequest request,Model model){
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    model.addAttribute("basePath", basePath);
}


}