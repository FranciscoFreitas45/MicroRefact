package com.cym.config;
 import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.cym.model.Admin;
import com.cym.model.Remote;
import com.cym.service.CreditService;
import com.cym.utils.BaseController;
import com.cym.utils.MessageUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cym.Interface.MessageUtils;
import com.cym.DTO.Remote;
@Component
public class AdminInterceptor implements HandlerInterceptor{

@Autowired
 private CreditService creditService;

@Autowired
 private MessageUtils m;


public String buldBody(Map<String,String[]> parameterMap,Remote remote,Admin admin){
    List<String> body = new ArrayList<>();
    body.add("creditKey=" + remote.getCreditKey());
    if (admin != null) {
        body.add("adminName=" + admin.getName());
    }
    for (Iterator itr = parameterMap.entrySet().iterator(); itr.hasNext(); ) {
        Map.Entry me = (Map.Entry) itr.next();
        for (String value : (String[]) me.getValue()) {
            body.add(me.getKey() + "=" + URLEncoder.encode(value, "UTF-8"));
        }
    }
    return StrUtil.join("&", body);
}


@Override
public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,Exception arg3){
}


@Override
public void postHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,ModelAndView arg3){
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object obj){
    String httpHost = request.getHeader("X-Forwarded-Host");
    String realPort = request.getHeader("X-Forwarded-Port");
    String host = request.getHeader("Host");
    String ctx = getCtx(httpHost, host, realPort);
    if (request.getRequestURL().toString().contains("adminPage/login")) {
        return true;
    }
    String creditKey = request.getParameter("creditKey");
    Boolean isCredit = creditService.check(creditKey);
    Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
    if (!((isLogin != null && isLogin) || isCredit)) {
        response.sendRedirect(ctx + "/adminPage/login");
        return false;
    }
    String localType = (String) request.getSession().getAttribute("localType");
    if (localType != null && localType.equals("remote") && !request.getRequestURL().toString().contains("adminPage/remote")) {
        // 转发到远程服务器
        Remote remote = (Remote) request.getSession().getAttribute("remote");
        String url = buildUrl(ctx, request, remote);
        try {
            String rs = null;
            if (url.contains("main/upload")) {
                // 上传文件
                Map<String, Object> map = new HashMap<>();
                map.put("creditKey", remote.getCreditKey());
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile multipartFile = multipartRequest.getFile("file");
                File temp = new File(FileUtil.getTmpDir() + "/" + multipartFile.getOriginalFilename());
                multipartFile.transferTo(temp);
                map.put("file", temp);
                rs = HttpUtil.post(url, map);
            } else {
                // 普通请求
                Admin admin = new BaseController().getAdmin(request);
                String body = buldBody(request.getParameterMap(), remote, admin);
                rs = HttpUtil.post(url, body);
            }
            rs = // 
            rs.replace(// 
            "'//" + remote.getIp() + ":" + remote.getPort() + "/'", // 
            "'//" + request.getServerName() + ":" + request.getServerPort() + "/'").replace(// 
            "//" + remote.getIp() + ":" + remote.getPort() + "/adminPage", // 
            "//" + request.getServerName() + ":" + request.getServerPort() + "/adminPage").replace(// 
            "//" + remote.getIp() + ":" + remote.getPort() + "/lib", // 
            "//" + request.getServerName() + ":" + request.getServerPort() + "/lib").replace(// 
            "//" + remote.getIp() + ":" + remote.getPort() + "/js", // 
            "//" + request.getServerName() + ":" + request.getServerPort() + "/js").replace(// 
            "//" + remote.getIp() + ":" + remote.getPort() + "/css", // 
            "//" + request.getServerName() + ":" + request.getServerPort() + "/css").replace(// 
            "//" + remote.getIp() + ":" + remote.getPort() + "/img", // 
            "//" + request.getServerName() + ":" + request.getServerPort() + "/img");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            if (JSONUtil.isJson(rs)) {
                String date = DateUtil.format(new Date(), "yyyy-MM-dd_HH-mm-ss");
                response.addHeader("Content-Type", "application/octet-stream");
                // 设置文件名
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(date + ".json", "UTF-8"));
                byte[] buffer = new byte[1024];
                BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(rs.getBytes(Charset.forName("UTF-8"))));
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } else {
                PrintWriter out = response.getWriter();
                out.append(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ctx + "/adminPage/login/noServer");
        }
        return false;
    }
    return true;
}


public String buildUrl(String ctx,HttpServletRequest request,Remote remote){
    String url = request.getRequestURL().toString().replace(ctx, "//" + remote.getIp() + ":" + remote.getPort() + "/");
    if (!url.startsWith("http")) {
        url = remote.getProtocol() + ":" + url;
    }
    return url + "?jsrandom=" + System.currentTimeMillis();
}


public String getCtx(String httpHost,String host,String realPort){
    String ctx = "//";
    if (StrUtil.isNotEmpty(httpHost)) {
        ctx += httpHost;
    } else {
        ctx += host;
        if (!host.contains(":") && StrUtil.isNotEmpty(realPort)) {
            ctx += ":" + realPort;
        }
    }
    return ctx;
}


}