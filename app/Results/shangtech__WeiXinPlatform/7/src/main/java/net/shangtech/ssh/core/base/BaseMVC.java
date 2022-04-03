package net.shangtech.ssh.core.base;
 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
public class BaseMVC {


public int getStart(){
    String start = getRequest().getParameter("start");
    if (StringUtils.isBlank(start))
        start = "0";
    return Integer.parseInt(start);
}


public int getLimit(){
    String limit = getRequest().getParameter("limit");
    if (StringUtils.isBlank(limit))
        limit = "10";
    return Integer.parseInt(limit);
}


public HttpServletRequest getRequest()


public HttpServletResponse getResponse()


public String noSelected(){
    renderResult(false, "请先选择需要操作的记录");
    return null;
}


public Integer getId(){
    String id = getRequest().getParameter("id");
    if (StringUtils.isNotBlank(id))
        return Integer.parseInt(id);
    return null;
}


public int getRows(){
    String rows = getRequest().getParameter("rows");
    if (StringUtils.isBlank(rows))
        rows = "10";
    return Integer.parseInt(rows);
}


public String failed(String msg){
    renderResult(false, msg);
    return null;
}


public String getString(String name){
    return getRequest().getParameter(name);
}


public void out(String str){
    outJson(str);
}


public Integer getInt(String name){
    String value = getRequest().getParameter(name);
    if (value != null && !"".equals(value))
        return Integer.parseInt(value);
    return null;
}


public void outJson(String json){
    PrintWriter out = null;
    try {
        getResponse().setContentType("text/html;charset=utf-8");
        out = getResponse().getWriter();
        out.print(json);
        out.flush();
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null)
            out.close();
    }
}


public String success(String msg){
    renderResult(true, msg);
    return null;
}


public void outXml(String xml){
    PrintWriter out = null;
    try {
        getResponse().setContentType("text/xml;charset=gbk");
        out = getResponse().getWriter();
        out.print(xml);
        out.flush();
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null)
            out.close();
    }
}


public int getPageNo(){
    String pageNo = getRequest().getParameter("page");
    if (StringUtils.isBlank(pageNo))
        pageNo = "1";
    return Integer.parseInt(pageNo);
}


public String renderResult(boolean success,String msg){
    JSONObject obj = new JSONObject();
    obj.put("success", success);
    obj.put("msg", msg);
    outJson(obj.toJSONString());
    return null;
}


}