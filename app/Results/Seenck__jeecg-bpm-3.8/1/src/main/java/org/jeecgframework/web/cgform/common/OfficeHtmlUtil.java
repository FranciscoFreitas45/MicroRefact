package org.jeecgframework.web.cgform.common;
 import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jodd.util.StringUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.LogUtil;
import org.w3c.dom.Document;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
public class OfficeHtmlUtil {

 private int WORD_HTML;

 private int WORD_TXT;

 private int EXCEL_HTML;

 private  String regEx_style;

 private  String regEx_attr1;

 private  String regEx_attr2;

 private  String regEx_attr3;

 private  String regEx_attr4;

 private  String regEx_attr5;

 private  String regEx_attr7;

 private  String regEx_attr8;

 private  String regEx_attr6;

 private  String regEx_replace;


public void stringToFile(String str,String filename){
    try {
        FileOutputStream fout = new FileOutputStream(filename);
        OutputStreamWriter out = null;
        out = new OutputStreamWriter(fout, "gb2312");
        out.write(str);
        out.close();
    } catch (IOException e4) {
        LogUtil.error(e4.toString());
    }
}


public String getInfo(String tmpFile){
    StringBuffer sbFile = null;
    try {
        FileInputStream fin = new FileInputStream(tmpFile);
        InputStreamReader in = null;
        char[] buffer = new char[4096];
        int len;
        sbFile = new StringBuffer();
        in = new InputStreamReader(fin, "gb2312");
        while ((len = in.read(buffer)) != -1) {
            String s = new String(buffer, 0, len);
            sbFile.append(s);
        }
    } catch (IOException e4) {
        LogUtil.error(e4.toString());
    }
    return sbFile.toString();
}


public String doHtml(String htmlStr){
    java.util.regex.Pattern pattern;
    java.util.regex.Matcher matcher;
    HttpSession session = ContextHolderUtils.getSession();
    String lang = (String) session.getAttribute("lang");
    try {
        pattern = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_attr1, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_attr2, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_attr3, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(" ");
        pattern = Pattern.compile(regEx_attr4, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_attr5, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        // pattern = Pattern.compile(regEx_attr7,Pattern.CASE_INSENSITIVE);
        // matcher = pattern.matcher(htmlStr);
        // htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_attr8, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        // update-start--Author: zhoujf  Date:20170524 for：TASK #2014 【online表单】online表单 模板配置 basePath问题 word模板文件上传问题
        // include
        StringBuilder ls_include = new StringBuilder("");
        // update-begin--Author:zhoujf  Date:20170601 for：【online表单】 日期控件不好使
        ls_include.append("<base href=\"${basePath}/\" />");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/jquery/jquery-1.8.3.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/dataformat.js\"></script>");
        ls_include.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"${basePath}/plug-in/accordion/css/accordion.css\"></link>");
        ls_include.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/easyui/locale/zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/syUtil.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/My97DatePicker/WdatePicker.js\"></script>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/tools/css/metrole/common.css\" type=\"text/css\"></link>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/ace/css/font-awesome.css\" type=\"text/css\"></link>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/lhgDialog/lhgdialog.min.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/layer/layer.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/curdtools.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/easyuiextend.js\"></script>");
        ls_include.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/metrole/main.css\" type=\"text/css\"></link>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/Map.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/datatype_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/Validform/css/metrole/style.css\" type=\"text/css\"/>");
        ls_include.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"/>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/ueditor/ueditor.config.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/ueditor/ueditor.all.js\"></script>");
        // update-end--Author:zhoujf  Date:20170601 for：【online表单】 日期控件不好使
        // update-end--Author: zhoujf  Date:20170524 for：TASK #2014 【online表单】online表单 模板配置 basePath问题 word模板文件上传问题
        ls_include.append("<style>");
        ls_include.append("body{font-size:12px;}");
        ls_include.append("table{border: 1px solid #000000;padding:0; margin:0 auto;border-collapse: collapse;width:100%;align:right;}");
        ls_include.append("td {border: 1px solid #000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");
        ls_include.append("</style>\r\n<body");
        pattern = Pattern.compile("<body", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(ls_include.toString().replace("$", "\\$"));
        /*			pattern = Pattern.compile("<p[^>]+>",Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   
			htmlStr = matcher.replaceAll("");    
			
			pattern = Pattern.compile("<\\/p>",Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   
			htmlStr = matcher.replaceAll("");    
*/
        // 添加<form>语句
        String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">\r\n" + "<input type=\"hidden\" name=\"tableName\" value=\"\\${tableName?if_exists?html}\"\\/>\r\n" + "<input type=\"hidden\" name=\"id\" value=\"\\${id?if_exists?html}\"\\/>\r\n" + "<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\"\\/>\r\n#{jform_hidden_field}<table";
        pattern = Pattern.compile("<table", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(ls_form);
        // 添加</form>语句
        pattern = Pattern.compile("</table>", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("</table>\r\n</form>");
    /*
			//添加input语句
			pattern = Pattern.compile(regEx_attr6,Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   

			StringBuffer sb = new StringBuffer(); 
			String thStr = "";
			String inputStr = "";

			boolean result = matcher.find(); 
			while(result) {
				thStr = matcher.group(1);
//				inputStr = "<input type=\"text\" name=\""+thStr+"\" value=\"\\${"+thStr+"?if_exists?html}\"\\/>";
				inputStr = "";
				if(fieldMap.get(thStr)!=null){
					inputStr = FormHtmlUtil.getFormHTML(fieldMap.get(thStr));
					inputStr +="<span class=\"Validform_checktip\">&nbsp;</span>";
				}
				matcher.appendReplacement(sb, inputStr); 
				result = matcher.find(); 
			} 
			matcher.appendTail(sb); 
			htmlStr = sb.toString(); 
			*/
    } catch (Exception e) {
        LogUtil.error(e.getMessage());
    }
    return htmlStr;
}


public void excelToHtml(String xlsfile,String htmlfile){
    // 启动excel
    ActiveXComponent app = new ActiveXComponent("Excel.Application");
    try {
        app.setProperty("Visible", new Variant(false));
        Dispatch excels = app.getProperty("Workbooks").toDispatch();
        Dispatch excel = Dispatch.invoke(excels, "Open", Dispatch.Method, new Object[] { xlsfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
        Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(EXCEL_HTML) }, new int[1]);
        Variant f = new Variant(false);
        Dispatch.call(excel, "Close", f);
        org.jeecgframework.core.util.LogUtil.info("wordtohtml转换成功");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        app.invoke("Quit", new Variant[] {});
    }
}


public void wordToHtml(String docfile,String htmlfile){
    ActiveXComponent app = null;
    try {
        // 启动word
        app = new ActiveXComponent("Word.Application");
        app.setProperty("Visible", new Variant(false));
        Dispatch docs = app.getProperty("Documents").toDispatch();
        Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { docfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
        Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(WORD_HTML) }, new int[1]);
        Variant f = new Variant(false);
        Dispatch.call(doc, "Close", f);
    } catch (Exception e) {
        // e.printStackTrace();
        throw new Exception("请确认，Word转化组件是否安装！");
    } finally {
        if (app != null) {
            app.invoke("Quit", new Variant[] {});
        }
    }
}


public void main(String[] args){
    try {
        OfficeHtmlUtil wordtohtml = new OfficeHtmlUtil();
        // wordtohtml.wordToHtml("D://jeecg//qjd.doc", "D://jeecg//test.html");
        wordtohtml.WordConverterHtml("D://jeecg//qjd.doc", "D://jeecg//test.html");
        String htmlStr = wordtohtml.getInfo("D://jeecg//test.html");
        htmlStr = wordtohtml.doPoiHtml(htmlStr);
        wordtohtml.stringToFile(htmlStr, "D://jeecg//tt.html");
    } catch (IOException e4) {
    }
}


public String regExReplace(String sourceStr,String oldStr,String newStr){
    java.util.regex.Pattern pattern;
    java.util.regex.Matcher matcher;
    String ls_comStr = oldStr + regEx_replace;
    String ls_returnStr = "";
    try {
        pattern = Pattern.compile(ls_comStr, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(sourceStr);
        ls_returnStr = matcher.replaceAll(newStr);
    } catch (Exception e) {
        LogUtil.error(e.toString());
    }
    return ls_returnStr;
}


public void WordConverterHtml(String docfile,String htmlfile){
    try {
        InputStream input = new FileInputStream(docfile);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray(), "UTF-8");
        stringToFile(content, htmlfile);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public String doPoiHtml(String htmlStr){
    java.util.regex.Pattern pattern;
    java.util.regex.Matcher matcher;
    // 清除HTML标签匹配内容
    String regEx_poi1 = "<meta[^>]+>";
    String regEx_poi2 = "<[\\s]*?(style)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(style)[\\s]*?>";
    String regEx_poi3 = "[\\s]?(class|lang)=([^?(\\s|>)]+)";
    HttpSession session = ContextHolderUtils.getSession();
    String lang = (String) session.getAttribute("lang");
    try {
        pattern = Pattern.compile(regEx_poi1, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_poi2, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile(regEx_poi3, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile("b2\"", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        pattern = Pattern.compile("<tbody>|</tbody>", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("");
        // include
        StringBuilder ls_include = new StringBuilder("");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
        ls_include.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
        ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
        ls_include.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\"></link>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
        ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
        ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
        ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
        ls_include.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:4,btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\").hide();}}}},callback:function(data){var win = frameElement.api.opener;if(data.success==true){frameElement.api.close();win.tip(data.msg);}else{if(data.responseText==''||data.responseText==undefined)$(\"#formobj\").html(data.msg);else $(\"#formobj\").html(data.responseText); return false;}win.reloadTable();}});});</script>");
        ls_include.append("<style>");
        ls_include.append("body{font-size:12px;}");
        ls_include.append("table{border: 1px solid #000000;padding:0; margin:0 auto;border-collapse: collapse;width:100%;align:right;}");
        ls_include.append("td {border: 1px solid #000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");
        ls_include.append("</style>\r\n<body");
        pattern = Pattern.compile("<body", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(ls_include.toString().replace("$", "\\$"));
        /*			pattern = Pattern.compile("<p[^>]+>",Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   
			htmlStr = matcher.replaceAll("");    
			
			pattern = Pattern.compile("<\\/p>",Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   
			htmlStr = matcher.replaceAll("");    
*/
        // 添加<form>语句
        String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">\r\n" + "<input type=\"hidden\" name=\"tableName\" value=\"\\${tableName?if_exists?html}\"\\/>\r\n" + "<input type=\"hidden\" name=\"id\" value=\"\\${id?if_exists?html}\"\\/>\r\n" + "<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\"\\/>\r\n<table";
        pattern = Pattern.compile("<table", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(ls_form);
        // 添加</form>语句
        pattern = Pattern.compile("</table>", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll("</table>\r\n</form>");
    /*
			//添加input语句
			pattern = Pattern.compile(regEx_attr6,Pattern.CASE_INSENSITIVE);   
			matcher = pattern.matcher(htmlStr);   

			StringBuffer sb = new StringBuffer(); 
			String thStr = "";
			String inputStr = "";

			boolean result = matcher.find(); 
			while(result) {
				thStr = matcher.group(1);
//				inputStr = "<input type=\"text\" name=\""+thStr+"\" value=\"\\${"+thStr+"?if_exists?html}\"\\/>";
				inputStr = "";
				if(fieldMap.get(thStr)!=null){
					inputStr = FormHtmlUtil.getFormHTML(fieldMap.get(thStr));
					inputStr +="<span class=\"Validform_checktip\">&nbsp;</span>";
				}
				matcher.appendReplacement(sb, inputStr); 
				result = matcher.find(); 
			} 
			matcher.appendTail(sb); 
			htmlStr = sb.toString(); 
			*/
    } catch (Exception e) {
        LogUtil.error(e.getMessage());
    }
    return htmlStr;
}


}