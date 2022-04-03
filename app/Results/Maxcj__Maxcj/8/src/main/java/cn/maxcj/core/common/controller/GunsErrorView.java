package cn.maxcj.core.common.controller;
 import org.springframework.web.servlet.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
public class GunsErrorView implements View{


@Override
public String getContentType(){
    return "text/html";
}


@Override
public void render(Map<String,?> map,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    httpServletRequest.getRequestDispatcher("/global/error").forward(httpServletRequest, httpServletResponse);
}


}