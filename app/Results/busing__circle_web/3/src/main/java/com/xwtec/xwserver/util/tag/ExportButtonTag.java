package com.xwtec.xwserver.util.tag;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.util.CommonUtil;
public class ExportButtonTag extends TagSupport{

 private  String onclick;

 private  String basePath;

 private  String path;

 private  String msg;

 private  String formId;

 private  String modelName;

 private  long serialVersionUID;

 private  Logger log;


public String getFormId(){
    return formId;
}


public String getMsg(){
    return msg;
}


public String getOnclick(){
    return onclick;
}


public void setPath(String path){
    this.path = path;
}


public void setOnclick(String onclick){
    this.onclick = onclick;
}


public void setBasePath(String basePath){
    this.basePath = basePath;
}


public String getModelName(){
    return modelName;
}


public void setFormId(String formId){
    this.formId = formId;
}


public int doEndTag(){
    return EVAL_PAGE;
}


public String getBasePath(){
    return basePath;
}


public int doStartTag(){
    try {
        // 标签体内容
        StringBuffer tagContent = new StringBuffer();
        // 导出按钮内容--start
        tagContent.append("<input type='button' onclick=");
        if (!CommonUtil.isEmpty(this.onclick)) {
            tagContent.append(" onclick=" + this.onclick);
            tagContent.append(";");
        }
        tagContent.append("\"messageVerify('" + this.basePath + "','" + this.msg + "','" + this.path + "','" + this.formId + "','" + this.modelName + "')\"");
        tagContent.append(" class='tabSub' value='导出'/>");
        // 导出按钮内容--end
        // 验证码提交表单--start
        tagContent.append("<div class='wishlistBox' id='wishlistBox1' style='display: none;position:absolute;left:400px;top:200px;'>");
        tagContent.append("<div class='personRigTop persongBgimg' style='height:150px;width:500px;'>");
        tagContent.append("<div class='persongRightTit' style='width:500px;text-align:left;'>消息提示</div>");
        tagContent.append("<div class='persongRigCon'>");
        tagContent.append("<table class='x-form-table'>");
        tagContent.append("<tbody>");
        tagContent.append("<tr class='line'>");
        tagContent.append("<td class='left' width='10%'><label id='message'></label></td>");
        tagContent.append("<td width='90%' align='left'><input type='text' class='normal-input' id='messageVerifyCode' name='messageVerifyCode' style='width: 100px;'/><label id='error'></label></td>");
        tagContent.append("</tr>");
        tagContent.append("<tr>");
        tagContent.append("<td class='left'></td>");
        tagContent.append("<td class='submit' align='left'>");
        tagContent.append("<input id='submitVal' class='tabSub' type='button' value='提交' onclick='checkCode(\"" + this.basePath + "\",\"" + this.msg + "\",\"" + this.path + "\",\"" + this.formId + "\");'/>");
        tagContent.append("<input id='submitVal1' class='tabSub' type='button' value='重新发送' onclick='sendMessage(\"" + this.basePath + "\",\"" + this.modelName + "\");'/>");
        tagContent.append("<input id='closeVal' class='tabSub' type='reset' value='关闭' onclick='closeDiv();'/>");
        tagContent.append("</td></tr></tbody></table></div>");
        tagContent.append("<p class='close' onclick='closeDiv();'></p>");
        tagContent.append("</div></div>");
        // 验证码提交表单--end
        // 脚本--start
        tagContent.append("<script>");
        tagContent.append("var i=31;var t;");
        tagContent.append("function messageVerify(basePath,msg, path, formId,modelName) {");
        tagContent.append("ajax(basePath+'/messageVerify/checkTel.action', function(data){");
        tagContent.append("if(data==1){");
        tagContent.append("$('.wishlistBox').show();");
        tagContent.append("$('#submitVal').show();");
        tagContent.append("$('#submitVal1').hide();");
        tagContent.append("$('#messageVerifyCode').show();");
        tagContent.append("$('#message').html('请输入验证码：');");
        tagContent.append("sendMessage(basePath,modelName);");
        tagContent.append("}");
        tagContent.append("else{");
        tagContent.append("$('.wishlistBox').show();");
        tagContent.append("$('#error').html('用户手机不是江苏移动号！');");
        tagContent.append("$('#submitVal1').hide();");
        tagContent.append("$('#submitVal').hide();");
        tagContent.append("$('#messageVerifyCode').hide();}");
        tagContent.append("});}");
        tagContent.append("function sendMessage(basePath,modelName){");
        tagContent.append("i=31;");
        tagContent.append("$('#submitVal').show();");
        tagContent.append("$('#submitVal1').hide();");
        tagContent.append("show_time();");
        tagContent.append("ajax(basePath+'/messageVerify/sendMessage.action', function(data){");
        tagContent.append("if(data==0){");
        tagContent.append("$('#error').html('验证码发送失败，请重新发送！');");
        tagContent.append("}");
        tagContent.append("}, {'modelName':modelName});}");
        tagContent.append("function checkCode(basePath,msg,path,formId){");
        tagContent.append("var messageVerifyCode=$('#messageVerifyCode').val();");
        tagContent.append("if($.trim(messageVerifyCode)==''){$('#error').html('验证码不为空！');return;}");
        tagContent.append("ajax(basePath+'/messageVerify/checkCode.action', function(data){");
        tagContent.append("if(data==2){");
        tagContent.append("$('#error').html('验证码失效，请重新发送！');}");
        tagContent.append("if(data==1){");
        tagContent.append("formSubmit(msg, path, formId);");
        tagContent.append("closeDiv();}");
        tagContent.append("if(data==0){");
        tagContent.append("clearTimeout(t);");
        tagContent.append("$('#submitVal1').show();");
        tagContent.append("$('#error').html('验证码错误，请重新输入！');}");
        tagContent.append("$('#messageVerifyCode').val('');");
        tagContent.append("}, {'messageVerifyCode':$.trim(messageVerifyCode)});}");
        tagContent.append("function closeDiv(){");
        tagContent.append("i=31;");
        tagContent.append("clearTimeout(t);");
        tagContent.append("$('.wishlistBox').hide();");
        tagContent.append("}");
        tagContent.append("function show_time(){");
        tagContent.append("i--;if(i==-1){ i=31;$('#error').html('');$('#submitVal1').show();return;}$('#error').html('短信验证码已发送'+i);t=setTimeout('show_time()',1000);");
        tagContent.append("}");
        tagContent.append("</script>");
        // 脚本--end
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return SKIP_BODY;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public String getPath(){
    return path;
}


public void setMsg(String msg){
    this.msg = msg;
}


}