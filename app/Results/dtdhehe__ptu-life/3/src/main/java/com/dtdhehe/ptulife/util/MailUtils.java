package com.dtdhehe.ptulife.util;
 import com.dtdhehe.ptulife.enums.ApprovalTypeEnum;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
public class MailUtils {


public String getApprovalHtml(String typeCode,String reason){
    String type = "";
    if (typeCode.equals(ApprovalTypeEnum.LEAVE.getTypeCode())) {
        type = ApprovalTypeEnum.LEAVE.getTypeText();
    }
    if (typeCode.equals(ApprovalTypeEnum.ROOM.getTypeCode())) {
        type = ApprovalTypeEnum.ROOM.getTypeText();
    }
    if (typeCode.equals(ApprovalTypeEnum.LAB.getTypeCode())) {
        type = ApprovalTypeEnum.LAB.getTypeText();
    }
    String htmls = "<html>\n" + "<body>\n" + "<h1>来自校园生活通：</h1>" + "<h3 style='margin:20px 0 0 30px'>您有一个新的<b>" + type + "</b>审核等待处理。</h3>" + "<p style='margin:10px 0 0 20px'><b>申请理由：</b>" + reason + "</p>" + "</body>\n</html>";
    return htmls;
}


public String getValidHtml(String code,HttpServletRequest request){
    StringBuffer requestURL = request.getRequestURL();
    String methodPath = request.getRequestURI();
    // 获得项目访问根路径
    String urlPath = new String(requestURL).replace(methodPath, "");
    String htmls = "<html>\n" + "<body>\n" + "<h1>欢迎注册校园生活通：</h1>" + "<h3 style='margin:20px 0 0 30px'>请点击<a href='" + urlPath + "/ptu/registUserController/validUser?userId=" + code + "'>此处</a>激活账号。</h3>" + "</body>\n</html>";
    return htmls;
}


}