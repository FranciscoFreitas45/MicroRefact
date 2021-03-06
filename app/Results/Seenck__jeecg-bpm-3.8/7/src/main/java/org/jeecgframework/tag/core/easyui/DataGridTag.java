package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.factory.BootstrapTableComponent;
import org.jeecgframework.tag.core.factory.ComponentFactory;
import org.jeecgframework.tag.vo.easyui.ColumnValue;
import org.jeecgframework.tag.vo.easyui.DataGridColumn;
import org.jeecgframework.tag.vo.easyui.DataGridUrl;
import org.jeecgframework.tag.vo.easyui.OptTypeDirection;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgSubTableVO;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.PublicUtil;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import Interface.CgFormHeadEntity;
import Interface.SystemService;
import DTO.TSType;
import DTO.CgFormFieldServiceI;
import DTO.CgFormFieldServiceI;
import DTO.FreemarkerHelper;
import DTO.TSOperation;
import DTO.CgSubTableVO;
import DTO.CgFormHeadEntity;
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
public class DataGridTag extends TagSupport{

 private  Logger log;

 private  String DATE_FORMATTER;

 private  String DATETIME_FORMATTER;

 protected  String fields;

 protected  String searchFields;

 protected  String name;

 protected  String title;

 protected  String idField;

 protected  boolean treegrid;

 protected  List<DataGridUrl> urlList;

 protected  List<DataGridUrl> toolBarList;

 protected  List<DataGridColumn> columnList;

 protected  List<ColumnValue> columnValueList;

 protected  List<ColumnValue> columnStyleList;

 public  Map<String,Object> map;

 private  String actionUrl;

 public  int allCount;

 public  int curPageNo;

 public  int pageSize;

 public  boolean pagination;

 private  String width;

 private  String height;

 private  boolean checkbox;

 private  boolean showPageList;

 private  boolean openFirstNode;

 private  boolean fit;

 private  boolean fitColumns;

 private  boolean collapsible;

 private  String sortName;

 private  String sortOrder;

 private  boolean showRefresh;

 private  boolean showText;

 private  String style;

 private  String onLoadSuccess;

 private  String onClick;

 private  String onDblClick;

 private  String queryMode;

 private  String entityName;

 private  String rowStyler;

 private  String extendParams;

 private  boolean autoLoadData;

 private  String langArg;

 private  boolean nowrap;

 private  Boolean singleSelect;

 protected  String cssTheme;

 private  boolean isShowSearch;

 private  String treeField;

 private  String btnCls;

 protected  CgFormHeadEntity head;

 protected  Map<String,Object> tableData;

 private  String configId;

 private  boolean isShowSubGrid;

 private  String component;

 private  boolean query;

 private  boolean queryBuilder;

 private  boolean superQuery;

 private  String complexSuperQuery;

 protected  Map<String,String> syscode;

@Autowired
 private  SystemService systemService;

 protected  boolean filter;

 protected  boolean filterBtn;


public String formatUrl(String url){
    MessageFormat formatter = new MessageFormat("");
    String parurlvalue = "";
    if (url.indexOf("&") >= 0) {
        // ??????????????????
        String beforeurl = url.substring(0, url.indexOf("&"));
        // ????????????
        String parurl = url.substring(url.indexOf("&") + 1, url.length());
        String[] pras = parurl.split("&");
        List value = new ArrayList<Object>();
        int j = 0;
        for (int i = 0; i < pras.length; i++) {
            if (pras[i].indexOf("{") >= 0 || pras[i].indexOf("#") >= 0) {
                String field = pras[i].substring(pras[i].indexOf("{") + 1, pras[i].lastIndexOf("}"));
                parurlvalue += "&" + pras[i].replace("{" + field + "}", "{" + j + "}");
                value.add("\"+rec." + field + " +\"");
                j++;
            } else {
                parurlvalue += "&" + pras[i];
            }
        }
        url = formatter.format(beforeurl + parurlvalue, value.toArray());
    }
    return url;
}


public void setPager(StringBuffer sb,String grid){
    sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
    sb.append("beforePageText:\'\'," + "afterPageText:\'/{pages}\',");
    if (showText) {
        sb.append("displayMsg:\'{from}-{to}" + MutiLangUtil.getLang("common.total") + " {total}" + MutiLangUtil.getLang("common.item") + "\',");
    } else {
        sb.append("displayMsg:\'\',");
    }
    if (showPageList == true) {
        sb.append("showPageList:true,");
    } else {
        sb.append("showPageList:false,");
    }
    sb.append("showRefresh:" + showRefresh + "");
    // end getPager
    sb.append("});");
    sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
    sb.append("onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination(\'loading\');$(this).pagination(\'loaded\'); }");
    sb.append("});");
}


public int getCurPageNo(){
    return curPageNo;
}


public void setConfUrl(String url,String title,String message,String exp,String operationCode,String urlStyle,String urlclass,String urlfont,boolean inGroup){
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setUrl(url);
    dataGridUrl.setType(OptTypeDirection.Confirm);
    dataGridUrl.setMessage(message);
    dataGridUrl.setExp(exp);
    // update-begin-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    dataGridUrl.setInGroup(inGroup);
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    // update--begin--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    if (checkBrowerIsNotIE()) {
        dataGridUrl.setUrlStyle(urlStyle);
        // update-begin--Author:zhangjq  Date:20160904 for???[1343???]???UI?????????t:dgConfOpt??????ace????????????
        dataGridUrl.setUrlclass(urlclass);
        dataGridUrl.setUrlfont(urlfont);
    // update-end--Author:zhangjq  Date:20160904 for???[1343???]???UI?????????t:dgConfOpt??????ace????????????
    } else if (StringUtil.isEmpty(urlclass) || !"ace_button".equals(urlclass)) {
        dataGridUrl.setUrlStyle(urlStyle);
    }
    // update--end--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    installOperationCode(dataGridUrl, operationCode, urlList);
}


public Boolean getSingleSelect(){
    return singleSelect;
}


public void getSearchFormInfo(StringBuffer sb){
    // update-begin--Author:xuelin  Date:20170712 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????----------------------
    // ???????????????????????????
    if ("group".equals(getQueryMode())) {
        int i = 0;
        for (DataGridColumn col : columnList) {
            if (col.isQuery()) {
                // update--begin--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
                sb.append("<span style=\"display:-moz-inline-box;display:inline-block;margin-bottom:2px;text-align:justify;\">");
                // update--end--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
                // update--begin--author:scott----- Date:20180613------ for:TASK #2795 ????????????????????????????????????
                if (i == 0) {
                    sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width:74px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + "&nbsp;&nbsp;&nbsp;" + col.getTitle() + "???" + (col.getTitle().length() < 4 ? "&nbsp;&nbsp;&nbsp;" : "") + "</span>");
                } else {
                    sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + col.getTitle() + "???</span>");
                }
                // update--end--author:scott----- Date:20180613------ for:TASK #2795 ????????????????????????????????????
                if ("single".equals(col.getQueryMode())) {
                    if (!StringUtil.isEmpty(col.getReplace())) {
                        sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"120\" style=\"width: 120px\"> ");
                        sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                        String[] test = col.getReplace().split(",");
                        String text = "";
                        String value = "";
                        for (String string : test) {
                            String lang_key = string.split("_")[0];
                            text = MutiLangUtil.getLang(lang_key);
                            value = string.split("_")[1];
                            // update-start--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                            if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(value)) {
                                sb.append("<option value =\"" + value + "\" selected=\"selected\">" + text + "</option>");
                            } else {
                                sb.append("<option value =\"" + value + "\" >" + text + "</option>");
                            }
                        // update-end--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                        }
                        sb.append("</select>");
                    } else if (!StringUtil.isEmpty(col.getDictionary())) {
                        // -- update-begin--Author:zhoujf  Date:20180330 for???datagrid???popup?????????????????????????????????--
                        // -- update-begin--Author:zhoujf  Date:20180409 for???datagrid???popup?????????????????????????????????(????????????)--
                        if (col.getDictionary().contains(",") && col.isPopup()) {
                            String[] dic = col.getDictionary().split(",");
                            // -- update-begin--Author:gj_shaojc  Date:20180314 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                            // String sql;
                            // if(!StringUtil.isEmpty(col.getDictCondition())){
                            // sql = "select " + dic[1] + " as field," + dic[2]+ " as text from " + dic[0]+" "+col.getDictCondition();
                            // }else{
                            // sql = "select " + dic[1] + " as field," + dic[2]+ " as text from " + dic[0];
                            // }
                            // -- update-end--Author:gj_shaojc  Date:20180314 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                            // System.out.println(dic[0]+"--"+dic[1]+"--"+dic[2]);
                            // <input type="text" name="order_code"  style="width: 100px"  class="searchbox-inputtext" value="" onClick="inputClick(this,'account','user_msg');" />
                            // update-start--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                            if (col.getDefaultVal() != null && !col.getDefaultVal().trim().equals("")) {
                                sb.append("<input type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\" style=\"width: 120px\" class=\"searchbox-inputtext\" value=\"\" onClick=\"popupClick(this,'" + dic[2].replaceAll("@", ",") + "','" + dic[1].replaceAll("@", ",") + "','" + dic[0] + "');\" value=\"" + col.getDefaultVal() + "\"/> ");
                            } else {
                                sb.append("<input type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\" style=\"width: 120px\" class=\"searchbox-inputtext\" value=\"\" onClick=\"popupClick(this,'" + dic[2].replaceAll("@", ",") + "','" + dic[1].replaceAll("@", ",") + "','" + dic[0] + "');\" /> ");
                            }
                        // update-end--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                        // -- update-end--Author:zhoujf  Date:20180409 for???datagrid???popup?????????????????????????????????(????????????)--
                        // -- update-end--Author:zhoujf  Date:20180330 for???datagrid???popup?????????????????????????????????--
                        } else if (col.getDictionary().contains(",") && (!col.isPopup())) {
                            String[] dic = col.getDictionary().split(",");
                            // -- update-begin--Author:gj_shaojc  Date:20180314 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                            String sql;
                            if (!StringUtil.isEmpty(col.getDictCondition())) {
                                sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0] + " " + col.getDictCondition();
                            } else {
                                sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
                            }
                            // -- update-end--Author:gj_shaojc  Date:20180314 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                            systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                            List<Map<String, Object>> list = systemService.findForJdbc(sql);
                            String showMode = col.getShowMode();
                            if (null != showMode && "radio".equals(showMode)) {
                                String field = col.getField().replaceAll("_", "\\.");
                                sb.append("<input type=\"hidden\"  name=\"" + field + "\" id=\"" + field + "_radio\"/>");
                                for (Map<String, Object> map : list) {
                                    // 
                                    // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                    if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(map.get("field"))) {
                                        sb.append(" <input type=\"radio\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + map.get("field") + "');\" value=\"" + map.get("field") + "\" checked=\"checked\" />");
                                        sb.append(" <script type=\"text/javascript\">");
                                        sb.append("  	$('#" + field + "_radio').val(\"" + map.get("field") + "\");");
                                        sb.append(" </script>");
                                    } else {
                                        sb.append(" <input type=\"radio\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + map.get("field") + "');\" value=\"" + map.get("field") + "\" />");
                                    }
                                    // update-end--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                    sb.append(map.get("text"));
                                }
                            } else if (null != showMode && "checkbox".equals(showMode)) {
                                String field = col.getField().replaceAll("_", "\\.");
                                sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_checkbox\" value=\"\" />");
                                for (Map<String, Object> map : list) {
                                    // 
                                    if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(map.get("field"))) {
                                        sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + map.get("field") + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + map.get("field") + ",',''));}\" value=\"" + map.get("field") + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" checked=\"checked\" />");
                                        sb.append(" <script type=\"text/javascript\">");
                                        sb.append("  	$(\"#" + field + "_checkbox\").val($(\"#" + field + "_checkbox\").val()+," + map.get("field") + ",);");
                                        sb.append(" </script>");
                                    } else {
                                        sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + map.get("field") + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + map.get("field") + ",',''));}\" value=\"" + map.get("field") + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" />");
                                    }
                                    sb.append(map.get("text"));
                                }
                            } else {
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"120\" style=\"width: 120px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                                for (Map<String, Object> map : list) {
                                    // update-start--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                                    if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(map.get("field"))) {
                                        sb.append(" <option value=\"" + map.get("field") + "\" selected=\"selected\">");
                                    } else {
                                        sb.append(" <option value=\"" + map.get("field") + "\">");
                                    }
                                    // update-end--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                                    sb.append(map.get("text"));
                                    sb.append(" </option>");
                                }
                                sb.append("</select>");
                            }
                        } else {
                            List<TSType> types = ResourceUtil.getCacheTypes(col.getDictionary().toLowerCase());
                            String showMode = col.getShowMode();
                            if (null != showMode && "radio".equals(showMode)) {
                                if (types != null) {
                                    String field = col.getField().replaceAll("_", "\\.");
                                    sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_radio\"/>");
                                    for (TSType type : types) {
                                        String typeCode = type.getTypecode();
                                        // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                        if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(typeCode)) {
                                            sb.append(" <input type=\"radio\" value=\"" + typeCode + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:#('#" + field + "_radio').val('" + typeCode + "');\" checked=\"checked\" />");
                                            sb.append(" <script type=\"text/javascript\">");
                                            sb.append("  $('#" + field + "_radio').val('" + typeCode + "');");
                                            sb.append(" </script>");
                                        } else {
                                            sb.append(" <input type=\"radio\" value=\"" + typeCode + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + typeCode + "');\" />");
                                        }
                                        // update-end--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                        sb.append(MutiLangUtil.getLang(type.getTypename()));
                                    }
                                }
                            } else if (null != showMode && "checkbox".equals(showMode)) {
                                if (types != null) {
                                    String field = col.getField().replaceAll("_", "\\.");
                                    sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_checkbox\" value=\"\" />");
                                    for (TSType type : types) {
                                        String typeCode = type.getTypecode();
                                        if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(typeCode)) {
                                            sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + typeCode + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + typeCode + ",',''));}\" value=\"" + typeCode + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" checked=\"checked\" />");
                                            sb.append(" <script type=\"text/javascript\">");
                                            sb.append("  	$(\"#" + field + "_checkbox\").val($(\"#" + field + "_checkbox\").val()+," + typeCode + ",);");
                                            sb.append(" </script>");
                                        } else {
                                            sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + typeCode + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + typeCode + ",',''));}\" value=\"" + typeCode + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" />");
                                        }
                                        sb.append(MutiLangUtil.getLang(type.getTypename()));
                                    }
                                }
                            } else {
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"120\" style=\"width: 120px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                                if (types != null) {
                                    for (TSType type : types) {
                                        // update-start--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                                        if (col.getDefaultVal() != null && col.getDefaultVal().trim().equals(type.getTypecode())) {
                                            sb.append(" <option value=\"" + type.getTypecode() + "\" selected=\"selected\">");
                                        } else {
                                            sb.append(" <option value=\"" + type.getTypecode() + "\">");
                                        }
                                        // update-end--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                                        sb.append(MutiLangUtil.getLang(type.getTypename()));
                                        sb.append(" </option>");
                                    }
                                }
                                sb.append("</select>");
                            }
                        }
                    } else if (col.isAutocomplete()) {
                        sb.append(getAutoSpan(col.getField().replaceAll("_", "\\."), extendAttribute(col.getExtend())));
                    } else {
                        // update-begin Robin 20140426 for:???????????? ??????IE,FF
                        // update-start--Author:longjb  Date:20150323 for?????????class????????????????????????
                        // update-start--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                        // update-begin--Author:scott  Date:20170321 for???Formatter????????????????????????????????????????????????js------
                        sb.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  " + extendAttribute(col.getExtend()) + " ");
                        if (this.DATE_FORMATTER.equals(col.getFormatter())) {
                            sb.append(" style=\"width: 120px\" class=\"Wdate\" onClick=\"WdatePicker()\" ");
                        } else if (this.DATETIME_FORMATTER.equals(col.getFormatter())) {
                            sb.append(" style=\"width: 160px\" class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
                        } else {
                            sb.append(" style=\"width: 120px\" class=\"inuptxt\" ");
                        }
                        if (oConvertUtils.isNotEmpty(col.getDefaultVal())) {
                            sb.append(" value=\"" + col.getDefaultVal() + "\" ");
                        }
                        sb.append(" />");
                    // update-end--Author:scott  Date:20170321 for???Formatter????????????????????????????????????????????????js------
                    // update-end--Author:chenjin  Date:20160718 for???????????????<t:dgCol ????????????defaultVal=""
                    // sb.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\""+col.getField().replaceAll("_","\\.")+"\"  "+extendAttribute(col.getExtend())+"  class=\"inuptxt\" style=\"width: 100px\" value="+col.getDefaultVal()==null?"":"\""+col.getDefaultVal()+"\""+"/>");
                    }
                } else if ("group".equals(col.getQueryMode())) {
                    // update-begin--Author:scott  Date:20170321 for???Formatter????????????????????????????????????????????????js------
                    if (this.DATE_FORMATTER.equals(col.getFormatter())) {
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 100px\" " + extendAttribute(col.getExtend()) + " class=\"Wdate\" onClick=\"WdatePicker()\"/>");
                        sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 100px\" " + extendAttribute(col.getExtend()) + " class=\"Wdate\" onClick=\"WdatePicker()\"/>");
                    } else if (this.DATETIME_FORMATTER.equals(col.getFormatter())) {
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 140px\" " + extendAttribute(col.getExtend()) + " class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"/>");
                        sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 140px\" " + extendAttribute(col.getExtend()) + " class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"/>");
                    } else {
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 100px\" " + extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                        sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 100px\" " + extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                    }
                // update-end--Author:scott  Date:20170321 for???Formatter????????????????????????????????????????????????js------
                // update-end--Author:longjb  Date:20150323 for?????????class????????????????????????
                }
                sb.append("</span>");
                i++;
            }
        }
    }
// update-end--Author:xuelin  Date:20170712 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????----------------------
}


public void setCollapsible(boolean collapsible){
    this.collapsible = collapsible;
}


public boolean isFilterBtn(){
    return filterBtn;
}


public boolean getSuperQuery(){
    return superQuery;
}


public StringBuffer jqGrid(){
    StringBuffer sb = new StringBuffer();
    // update--begin--author:zhoujf date:20170427 for:??????jqgrid??????????????????
    // update--begin--author:zhoujf date:20180428 for:TASK #2677 ???H+???????????????hplus ???????????????jar????????? ???????????????
    sb.append("<link href=\"plug-in/hplus/css/bootstrap.min.css?v=3.3.6\" rel=\"stylesheet\">");
    sb.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"plug-in/hplus/css/plugins/jqgrid/ui.jqgrid.css\">");
    sb.append("<script src=\"plug-in/hplus/js/jquery.min.js\"></script>");
    sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
    sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
    sb.append("<script src=\"plug-in/hplus/js/bootstrap.min.js\"></script>");
    sb.append("<script src=\"plug-in/hplus/js/plugins/peity/jquery.peity.min.js\"></script>");
    sb.append("<script src=\"plug-in/hplus/js/plugins/jqgrid/i18n/grid.locale-cn.js\"></script>");
    sb.append("<script src=\"plug-in/hplus/js/plugins/jqgrid/jquery.jqGrid.min.js\"></script>");
    // update--end--author:zhoujf date:20180428 for:TASK #2677 ???H+???????????????hplus ???????????????jar????????? ???????????????
    // update--end--author:zhoujf date:20170427 for:??????jqgrid??????????????????
    sb.append("<script src=\"plug-in/tools/datagrid_2_jqgrid.js\"></script>");
    sb.append("<style>");
    sb.append("#t_" + name + "{border-bottom:1px solid #ddd;}");
    sb.append("#t_" + name + " .btn{margin-right:10px;}");
    sb.append(".search_div{padding:10px;}");
    sb.append(".tool_bar_div{padding:10px;}");
    sb.append("</style>");
    sb.append("<table id=\"" + name + "\"></table>");
    sb.append("<div id=\"gridPager\"></div>");
    sb.append("<script type=\"text/javascript\">");
    sb.append("$(document).ready(function() {");
    sb.append(" $.jgrid.defaults.styleUI=\"Bootstrap\";");
    sb.append("$('#" + name + "').jqGrid({");
    sb.append("url:'" + actionUrl + "&dataStyle=jqgrid&field=" + fields + "',");
    sb.append("datatype:\"json\",");
    sb.append("mtype:\"POST\",");
    sb.append("height:'auto',");
    sb.append("autowidth:true,");
    sb.append("shrinkToFit: true,");
    sb.append("multiselect: true,");
    sb.append("toolbar:[true,'top'],");
    StringBuffer colNameBuffer = new StringBuffer();
    StringBuffer colModelBuffer = new StringBuffer();
    for (DataGridColumn column : columnList) {
        colNameBuffer.append("'");
        colNameBuffer.append(column.getTitle());
        colNameBuffer.append("',");
        if ("opt".equals(column.getField())) {
            colModelBuffer.append("{name:'");
            colModelBuffer.append(column.getField());
            colModelBuffer.append("',index:'");
            colModelBuffer.append(column.getField());
            colModelBuffer.append("',width:'");
            colModelBuffer.append(column.getWidth());
            colModelBuffer.append("',align:'");
            colModelBuffer.append(column.getAlign());
            colModelBuffer.append("' ");
            colModelBuffer.append(",hidden:");
            colModelBuffer.append(column.isHidden());
            colModelBuffer.append(",formatter:currencyFormatter");
            colModelBuffer.append("},");
        } else {
            colModelBuffer.append("{name:'");
            colModelBuffer.append(column.getField());
            colModelBuffer.append("',index:'");
            colModelBuffer.append(column.getField());
            colModelBuffer.append("',width:'");
            colModelBuffer.append(column.getWidth());
            colModelBuffer.append("',align:'");
            colModelBuffer.append(column.getAlign());
            colModelBuffer.append("' ");
            if (oConvertUtils.isNotEmpty(column.getFormatter())) {
                if ("yyyy-MM-dd".equals(column.getFormatter())) {
                    colModelBuffer.append(",formatter:'date'");
                } else {
                    colModelBuffer.append(",formatter:");
                    colModelBuffer.append(column.getFormatter());
                }
            // colModelBuffer.append(date);
            // colModelBuffer.append("' ");
            }
            if (oConvertUtils.isNotEmpty(column.getReplace())) {
                colModelBuffer.append(",formatter:replaceFormatter");
                colModelBuffer.append(",formatoptions:{replace:");
                String[] replaceArray = column.getReplace().split(",");
                StringBuffer replaceBuffer = new StringBuffer();
                replaceBuffer.append("{");
                if (replaceArray.length > 0) {
                    String text = "";
                    String value = "";
                    for (String replaceOri : replaceArray) {
                        String lang_key = replaceOri.split("_")[0];
                        text = MutiLangUtil.getLang(lang_key);
                        value = replaceOri.split("_")[1];
                        replaceBuffer.append("'");
                        replaceBuffer.append(value);
                        replaceBuffer.append("':'");
                        replaceBuffer.append(text);
                        replaceBuffer.append("',");
                    }
                }
                replaceBuffer.append("}");
                colModelBuffer.append(replaceBuffer.toString());
                colModelBuffer.append("}");
            }
            if (oConvertUtils.isNotEmpty(column.getFormatterjs())) {
                colModelBuffer.append(",formatter:" + column.getFormatterjs());
            }
            colModelBuffer.append(",hidden:");
            colModelBuffer.append(column.isHidden());
            colModelBuffer.append("},");
        }
    }
    String colNames = colNameBuffer.toString();
    colNames = colNames.substring(0, colNames.length() - 1);
    String colModels = colModelBuffer.toString();
    colModels = colModels.substring(0, colModels.length() - 1);
    sb.append("colNames:[");
    sb.append(colNames);
    sb.append("], colModel:[");
    sb.append(colModels);
    sb.append("],");
    sb.append("rownumbers:true,");
    sb.append("viewrecords: true,");
    sb.append("rowNum:" + pageSize + ",");
    sb.append("rowList:[" + pageSize + "," + 2 * pageSize + "," + 3 * pageSize + "],");
    // sb.append("jsonReader:{");
    // sb.append("id: \"blackId\",");
    // sb.append("repeatitems : false},");
    sb.append("pager:$('#gridPager')");
    sb.append(",caption:'");
    sb.append(title);
    sb.append("'});");
    // ?????????????????????
    // sb.append("$(\"#"+name+"\").setGridWidth($(window).width()*0.99);");
    // ?????????????????????????????????
    sb.append("$('#t_" + name + "').append('");
    if (hasQueryColum(columnList)) {
        sb.append("<div id=\"" + name + "tb\" class=\"search_div row\">");
        sb.append("<div name=\"searchColums\" class=\"search-content\"><form name=\"" + name + "Form\" id=\"" + name + "Form\"></form></div><div class=\"col-sm-1 pull-right\">");
        sb.append("<button class=\"btn btn-success\" type=\"button\" onclick=\"javascript:" + name + "search();\"><span><i class=\"fa fa-search\"></i>??????</span></button>");
        sb.append("</div></div>");
    }
    sb.append("<div class=\"tool_bar_div bg-info\"></div>");
    sb.append("');");
    // update-begin--Author:xuelin Date:20170712 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????--------------------
    // ??????????????????
    if (hasQueryColum(columnList) && !columnList.isEmpty()) {
        for (DataGridColumn column : columnList) {
            if (column.isQuery()) {
                sb.append("$('#t_" + name + " .search-content form').append('");
                sb.append("<label style=\"margin-right:10px;margin-left:10px;\">");
                sb.append(column.getTitle());
                sb.append("</label>");
                String dictionary = column.getDictionary();
                if (oConvertUtils.isNotEmpty(dictionary)) {
                    // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    String showMode = column.getShowMode();
                    if (showMode != null && "radio".equals(showMode)) {
                        if (dictionary.indexOf(",") > -1) {
                            // ??????????????????
                            try {
                                String[] dictionaryArray = dictionary.split(",");
                                if (dictionaryArray.length == 3) {
                                    String field = column.getField().replaceAll("_", "\\.");
                                    sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_radio\"/>");
                                    String sql = "select " + dictionaryArray[1] + "," + dictionaryArray[2] + " from " + dictionaryArray[0];
                                    List<Map<String, Object>> dictionaryList = systemService.findForJdbc(sql);
                                    if (dictionaryList != null && !dictionaryList.isEmpty()) {
                                        for (Map<String, Object> map : dictionaryList) {
                                            if (map.containsKey(dictionaryArray[1]) && map.containsKey(dictionaryArray[2])) {
                                                // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                                sb.append(" <input type=\"radio\" value=\"" + map.get(dictionaryArray[1]) + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + map.get(dictionaryArray[1]) + "');\" />");
                                                // update-end--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                                sb.append(map.get(dictionaryArray[2]));
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                            // TODO: ??????????????????
                            }
                        } else {
                            // ???????????????
                            List<TSType> typeList = ResourceUtil.getCacheTypes(dictionary.toLowerCase());
                            if (typeList != null && !typeList.isEmpty()) {
                                String field = column.getField().replaceAll("_", "\\.");
                                sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_radio\"/>");
                                for (TSType type : typeList) {
                                    // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                    sb.append(" <input type=\"radio\" value=\"" + type.getTypecode() + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + type.getTypecode() + "');\" />");
                                    // update-end--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                                    sb.append(MutiLangUtil.getLang(type.getTypename()));
                                }
                            }
                        }
                    } else if (showMode != null && "checkbox".equals(showMode)) {
                        if (dictionary.indexOf(",") > -1) {
                            // ??????????????????
                            try {
                                String[] dictionaryArray = dictionary.split(",");
                                if (dictionaryArray.length == 3) {
                                    String field = column.getField().replaceAll("_", "\\.");
                                    sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_checkbox\" value=\"\" />");
                                    String sql = "select " + dictionaryArray[1] + "," + dictionaryArray[2] + " from " + dictionaryArray[0];
                                    List<Map<String, Object>> dictionaryList = systemService.findForJdbc(sql);
                                    if (dictionaryList != null && !dictionaryList.isEmpty()) {
                                        for (Map<String, Object> map : dictionaryList) {
                                            if (map.containsKey(dictionaryArray[1]) && map.containsKey(dictionaryArray[2])) {
                                                String value = map.get(dictionaryArray[1]).toString();
                                                sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + value + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + value + ",',''));}\" value=\"" + value + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" />");
                                                sb.append(map.get(dictionaryArray[2]));
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                            // TODO: ??????????????????
                            }
                        } else {
                            // ???????????????
                            List<TSType> typeList = ResourceUtil.getCacheTypes(dictionary.toLowerCase());
                            if (typeList != null && !typeList.isEmpty()) {
                                String field = column.getField().replaceAll("_", "\\.");
                                sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_checkbox\" value=\"\" />");
                                for (TSType type : typeList) {
                                    String typeCode = type.getTypecode();
                                    sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + typeCode + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + typeCode + ",',''));}\" value=\"" + typeCode + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" />");
                                    sb.append(MutiLangUtil.getLang(type.getTypename()));
                                }
                            }
                        }
                    } else {
                        sb.append("<select  name=\"");
                        sb.append(column.getField());
                        sb.append("\">");
                        sb.append("<option value=\"\"></option>");
                        if (dictionary.indexOf(",") > -1) {
                            // ??????????????????
                            try {
                                String[] dictionaryArray = dictionary.split(",");
                                if (dictionaryArray.length == 3) {
                                    String sql = "select " + dictionaryArray[1] + "," + dictionaryArray[2] + " from " + dictionaryArray[0];
                                    List<Map<String, Object>> dictionaryList = systemService.findForJdbc(sql);
                                    if (dictionaryList != null && !dictionaryList.isEmpty()) {
                                        for (Map<String, Object> map : dictionaryList) {
                                            if (map.containsKey(dictionaryArray[1]) && map.containsKey(dictionaryArray[2])) {
                                                sb.append("<option value=\"");
                                                sb.append(map.get(dictionaryArray[1]));
                                                sb.append("\">");
                                                sb.append(map.get(dictionaryArray[2]));
                                                sb.append("</option>");
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                            // TODO: ??????????????????
                            }
                        } else {
                            // ???????????????
                            List<TSType> typeList = ResourceUtil.getCacheTypes(dictionary.toLowerCase());
                            if (typeList != null && !typeList.isEmpty()) {
                                for (TSType type : typeList) {
                                    sb.append("<option value=\"");
                                    sb.append(type.getTypecode());
                                    sb.append("\">");
                                    sb.append(MutiLangUtil.getLang(type.getTypename()));
                                    sb.append("</option>");
                                }
                            }
                        }
                        sb.append("</select>");
                    }
                } else if (oConvertUtils.isNotEmpty(column.getReplace())) {
                    String showMode = column.getShowMode();
                    if (showMode != null && "radio".equals(showMode)) {
                        String field = column.getField().replaceAll("_", "\\.");
                        sb.append("<input type=\"hidden\"  name=\"" + field + "\" id=\"" + field + "_radio\"/>");
                        String[] test = column.getReplace().split(",");
                        String text = "";
                        String value = "";
                        for (String string : test) {
                            String lang_key = string.split("_")[0];
                            text = MutiLangUtil.getLang(lang_key);
                            value = string.split("_")[1];
                            // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                            if (column.getDefaultVal() != null && column.getDefaultVal().trim().equals(value)) {
                                sb.append(" <input type=\"radio\" value=\"" + value + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + value + "');\" checked=\"checked\" />" + text);
                                sb.append(" <script type=\"text/javascript\">");
                                sb.append("  $('#" + field + "_radio').val('" + value + "');");
                                sb.append(" </script>");
                            } else {
                                sb.append(" <input type=\"radio\" value=\"" + value + "\" name=\"" + field + "_radio\" onclick=\"javascrpt:$('#" + field + "_radio').val('" + value + "');\" />" + text);
                            }
                        // update-end--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
                        }
                    } else if (showMode != null && "checkbox".equals(showMode)) {
                        String field = column.getField().replaceAll("_", "\\.");
                        sb.append("<input type=\"hidden\" name=\"" + field + "\" id=\"" + field + "_checkbox\" value=\"\" />");
                        String[] test = column.getReplace().split(",");
                        String text = "";
                        String value = "";
                        for (String string : test) {
                            String lang_key = string.split("_")[0];
                            text = MutiLangUtil.getLang(lang_key);
                            value = string.split("_")[1];
                            if (column.getDefaultVal() != null && column.getDefaultVal().trim().equals(value)) {
                                sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + value + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + value + ",',''));}\" value=\"" + value + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" checked=\"checked\" />" + text);
                                sb.append(" <script type=\"text/javascript\">");
                                sb.append("  	$(\"#" + field + "_checkbox\").val($(\"#" + field + "_checkbox\").val()+," + value + ",);");
                                sb.append(" </script>");
                            } else {
                                sb.append(" <input type=\"checkbox\" onclick=\"javascript:if(this.checked)$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val()+'," + value + ",');else{$('#" + field + "_checkbox').val($('#" + field + "_checkbox').val().replace('," + value + ",',''));}\" value=\"" + value + "\" name=\"" + field + "_checkbox\" class=\"" + field + "_checkbox\" />" + text);
                            }
                        }
                    } else {
                        sb.append("<select  name=\"" + column.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                        sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                        String[] test = column.getReplace().split(",");
                        String text = "";
                        String value = "";
                        for (String string : test) {
                            String lang_key = string.split("_")[0];
                            text = MutiLangUtil.getLang(lang_key);
                            value = string.split("_")[1];
                            if (column.getDefaultVal() != null && column.getDefaultVal().trim().equals(value)) {
                                sb.append("<option value =\"" + value + "\" selected=\"selected\">" + text + "</option>");
                            } else {
                                sb.append("<option value =\"" + value + "\" >" + text + "</option>");
                            }
                        }
                        sb.append("</select>");
                    }
                } else {
                    sb.append("<input  onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + column.getField().replaceAll("_", "\\.") + "\"  " + extendAttribute(column.getExtend()) + " ");
                    if (this.DATE_FORMATTER.equals(column.getFormatter())) {
                        sb.append(" style=\"width: 160px\" class=\"Wdate\" onClick=\"WdatePicker()\" ");
                    } else if (this.DATETIME_FORMATTER.equals(column.getFormatter())) {
                        sb.append(" style=\"width: 160px\" class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
                    } else {
                        sb.append(" style=\"width: 120px\" class=\"inuptxt\" ");
                    }
                    if (oConvertUtils.isNotEmpty(column.getDefaultVal())) {
                        sb.append(" value=\"" + column.getDefaultVal() + "\" ");
                    }
                    sb.append(" />");
                }
                sb.append("');");
            }
        }
    }
    // update-end--Author:xuelin  Date:20170712 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????----------------------
    // ????????????????????????
    if (toolBarList.size() > 0) {
        for (DataGridUrl toolBar : toolBarList) {
            sb.append("$('#t_" + name + " .tool_bar_div').append('");
            sb.append("<button class=\"btn btn-success\"");
            // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
            if (StringUtil.isNotEmpty(toolBar.getId())) {
                sb.append(" id=\"");
                sb.append(toolBar.getId());
                sb.append("\"");
            }
            // update--end--author:zhangjiaqiang date:20170620 for:????????????ID
            sb.append(" onclick=\"");
            sb.append(toolBar.getFunname());
            sb.append("(\\'");
            sb.append(toolBar.getTitle());
            sb.append("\\',\\'");
            sb.append(toolBar.getUrl());
            sb.append("\\',\\'");
            sb.append(name);
            sb.append("\\',");
            String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
            String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
            sb.append(width + "," + height + ")");
            sb.append("\" >");
            // ?????????????????????
            String toolBarIcon = toolBar.getIcon();
            if (oConvertUtils.isNotEmpty(toolBarIcon)) {
                if (toolBarIcon.equals("icon-add")) {
                    sb.append("<i class=\"fa fa-plus\"></i>");
                } else if (toolBarIcon.equals("icon-edit")) {
                    sb.append("<i class=\"fa fa-edit\"></i>");
                } else if (toolBarIcon.equals("icon-put")) {
                    sb.append("<i class=\"fa fa-download\"></i>");
                } else if (toolBarIcon.equals("icon-putout")) {
                    sb.append("<i class=\"fa fa-upload\"></i>");
                } else if (toolBarIcon.equals("icon-remove")) {
                    sb.append("<i class=\"fa fa-trash-o\"></i>");
                } else if (toolBarIcon.equals("icon-search")) {
                    sb.append("<i class=\"fa fa-search\"></i>");
                } else {
                    sb.append("<i class=\"fa " + toolBarIcon + "\"></i>");
                }
            }
            sb.append(toolBar.getTitle());
            sb.append("</button>");
            sb.append("');");
        }
    }
    // ????????????????????????
    // sb.append("$('#"+name+"').navGrid('#gridPager',{edit:false,add:false,del:false,search:false})");
    // if(toolBarList.size() > 0){
    // for (DataGridUrl toolBar : toolBarList) {
    // sb.append(".navButtonAdd('#gridPager',{");
    // sb.append("caption:'");
    // sb.append(toolBar.getTitle());
    // sb.append("'");
    // sb.append(",buttonicon:'");
    // sb.append(toolBar.getIcon());
    // sb.append("'");
    // sb.append(",onClickButton:");
    // sb.append("function(){");
    // if(oConvertUtils.isNotEmpty(toolBar.getOnclick())){
    // sb.append(toolBar.getOnclick());
    // }else{
    // sb.append(toolBar.getFunname());
    // sb.append("('");
    // sb.append(toolBar.getTitle());
    // sb.append("','");
    // sb.append(toolBar.getUrl());
    // sb.append("','");
    // sb.append(name);
    // sb.append("',");
    // String width = toolBar.getWidth().contains("%")?"'"+toolBar.getWidth()+"'":toolBar.getWidth();
    // String height = toolBar.getHeight().contains("%")?"'"+toolBar.getHeight()+"'":toolBar.getHeight();
    // sb.append(width+","+height+")");
    // }
    // sb.append("}");
    // sb.append(",position:'last'");
    // sb.append("})");
    // }
    // }
    sb.append("});");
    sb.append("function currencyFormatter(cellvalue, options, rec){ ");
    sb.append("var index = options.pos;");
    StringBuffer optSb = new StringBuffer();
    this.getOptUrl(optSb);
    sb.append(optSb.toString());
    sb.append("}");
    sb.append("function reloadTable(){");
    sb.append("try{");
    sb.append("	$(\'#\'+gridname).trigger(\"reloadGrid\");");
    sb.append("}catch(ex){}");
    sb.append("}");
    // ????????????
    sb.append("function replaceFormatter(cellvalue,options,rec){");
    sb.append("var formatterOptions = options.colModel.formatoptions;");
    sb.append("var replace = formatterOptions.replace;");
    sb.append("return replace[cellvalue];");
    sb.append("}");
    // ????????????
    sb.append("function EnterPress(e){");
    sb.append("var e = e || window.event;");
    sb.append("if(e.keyCode == 13){ ");
    sb.append(name + "search();");
    sb.append("}}");
    // ????????????
    sb.append("function " + name + "search(){");
    sb.append("try { if(! $(\"#" + name + "Form\").Validform({tiptype:3}).check()){return false;} } catch (e){}");
    sb.append("var queryParams = '';");
    sb.append("$(\'#" + name + "tb\').find('*').each(function(){ if($(this).attr('name') != undefined && $(this).val() != ''){queryParams += \"&\" + $(this).attr('name') + \"=\" + $(this).val();}});");
    sb.append("console.log(queryParams);");
    sb.append("var url = '" + actionUrl + "&dataStyle=jqgrid&field=" + searchFields + "' + queryParams;");
    sb.append("console.log(url);");
    sb.append("$(\'#" + name + "\').jqGrid('setGridParam',{url:url,page:1}).trigger(\"reloadGrid\");" + "}");
    sb.append("</script>");
    return sb;
}


public boolean hasGroup(){
    boolean flag = false;
    for (DataGridColumn column : columnList) {
        if (StringUtil.isNotEmpty(column.getField()) && column.getField().equals("opt")) {
            if (column.isOptsMenu()) {
                return false;
            }
        }
    }
    for (DataGridUrl dataGridUrl : urlList) {
        if (dataGridUrl.isInGroup()) {
            flag = true;
            break;
        }
    }
    return flag;
}


public void setFitColumns(boolean fitColumns){
    this.fitColumns = fitColumns;
}


public int getAllCount(){
    return allCount;
}


public void setQuery(boolean query){
    this.query = query;
}


public void setPagination(boolean pagination){
    this.pagination = pagination;
}


public List<DataGridUrl> getToolBarList(){
    return toolBarList;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public void getField(StringBuffer sb,int frozen){
    // ?????????
    if (checkbox && frozen == 0) {
        sb.append("{field:\'ck\',checkbox:\'true\'},");
    }
    int i = 0;
    for (DataGridColumn column : columnList) {
        i++;
        if ((column.isFrozenColumn() && frozen == 0) || (!column.isFrozenColumn() && frozen == 1)) {
            String field;
            if (treegrid) {
                // update-begin--Author:zhoujf  Date:20170323 for???TASK #1739 ????????????t:dgCol?????????treefield?????????????????????????????????
                field = column.getTreefield();
                if (StringUtils.isEmpty(field)) {
                    field = column.getField();
                }
            // update-end--Author:zhoujf  Date:20170323 for???TASK #1739 ????????????t:dgCol?????????treefield?????????????????????????????????
            } else {
                field = column.getField();
            }
            // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
            sb.append("{");
            if (StringUtil.isNotEmpty(field)) {
                sb.append("field:\'" + field + "\',");
            }
            sb.append("title:\'" + column.getTitle() + "\'");
            // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
            if (column.getWidth() != null) {
                sb.append(",width:" + column.getWidth());
            }
            if (column.getAlign() != null) {
                sb.append(",align:\'" + column.getAlign() + "\'");
            }
            if (StringUtil.isNotEmpty(column.getRowspan())) {
                sb.append(",rowspan:" + column.getRowspan());
            }
            if (StringUtils.isNotEmpty(column.getExtendParams())) {
                // update-start--Author:chenjin  Date:20160811 for???TASK #1259 ?????????bug?????????bug?????????????????????????????????????????????,???????????????????????????combobox
                // ????????????????????????????????????editor:'combobox'?????????
                StringBuffer comboboxStr = new StringBuffer();
                if (column.getExtendParams().indexOf("editor:'combobox'") > -1) {
                    // ???????????????????????????editor:'combobox'
                    if (!StringUtil.isEmpty(column.getDictionary())) {
                        // ????????????????????????editor:'combobox'
                        if (column.getDictionary().contains(",")) {
                            // update-begin-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
                            // update-begin-Author:zhoujf  Date:20180724 for:TASK #2996 ??????????????????ajax?????????????????????????????????
                            if (column.isPopup()) {
                            // TODO popup????????????????????????????????????
                            } else {
                                if (column.getIsAjaxDict()) {
                                // TODO ajax????????????????????????????????????
                                // update-end-Author:zhoujf  Date:20180724 for:TASK #2996 ??????????????????ajax?????????????????????????????????
                                } else {
                                    String[] dic = column.getDictionary().split(",");
                                    String sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
                                    // -- update-begin--Author:zhoujf  Date:20180319 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                                    if (!StringUtil.isEmpty(column.getDictCondition())) {
                                        sql += " " + column.getDictCondition();
                                    }
                                    // -- update-end--Author:zhoujf  Date:20180319 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                                    systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                                    List<Map<String, Object>> list = systemService.findForJdbc(sql);
                                    comboboxStr.append("editor:{type:'combobox',options:{valueField:'typecode',textField:'typename',data:[");
                                    // -- update-begin--Author:zhoujf  Date:20180409 for??????????????????????????????????????????????????????(?????????)
                                    for (Map<String, Object> map : list) {
                                        comboboxStr.append("{'typecode':'" + map.get("field") + "','typename':'" + map.get("text") + "'},");
                                    }
                                    comboboxStr.append("],required:true}}");
                                    // ?????????formatter??????
                                    comboboxStr.append(",formatter:function(value,row){");
                                    for (Map<String, Object> map : list) {
                                        comboboxStr.append("if(value =='" + map.get("field") + "'){");
                                        comboboxStr.append("return '" + map.get("text") + "';");
                                        comboboxStr.append("}");
                                    }
                                    comboboxStr.append("return value;");
                                    // -- update-end--Author:zhoujf  Date:20180409 for??????????????????????????????????????????????????????(?????????)
                                    comboboxStr.append("}");
                                }
                            }
                        // update-end-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
                        } else {
                            List<TSType> types = ResourceUtil.getCacheTypes(column.getDictionary().toLowerCase());
                            if (types != null) {
                                comboboxStr.append("editor:{type:'combobox',options:{valueField:'typecode',textField:'typename',data:[");
                                for (TSType type : types) {
                                    comboboxStr.append("{'typecode':'" + type.getTypecode() + "','typename':'" + MutiLangUtil.getLang(type.getTypename()) + "'},");
                                }
                                comboboxStr.append("],required:true}}");
                                // ?????????formatter??????
                                comboboxStr.append(",formatter:function(value,row){");
                                for (TSType type : types) {
                                    comboboxStr.append("if(value =='" + type.getTypecode() + "'){");
                                    comboboxStr.append("return '" + MutiLangUtil.getLang(type.getTypename()) + "';");
                                    comboboxStr.append("}");
                                }
                                comboboxStr.append("return row." + field + ";");
                                comboboxStr.append("}");
                            }
                        }
                    }
                    // update--begin--author:zhoujf date:20180330 for:TASK #2592 ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    if (StringUtils.isEmpty(comboboxStr.toString())) {
                        comboboxStr.append("editor:'text'");
                    }
                    // update--end--author:zhoujf date:20180330 for:TASK #2592 ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    // ??????????????????
                    column.setExtendParams(column.getExtendParams().replaceAll("editor:'combobox'", comboboxStr.toString()));
                // System.out.println("column.getExtendParams()=="+column.getExtendParams());
                }
                // update-start-Author:scott  Date:20160812 for???????????????????????????????????????????????????
                // sb.append(","+column.getExtendParams().substring(0,column.getExtendParams().length()-1));
                // update--begin--author:zhoujf Date:20180319 for:?????????????????????????????????????????????
                // update--begin--author:zhoujf date:20180330 for:TASK #2592 ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                String extendParm = "";
                if (column.getExtendParams().endsWith(",") || column.getExtendParams().endsWith("''")) {
                    extendParm = column.getExtendParams().substring(0, column.getExtendParams().length() - 1);
                } else {
                    extendParm = column.getExtendParams();
                }
                extendParm = extendParm.trim();
                if (StringUtils.isNotEmpty(extendParm)) {
                    sb.append("," + extendParm);
                }
            // update--end--author:zhoujf date:20180330 for:TASK #2592 ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            // update--end--author:zhoujf Date:20180319 for:?????????????????????????????????????????????
            // update-end-Author:scott  Date:20160812 for???????????????????????????????????????????????????
            // update-end--Author:chenjin  Date:20160811 for???TASK #1259 ??????bug?????????bug?????????????????????????????????????????????,???????????????????????????combobox
            }
            // update-start-Author:zhangguoming  Date:20140921 for???TASK #458 ??????hidden=false?????????????????????????????????
            // ????????????
            if (column.isHidden()) {
                sb.append(",hidden:true");
            }
            // update-end-Author:zhangguoming  Date:20140921 for???TASK #458 ??????hidden=false?????????????????????????????????
            if (!treegrid) {
                // ????????????
                // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                if ((column.isSortable()) && StringUtil.isNotEmpty(field) && (field.indexOf("_") <= 0 && field != "opt")) {
                    // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                    sb.append(",sortable:" + column.isSortable() + "");
                }
            }
            if (column.getFormatterjs() != null) {
                // update-start--Author:chenjin  Date:20160811 for???TASK #1259 ?????????bug?????????bug?????????????????????????????????????????????,???????????????????????????combobox
                if (StringUtils.isNotEmpty(column.getExtendParams()) && column.getExtendParams().indexOf("editor:'combobox'") > -1) {
                // ???????????????????????????editor:'combobox'
                // ??????????????????formatter?????????
                } else {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return " + column.getFormatterjs() + "(value,rec,index);}");
                }
            // update-end--Author:chenjin  Date:20160811 for???TASK #1259 ?????????bug?????????bug?????????????????????????????????????????????,???????????????????????????combobox
            } else {
                // ????????????
                if (column.isImage()) {
                    if (column.getImageSize() != null) {
                        // ?????????????????????
                        String[] tld = column.getImageSize().split(",");
                        sb.append(",formatter:function(value,rec,index){");
                        sb.append(" return '<img width=\"" + tld[0] + "\" height=\"" + tld[1] + "\" border=\"0\" " + " onMouseOver=\"tipImg(this)\" onMouseOut=\"moveTipImg()\" " + // + " onMouseOver=\"toolTip('<img src='+value+'>')\" onMouseOut=\"toolTip()\" "
                        " src=\"'+value+'\"/>';}");
                        tld = null;
                    } else {
                        sb.append(",formatter:function(value,rec,index){");
                        sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>';}");
                    }
                } else if (column.getDownloadName() != null) {
                    sb.append(",formatter:function(value,rec,index){");
                    // update--begin--author:zhangjiaqiang date:20170527 for:????????????????????????
                    sb.append("var html = '';");
                    // update--begin--author:zhoujf date:20170601 for:?????????????????????
                    sb.append("if(value==null || value.length==0){return html;}");
                    // update--end--author:zhoujf date:20170601 for:?????????????????????
                    sb.append("if(value.indexOf('.jpg')>-1 || value.indexOf('.png')>-1 || value.indexOf('.jpeg')>-1 || value.indexOf('.gif') > -1){");
                    sb.append(" html = '<img onMouseOver=\"tipImg(this)\" onMouseOut=\"moveTipImg()\" src=\"'+value+'\" width=50 height=50/>';");
                    sb.append("}else{");
                    // update-begin-- author:gj_shaojc date:20180608 for:TASK #2760 ???online??????????????????????????? ????????????
                    sb.append(" html = '<a class=\"ace_button fa fa-download\" style=\"padding:3px 5px;\" target=\"_blank\" href=\"systemController/downloadFile.do?filePath='+value+'\">" + // update-end-- author:gj_shaojc date:20180608 for:TASK #2760 ???online??????????????????????????? ????????????
                    column.getDownloadName() + "</a>';}");
                    sb.append("return html;}");
                // update--end--author:zhangjiaqiang date:20170527 for:????????????????????????
                } else if (column.getUrl() != null) {
                    // ???????????????
                    sb.append(",formatter:function(value,rec,index){");
                    this.getFun(sb, column);
                    sb.append("}");
                // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                } else if (StringUtil.isNotEmpty(column.getField()) && column.getField().equals("opt")) {
                    // ????????????
                    // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                    sb.append(",formatter:function(value,rec,index){");
                    // update-begin-author:taoYan date:20180524 for:datagrid ?????????????????????--
                    if (column.isOptsMenu()) {
                        getOptsMenuUrl(sb);
                    } else {
                        this.getOptUrl(sb);
                    }
                    sb.append("}");
                // update-end-author:taoYan date:20180524 for:datagrid ?????????????????????--
                } else if (column.getFormatter() != null) {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return new Date().format('" + column.getFormatter() + "',value);}");
                } else // author:xugj-----start-----date:20160512 ---- for: TASK #1080 ???UI???????????????t:dgCol ????????????????????????
                if (column.getShowLen() != null) {
                    // ??????????????????????????????
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" if(value==undefined) {return ''} ");
                    sb.append(" if(value.length<=");
                    sb.append(column.getShowLen());
                    sb.append(") {return value}");
                    sb.append(" else{ return '<a title= '+value+'>'+ value.substring(0,");
                    sb.append(column.getShowLen());
                    sb.append(")+'...';}}");
                } else // author:xugj-----end-----date:20160512 ---- for: TASK #1080 ???UI???????????????t:dgCol ????????????????????????
                // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                if (columnValueList.size() > 0 && StringUtil.isNotEmpty(column.getField()) && !column.getField().equals("opt")) {
                    // ?????????
                    // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                    // update-begin-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
                    if (column.getDictionary() != null && column.getDictionary().contains(",") && column.getIsAjaxDict()) {
                        sb.append(",formatter:function(value,rec,index){");
                        sb.append("var rtn = \"<span name=\\\"ajaxDict\\\" dictionary=\\\"" + column.getDictionary() + "\\\" dictCondition=\\\"" + (column.getDictCondition() == null ? "" : column.getDictCondition()) + "\\\" popup=\\\"" + column.isPopup() + "\\\" value=\\\"\"+value+\"\\\"><img src='plug-in/easyui/themes/icons/loading.gif'/></span>\";");
                        sb.append("return rtn;");
                        sb.append("}");
                    } else {
                        String testString = "";
                        for (ColumnValue columnValue : columnValueList) {
                            if (columnValue.getName().equals(column.getField())) {
                                String[] value = columnValue.getValue().split(",");
                                String[] text = columnValue.getText().split(",");
                                sb.append(",formatter:function(value,rec,index){");
                                sb.append("if(value==undefined) return '';");
                                sb.append("var valArray = value.split(',');");
                                sb.append("if(valArray.length > 1){");
                                sb.append("var checkboxValue = '';");
                                sb.append("for(var k=0; k<valArray.length; k++){");
                                for (int j = 0; j < value.length; j++) {
                                    sb.append("if(valArray[k] == '" + value[j] + "'){ checkboxValue = checkboxValue + \'" + text[j] + "\' + ',';}");
                                }
                                sb.append("}");
                                sb.append("return checkboxValue.substring(0,checkboxValue.length-1);");
                                sb.append("}");
                                sb.append("else{");
                                for (int j = 0; j < value.length; j++) {
                                    testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\';}";
                                }
                                sb.append(testString);
                                sb.append("else{return value;}");
                                sb.append("}");
                                sb.append("}");
                            }
                        }
                    // update-end-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
                    }
                }
            }
            // ????????????
            // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
            if (columnStyleList.size() > 0 && StringUtil.isNotEmpty(column.getField()) && !column.getField().equals("opt")) {
                // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
                String testString = "";
                for (ColumnValue columnValue : columnStyleList) {
                    if (columnValue.getName().equals(column.getField())) {
                        String[] value = columnValue.getValue().split(",");
                        String[] text = columnValue.getText().split(",");
                        sb.append(",styler:function(value,rec,index){");
                        if ((value.length == 0 || StringUtils.isEmpty(value[0])) && text.length == 1) {
                            if (text[0].indexOf("(") > -1) {
                                testString = " return \'" + text[0].replace("(", "(value,rec,index") + "\'";
                            } else {
                                testString = " return \'" + text[0] + "\'";
                            }
                        } else {
                            for (int j = 0; j < value.length; j++) {
                                testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\'}";
                            }
                        }
                        sb.append(testString);
                        sb.append("}");
                    }
                }
            }
            // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
            if (StringUtil.isNotEmpty(column.getColspan())) {
                sb.append(",colspan:\"" + column.getColspan() + "\"");
            }
            sb.append("}");
            if (i < columnList.size() && column.isNewColumn()) {
                sb.append("],[");
                continue;
            }
            // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 ???demo???datagrid ?????????demo
            // ????????????,
            if (i < columnList.size()) {
                sb.append(",");
            }
        }
    }
}


public void setCssTheme(String cssTheme){
    this.cssTheme = cssTheme;
}


public void setIsShowSearch(boolean isShowSearch){
    this.isShowSearch = isShowSearch;
}


public void setBtnCls(String btnCls){
    // update--begin--author:zhangjiaqiang date:20170627 for:TASK #2179 ?????????IE???????????????????????????????????????
    if (checkBrowerIsNotIE()) {
        this.btnCls = btnCls;
    } else {
        // IE?????????
        this.btnCls = "easyui";
    }
// update--end--author:zhangjiaqiang date:20170627 for:TASK #2179 ?????????IE???????????????????????????????????????
}


public void setShowText(boolean showText){
    this.showText = showText;
}


public void setFilter(boolean filter){
    this.filter = filter;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setShowSearch(boolean isShowSearch){
    this.isShowSearch = isShowSearch;
}


public void getOptsMenuUrl(StringBuffer sb){
    this.getOptUrl(sb, true, false);
    StringBuffer groupString = new StringBuffer();
    this.getOptUrl(groupString, true, true);
    if (oConvertUtils.isNotEmpty(groupString.toString())) {
        sb.append("href+='<div style=\"left:40px;top:-1px\" class=\"opts_menu_container\"><div class=\"opts_menu_btn btn-menu\"><i class=\"fa fa-caret-right\" style=\"margin-top:1px;\"></i></div>';");
        sb.append("href+='<div style=\"clear: both;\"></div><div style=\"\" class=\"opts-menus-parent pp_menu_box\"><div class=\"opts_menu_box opts-menus-auto\" style=\"left:18px;\">';");
        sb.append(groupString.toString());
        sb.append("href+='</div></div><em class=\"ops_shadeEm\" style=\"display: inline;\"></em></div>';");
    }
    sb.append("return href;");
}


public String getConfigId(){
    return configId;
}


public void setSuperQuery(boolean superQuery){
    this.superQuery = superQuery;
}


public boolean checkBrowerIsNotIE(){
    String browserType = "";
    Object brower_type = ContextHolderUtils.getSession().getAttribute("brower_type");
    if (brower_type == null) {
        HttpServletRequest req = ContextHolderUtils.getRequest();
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if ("BROWSER_TYPE".equals(cookie.getName())) {
                browserType = cookie.getValue();
            }
        }
    } else {
        browserType = brower_type.toString();
    }
    if (!"Microsoft%20Internet%20Explorer".equals(browserType)) {
        return true;
    }
    return false;
}


public void setRowStyler(String rowStyler){
    this.rowStyler = rowStyler;
}


public boolean isShowPageList(){
    return showPageList;
}


public String getOnLoadSuccess(){
    return onLoadSuccess;
}


public void setQueryBuilder(boolean queryBulder){
    this.queryBuilder = queryBulder;
}


public Map<String,Object> getTableData(){
    return tableData;
}


public void setName(String name){
    this.name = name;
}


public void setQueryMode(String queryMode){
    this.queryMode = queryMode;
}


public String getDBFieldName(String fieldName){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < fieldName.length(); i++) {
        char c = fieldName.charAt(i);
        if (c <= 'Z' && c >= 'A') {
            sb.append("_").append((char) ((int) c + 32));
        } else {
            sb.append(c);
        }
    }
    return sb.toString();
}


public void setAllCount(int allCount){
    this.allCount = allCount;
}


public String getWidth(){
    return width;
}


public void loadSubData(String id){
    CgFormFieldServiceI cgFormFieldService = (CgFormFieldServiceI) ApplicationContextUtil.getContext().getBean("cgFormFieldService");
    String tableName = id;
    String tablename = PublicUtil.replaceTableName(tableName);
    Map<String, Object> data = new HashMap<String, Object>();
    Map configData = null;
    String jversion = cgFormFieldService.getCgFormVersionByTableName(id);
    configData = cgFormFieldService.getFtlFormConfig(tableName, jversion);
    data = new HashMap(configData);
    // ??????????????????????????????????????????
    CgFormHeadEntity head = (CgFormHeadEntity) data.get("head");
    this.tableData = (Map<String, Object>) data.get("field");
    this.head = head;
}


public void setFit(boolean fit){
    this.fit = fit;
}


public void setShowRefresh(boolean showRefresh){
    this.showRefresh = showRefresh;
}


public String getSortOrder(){
    return sortOrder;
}


public void setExtendParams(String extendParams){
    // update--begin--author:zhangjiaqiang date:20170705 for:TASK #2195 ???ui?????????????????????????????????????????????????????????????????????
    if (StringUtil.isNotEmpty(extendParams) && !extendParams.endsWith(",")) {
        extendParams = extendParams + ",";
    }
    // update--end--author:zhangjiaqiang date:20170705 for:TASK #2195 ???ui?????????????????????????????????????????????????????????????????????
    this.extendParams = extendParams;
}


public String getHeight(){
    return height;
}


public void getOptUrl(StringBuffer sb,boolean noReturn,boolean initGroup){
    // ????????????????????????????????????????????????????????????
    List<DataGridUrl> list = urlList;
    if (!initGroup) {
        sb.append("if(!rec.id){return '';}");
        sb.append("var href='';");
    }
    for (DataGridUrl dataGridUrl : list) {
        if (initGroup) {
            // ???????????????????????? ???????????????????????????????????? ??????
            if (!dataGridUrl.isInGroup()) {
                continue;
            }
        } else {
            // ??????????????????????????? ????????????????????????????????? ??????
            if (dataGridUrl.isInGroup()) {
                continue;
            }
        }
        String url = dataGridUrl.getUrl();
        MessageFormat formatter = new MessageFormat("");
        if (dataGridUrl.getValue() != null) {
            String[] testvalue = dataGridUrl.getValue().split(",");
            List value = new ArrayList<Object>();
            for (String string : testvalue) {
                value.add("\"+rec." + string + " +\"");
            }
            url = formatter.format(url, value.toArray());
        }
        if (url != null && dataGridUrl.getValue() == null) {
            url = formatUrlPlus(url);
        }
        // ?????????????????????
        String exp = dataGridUrl.getExp();
        if (StringUtil.isNotEmpty(exp)) {
            String[] ShowbyFields = exp.split("&&");
            for (String ShowbyField : ShowbyFields) {
                int beginIndex = ShowbyField.indexOf("#");
                int endIndex = ShowbyField.lastIndexOf("#");
                // ???????????????
                String exptype = ShowbyField.substring(beginIndex + 1, endIndex);
                // ????????????????????????
                String field = ShowbyField.substring(0, beginIndex);
                // ???????????????
                String[] values = ShowbyField.substring(endIndex + 1, ShowbyField.length()).split(",");
                String value = "";
                for (int i = 0; i < values.length; i++) {
                    value += "'" + "" + values[i] + "" + "'";
                    if (i < values.length - 1) {
                        value += ",";
                    }
                }
                if ("eq".equals(exptype)) {
                    sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
                }
                if ("ne".equals(exptype)) {
                    sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
                }
                if ("empty".equals(exptype) && value.equals("'true'")) {
                    sb.append("if(rec." + field + "==''){");
                }
                if ("empty".equals(exptype) && value.equals("'false'")) {
                    sb.append("if(rec." + field + "!=''){");
                }
            }
        }
        StringBuffer style = new StringBuffer();
        if (!StringUtil.isEmpty(dataGridUrl.getUrlStyle())) {
            style.append(" style=\'");
            style.append(dataGridUrl.getUrlStyle());
            style.append("\' ");
        }
        StringBuffer urlclass = new StringBuffer();
        StringBuffer urlfont = new StringBuffer();
        if (initGroup) {
            urlclass.append(" class=\'btn btn-menu fa ");
            if (!StringUtil.isEmpty(dataGridUrl.getUrlfont())) {
                urlclass.append(dataGridUrl.getUrlfont());
            } else {
                urlclass.append("fa-font");
            }
            urlclass.append(" menu-more\'");
        } else {
            urlclass.append(" class=\'btn-menu\'");
            urlfont.append("<i class=\'fa ");
            if (!StringUtil.isEmpty(dataGridUrl.getUrlfont())) {
                urlfont.append(dataGridUrl.getUrlfont());
            } else {
                urlfont.append("fa-font");
            }
            urlfont.append("\'></i>");
        }
        if (OptTypeDirection.Fun.equals(dataGridUrl.getType())) {
            String name = TagUtil.getFunction(dataGridUrl.getFunname());
            String parmars = TagUtil.getFunParams(dataGridUrl.getFunname());
            sb.append("href+=\"<a href=\'#\' title='" + dataGridUrl.getTitle() + "' " + urlclass.toString() + " onclick=" + name + "(" + parmars + ")" + style.toString() + ">" + urlfont.toString() + "\";");
        }
        if (OptTypeDirection.OpenWin.equals(dataGridUrl.getType())) {
            String funname = dataGridUrl.getFunname();
            if (oConvertUtils.isEmpty(funname)) {
                funname = "openwindow";
            }
            String dgFormWidth = dataGridUrl.getWidth();
            if ("100%".equals(dgFormWidth)) {
                dgFormWidth = "'" + dgFormWidth + "'";
            } else if (oConvertUtils.isEmpty(dgFormWidth)) {
                dgFormWidth = "''";
            }
            String dgFormHeight = dataGridUrl.getHeight();
            if ("100%".equals(dgFormHeight)) {
                dgFormHeight = "'" + dgFormHeight + "'";
            } else if (oConvertUtils.isEmpty(dgFormHeight)) {
                dgFormHeight = "''";
            }
            sb.append("href+=\"<a href=\'####\' title='" + dataGridUrl.getTitle() + "' " + urlclass.toString() + " onclick=" + funname + "('" + dataGridUrl.getTitle() + "','" + url + "','" + name + "'," + dgFormWidth + "," + dgFormHeight + ")" + style.toString() + ">" + urlfont.toString() + "\";");
        }
        sb.append("href+=\"" + "" + "</a>&nbsp;\";");
        if (StringUtil.isNotEmpty(exp)) {
            for (int i = 0; i < exp.split("&&").length; i++) {
                sb.append("}");
            }
        }
    }
    if (!noReturn) {
        sb.append("return href;");
    }
}


public List<ColumnValue> getColumnValueList(){
    return columnValueList;
}


public void setSortName(String sortName){
    this.sortName = sortName;
}


public void appendLine(StringBuffer sb,String str){
    // ??????  ?????????
    String format = "\r\n";
    sb.append(str).append(format);
}


public String getSortName(){
    return sortName;
}


public void setColumnValueList(List<ColumnValue> columnValueList){
    this.columnValueList = columnValueList;
}


public void getSearchButton(StringBuffer sb){
    if ("group".equals(getQueryMode()) && hasQueryColum(columnList)) {
        // ???????????????????????????
        // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
        // update--begin--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
        sb.append("<span style=\"float:right;\">");
        // update--end--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ??????????????? bootstrap????????????
            if (btnCls.indexOf("bootstrap") == 0) {
                String defalutCls = "btn btn-info btn-xs";
                if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                    defalutCls = btnCls.replace("bootstrap", "").trim();
                }
                // update--begin--author:guoxianhui date:20171204 for:TASK #2446 ???bug???online???????????????????????????
                sb.append("<button class=\"" + defalutCls + "\" type=\"button\"  onclick=\"" + name + "search()\">");
                sb.append("<i class=\"fa fa-search\"></i>");
                sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.query") + "</span>");
                sb.append("</button>");
                sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"searchReset('" + name + "')\">");
                sb.append("<i class=\"fa fa-refresh\"></i>");
                sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.reset") + "</span>");
                sb.append("</button>");
                if (queryBuilder) {
                    sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"queryBuilder()\">");
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.querybuilder") + "</span>");
                    sb.append("</button>");
                }
                // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356??????????????????????????????????????????-----
                if (superQuery) {
                    sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"queryBuilder()\">");
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.superquery") + "</span>");
                    sb.append("</button>");
                }
                // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356??????????????????????????????????????????-----
                // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
                if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                    // update-begin-author:taoyan date:2180522 for:????????????????????????????????????----
                    sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">");
                    // update-end-author:taoyan date:2180522 for:????????????????????????????????????----
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.advancedQuery") + "</span>");
                    sb.append("</button>");
                }
            // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            // update--end--author:guoxianhui date:20171204 for:TASK #2446 ???bug???online???????????????????????????
            } else {
                // ???????????????
                sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"" + name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getLang("common.query")));
                sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"searchReset('" + name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.reset")));
                if (queryBuilder) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.querybuilder")));
                }
                // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356??????????????????????????????????????????-----
                if (superQuery) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superquery")));
                }
                // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356??????????????????????????????????????????-----
                // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
                if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                    // update-begin-author:taoyan date:2180522 for:????????????????????????????????????----
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">" + MutiLangUtil.getLang("common.advancedQuery") + "</a>");
                // update-end-author:taoyan date:2180522 for:????????????????????????????????????----
                }
            // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            }
        } else {
            // ????????????easyUI????????????
            sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getLang("common.query")));
            sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"searchReset('" + name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.reset")));
            if (queryBuilder) {
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.querybuilder")));
            }
            // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
            if (superQuery) {
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superquery")));
            }
            // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
            // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                // update-begin-author:taoyan date:2180522 for:????????????????????????????????????----
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">" + MutiLangUtil.getLang("common.advancedQuery") + "</a>");
            // update-end-author:taoyan date:2180522 for:????????????????????????????????????----
            }
        // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
        }
        sb.append("</span>");
    // update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
    }
}


public void setTreegrid(boolean treegrid){
    this.treegrid = treegrid;
}


public boolean isShowRefresh(){
    return showRefresh;
}


public boolean isCheckbox(){
    return checkbox;
}


public String getQueryMode(){
    return queryMode;
}


public boolean isNowrap(){
    return nowrap;
}


public void setTableData(Map<String,Object> tableData){
    this.tableData = tableData;
}


public void setCheckbox(boolean checkbox){
    this.checkbox = checkbox;
}


public String getRowStyler(){
    return rowStyler;
}


public boolean getIsShowSearch(){
    return isShowSearch;
}


public void setWidth(String width){
    this.width = width;
}


public void getFilter(StringBuffer sb){
    if (this.isFilter()) {
        FreemarkerHelper free = new FreemarkerHelper();
        Map<String, Object> mainConfig = new HashMap<String, Object>();
        mainConfig.put("gridId", name);
        String superQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/filter.ftl", mainConfig);
        appendLine(sb, superQuery);
    }
}


public boolean isTreegrid(){
    return treegrid;
}


public boolean isOpenFirstNode(){
    return openFirstNode;
}


public void setOnLoadSuccess(String onLoadSuccess){
    this.onLoadSuccess = onLoadSuccess;
}


public boolean isFilter(){
    return filter;
}


public void addQueryBuilder(StringBuffer sb,String buttonSytle){
    // update-begin--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? -------------------
    appendLine(sb, "<div style=\"position:relative;overflow:auto;\">");
    appendLine(sb, "<div id=\"" + name + "_qbwin\" class=\"easyui-window\" data-options=\"closed:true,title:'&nbsp;&nbsp;?????????????????????'\" style=\"width:600px;height:370px;padding:0px\">");
    appendLine(sb, "	<div class=\"easyui-layout\" data-options=\"fit:true\">");
    appendLine(sb, "		<div data-options=\"region:'east',split:true\" style=\"width:130px;overflow: hidden;\"><div data-options=\"fit:true\" class=\"easyui-accordion\" style=\"width:120px;height:300px;\">");
    appendLine(sb, "<div title=\"????????????\" data-options=\"iconCls:'icon-search'\" style=\"padding:0px;\">");
    appendLine(sb, "	<ul id=\"" + name + "tt\" class=\"easyui-tree\" data-options=\"onClick:function(node){");
    appendLine(sb, "historyQuery( node.id);  ");
    appendLine(sb, "},ondbClick: function(node){");
    appendLine(sb, "$(this).tree('beginEdit',node.target);");
    appendLine(sb, "},onContextMenu: function(e,node){");
    appendLine(sb, "		e.preventDefault();");
    appendLine(sb, "		$(this).tree('select',node.target);");
    appendLine(sb, "		$('#" + name + "mmTree').menu('show',{");
    appendLine(sb, "			left: e.pageX,");
    appendLine(sb, "			top: e.pageY");
    appendLine(sb, "		});");
    appendLine(sb, "	},  ");
    appendLine(sb, " onAfterEdit:function(node){  ");
    appendLine(sb, "    if(node.text!=''){ " + name + "his[node.id].name=node.text; saveHistory();}	}");
    appendLine(sb, "\">");
    appendLine(sb, "	</ul>");
    appendLine(sb, "</div>");
    appendLine(sb, "</div></div>");
    appendLine(sb, "		<div data-options=\"region:'center'\" style=\"width:450px;padding:0px;overflow: hidden;\">");
    appendLine(sb, "			<table id=\"" + name + "tg\" class=\"easyui-treegrid\" title=\"??????????????????\" style=\"width:450px;height:300px;\"");
    appendLine(sb, "		data-options=\"");
    appendLine(sb, "			iconCls: 'icon-ok',");
    appendLine(sb, "			rownumbers: true,");
    appendLine(sb, "			animate: true,");
    appendLine(sb, "			fitColumns: true,");
    appendLine(sb, "			//url: 'sqlbuilder.json',//?????????????????????\r\n");
    appendLine(sb, "			method: 'get',");
    appendLine(sb, "			idField: 'id',");
    appendLine(sb, "			fit:true,");
    appendLine(sb, "autoEditing: true,  ");
    appendLine(sb, "extEditing: false, ");
    appendLine(sb, "singleEditing: false ,");
    appendLine(sb, "			treeField: 'field',toolbar:toolbar,onContextMenu: onContextMenu");
    appendLine(sb, "		\">");
    appendLine(sb, "<thead>");
    appendLine(sb, "	<tr>");
    sb.append("	<th data-options=\"field:'relation',width:18,formatter:function(value,row){");
    appendLine(sb, "				return value=='and'?'??????':'??????';");
    appendLine(sb, "			},editor:{");
    appendLine(sb, "				type:'combobox',");
    appendLine(sb, "				options:{");
    appendLine(sb, "				valueField:'relationId',");
    appendLine(sb, "						textField:'relationName',");
    appendLine(sb, "						data:  ");
    appendLine(sb, "						[  ");
    appendLine(sb, "						{'relationId':'and','relationName':'??????'},  ");
    appendLine(sb, "						{'relationId':'or','relationName':'??????'}  ");
    appendLine(sb, "						],  ");
    appendLine(sb, "						required:true");
    appendLine(sb, "					}}\">??????</th>");
    sb.append("		<th data-options=\"field:'field',width:30,formatter:function(value,row){");
    appendLine(sb, "			var data= ");
    StringBuffer fieldArray = new StringBuffer();
    fieldArray.append("	[  ");
    for (int i = 0; i < columnList.size(); i++) {
        DataGridColumn col = columnList.get(i);
        // ????????????????????????
        if ("opt".equals(col.getField()))
            continue;
        fieldArray.append("	{'fieldId':'" + getDBFieldName(col.getField()) + "','fieldName':'" + col.getTitle() + "'");
        if (col.getEditor() != null) {
            fieldArray.append(",editor:'" + col.getEditor() + "'");
        }
        fieldArray.append("}");
        if (i < columnList.size() - 1) {
            fieldArray.append(",");
        }
    }
    // appendLine(sb,"				{'fieldId':'office_Phone','fieldName':'????????????'},");
    fieldArray.append("]");
    sb.append(fieldArray).append(";");
    appendLine(sb, "for(var i=0;i<data.length;i++){");
    appendLine(sb, "if(value == data[i]['fieldId']){");
    appendLine(sb, "return data[i]['fieldName'];");
    appendLine(sb, "}");
    appendLine(sb, "}");
    appendLine(sb, "return value;");
    appendLine(sb, "},editor:{");
    appendLine(sb, "type:'combobox',");
    appendLine(sb, "	options:{");
    appendLine(sb, "valueField:'fieldId',");
    appendLine(sb, "textField:'fieldName',");
    appendLine(sb, "data:  ");
    sb.append(fieldArray);
    appendLine(sb, " , ");
    appendLine(sb, "required:true,onSelect : function(record) {");
    appendLine(sb, "var opts = $('#" + name + "tg').treegrid('getColumnOption','value');");
    appendLine(sb, "	if(record.editor){");
    appendLine(sb, "			opts.editor=record.editor;");
    appendLine(sb, "	}else{");
    appendLine(sb, "			opts.editor='text';");
    appendLine(sb, "	}");
    appendLine(sb, "	var tr = $(this).closest('tr.datagrid-row');");
    appendLine(sb, "	var index = parseInt(tr.attr('node-id'));");
    appendLine(sb, "	$('#" + name + "tg').treegrid('endEdit', index);");
    appendLine(sb, "	$('#" + name + "tg').treegrid('beginEdit', index);");
    appendLine(sb, "}");
    appendLine(sb, "}}\">??????</th>");
    appendLine(sb, "<th data-options=\"field:'condition',width:20,align:'right',formatter:function(value,row){");
    appendLine(sb, "							var data=  ");
    appendLine(sb, "					[  ");
    List<TSType> types = ResourceUtil.getCacheTypes("rulecon");
    if (types != null) {
        for (int i = 0; i < types.size(); i++) {
            TSType type = types.get(i);
            appendLine(sb, " {'conditionId':'" + type.getTypecode() + "','conditionName':'" + MutiLangUtil.getLang(type.getTypename()) + "'}");
            if (i < types.size() - 1) {
                appendLine(sb, ",");
            }
        }
    }
    appendLine(sb, "];");
    appendLine(sb, "	for(var i=0;i<data.length;i++){");
    appendLine(sb, "			if(value == data[i]['conditionId']){");
    appendLine(sb, "			return data[i]['conditionName'];");
    appendLine(sb, "			}");
    appendLine(sb, "		}");
    appendLine(sb, "		return value;");
    appendLine(sb, "		},editor:{");
    appendLine(sb, "		type:'combobox',");
    appendLine(sb, "		options:{");
    appendLine(sb, "			valueField:'conditionId',");
    appendLine(sb, "				textField:'conditionName',	");
    appendLine(sb, "			data:  ");
    appendLine(sb, "[");
    if (types != null) {
        for (int i = 0; i < types.size(); i++) {
            TSType type = types.get(i);
            appendLine(sb, " {'conditionId':'" + type.getTypecode() + "','conditionName':'" + MutiLangUtil.getLang(type.getTypename()) + "'}");
            if (i < types.size() - 1) {
                appendLine(sb, ",");
            }
        }
    }
    appendLine(sb, "				],  ");
    appendLine(sb, "				required:true");
    appendLine(sb, "			}}\">??????</th>");
    sb.append("	<th data-options=\"field:'value',width:30,editor:'text'\">???</th>");
    appendLine(sb, "<th data-options=\"field:'opt',width:30,formatter:function(value,row){");
    appendLine(sb, "	return '<a  onclick=\\'removeIt('+row.id+')\\' >??????</a>';}\">??????</th>");
    appendLine(sb, "		</tr>");
    appendLine(sb, "	</thead>");
    appendLine(sb, "	</table>");
    appendLine(sb, "</div>");
    appendLine(sb, "<div data-options=\"region:'south',border:false\" style=\"text-align:right;padding:5px 0 3px;\">");
    // update-begin--Author:xuelin  Date:20170606 for???TASK #2083 ????????????JEECG??????????????????  ??????1 ????????????????????????--------------------
    if (btnCls != null && !btnCls.equals("easyui")) {
        String defalutCls = "btn btn-default btn-xs";
        if (btnCls.replace("bootstrap", "").trim().length() > 0) {
            defalutCls = btnCls.replace("bootstrap", "").trim();
        }
        appendLine(sb, "<button class=\"" + defalutCls + "\" onclick=\"javascript:queryBuilderSearch()\">&nbsp;<i class=\"fa fa-check\"></i>??????</button>");
        appendLine(sb, "<button class=\"" + defalutCls + "\" onclick=\"javascript:$('#" + name + "_qbwin').window('close')\">&nbsp;<i class=\"fa fa-close\"></i>??????</button>");
    } else {
        appendLine(sb, "<a class=\"" + buttonSytle + "\" data-options=\"iconCls:'icon-ok'\" href=\"javascript:void(0)\" onclick=\"javascript:queryBuilderSearch()\">??????</a>");
        appendLine(sb, "<a class=\"" + buttonSytle + "\" data-options=\"iconCls:'icon-cancel'\" href=\"javascript:void(0)\" onclick=\"javascript:$('#" + name + "_qbwin').window('close')\">??????</a>");
    }
    // update-end--Author:xuelin  Date:20170606 for???TASK #2083 ????????????JEECG??????????????????  ??????1 ????????????????????????----------------------
    appendLine(sb, "		</div>");
    appendLine(sb, "	</div>	");
    appendLine(sb, "</div>		");
    appendLine(sb, "</div>");
    appendLine(sb, "<div id=\"mm\" class=\"easyui-menu\" style=\"width:120px;\">");
    appendLine(sb, "	<div onclick=\"append()\" data-options=\"iconCls:'icon-add'\">??????</div>");
    appendLine(sb, "	<div onclick=\"edit()\" data-options=\"iconCls:'icon-edit'\">??????</div>");
    appendLine(sb, "	<div onclick=\"save()\" data-options=\"iconCls:'icon-save'\">??????</div>");
    appendLine(sb, "	<div onclick=\"removeIt()\" data-options=\"iconCls:'icon-remove'\">??????</div>");
    appendLine(sb, "	<div class=\"menu-sep\"></div>");
    appendLine(sb, "	<div onclick=\"cancel()\">??????</div>");
    appendLine(sb, "<div onclick=\"expand()\">Expand</div>");
    appendLine(sb, "</div><div id=\"" + name + "mmTree\" class=\"easyui-menu\" style=\"width:100px;\">");
    appendLine(sb, "<div onclick=\"editTree()\" data-options=\"iconCls:'icon-edit'\">??????</div>");
    appendLine(sb, "<div onclick=\"deleteTree()\" data-options=\"iconCls:'icon-remove'\">??????</div></div>");
    // ??????baseTag?????????
    // appendLine(sb,"<script type=\"text/javascript\" src=\"plug-in/jquery/jquery.cookie.js\" ></script>");
    // appendLine(sb,"<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/storage/jquery.storageapi.min.js\" ></script>");
    appendLine(sb, "<script type=\"text/javascript\">");
    if (btnCls != null && !btnCls.equals("easyui")) {
        String defalutCls = "btn btn-info btn-xs";
        if (btnCls.replace("bootstrap", "").trim().length() > 0) {
            defalutCls = btnCls.replace("bootstrap", "").trim();
        }
        // update-begin--Author:xuelin  Date:20170606 for???TASK #2083 ????????????JEECG??????????????????  ??????1 ????????????????????????--------------------
        sb.append("var toolbar = '<div>");
        sb.append("<button class=\"" + defalutCls + "\" onclick=\"append()\">&nbsp;<i class=\"fa fa-plus\"></i>&nbsp;</button>");
        sb.append("<button class=\"" + defalutCls + "\" onclick=\"edit()\">&nbsp;<i class=\"fa fa-pencil-square-o\"></i></button>");
        sb.append("<button class=\"" + defalutCls + "\" onclick=\"removeIt()\">&nbsp;<i class=\"fa fa-trash\"></i></button>");
        sb.append("<button class=\"" + defalutCls + "\" onclick=\"save()\">&nbsp;<i class=\"fa fa-save\"></i></button>");
        sb.append("</div>';");
    // update-end--Author:xuelin  Date:20170606 for???TASK #2083 ????????????JEECG??????????????????  ??????1 ????????????????????????----------------------
    } else {
        appendLine(sb, "var toolbar = [{");
        appendLine(sb, "	text:'',");
        appendLine(sb, "	iconCls:'" + btnCls + "',");
        appendLine(sb, "	handler:function(){append();}");
        appendLine(sb, "},{");
        appendLine(sb, "	text:'',");
        appendLine(sb, "	iconCls:'icon-edit',");
        appendLine(sb, "	handler:function(){edit();}");
        appendLine(sb, "},{");
        appendLine(sb, "	text:'',");
        appendLine(sb, "	iconCls:'icon-remove',");
        appendLine(sb, "	handler:function(){removeIt();}");
        appendLine(sb, "},'-',{");
        appendLine(sb, "	text:'',");
        appendLine(sb, "	iconCls:'icon-save',");
        appendLine(sb, "	handler:function(){save();}");
        appendLine(sb, "	}];");
    }
    appendLine(sb, "function onContextMenu(e,row){");
    appendLine(sb, "	e.preventDefault();");
    appendLine(sb, "	$(this).treegrid('select', row.id);");
    appendLine(sb, "	$('#mm').menu('show',{");
    appendLine(sb, "		left: e.pageX,");
    appendLine(sb, "		top: e.pageY");
    appendLine(sb, "	});");
    appendLine(sb, "}");
    appendLine(sb, "	var idIndex = 100;");
    appendLine(sb, "function append(){");
    appendLine(sb, "	idIndex++;");
    appendLine(sb, "	var node = $('#" + name + "tg').treegrid('getSelected');");
    appendLine(sb, "	$('#" + name + "tg').treegrid('append',{");
    appendLine(sb, "		data: [{");
    appendLine(sb, "			id: idIndex,");
    appendLine(sb, "			field: '',");
    appendLine(sb, "		condition:'like',");
    appendLine(sb, "		value: '%a%',");
    appendLine(sb, "		relation: 'and'");
    appendLine(sb, "				}]");
    appendLine(sb, "});$('#" + name + "tg').datagrid('beginEdit',idIndex);");
    appendLine(sb, "}");
    appendLine(sb, "		function removeIt(id){");
    appendLine(sb, "var node = $('#" + name + "tg').treegrid('getSelected');");
    appendLine(sb, "if(id){");
    appendLine(sb, "$('#" + name + "tg').treegrid('remove', id);");
    appendLine(sb, "}else if(node){	$('#" + name + "tg').treegrid('remove', node.id);");
    appendLine(sb, "}");
    appendLine(sb, "}");
    appendLine(sb, "function collapse(){");
    appendLine(sb, "	var node = $('#" + name + "tg').treegrid('getSelected');");
    appendLine(sb, "if(node){");
    appendLine(sb, "	$('#" + name + "tg').treegrid('collapse', node.id);");
    appendLine(sb, "}");
    appendLine(sb, "}");
    appendLine(sb, "function expand(){");
    appendLine(sb, "var node = $('#" + name + "tg').treegrid('getSelected');");
    appendLine(sb, "if(node){");
    appendLine(sb, "	$('#" + name + "tg').treegrid('expand', node.id);");
    appendLine(sb, "}");
    appendLine(sb, "}");
    appendLine(sb, "var editingId;");
    appendLine(sb, "function edit(id){");
    appendLine(sb, "var row = $('#" + name + "tg').treegrid('getSelected');");
    appendLine(sb, "if(id){	$('#" + name + "tg').treegrid('beginEdit', id);}else if(row){");
    appendLine(sb, "	$('#" + name + "tg').treegrid('beginEdit', row.id);");
    appendLine(sb, "}");
    appendLine(sb, "}");
    appendLine(sb, "function save(){");
    appendLine(sb, "	var t = $('#" + name + "tg');");
    appendLine(sb, "	var nodes = t.treegrid('getRoots');");
    appendLine(sb, "	for (var i = 0; i < nodes.length; i++) {");
    appendLine(sb, "	t.treegrid('endEdit',nodes[i].id);}");
    appendLine(sb, "	}");
    appendLine(sb, "function cancel(){");
    appendLine(sb, "	var t = $('#" + name + "tg');");
    appendLine(sb, "var nodes = t.treegrid('getRoots');for (var i = 0; i < nodes.length; i++) {t.treegrid('cancelEdit',nodes[i].id);}");
    appendLine(sb, "}");
    appendLine(sb, "var " + name + "his=new Array();");
    appendLine(sb, " function historyQuery(index) {");
    appendLine(sb, "	  var data  = { rows:JSON.parse(" + name + "his[index].json)};  ");
    appendLine(sb, "	    var t = $('#" + name + "tg');");
    appendLine(sb, "		var data = t.treegrid('loadData',data);");
    appendLine(sb, "		$('#_sqlbuilder').val( " + name + "his[index].json);   ");
    appendLine(sb, "		" + name + "search();");
    appendLine(sb, "	}");
    appendLine(sb, "function view(){");
    appendLine(sb, "save();");
    appendLine(sb, "var t = $('#" + name + "tg');");
    appendLine(sb, "var data = t.treegrid('getData');");
    appendLine(sb, "return   JSON.stringify(data) ;");
    appendLine(sb, "}");
    appendLine(sb, "	 function queryBuilder() {");
    appendLine(sb, "	$('#" + name + "_qbwin').window('open');");
    appendLine(sb, "}");
    appendLine(sb, "function queryBuilderSearch() {");
    appendLine(sb, "         var json =  view();");
    appendLine(sb, "	$('#_sqlbuilder').val(json);  ");
    appendLine(sb, "	var isnew=true;");
    appendLine(sb, "for(var i=0;i< " + name + "his.length;i++){");
    appendLine(sb, "	if(" + name + "his[i]&&" + name + "his[i].json==json){");
    appendLine(sb, "		isnew=false;");
    appendLine(sb, "	}");
    appendLine(sb, "}");
    appendLine(sb, "if(isnew){");
    appendLine(sb, " " + name + "his.push({name:'Query'+" + name + "his.length,json:json});saveHistory();");
    appendLine(sb, "var name= 'Query'+( " + name + "his.length-1);");
    appendLine(sb, "	var name= 'Query'+(" + name + "his.length-1);");
    appendLine(sb, "appendTree(" + name + "his.length-1,name);");
    appendLine(sb, "}");
    appendLine(sb, "	" + name + "search();");
    appendLine(sb, " }");
    appendLine(sb, " $(document).ready(function(){ ");
    appendLine(sb, " storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
    appendLine(sb, "	var _qhistory = storage.get('" + name + "_query_history');");
    appendLine(sb, " if(_qhistory){");
    appendLine(sb, " " + name + "his=_qhistory;");
    // appendLine(sb, " 	var data  = { rows:his[0]};");
    appendLine(sb, " 	for(var i=0;i< " + name + "his.length;i++){");
    appendLine(sb, " 		if(" + name + "his[i])appendTree(i," + name + "his[i].name);");
    appendLine(sb, " 	}restoreheader();");
    appendLine(sb, " }});");
    appendLine(sb, "function saveHistory(){");
    appendLine(sb, "	var history=new Array();");
    appendLine(sb, "	for(var i=0;i<" + name + "his.length;i++){");
    appendLine(sb, "		if(" + name + "his[i]){");
    appendLine(sb, "			history.push(" + name + "his[i]);");
    appendLine(sb, "		}");
    appendLine(sb, "	}");
    appendLine(sb, "	storage.set( '" + name + "_query_history',JSON.stringify(history));");
    appendLine(sb, "}");
    appendLine(sb, "function deleteTree(){");
    appendLine(sb, "	var tree = $('#" + name + "tt');var node= tree.tree('getSelected');");
    appendLine(sb, "	" + name + "his[node.id]=null;saveHistory();");
    appendLine(sb, "	tree.tree('remove', node.target);");
    appendLine(sb, "}");
    appendLine(sb, "function editTree(){");
    appendLine(sb, "	var node = $('#" + name + "tt').tree('getSelected');");
    appendLine(sb, "	$('#" + name + "tt').tree('beginEdit',node.target);");
    appendLine(sb, "	saveHistory();");
    appendLine(sb, "}");
    appendLine(sb, "function appendTree(id,name){");
    appendLine(sb, "	$('#" + name + "tt').tree('append',{");
    appendLine(sb, "	data:[{");
    appendLine(sb, "id : id,");
    appendLine(sb, "text :name");
    appendLine(sb, "	}]");
    appendLine(sb, "});");
    appendLine(sb, "}");
    appendLine(sb, "</script>");
// update-end--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? -------------------
}


public String toString(){
    StringBuffer key = new StringBuffer();
    key.append("DataGridTag [fields=").append(fields).append(",searchFields=").append(searchFields).append(",name=").append(name).append(",title=").append(title).append(",idField=").append(idField).append(",treegrid=").append(treegrid).append(",actionUrl=").append(actionUrl).append(",sortName=").append(sortName).append(",queryMode=").append(queryMode).append(",entityName=").append(entityName).append(", cssTheme=").append(cssTheme).append(",treeField=").append(treeField).append(",btnCls=").append(btnCls).append(",queryBuilder=").append(queryBuilder).append(",collapsible=").append(collapsible).append(",superQuery=").append(superQuery).append(",complexSuperQuery=").append(complexSuperQuery);
    key.append(",urlList=[");
    for (DataGridUrl url : urlList) {
        key.append(url.toString() + ",");
    }
    key.append("]");
    key.append(",toolBarList=[");
    for (DataGridUrl url : toolBarList) {
        key.append(url.toString() + ",");
    }
    key.append(",columnList=[");
    for (DataGridColumn col : columnList) {
        key.append(col.toString() + ",");
    }
    key.append("]");
    key.append(",columnValueList=[");
    for (ColumnValue col : columnValueList) {
        key.append(col.toString() + ",");
    }
    key.append("]");
    key.append(",columnStyleList=[");
    for (ColumnValue col : columnStyleList) {
        key.append(col.toString() + ",");
    }
    key.append("]");
    key.append(",sysTheme=").append(SysThemesUtil.getSysTheme(ContextHolderUtils.getRequest()).getStyle()).append(",brower_type=" + ContextHolderUtils.getSession().getAttribute("brower_type")).append("]");
    return key.toString();
}


public boolean isFit(){
    return fit;
}


public boolean isCollapsible(){
    return collapsible;
}


public void setToolBarList(List<DataGridUrl> toolBarList){
    this.toolBarList = toolBarList;
}


public void setCurPageNo(int curPageNo){
    this.curPageNo = curPageNo;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public void setIdField(String idField){
    this.idField = idField;
}


public void setConfigId(String configId){
    this.configId = configId;
}


public void setOpenFirstNode(boolean openFirstNode){
    this.openFirstNode = openFirstNode;
}


public void addAdvancedQuery(StringBuffer sb,String buttonSytle){
// update-begin-author:taoyan date:2180522 for:?????????????????????????????????---
/*FreemarkerHelper free = new FreemarkerHelper();
		Map<String, Object> mainConfig = new HashMap<String, Object>();
		mainConfig.put("queryCode", complexSuperQuery);
		mainConfig.put("tableName", name);
		String complexSuperQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/complexSuperQuery.ftl", mainConfig);
		appendLine(sb,complexSuperQuery);*/
// update-end-author:taoyan date:2180522 for:?????????????????????????????????---
}


public String getIdField(){
    return idField;
}


public void setDefUrl(String url,String title,String exp,String operationCode,String urlStyle,String urlclass,String urlfont,boolean inGroup){
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setUrl(url);
    dataGridUrl.setType(OptTypeDirection.Deff);
    dataGridUrl.setExp(exp);
    // update-begin-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    dataGridUrl.setInGroup(inGroup);
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    // update--begin--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    if (checkBrowerIsNotIE()) {
        dataGridUrl.setUrlStyle(urlStyle);
        // update--begin--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
        dataGridUrl.setUrlclass(urlclass);
        dataGridUrl.setUrlfont(urlfont);
    // update--end--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
    } else if (StringUtil.isEmpty(urlclass) || !"ace_button".equals(urlclass)) {
        dataGridUrl.setUrlStyle(urlStyle);
    }
    // update--end--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    installOperationCode(dataGridUrl, operationCode, urlList);
}


public String getName(){
    return name;
}


public void setActionUrl(String actionUrl){
    this.actionUrl = actionUrl;
}


public String extendAttribute(String field){
    if (StringUtil.isEmpty(field)) {
        return "";
    }
    field = dealSyscode(field, 1);
    StringBuilder re = new StringBuilder();
    try {
        JSONObject obj = JSONObject.fromObject(field);
        Iterator it = obj.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            JSONObject nextObj = null;
            try {
                nextObj = ((JSONObject) obj.get(key));
                Iterator itvalue = nextObj.keys();
                re.append(key + "=" + "\"");
                if (nextObj.size() <= 1) {
                    String onlykey = String.valueOf(itvalue.next());
                    if ("value".equals(onlykey)) {
                        re.append(nextObj.get(onlykey) + "");
                    } else {
                        re.append(onlykey + ":" + nextObj.get(onlykey) + "");
                    }
                } else {
                    while (itvalue.hasNext()) {
                        String multkey = String.valueOf(itvalue.next());
                        String multvalue = nextObj.getString(multkey);
                        re.append(multkey + ":" + multvalue + ";");
                    }
                    re.deleteCharAt(re.length() - 1);
                }
                re.append("\" ");
            } catch (Exception e) {
                re.append(key + "=" + "\"");
                re.append(obj.get(key) + "\"");
                re.append("\" ");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "";
    }
    return dealSyscode(re.toString(), 2);
}


public void getFilterFields(StringBuffer sb){
    if (this.isFilterBtn()) {
        StringBuffer ffs = new StringBuffer();
        int index = 0;
        for (DataGridColumn column : columnList) {
            if (column.getField().equals("opt")) {
                continue;
            }
            if (index != 0) {
                ffs.append(",");
            }
            index++;
            String filterType = column.getFilterType();
            ffs.append("{");
            ffs.append("field:'" + column.getField() + "',");
            ffs.append("type:'" + filterType + "',");
            if ("combobox".equals(filterType)) {
                ffs.append("options:{");
                ffs.append("panelHeight:'auto',");
                ffs.append("data:[{value:'',text:'All'}");
                for (ColumnValue columnValue : columnValueList) {
                    if (columnValue.getName().equals(column.getField())) {
                        String[] value = columnValue.getValue().split(",");
                        String[] text = columnValue.getText().split(",");
                        for (int k = 0; k < value.length; k++) {
                            ffs.append(",{value:'" + value[k] + "',text:'" + text[k] + "'}");
                        }
                        break;
                    }
                }
                ffs.append("],");
                // if (value == ''){}$('#"+name+"').datagrid('removeFilterRule', '"+column.getField()+"');
                ffs.append("onChange:function(value){");
                // else {
                // }
                ffs.append("$('#" + name + "').datagrid('addFilterRule', {field: '" + column.getField() + "',op: 'equal',value: value});");
                // option-end
                ffs.append("$('#" + name + "').datagrid('doFilter');}}");
            } else {
                ffs.append("options:{precision:1},");
                if ("numberbox".equals(filterType) || "datebox".equals(filterType) || "datetimebox".equals(filterType)) {
                    ffs.append("op:['equal','lessorequal','greaterorequal']");
                } else {
                    ffs.append("op:['equal','contains']");
                }
            }
            ffs.append("}");
        }
        sb.append("function " + name + "Filter(){$('#" + name + "').datagrid('enableFilter',[" + ffs.toString() + "]);}");
    }
}


public void installOperationCode(DataGridUrl dataGridUrl,String operationCode,List optList){
    if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
        optList.add(dataGridUrl);
    } else if (!oConvertUtils.isEmpty(operationCode)) {
        Set<String> operationCodes = (Set<String>) super.pageContext.getRequest().getAttribute(Globals.OPERATIONCODES);
        if (null != operationCodes) {
            List<String> operationCodesStr = new ArrayList<String>();
            for (String MyoperationCode : operationCodes) {
                if (oConvertUtils.isEmpty(MyoperationCode))
                    break;
                systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                TSOperation operation = systemService.getEntity(TSOperation.class, MyoperationCode);
                operationCodesStr.add(operation.getOperationcode());
            }
            if (!operationCodesStr.contains(operationCode)) {
                optList.add(dataGridUrl);
            }
        }
    } else {
        optList.add(dataGridUrl);
    }
}


public String getOnClick(){
    return onClick;
}


public void setFunUrl(String title,String exp,String funname,String operationCode,String urlStyle,String urlclass,String urlfont,boolean inGroup){
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setType(OptTypeDirection.Fun);
    dataGridUrl.setExp(exp);
    dataGridUrl.setFunname(funname);
    // update-begin-author:taoYan date:20180524 for:??????????????????---
    dataGridUrl.setInGroup(inGroup);
    // update-end-author:taoYan date:20180524 for:??????????????????---
    // update--begin--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    if (checkBrowerIsNotIE()) {
        dataGridUrl.setUrlStyle(urlStyle);
        // update-start--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
        dataGridUrl.setUrlclass(urlclass);
        dataGridUrl.setUrlfont(urlfont);
    // update-start--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
    } else if (StringUtil.isEmpty(urlclass) || !"ace_button".equals(urlclass)) {
        dataGridUrl.setUrlStyle(urlStyle);
    }
    // update--end--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    installOperationCode(dataGridUrl, operationCode, urlList);
}


public void setOnClick(String onClick){
    this.onClick = onClick;
}


public void addSuperQuery(StringBuffer sb,String buttonSytle,List<DataGridColumn> columnList){
    FreemarkerHelper free = new FreemarkerHelper();
    Map<String, Object> mainConfig = new HashMap<String, Object>();
    mainConfig.put("fields", columnList);
    mainConfig.put("tableName", name);
    // update-begin-Author:xuelin  Date:20171211 for???TASK #2441 ?????????????????????????????????????????????????????????????????????????????????-----
    mainConfig.put("valueList", columnValueList);
    // update-end-Author:xuelin  Date:20171211 for???TASK #2441 ?????????????????????????????????????????????????????????????????????????????????-----
    String superQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/superquery.ftl", mainConfig);
    appendLine(sb, superQuery);
}


public boolean isQueryBuilder(){
    return queryBuilder;
}


public boolean isShowText(){
    return showText;
}


public String getTitle(){
    return title;
}


public String getAutoSpan(String filed,String extend){
    String id = filed.replaceAll("\\.", "_");
    StringBuffer nsb = new StringBuffer();
    nsb.append("<script type=\"text/javascript\">");
    nsb.append("$(document).ready(function() {").append("$(\"#" + getEntityName() + "_" + id + "\").autocomplete(\"commonController.do?getAutoList\",{").append("max: 5,minChars: 2,width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{").append("featureClass : \"P\",style : \"full\",	maxRows : 10,labelField : \"" + filed + "\",valueField : \"" + filed + "\",").append("searchField : \"" + filed + "\",entityName : \"" + getEntityName() + "\",trem: function(){return $(\"#" + getEntityName() + "_" + id + "\").val();}}");
    nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
    nsb.append(",formatItem:function(row, i, max){return row['" + filed + "'];} ");
    nsb.append("}).result(function (event, row, formatted) {");
    nsb.append("$(\"#" + getEntityName() + "_" + id + "\").val(row['" + filed + "']);}); });").append("</script>").append("<input class=\"inuptxt\" type=\"text\" id=\"" + getEntityName() + "_" + id + "\" name=\"" + filed + "\"  " + extend + StringUtil.replace(" nullmsg=\"\" errormsg=\"{0}\"/>", "{0}", MutiLangUtil.getLang("input.error")));
    return nsb.toString();
}


public void setSearchFields(String searchFields){
    this.searchFields = searchFields;
}


public String getCssTheme(){
    return cssTheme;
}


public void setMap(Map<String,Object> map){
    this.map = map;
}


public DataGridTag getDataGridTag(){
    return this;
}


public StringBuffer aceStyleTable(){
    String grid = "";
    StringBuffer sb = new StringBuffer();
    // update-begin--Author:xuelin  Date:20170525 for???TASK #2021 ???UI?????????????????????????????? ?????????????????? ????????????Boostrap.css ?????????Boostrap-btn.css-------------------
    if (btnCls != null && btnCls.indexOf("bootstrap") == 0) {
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/bootstrap/css/bootstrap-btn.css\" type=\"text/css\"></link>");
    }
    // update-end--Author:xuelin  Date:20170525 for???TASK #2021 ???UI?????????????????????????????? ?????????????????? ????????????Boostrap.css ?????????Boostrap-btn.css--------------------
    width = (width == null) ? "auto" : width;
    height = (height == null) ? "auto" : height;
    // sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/metro/main.css\" />");
    sb.append("<script type=\"text/javascript\">");
    sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
    sb.append(this.getNoAuthOperButton());
    if (treegrid) {
        grid = "treegrid";
        sb.append("$(\'#" + name + "\').treegrid({");
        sb.append("idField:'id',");
        sb.append("treeField:'text',");
    } else {
        grid = "datagrid";
        sb.append("$(\'#" + name + "\').datagrid({");
        // update-begin--Author:xuelin  Date:20171128 for???[#2421]???bug????????????????????? --?????????????????? --------------------
        if (this.isFilter()) {
            sb.append("onHeaderContextMenu: function(e, field){headerMenu(e, field);},");
        }
        // update-end--Author:xuelin  Date:20171128 for???[#2421]???bug????????????????????? --?????????????????? --------------------
        sb.append("idField: '" + idField + "',");
    }
    if (title != null) {
        sb.append("title: \'" + title + "\',");
    }
    if (autoLoadData)
        sb.append("url:\'" + actionUrl + "&field=" + fields + "\',");
    else
        sb.append("url:\'',");
    if (StringUtils.isNotEmpty(rowStyler)) {
        sb.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
    }
    if (StringUtils.isNotEmpty(extendParams)) {
        sb.append(extendParams);
    }
    if (fit) {
        sb.append("fit:true,");
    } else {
        sb.append("fit:false,");
    }
    sb.append(StringUtil.replaceAll("loadMsg: \'{0}\',", "{0}", MutiLangUtil.getLang("common.data.loading")));
    sb.append("striped:true,pageSize: " + pageSize + ",");
    sb.append("pagination:" + pagination + ",");
    sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
    if (StringUtils.isNotBlank(sortName)) {
        sb.append("sortName:'" + sortName + "',");
    }
    sb.append("sortOrder:'" + sortOrder + "',");
    sb.append("rownumbers:true,");
    if (singleSelect == null) {
        sb.append("singleSelect:" + !checkbox + ",");
    } else {
        sb.append("singleSelect:" + singleSelect + ",");
    }
    if (fitColumns) {
        sb.append("fitColumns:true,");
    } else {
        sb.append("fitColumns:false,");
    }
    sb.append("showFooter:true,");
    sb.append("frozenColumns:[[");
    this.getField(sb, 0);
    sb.append("]],");
    sb.append("columns:[[");
    this.getField(sb);
    sb.append("]],");
    sb.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + grid + "(\"clearSelections\");");
    // update-begin--Author:xuelin  Date:20171228 for???TASK #2462 ???bug???Easyui Datagrid rownumbers???????????????????????????????????????????????????(???)
    // update-begin--Author:xuelin  Date:20170613 for???TASK #2109 ??????????????????????????????????????????--------------------
    // sb.append(" $(this).datagrid(\"fixRownumber\");");
    // update-end--Author:xuelin  Date:20170613 for???TASK #2109 ??????????????????????????????????????????----------------------
    // update-end--Author:xuelin  Date:20171228 for???TASK #2462 ???bug???Easyui Datagrid rownumbers???????????????????????????????????????????????????(???)
    if (openFirstNode && treegrid) {
        sb.append(" if(data==null){");
        sb.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
        sb.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
    }
    if (StringUtil.isNotEmpty(onLoadSuccess)) {
        sb.append(onLoadSuccess + "(data);");
    }
    sb.append("},");
    if (StringUtil.isNotEmpty(onDblClick)) {
        sb.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
    }
    if (treegrid) {
        sb.append("onClickRow:function(rowData){");
    } else {
        sb.append("onClickRow:function(rowIndex,rowData){");
    }
    /**
     * ???????????????
     */
    sb.append("rowid=rowData.id;");
    sb.append("gridname=\'" + name + "\';");
    if (StringUtil.isNotEmpty(onClick)) {
        if (treegrid) {
            sb.append("" + onClick + "(rowData);");
        } else {
            sb.append("" + onClick + "(rowIndex,rowData);");
        }
    }
    sb.append("}");
    sb.append("});");
    this.setPager(sb, grid);
    sb.append("try{restoreheader();}catch(ex){}");
    sb.append("});");
    sb.append("function reloadTable(){");
    sb.append("try{");
    sb.append("	$(\'#\'+gridname).datagrid(\'reload\');");
    sb.append("	$(\'#\'+gridname).treegrid(\'reload\');");
    sb.append("}catch(ex){}");
    sb.append("}");
    sb.append("function reload" + name + "(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
    sb.append("function get" + name + "Selected(field){return getSelected(field);}");
    sb.append("function getSelected(field){" + "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value=\'\';" + "}" + "return value;" + "}");
    sb.append("function get" + name + "Selections(field){" + "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid + "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');" + "return ids" + "};");
    sb.append("function getSelectRows(){");
    sb.append("	return $(\'#" + name + "\').datagrid('getChecked');}");
    // update-begin longjb 20150515 for:???????????????????????????????????????
    sb.append(" function saveHeader(){");
    sb.append(" var columnsFields =null;var easyextends=false;try{columnsFields = $('#" + name + "').datagrid('getColumns');easyextends=true;");
    sb.append("}catch(e){columnsFields =$('#" + name + "').datagrid('getColumnFields');}");
    sb.append("	var cols = storage.get( '" + name + "hiddenColumns');var init=true;	if(cols){init =false;} " + "var hiddencolumns = [];for(var i=0;i< columnsFields.length;i++) {if(easyextends){");
    sb.append("hiddencolumns.push({field:columnsFields[i].field,hidden:columnsFields[i].hidden});}else{");
    sb.append(" var columsDetail = $('#" + name + "').datagrid(\"getColumnOption\", columnsFields[i]); ");
    sb.append("if(init){hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:(columsDetail.hidden==true?false:true)});}else{");
    sb.append("for(var j=0;j<cols.length;j++){");
    sb.append("		if(cols[j].field==columsDetail.field){");
    sb.append("					hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:cols[j].visible});");
    sb.append("		}");
    sb.append("}");
    sb.append("}} }");
    sb.append("storage.set( '" + name + "hiddenColumns',JSON.stringify(hiddencolumns));");
    sb.append("}");
    sb.append("function restoreheader(){");
    sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
    sb.append("for(var i=0;i<cols.length;i++){");
    sb.append("	try{");
    sb.append("if(cols.visible!=false)$('#" + name + "').datagrid((cols[i].hidden==true?'hideColumn':'showColumn'),cols[i].field);");
    sb.append("}catch(e){");
    sb.append("}");
    sb.append("}");
    sb.append("}");
    sb.append("function resetheader(){");
    sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
    sb.append("for(var i=0;i<cols.length;i++){");
    sb.append("	try{");
    sb.append("  $('#" + name + "').datagrid((cols.visible==false?'hideColumn':'showColumn'),cols[i].field);");
    sb.append("}catch(e){");
    sb.append("}");
    sb.append("}");
    sb.append("}");
    // update-end longjb 201515 for:???????????????????????????????????????
    if (columnList.size() > 0) {
        sb.append("function " + name + "search(){");
        sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
        sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
        sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});" + "}");
        // ????????????????????????
        sb.append("function dosearch(params){");
        sb.append("var jsonparams=$.parseJSON(params);");
        sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',queryParams:jsonparams});" + "}");
        // searchbox???????????????
        searchboxFun(sb, grid);
        // ????????????
        sb.append("function EnterPress(e){");
        sb.append("var e = e || window.event;");
        sb.append("if(e.keyCode == 13){ ");
        sb.append(name + "search();");
        sb.append("}}");
        // update-begin Robin 20140426 for:????????????
        sb.append("function searchReset(name){");
        sb.append(" $(\"#" + name + "tb\").find(\":input\").val(\"\");");
        String func = name.trim() + "search();";
        sb.append(func);
        sb.append("}");
    }
    sb.append("</script>");
    sb.append("<table width=\"100%\"   id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
    sb.append("<div id=\"" + name + "tb\" style=\"padding:3px; height: auto\">");
    if (hasQueryColum(columnList)) {
        sb.append("<div name=\"searchColums\">");
        sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
        // ???????????????????????????
        if ("group".equals(getQueryMode())) {
            for (DataGridColumn col : columnList) {
                if (col.isQuery()) {
                    sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
                    sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + col.getTitle() + "???</span>");
                    if ("single".equals(col.getQueryMode())) {
                        if (!StringUtil.isEmpty(col.getReplace())) {
                            sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                            sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                            String[] test = col.getReplace().split(",");
                            String text = "";
                            String value = "";
                            for (String string : test) {
                                String lang_key = string.split("_")[0];
                                text = MutiLangUtil.getLang(lang_key);
                                value = string.split("_")[1];
                                sb.append("<option value =\"" + value + "\">" + text + "</option>");
                            }
                            sb.append("</select>");
                        } else if (!StringUtil.isEmpty(col.getDictionary())) {
                            if (col.getDictionary().contains(",")) {
                                String[] dic = col.getDictionary().split(",");
                                String sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
                                // -- update-begin--Author:zhoujf  Date:20180319 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                                if (!StringUtil.isEmpty(col.getDictCondition())) {
                                    sql += " " + col.getDictCondition();
                                }
                                // -- update-end--Author:zhoujf  Date:20180319 for???TASK #2560 ???UI???????????????column????????????????????????dictCondition???sql??????  --
                                systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                                List<Map<String, Object>> list = systemService.findForJdbc(sql);
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                                for (Map<String, Object> map : list) {
                                    sb.append(" <option value=\"" + map.get("field") + "\">");
                                    sb.append(map.get("text"));
                                    sb.append(" </option>");
                                }
                                sb.append("</select>");
                            } else {
                                List<TSType> types = ResourceUtil.getCacheTypes(col.getDictionary().toLowerCase());
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getLang("common.please.select")));
                                for (TSType type : types) {
                                    sb.append(" <option value=\"" + type.getTypecode() + "\">");
                                    sb.append(MutiLangUtil.getLang(type.getTypename()));
                                    sb.append(" </option>");
                                }
                                sb.append("</select>");
                            }
                        } else if (col.isAutocomplete()) {
                            sb.append(getAutoSpan(col.getField().replaceAll("_", "\\."), extendAttribute(col.getExtend())));
                        } else {
                            sb.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  " + extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                        }
                    } else if ("group".equals(col.getQueryMode())) {
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"   " + extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                        sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                        sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"   " + extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                    }
                    sb.append("</span>");
                }
            }
        }
        sb.append("</div>");
    }
    // update-start--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
    if (toolBarList == null || toolBarList.size() == 0) {
        // update-end--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
        sb.append("<div style=\"height:0px;\" >");
    } else {
        // TODO
        // update-begin--Author:xuelin  Date:20170524 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
        sb.append("<div style=\"border-bottom-width:0;height:auto;\" class=\"datagrid-toolbar\">");
    // update-end--Author:xuelin  Date:20170524 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    }
    sb.append("<span style=\"float:left;\" >");
    if (toolBarList.size() > 0) {
        for (DataGridUrl toolBar : toolBarList) {
            // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
            if (btnCls != null && !btnCls.equals("easyui")) {
                // ??????????????? bootstrap????????????
                if (btnCls.indexOf("bootstrap") == 0) {
                    if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                        sb.append("<button class=\"" + btnCls.replace("bootstrap", "").trim() + "\" ");
                    } else {
                        sb.append("<button class=\"btn btn-info btn-xs\" ");
                    }
                    if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
                        sb.append("onclick=" + toolBar.getOnclick() + "");
                    } else {
                        sb.append("onclick=\"" + toolBar.getFunname() + "(");
                        if (!toolBar.getFunname().equals("doSubmit")) {
                            sb.append("\'" + toolBar.getTitle() + "\',");
                        }
                        String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                        String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                        sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
                    }
                    sb.append("><i class=\"" + toolBar.getIcon() + "\"></i><span class=\"bigger-110 no-text-shadow\">" + toolBar.getTitle() + "</span></button>");
                } else {
                    sb.append("<a href=\"#\" class=\"" + btnCls + " " + toolBar.getIcon() + "\" ");
                    if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
                        sb.append("onclick=" + toolBar.getOnclick() + "");
                    } else {
                        sb.append("onclick=\"" + toolBar.getFunname() + "(");
                        if (!toolBar.getFunname().equals("doSubmit")) {
                            sb.append("\'" + toolBar.getTitle() + "\',");
                        }
                        String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                        String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                        sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
                    }
                    sb.append(">" + toolBar.getTitle() + "</a>");
                }
            } else if (btnCls == null || btnCls.equals("easyui")) {
                // easyUI????????????
                sb.append("<a href=\"#\" class=\"button\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
                if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
                    sb.append("onclick=" + toolBar.getOnclick() + "");
                } else {
                    sb.append("onclick=\"" + toolBar.getFunname() + "(");
                    if (!toolBar.getFunname().equals("doSubmit")) {
                        sb.append("\'" + toolBar.getTitle() + "\',");
                    }
                    String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                    String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                    sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
                }
                sb.append(">" + toolBar.getTitle() + "</a>");
            }
        // update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
        }
    }
    sb.append("</span>");
    if ("group".equals(getQueryMode()) && hasQueryColum(columnList)) {
        // ???????????????????????????
        // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
        sb.append("<span style=\"float:right\">");
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ??????????????? bootstrap????????????
            if (btnCls.indexOf("bootstrap") == 0) {
                String defalutCls = "btn btn-info btn-xs";
                if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                    defalutCls = btnCls.replace("bootstrap", "").trim();
                }
                sb.append("<button class=\"" + defalutCls + "\" onclick=\"" + name + "search()\">");
                sb.append("<i class=\"fa fa-search\"></i>");
                sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.query") + "</span>");
                sb.append("</button>");
                sb.append("<button class=\"" + defalutCls + "\" onclick=\"searchReset('" + name + "')\">");
                sb.append("<i class=\"fa fa-refresh\"></i>");
                sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.reset") + "</span>");
                sb.append("</button>");
                if (queryBuilder) {
                    sb.append("<button class=\"" + defalutCls + "\" onclick=\"queryBuilder()\">");
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.querybuilder") + "</span>");
                    sb.append("</button>");
                }
                // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
                if (superQuery) {
                    sb.append("<button class=\"" + defalutCls + "\" onclick=\"queryBuilder()\">");
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.superquery") + "</span>");
                    sb.append("</button>");
                }
                // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
                // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
                if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                    // update-begin-author:taoyan date:2180522 for:????????????????????????????????????----
                    sb.append("<button class=\"" + defalutCls + "\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">");
                    // update-end-author:taoyan date:2180522 for:????????????????????????????????????----
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.advancedQuery") + "</span>");
                    sb.append("</button>");
                }
            // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            } else {
                // ???????????????
                sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"" + name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getLang("common.query")));
                sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"searchReset('" + name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.reset")));
                if (queryBuilder) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.querybuilder")));
                }
                // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
                if (superQuery) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superquery")));
                }
                // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
                // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
                if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">" + MutiLangUtil.getLang("common.advancedQuery") + "</a>");
                }
            // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            }
        } else {
            // ????????????easyUI????????????
            sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"" + name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getLang("common.query")));
            sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-reload\" onclick=\"searchReset('" + name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.reset")));
            if (queryBuilder) {
                sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.querybuilder")));
            }
            // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
            if (superQuery) {
                sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superQuery")));
            }
            // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
            // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
            if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
                // update-begin-author:taoyan date:2180522 for:????????????????????????????????????----
                sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"" + name + "SuperQuery('" + complexSuperQuery + "')\">" + MutiLangUtil.getLang("common.advancedQuery") + "</a>");
            // update-end-author:taoyan date:2180522 for:????????????????????????????????????----
            }
        // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
        }
        sb.append("</span>");
    // update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
    } else if ("single".equals(getQueryMode()) && hasQueryColum(columnList)) {
        // ????????????????????????
        sb.append("<span style=\"float:right\">");
        sb.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name + StringUtil.replaceAll("searchbox,prompt:\'{0}\',menu:\'#", "{0}", MutiLangUtil.getLang("common.please.input.keyword")) + name + "mm\'\"></input>");
        sb.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
        for (DataGridColumn col : columnList) {
            if (col.isQuery()) {
                sb.append("<div data-options=\"name:\'" + col.getField().replaceAll("_", "\\.") + "\',iconCls:\'icon-ok\' " + extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
            }
        }
        sb.append("</div>");
        sb.append("</span>");
    }
    // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    sb.append("<div style=\"clear:both\"></div>");
    // update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    sb.append("</div>");
    if (queryBuilder) {
        // update-end--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? -------------------
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ??????????????? bootstrap????????????
            addQueryBuilder(sb, btnCls);
        } else {
            // ????????????easyUI????????????
            addQueryBuilder(sb, "button");
        }
    // update-end--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? -------------------
    }
    // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
    if (superQuery) {
        if (btnCls != null && !btnCls.equals("easyui")) {
            addSuperQuery(sb, btnCls, columnList);
        } else {
            addSuperQuery(sb, "button", columnList);
        }
    }
    // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
    // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
    if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
        if (btnCls != null && !btnCls.equals("easyui")) {
            addAdvancedQuery(sb, btnCls);
        } else {
            addAdvancedQuery(sb, "button");
        }
    }
    // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
    return sb;
}


public String getActionUrl(){
    return actionUrl;
}


public boolean isPagination(){
    return pagination;
}


public String extendAttributeOld(String field){
    StringBuffer sb = new StringBuffer();
    // ??????????????????
    if (!StringUtils.isBlank(field)) {
        Gson gson = new Gson();
        Map<String, String> mp = gson.fromJson(field, Map.class);
        for (Map.Entry<String, String> entry : mp.entrySet()) {
            // update-author???scott     for:jeecg-bpm??????-    date:20151557
            sb.append(entry.getKey() + "=" + gson.toJson(entry.getValue()) + "\"");
        }
    }
    return sb.toString();
}


public void setTitle(String title){
    this.title = title;
}


public void setStyleColumn(String field,String text,String value){
    ColumnValue columnValue = new ColumnValue();
    columnValue.setName(field);
    columnValue.setText(text);
    columnValue.setValue(value);
    columnStyleList.add(columnValue);
}


public void setHeight(String height){
    this.height = height;
}


public Map<String,Object> getMap(){
    return map;
}


public int getPageSize(){
    return pageSize;
}


public String getFields(){
    return fields;
}


public List<DataGridColumn> getColumnList(){
    return columnList;
}


public String getTreeField(){
    return treeField;
}


public boolean getIsShowSubGrid(){
    return isShowSubGrid;
}


public String getSearchFields(){
    return searchFields;
}


public void setFilterBtn(boolean filterBtn){
    this.filterBtn = filterBtn;
}


public String getComponent(){
    return component;
}


public void setOnDblClick(String onDblClick){
    this.onDblClick = onDblClick;
}


public void getFun(StringBuffer sb,DataGridColumn column){
    String url = column.getUrl();
    url = formatUrlPlus(url);
    sb.append("var href=\"<a style=\'color:red\' href=\'#\' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + url + "')>\";");
    sb.append("return href+value+\'</a>\';");
}


public String getBtnCls(){
    return btnCls;
}


public void setSingleSelect(Boolean singleSelect){
    this.singleSelect = singleSelect;
}


public void setShowPageList(boolean showPageList){
    this.showPageList = showPageList;
}


public boolean isFitColumns(){
    return fitColumns;
}


public void loadToolbar(DataGridUrl toolBar,StringBuffer sb){
    // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
    if (btnCls != null && !btnCls.equals("easyui")) {
        // ??????????????? bootstrap????????????
        if (btnCls.indexOf("bootstrap") == 0) {
            if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                sb.append("<button class=\"" + btnCls.replace("bootstrap", "").trim() + "\" ");
            } else {
                sb.append("<button class=\"btn btn-default btn-xs\" ");
            }
            // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
            if (StringUtil.isNotEmpty(toolBar.getId())) {
                sb.append(" id=\"");
                sb.append(toolBar.getId());
                sb.append("\" ");
            }
            // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
            if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
                sb.append("onclick=" + toolBar.getOnclick() + "");
            } else {
                sb.append("onclick=\"" + toolBar.getFunname() + "(");
                if (!toolBar.getFunname().equals("doSubmit")) {
                    sb.append("\'" + toolBar.getTitle() + "\',");
                }
                String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
            }
            sb.append("><i class=\"" + toolBar.getIcon() + "\"></i><span class=\"bigger-110 no-text-shadow\">" + toolBar.getTitle() + "</span></button>");
        } else {
            sb.append("<a href=\"#\" class=\"" + btnCls + " " + toolBar.getIcon() + "\" ");
            // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
            if (StringUtil.isNotEmpty(toolBar.getId())) {
                sb.append(" id=\"");
                sb.append(toolBar.getId());
                sb.append("\" ");
            }
            // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
            if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
                sb.append("onclick=" + toolBar.getOnclick() + "");
            } else {
                sb.append("onclick=\"" + toolBar.getFunname() + "(");
                if (!toolBar.getFunname().equals("doSubmit")) {
                    sb.append("\'" + toolBar.getTitle() + "\',");
                }
                String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
            }
            sb.append(">" + toolBar.getTitle() + "</a>");
        }
    } else if (btnCls == null || btnCls.equals("easyui")) {
        // easyUI????????????
        sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
        // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
        if (StringUtil.isNotEmpty(toolBar.getId())) {
            sb.append(" id=\"");
            sb.append(toolBar.getId());
            sb.append("\" ");
        }
        // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
        if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
            sb.append("onclick=" + toolBar.getOnclick() + "");
        } else {
            sb.append("onclick=\"" + toolBar.getFunname() + "(");
            if (!toolBar.getFunname().equals("doSubmit")) {
                sb.append("\'" + toolBar.getTitle() + "\',");
            }
            String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
            String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
            sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
        }
        sb.append(">" + toolBar.getTitle() + "</a>");
    }
// update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button??????????????????-------------------
}


public String getOnDblClick(){
    return onDblClick;
}


public String formatUrlPlus(String url){
    boolean isTrans = false;
    // tsBlackListController.do?doDel&id=#{id}
    if (url.indexOf("#{") >= 0) {
        isTrans = true;
        url = url.replace("#{", "\"+rec.");
        url = url.replace("}", "+\"");
    }
    // tsBlackListController.do?doDel&id={id}
    if (url.indexOf("{") >= 0 && !isTrans) {
        url = url.replace("{", "\"+rec.");
        url = url.replace("}", "+\"");
    }
    // tsBlackListController.do?doDel&id=#id#
    if (url.indexOf("#") > 0 && !isTrans) {
        Pattern p = Pattern.compile("#", Pattern.CASE_INSENSITIVE);
        StringBuffer sb = new StringBuffer();
        Matcher m = p.matcher(url);
        int i = 0;
        while (m.find()) {
            i++;
            if (i % 2 == 0) {
                m.appendReplacement(sb, "+\"");
            } else {
                m.appendReplacement(sb, "\"+rec.");
            }
        }
        m.appendTail(sb);
        url = sb.toString();
    }
    return url;
}


public void setColumn(String name,String text,String value){
    ColumnValue columnValue = new ColumnValue();
    columnValue.setName(name);
    columnValue.setText(text);
    columnValue.setValue(value);
    columnValueList.add(columnValue);
}


public List<DataGridUrl> getUrlList(){
    return urlList;
}


public String getStyle(){
    return style;
}


public void setDelUrl(String url,String title,String message,String exp,String funname,String operationCode,String urlStyle,String urlclass,String urlfont,boolean inGroup){
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setUrl(url);
    dataGridUrl.setType(OptTypeDirection.Del);
    dataGridUrl.setMessage(message);
    dataGridUrl.setExp(exp);
    dataGridUrl.setFunname(funname);
    // update-begin-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    dataGridUrl.setInGroup(inGroup);
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    // update--begin--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    if (checkBrowerIsNotIE()) {
        dataGridUrl.setUrlStyle(urlStyle);
        // update-start--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
        dataGridUrl.setUrlclass(urlclass);
        dataGridUrl.setUrlfont(urlfont);
    // update-start--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
    } else if (StringUtil.isEmpty(urlclass) || !"ace_button".equals(urlclass)) {
        dataGridUrl.setUrlStyle(urlStyle);
    }
    // update--end--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    installOperationCode(dataGridUrl, operationCode, urlList);
}


public StringBuffer datatables(){
    StringBuffer sb = new StringBuffer();
    // update--begin--author:zhoujf date:20180428 for:TASK #2677 ???H+???????????????hplus ???????????????jar????????? ???????????????
    sb.append("<link href=\"plug-in/hplus/css/plugins/dataTables/dataTables.bootstrap.css\" rel=\"stylesheet\">");
    sb.append("<script src=\"plug-in/hplus/js/plugins/dataTables/jquery.dataTables.js\"></script>");
    // update--end--author:zhoujf date:20180428 for:TASK #2677 ???H+???????????????hplus ???????????????jar????????? ???????????????
    sb.append("<script type=\"text/javascript\">");
    sb.append("$(document).ready(function() {");
    sb.append("var oTable = $(\'#userList\').dataTable({");
    // sb.append(
    // "\"sDom\" : \"<\'row\'<\'span6\'l><\'span6\'f>r>t<\'row\'<\'span6\'i><\'span6\'p>>\",");
    // ???datatable??????????????????????????????????????????????????????"
    sb.append("\"bProcessing\" : true,");
    // ????????????"
    sb.append("\"bPaginate\" : true,");
    // ????????????full_numbers,"
    sb.append("\"sPaginationType\" : \"full_numbers\",");
    // ?????????????????????????????????"
    sb.append("\"bFilter\" : true,");
    // ????????????"
    sb.append("\"bSort\" : true, ");
    // ????????????"
    sb.append("\"bAutoWidth\" : true,");
    // ?????????????????????????????????????????????"
    sb.append("\"bLengthChange\" : true,");
    // ????????????"
    sb.append("\"bInfo\" : true,");
    sb.append("\"sAjaxSource\" : \"" + actionUrl + "&field=" + fields + "\",");
    // ?????????????????????????????????
    sb.append("\"bServerSide\" : true,");
    // ??????
    sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" _MENU_ ?????????\"," + "\"sZeroRecords\" : \"?????????????????????\"," + "\"sInfo\" : \"??? _START_ ??? _END_ ????????? ??? _TOTAL_ ???\"," + "\"sInfoEmtpy\" : \"????????????\"," + "\"sProcessing\" : \"??????????????????...\"," + "\"sSearch\" : \"??????\"," + "\"oPaginate\" : {" + "\"sFirst\" : \"??????\"," + "\"sPrevious\" : \"??????\", " + "\"sNext\" : \"??????\"," + "\"sLast\" : \"??????\"" + "}" + "},");
    // ??????????????????????????? \"data\" : {_dt_json : JSON.stringify(aoData)},
    sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
    // + "\"data\" : {_dt_json : JSON.stringify(aoData)},"
    sb.append("oSettings.jqXHR = $.ajax({" + "\"dataType\" : \'json\'," + "\"type\" : \"POST\"," + "\"url\" : sSource," + "\"data\" : aoData," + "\"success\" : fnCallback" + "});},");
    sb.append("\"aoColumns\" : [ ");
    int i = 0;
    for (DataGridColumn column : columnList) {
        i++;
        sb.append("{");
        sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
        if (column.getField().equals("opt")) {
            sb.append(",\"mData\":\"" + idField + "\"");
            sb.append(",\"sWidth\":\"20%\"");
            sb.append(",\"bSortable\":false");
            sb.append(",\"bSearchable\":false");
            sb.append(",\"mRender\" : function(data, type, rec) {");
            this.getOptUrl(sb);
            sb.append("}");
        } else {
            int colwidth = (column.getWidth() == null) ? column.getTitle().length() * 15 : column.getWidth();
            sb.append(",\"sName\":\"" + column.getField() + "\"");
            sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
            sb.append(",\"mData\":\"" + column.getField() + "\"");
            sb.append(",\"sWidth\":\"" + colwidth + "\"");
            sb.append(",\"bSortable\":" + column.isSortable() + "");
            // update-start-Author:zhangguoming  Date:20140921 for???TASK #458 ??????hidden=false?????????????????????????????????
            sb.append(",\"bVisible\":" + !column.isHidden() + "");
            // update-end-Author:zhangguoming  Date:20140921 for???TASK #458 ??????hidden=false?????????????????????????????????
            sb.append(",\"bSearchable\":" + column.isQuery() + "");
        }
        sb.append("}");
        if (i < columnList.size())
            sb.append(",");
    }
    sb.append("]" + "});" + "});" + "</script>");
    sb.append("<table width=\"100%\"  class=\"" + style + "\" id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
    return sb;
}


public List<ColumnValue> getColumnStyleList(){
    return columnStyleList;
}


public void setNowrap(boolean nowrap){
    this.nowrap = nowrap;
}


public void setStyle(String style){
    this.style = style;
}


public void setAutoLoadData(boolean autoLoadData){
    this.autoLoadData = autoLoadData;
}


public StringBuffer end(){
    // update-start--Author:yugwu  Date:20170830 for:TASK #2258 ??????????????????jeecg???jsp?????????????????????????????????????????????html?????????----
    StringBuffer sb = null;
    if (style.equals("easyui")) {
        sb = null;
    } else if ("jqgrid".equals(style)) {
        sb = jqGrid();
    } else {
        sb = datatables();
    }
    // update-end--Author:yugwu  Date:20170830 for:TASK #2258 ??????????????????jeecg???jsp?????????????????????????????????????????????html?????????----
    String grid = "";
    sb = new StringBuffer();
    // update-begin--Author:xuelin  Date:20170525 for???TASK #2021 ???UI?????????????????????????????? ?????????????????? ????????????Boostrap.css ?????????Boostrap-btn.css-------------------
    if (btnCls != null && btnCls.indexOf("bootstrap") == 0) {
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/bootstrap/css/bootstrap-btn.css\" type=\"text/css\"></link>");
    }
    // update-begin-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    boolean hasGroup = hasGroup();
    if (hasGroup) {
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/optgroup.css\" type=\"text/css\"></link>");
    }
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    // update-end--Author:xuelin  Date:20170525 for???TASK #2021 ???UI?????????????????????????????? ?????????????????? ????????????Boostrap.css ?????????Boostrap-btn.css--------------------
    width = (width == null) ? "auto" : width;
    height = (height == null) ? "auto" : height;
    // --update--begin--Author:guoxianhui  Date:20171214 for???TASK #2450 ?????????????????????????????????
    if (!treegrid && isShowSubGrid) {
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-detailview.js\"></script>");
    }
    // --update--end--Author:guoxianhui  Date:20171214 for???TASK #2450 ?????????????????????????????????
    sb.append("<script type=\"text/javascript\">");
    // --update--begin--Author:guoxianhui  Date:20171214 for???TASK #2450 ?????????????????????????????????
    if (!treegrid && isShowSubGrid) {
        loadSubData(configId);
        sb.append("function  detailFormatterFun(){");
        sb.append("var s = '<div class=\"orderInfoHidden\" style=\"padding:2px;\">'+");
        // --update--begin--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
        sb.append("'<div class=\"easyui-tabs\"   style=\"height:230px;width:800px;\">'+");
        // --update--begin--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
        String[] subtableids = null;
        if (head.getSubTableStr() != null && head.getSubTableStr().length() >= 0) {
            subtableids = head.getSubTableStr().split(",");
            for (String subtable : subtableids) {
                sb.append("'<div title=\"" + ((CgSubTableVO) tableData.get(subtable)).getHead().getContent() + "\" style=\"padding:2px;\">'+");
                sb.append("'<table class=\"" + ((CgSubTableVO) tableData.get(subtable)).getHead().getTableName() + "tablelines\" ></table>'+");
                // --update--begin--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
                sb.append("'</div>'+");
            // --update--begin--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
            }
        }
        sb.append("'</div>'+");
        sb.append("'</div>'; return s;}");
        sb.append("function onExpandRowFun(index,row){");
        sb.append("var tabs = $(this).datagrid('getRowDetail',index).find('div.easyui-tabs');");
        sb.append("tabs.tabs();");
        if (subtableids != null) {
            for (String ss : subtableids) {
                CgSubTableVO submap = ((CgSubTableVO) tableData.get(ss));
                String linkid = "";
                String subfield = "";
                String columns = "";
                List<Map<String, Object>> subfieldlist = submap.getFieldList();
                for (Map<String, Object> map : subfieldlist) {
                    subfield += map.get("field_name") + ",";
                    // --update--begin--Author:Yandong  Date:20180413 for???TASK #2635 ?????????????????????????????????????????????????????????
                    // if(map.get("main_field")!=null && map.get("main_field").toString().length()>0 && "".equals(linkid)){
                    // linkid = (String)map.get("field_name");
                    // }
                    // --update--end--Author:Yandong  Date:20180413 for???TASK #2635 ?????????????????????????????????????????????????????????
                    // --update--begin--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
                    columns += "{title:'" + map.get("content") + "',field:'" + map.get("field_name") + "',align:'left',width:50},";
                // --update--end--Author:Yandong  Date:20180410 for???TASK #2450 ?????????????????????????????????
                }
                // --update--begin--Author:Yandong  Date:20180413 for???TASK #2635 ?????????????????????????????????????????????????????????
                List<CgFormFieldEntity> subFields = submap.getHead().getColumns();
                for (CgFormFieldEntity subField : subFields) {
                    if (StringUtils.isNotBlank(subField.getMainField())) {
                        linkid = subField.getFieldName();
                        break;
                    }
                }
                // --update--end--Author:Yandong  Date:20180413 for???TASK #2635 ?????????????????????????????????????????????????????????
                sb.append("var " + submap.getHead().getTableName() + "durl = 'cgAutoListController.do?datagrid&configId=" + submap.getHead().getTableName() + "&" + linkid + "='+row.id+'&field=" + subfield + "&page=1&rows=100';");
                sb.append("var " + submap.getHead().getTableName() + "tablelines = $(this).datagrid('getRowDetail',index).find('table." + submap.getHead().getTableName() + "tablelines');");
                sb.append("" + submap.getHead().getTableName() + "tablelines.datagrid({");
                sb.append("singleSelect:true,");
                sb.append("loadMsg:'????????????',");
                sb.append("fitColumns:true,");
                sb.append("height:'180',");
                sb.append("pageSize : 50,");
                sb.append("pageList : [ 50, 150, 200, 250, 300 ],");
                sb.append("border:false,");
                sb.append("loadMsg:\"\",");
                sb.append("url: " + submap.getHead().getTableName() + "durl,");
                sb.append("idField : 'id',");
                sb.append("rownumbers : true,");
                sb.append("pagination : false,");
                sb.append("onLoadSuccess : function(a,b,c) {},");
                sb.append("columns:[[");
                sb.append(columns);
                sb.append("{field:'0000',hidden:true}");
                sb.append("]]");
                sb.append("});");
            }
        }
        sb.append("}");
    }
    // --update--end--Author:guoxianhui  Date:20171214 for???TASK #2450 ?????????????????????????????????
    sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
    sb.append(this.getNoAuthOperButton());
    if (treegrid) {
        grid = "treegrid";
        sb.append("$(\'#" + name + "\').treegrid({");
        sb.append("idField:'id',");
        if (StringUtils.isNotEmpty(treeField)) {
            sb.append("treeField:'" + treeField + "',");
        } else {
            sb.append("treeField:'text',");
        }
        // update-begin--Author:dangzhenghui  Date:20170531 for???TASK #2038 ???demo??????????????? ??????demo--------------------
        sb.append(" onBeforeLoad: function(row,param){\n" + "                    if (!row) {    \n" + "                     delete param.id;  \n" + "                    }\n" + "                },");
    // update-end--Author:dangzhenghui  Date:20170531 for???TASK #2038 ???demo??????????????? ??????demo--------------------
    } else {
        grid = "datagrid";
        sb.append("$(\'#" + name + "\').datagrid({");
        // update-begin--Author:xuelin  Date:20171128 for???[#2421]???bug????????????????????? --?????????????????? --------------------
        if (this.isFilter()) {
            sb.append("onHeaderContextMenu: function(e, field){headerMenu(e, field);},");
        }
        // update-end--Author:xuelin  Date:20171128 for???[#2421]???bug????????????????????? --?????????????????? --------------------
        sb.append("idField: '" + idField + "',");
    }
    if (title != null) {
        sb.append("title: \'" + title + "\',");
    }
    if (isShowSubGrid) {
        sb.append("view: detailview,");
        sb.append("detailFormatter:detailFormatterFun,");
        sb.append("onExpandRow: onExpandRowFun,");
    }
    if (autoLoadData)
        sb.append("url:\'" + actionUrl + "&field=" + fields + "\',");
    else
        sb.append("url:\'',");
    if (StringUtils.isNotEmpty(rowStyler)) {
        sb.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
    }
    if (StringUtils.isNotEmpty(extendParams)) {
        sb.append(extendParams);
    }
    if (fit) {
        sb.append("fit:true,");
    } else {
        sb.append("fit:false,");
    }
    // update--begin--author:zhangjiaqiang date:20170622 for:????????????????????????nowrap
    if (!nowrap) {
        sb.append("nowrap:false,");
    }
    // update--end--author:zhangjiaqiang date:20170622 for:????????????????????????nowrap
    sb.append("rownumbers: true,");
    if (collapsible) {
        sb.append("collapsible: true,");
    }
    // update-start--Author:chenjin  Date:20160715 for???????????????<t:dgCol ????????????defaultVal=""
    if (hasQueryColum(columnList)) {
        String queryParams = "";
        queryParams += "queryParams:{";
        for (DataGridColumn col : columnList) {
            if (col.isQuery() && col.getDefaultVal() != null && !col.getDefaultVal().trim().equals("")) {
                // sb.append("queryParams:{documentTitle:'woniu'},");
                // update-begin--Author:scott  Date:20170321 for?????????????????????????????????????????????------
                if (!"group".equals(col.getQueryMode())) {
                    queryParams += col.getField() + ":'" + col.getDefaultVal() + "',";
                }
            // update-end--Author:scott  Date:20170321 for?????????????????????????????????????????????------
            }
        }
        if (queryParams.indexOf(",") > -1) {
            queryParams = queryParams.substring(0, queryParams.length() - 1);
        }
        queryParams += "},";
        // System.out.println("queryParams===="+queryParams);
        sb.append(queryParams);
    }
    // update-end--Author:chenjin  Date:20160715 for???????????????<t:dgCol ????????????defaultVal=""
    sb.append(StringUtil.replaceAll("loadMsg: \'{0}\',", "{0}", MutiLangUtil.getLang("common.data.loading")));
    sb.append("pageSize: " + pageSize + ",");
    sb.append("pagination:" + pagination + ",");
    sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
    if (StringUtils.isNotBlank(sortName)) {
        sb.append("sortName:'" + sortName + "',");
    }
    sb.append("sortOrder:'" + sortOrder + "',");
    sb.append("rownumbers:true,");
    if (singleSelect == null) {
        sb.append("singleSelect:" + !checkbox + ",");
    } else {
        sb.append("singleSelect:" + singleSelect + ",");
    }
    if (fitColumns) {
        sb.append("fitColumns:true,");
    } else {
        sb.append("fitColumns:false,");
    }
    sb.append("striped:true,showFooter:true,");
    sb.append("frozenColumns:[[");
    this.getField(sb, 0);
    sb.append("]],");
    sb.append("columns:[[");
    this.getField(sb);
    sb.append("]],");
    sb.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + grid + "(\"clearSelections\");");
    // update-begin--Author:xuelin  Date:20171228 for???TASK #2462 ???bug???Easyui Datagrid rownumbers???????????????????????????????????????????????????(???)
    // update-begin--Author:xuelin  Date:20170613 for???TASK #2109 ??????????????????????????????????????????--------------------
    // sb.append(" $(this).datagrid(\"fixRownumber\");");
    // update-end--Author:xuelin  Date:20170613 for???TASK #2109 ??????????????????????????????????????????----------------------
    // update-end--Author:xuelin  Date:20171228 for???TASK #2462 ???bug???Easyui Datagrid rownumbers???????????????????????????????????????????????????(???)
    if (openFirstNode && treegrid) {
        sb.append(" if(data==null){");
        sb.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
        sb.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
    }
    // update-begin--author:zhangjiaqiang date:20170408 for:???????????????????????????????????????????????????
    sb.append("if(!" + treegrid + "){");
    sb.append("if(data.total && data.rows.length==0) {");
    sb.append("var grid = $(\'#" + name + "\');");
    sb.append("var curr = grid.datagrid(\'getPager\').data(\"pagination\").options.pageNumber;");
    sb.append("grid.datagrid({pageNumber:(curr-1)});}}");
    // update-end--author:zhangjiaqiang date:20170408 for:???????????????????????????????????????????????????
    // update-begin-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
    sb.append(" try{loadAjaxDict(data);}catch(e){}");
    // update-end-Author:zhoujf  Date:20180712 for:TASK #2809 ???bug??????????????????????????????????????????
    // update-begin-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    if (hasGroup) {
        sb.append("optsMenuToggle('" + name + "');");
    }
    // update-end-author???jiaqiankun date:20180713 for:TASK #2872 ????????????????????????????????????????????????????????????????????????????????????
    if (StringUtil.isNotEmpty(onLoadSuccess)) {
        sb.append(onLoadSuccess + "(data);");
    }
    sb.append("},");
    if (StringUtil.isNotEmpty(onDblClick)) {
        sb.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
    }
    if (treegrid) {
        sb.append("onClickRow:function(rowData){");
    } else {
        sb.append("onClickRow:function(rowIndex,rowData){");
    }
    /**
     * ???????????????
     */
    sb.append("rowid=rowData.id;");
    sb.append("gridname=\'" + name + "\';");
    if (StringUtil.isNotEmpty(onClick)) {
        if (treegrid) {
            sb.append("" + onClick + "(rowData);");
        } else {
            sb.append("" + onClick + "(rowIndex,rowData);");
        }
    }
    sb.append("}");
    sb.append("});");
    this.setPager(sb, grid);
    // update-begin longjb 20150515 for:??????????????????????????????
    // update-begin SCOTT 20150525 for: ?????????????????????????????????????????????????????????
    sb.append("try{restoreheader();}catch(ex){}");
    sb.append("});");
    // update-end SCOTT 20150525 for: ?????????????????????????????????????????????????????????
    // update-begin longjb 20150515 for:??????????????????????????????
    sb.append("function reloadTable(){");
    sb.append("try{");
    sb.append("	$(\'#\'+gridname).datagrid(\'reload\');");
    sb.append("	$(\'#\'+gridname).treegrid(\'reload\');");
    sb.append("}catch(ex){}");
    sb.append("}");
    sb.append("function reload" + name + "(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
    sb.append("function get" + name + "Selected(field){return getSelected(field);}");
    sb.append("function getSelected(field){" + "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value=\'\';" + "}" + "return value;" + "}");
    sb.append("function get" + name + "Selections(field){" + "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid + "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');" + "return ids" + "};");
    sb.append("function getSelectRows(){");
    sb.append("	return $(\'#" + name + "\').datagrid('getChecked');");
    sb.append("}");
    // update-begin longjb 20150515 for:???????????????????????????????????????
    sb.append(" function saveHeader(){");
    sb.append(" var columnsFields =null;var easyextends=false;try{columnsFields = $('#" + name + "').datagrid('getColumns');easyextends=true;");
    sb.append("}catch(e){columnsFields =$('#" + name + "').datagrid('getColumnFields');}");
    sb.append("	var cols = storage.get( '" + name + "hiddenColumns');var init=true;	if(cols){init =false;} " + "var hiddencolumns = [];for(var i=0;i< columnsFields.length;i++) {if(easyextends){");
    sb.append("hiddencolumns.push({field:columnsFields[i].field,hidden:columnsFields[i].hidden});}else{");
    sb.append(" var columsDetail = $('#" + name + "').datagrid(\"getColumnOption\", columnsFields[i]); ");
    sb.append("if(init){hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:(columsDetail.hidden==true?false:true)});}else{");
    sb.append("for(var j=0;j<cols.length;j++){");
    sb.append("		if(cols[j].field==columsDetail.field){");
    sb.append("					hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:cols[j].visible});");
    sb.append("		}");
    sb.append("}");
    sb.append("}} }");
    sb.append("storage.set( '" + name + "hiddenColumns',JSON.stringify(hiddencolumns));");
    sb.append("}");
    // add-begin- Author:gengjiajia datagrid???????????????????????????????????????,
    sb.append(" function isShowBut(){");
    sb.append("	  var isShowSearchId = $(\'#isShowSearchId\').val();");
    sb.append("	  if(isShowSearchId == \"true\"){");
    sb.append("		  $(\"#searchColums\").hide();");
    sb.append("	  	  $(\'#isShowSearchId\').val(\"false\");");
    sb.append("	  	  $(\'#columsShow\').remove(\"src\");");
    sb.append("	  	  $(\'#columsShow\').attr(\"src\",\"plug-in/easyui/themes/default/images/accordion_expand.png\");");
    sb.append("	  } else{");
    sb.append("		  $(\"#searchColums\").show();");
    sb.append("	  	  $(\'#isShowSearchId\').val(\"true\");");
    sb.append("	  	  $(\'#columsShow\').remove(\"src\");");
    sb.append("	  	  $(\'#columsShow\').attr(\"src\",\"plug-in/easyui/themes/default/images/accordion_collapse.png\");");
    sb.append("	  }");
    sb.append("}");
    // add-end- Author:gengjiajia datagrid???????????????????????????????????????
    sb.append("function restoreheader(){");
    sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
    sb.append("for(var i=0;i<cols.length;i++){");
    sb.append("	try{");
    sb.append("if(cols.visible!=false)$('#" + name + "').datagrid((cols[i].hidden==true?'hideColumn':'showColumn'),cols[i].field);");
    sb.append("}catch(e){");
    sb.append("}");
    sb.append("}");
    sb.append("}");
    sb.append("function resetheader(){");
    sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
    sb.append("for(var i=0;i<cols.length;i++){");
    sb.append("	try{");
    sb.append("  $('#" + name + "').datagrid((cols.visible==false?'hideColumn':'showColumn'),cols[i].field);");
    sb.append("}catch(e){");
    sb.append("}");
    sb.append("}");
    sb.append("}");
    // update-end longjb 201515 for:???????????????????????????????????????
    if (columnList.size() > 0) {
        sb.append("function " + name + "search(){");
        // update by jg_renjie at 2016/1/11 for:TASK #823 ??????form??????Form????????????
        sb.append("try { if(! $(\"#" + name + "Form\").Validform({tiptype:3}).check()){return false;} } catch (e){}");
        sb.append("if(true){");
        // update by jg_renjie at 2016/1/11 for:TASK #823 ??????form??????Form????????????
        sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
        sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
        sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});" + "}}");
        // ????????????????????????
        sb.append("function dosearch(params){");
        sb.append("var jsonparams=$.parseJSON(params);");
        sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',queryParams:jsonparams});" + "}");
        // update-begin chenxu 20140423 for:??????????????????????????????single??????????????????????????????????????????
        // searchbox???????????????
        searchboxFun(sb, grid);
        // update-end chenxu 20140423 for:??????????????????????????????single??????????????????????????????????????????
        // ????????????????????????js
        // update-begin Robin 20140426 for:????????????
        // ????????????
        sb.append("function EnterPress(e){");
        sb.append("var e = e || window.event;");
        sb.append("if(e.keyCode == 13){ ");
        sb.append(name + "search();");
        sb.append("}}");
        // update-begin Robin 20140426 for:????????????
        sb.append("function searchReset(name){");
        sb.append(" $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
        // update by jg_renjie at 2016/1/11 for:TASK #823 ??????form??????Form????????????,????????????reset?????????????????????????????????
        // String func = name.trim() + "search();";
        // sb.append(func);
        sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
        sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
        // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
        sb.append("$(\'#" + name + "tb\').find(\"input[type='checkbox']\").each(function(){$(this).attr('checked',false);});");
        sb.append("$(\'#" + name + "tb\').find(\"input[type='radio']\").each(function(){$(this).attr('checked',false);});");
        // update-begin--Author:xuelin  Date:20170713 for???TASK #2205 ???UI?????????????????????????????????????????????????????????redio????????????  ???????????????????????????????????????-------------------
        sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});");
        // update by jg_renjie at 2016/1/11 for:TASK #823 ??????form??????Form????????????,????????????reset?????????????????????????????????
        sb.append("}");
    }
    // update-begin-author:taoyan date:20180522 for:TASK #2596 jeecg???????????????????????????demo????????????----
    // ???????????????????????????????????????
    if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
        sb.append("function " + name + "SuperQuery(queryCode){if(typeof(windowapi)=='undefined'){$.dialog({content:'url:superQueryMainController.do?dialog&code='+queryCode+'&tableName=" + name + "',width:880,height:400,zIndex:getzIndex(),title:'?????????????????????',cache:false,lock:true})}else{$.dialog({content:'url:superQueryMainController.do?dialog&code='+queryCode+'&tableName=" + name + "',width:880,height:400,zIndex:getzIndex(),title:title,cache:false,lock:true,parent:windowapi})}};");
    }
    // ????????????
    getFilterFields(sb);
    // update-end-author:taoyan date:20180522 for:TASK #2596 jeecg???????????????????????????demo????????????----
    sb.append("</script>");
    sb.append("<table width=\"100%\"   id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
    sb.append("<div id=\"" + name + "tb\" style=\"padding:3px; height: auto\">");
    // add-begin- Author:gengjiajia datagrid???????????????????????????????????????,????????????????????????
    if (hasQueryColum(columnList) && isShowSearch == true) {
        sb.append("<input  id=\"columsShow\" type=\"image\" src=\"plug-in/easyui/themes/default/images/accordion_collapse.png\" onclick=\"isShowBut()\">");
    }
    // add-end- Author:gengjiajia datagrid???????????????????????????????????????,????????????????????????
    // update-begin--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
    boolean blink = false;
    // update-end--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
    // update-start--Author:scott---date:20171109-----for:???????????????????????????????????????????????????------
    // update-begin-author:taoYan date:20180327 for:???????????????????????????????????????????????? ????????????????????????--
    // update-begin-Author:LiShaoQing date:20171229 -- for: ?????????????????????,???????????????-----
    sb.append("<input  id=\"_complexSqlbuilder\" name=\"complexSqlbuilder\"   type=\"hidden\" />");
    // update-end-Author:LiShaoQing date:20171229 -- for: ?????????????????????,???????????????-----
    // update-end-author:taoYan date:20180327 for:???????????????????????????????????????????????? ????????????????????????--
    if (hasQueryColum(columnList) && "group".equals(getQueryMode())) {
        // update-end--Author:scott---date:20171109-----for:???????????????????????????????????????????????????------
        // update-begin--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
        blink = true;
        // update-end--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
        // update-start--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
        String searchColumStyle = toolBarList != null && toolBarList.size() != 0 ? "" : "style='border-bottom: 0px'";
        sb.append("<div name=\"searchColums\" id=\"searchColums\" " + searchColumStyle + ">");
        // update-end--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
        // add-begin- Author:gengjiajia datagrid???????????????????????????????????????,???????????????????????????????????????????????????div
        sb.append("<input  id=\"isShowSearchId\" type=\"hidden\" value=\"" + isShowSearch + "\"/>");
        // add-end- Author:gengjiajia datagrid???????????????????????????????????????,???????????????????????????????????????????????????div
        // -----longjb1 ????????????????????????????????????
        sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
        // update by jg_renjie at 2016/1/11 for:TASK #823 ??????form??????Form????????????
        // update-begin--author:zhangjiaqiang date:20161222 for:????????????????????????
        sb.append("<form onkeydown='if(event.keyCode==13){" + name + "search();return false;}' id='" + name + "Form'>");
        // update-end--author:zhangjiaqiang date:20161222 for:????????????????????????
        // update by jg_renjie at 2016/1/11 for:TASK #823
        // update--begin--author:zhangjiaqiang Date:20170415 for:??????????????????freemarker??????
        // update--begin--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
        // update--begin--author:zhangjiaqiang Date:20180223 for:TASK #2525 ????????????????????????????????????
        sb.append("<span style=\"max-width: 79%;display: inline-block;\">");
        // update--end--author:zhangjiaqiang Date:20180223 for:TASK #2525 ????????????????????????????????????
        // update--begin--author:scott----- Date:20180613------ for:TASK #2795 ????????????????????????????????????
        sb.append("<span><img style=\"margin-top:-3px;vertical-align:middle;\" src=\"plug-in/easyui/themes/icons/ti.png\"  title=\"??????????????????????????????: *????????????????????????????????? ',' ?????????\" alt=\"??????????????????????????????: *????????????????????????????????? ',' ?????????\" /></span>");
        // update--end--scott----- Date:20180613------ for:TASK #2795 ????????????????????????????????????
        getSearchFormInfo(sb);
        sb.append("</span>");
        // update--end--author:zhangjiaqiang Date:20170415 for:??????????????????freemarker??????
        // update--begin--author:zhangjiaqiang Date:20171113 for:???????????????????????????????????????
        sb.append("<span>");
        getSearchButton(sb);
        sb.append("</span>");
        // update--end--author:zhangjiaqiang date:20171115 for:TASK #2418 ???????????????????????????????????????????????????
        // update--end--author:zhangjiaqiang Date:20171113 for:???????????????????????????????????????
        sb.append("</form></div>");
    // update--begin--author:Yandong date:20180403 for:TASK #2605 ???UI?????????????????????????????????
    } else if (hasQueryColum(columnList) && "advanced".equals(getQueryMode())) {
        blink = true;
        String searchColumStyle = toolBarList != null && toolBarList.size() != 0 ? "" : "style='border-bottom: 0px'";
        // update--begin--author:zhoujf date:20180410 for:TASK #2605 ???UI?????????????????????????????????(???????????????)
        sb.append("<div name=\"searchColums\" style=\"display:none;\" id=\"searchColums\" " + searchColumStyle + ">");
        // update--end--author:zhoujf date:20180410 for:TASK #2605 ???UI?????????????????????????????????(???????????????)
        sb.append("<input  id=\"isShowSearchId\" type=\"hidden\" value=\"" + isShowSearch + "\"/>");
        // -----longjb1 ????????????????????????????????????
        sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
        sb.append("<form onkeydown='if(event.keyCode==13){" + name + "search();return false;}' id='" + name + "Form'>");
        sb.append("<span style=\"max-width: 79%;display: inline-block;\">");
        getSearchFormInfo(sb);
        sb.append("</span>");
        sb.append("<span>");
        getSearchButton(sb);
        sb.append("</span>");
        sb.append("</form></div>");
    }
    // update--end--author:Yandong date:20180403 for:TASK #2605 ???UI?????????????????????????????????
    // update-start--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
    if (toolBarList == null || toolBarList.size() == 0) {
        // update-end--Author:scott---date:20171121-----for:?????????????????????????????????????????????????????????----------------
        sb.append("<div style=\"height:0px;\" >");
    } else {
        // TODO
        // update-begin--Author:xuelin  Date:20170524 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
        sb.append("<div style=\"border-bottom-width:0;\" class=\"datagrid-toolbar\">");
    // update-end--Author:xuelin  Date:20170524 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    }
    sb.append("<span style=\"float:left;\" >");
    if (toolBarList.size() > 0) {
        // update-begin-author:taoyan date:20180717 for:TASK #2873 ?????????????????????????????????
        Boolean hasMore = false;
        for (DataGridUrl toolBar : toolBarList) {
            if (toolBar.isInGroup()) {
                if (!hasMore) {
                    hasMore = true;
                }
            } else {
                loadToolbar(toolBar, sb);
            }
        }
        if (hasMore) {
            loadToolbarMoreBtn(sb, true, null);
            sb.append("<div class='toolbar-more-container'><ul class='toolbar-more-list'>");
            for (DataGridUrl toolBar : toolBarList) {
                if (toolBar.isInGroup()) {
                    sb.append("<li>");
                    loadToolbarMoreBtn(sb, false, toolBar);
                    sb.append("</li>");
                }
            }
            sb.append("</ul></div>");
        // sb.append("<div class='btn-group'><button data-toggle='dropdown' class='btn btn-default dropdown-toggle'>??????<span class='caret'></span> </button><ul class='dropdown-menu'><li><a href='buttons.html#'>??????</a></li><li><a href='buttons.html#' class='font-bold'>??????</a></li><li><a href='buttons.html#'>??????</a></li><li class='divider'></li> <li><a href='buttons.html#'>??????</a></li> </ul> </div>");
        }
    // update-end-author:taoyan date:20180717 for:TASK #2873 ?????????????????????????????????
    }
    sb.append("</span>");
    // update--begin--author:zhangjiaqiang date:20171113 for:TASK #2416 ???UI??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    if ("single".equals(getQueryMode()) && hasQueryColum(columnList)) {
        // ????????????????????????
        sb.append("<span style=\"float:right\">");
        sb.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name + StringUtil.replaceAll("searchbox,prompt:\'{0}\',menu:\'#", "{0}", MutiLangUtil.getLang("common.please.input.keyword")) + name + "mm\'\"></input>");
        sb.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
        for (DataGridColumn col : columnList) {
            if (col.isQuery()) {
                // update--begin--author:Yandong date:20180403 for:TASK #2608 ???UI?????????datagrid ??????
                sb.append("<div data-options=\"name:\'" + col.getField().replaceAll("_", "\\.") + "\',iconCls:\'icon-ok\' \">" + col.getTitle() + "</div>  ");
            // update--end--author:Yandong date:20180403 for:TASK #2608 ???UI?????????datagrid ??????
            }
        }
        sb.append("</div>");
        sb.append("</span>");
    // update--begin--author:Yandong date:20180403 for:TASK #2605 ???UI?????????????????????????????????
    } else if ("advanced".equals(getQueryMode()) && hasQueryColum(columnList)) {
        // ???????????????????????????
        sb.append("<span style=\"float:right\">");
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ??????????????? bootstrap????????????
            if (btnCls.indexOf("bootstrap") == 0) {
                String defalutCls = "btn btn-info btn-xs";
                if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                    defalutCls = btnCls.replace("bootstrap", "").trim();
                }
                if (superQuery) {
                    sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"queryBuilder()\">");
                    sb.append("<i class=\"fa fa-search\"></i>");
                    sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.superquery") + "</span>");
                    sb.append("</button>");
                    sb.append("</span>");
                }
            } else {
                // ???????????????
                if (superQuery) {
                    sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superquery")));
                }
            }
        } else {
            // ????????????easyUI????????????
            if (superQuery) {
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getLang("common.superquery")));
            }
        }
        sb.append("</span>");
    }
    // update--end--author:Yandong date:20180403 for:TASK #2605 ???UI?????????????????????????????????
    // update--end--author:zhangjiaqiang date:20171113 for:TASK #2416 ???UI??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    // update-begin--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    sb.append("<div style=\"clear:both\"></div>");
    // update-end--Author:xuelin  Date:20170523 for???TASK #2002 ???UI?????????????????????button?????????????????? ????????????DIV?????????????????????-------------------
    sb.append("</div>");
    // update-begin--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
    if (blink) {
        sb.insert(0, "<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\">" + "<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\">" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
    }
    // update-end--Author:guoxianhui  Date:20171128 TASK #2326 ??????????????????????????????????????????????????????????????????????????????????????????-------------------
    if (queryBuilder) {
        // update-begin--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? -------------------
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ??????????????? bootstrap????????????
            addQueryBuilder(sb, btnCls);
        } else {
            // ????????????easyUI????????????
            addQueryBuilder(sb, "easyui-linkbutton");
        }
    // update-end--Author:xuelin  Date:20170527 for???TASK #2022 ???UI?????????????????????????????????????????????????????? --------------------
    }
    // update-begin-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
    if (superQuery) {
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ?????????bootstrap????????????
            addSuperQuery(sb, btnCls, columnList);
        } else {
            addSuperQuery(sb, "easyui-linkbutton", columnList);
        }
    }
    // update-end-Author:LiShaoQing Date:20171019 for:TASK #2356 ??????????????????????????????????????????-----
    // update-begin-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
    if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
        if (btnCls != null && !btnCls.equals("easyui")) {
            // ?????????bootstrap????????????
            addAdvancedQuery(sb, btnCls);
        } else {
            addAdvancedQuery(sb, "easyui-linkbutton");
        }
    }
    // update-end-Author:LiShaoQing Date:20171227 for:???????????????????????????????????????-----
    // update-begin author:xuelin date:20171116 for:TASK #2404 ?????????UI?????????UI???????????????  5.??????fineui?????????????????????????????????-----
    this.getFilter(sb);
    // update-end author:xuelin date:20171116 for:TASK #2404 ?????????UI?????????UI???????????????  5.??????fineui?????????????????????????????????-----
    return sb;
}


public void setColumnStyleList(List<ColumnValue> columnStyleList){
    this.columnStyleList = columnStyleList;
}


public void searchboxFun(StringBuffer sb,String grid){
    sb.append("function " + name + "searchbox(value,name){");
    sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
    sb.append("queryParams[name]=value;queryParams.searchfield=name;$(\'#" + name + "\')." + grid + "(\'reload\');}");
    sb.append("$(\'#" + name + "searchbox\').searchbox({");
    sb.append("searcher:function(value,name){");
    sb.append("" + name + "searchbox(value,name);");
    sb.append("},");
    sb.append("menu:\'#" + name + "mm\',");
    sb.append(StringUtil.replaceAll("prompt:\'{0}\'", "{0}", MutiLangUtil.getLang("common.please.input.query.keyword")));
    sb.append("});");
}


public String getEntityName(){
    if (StringUtils.isEmpty(entityName)) {
        entityName = actionUrl.substring(0, actionUrl.indexOf("Controller"));
        entityName = (entityName.charAt(0) + "").toUpperCase() + entityName.substring(1) + "Entity";
    }
    return entityName;
}


public void setShowSubGrid(boolean isShowSubGrid){
    this.isShowSubGrid = isShowSubGrid;
}


public String getLangArg(){
    return langArg;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
}


public void loadToolbarMoreBtn(StringBuffer sb,boolean isShow,DataGridUrl toolBar){
    if (isShow) {
        if (btnCls != null && !btnCls.equals("easyui")) {
            if (btnCls.indexOf("bootstrap") == 0) {
                if (btnCls.replace("bootstrap", "").trim().length() > 0) {
                    sb.append("<button class=\"" + btnCls.replace("bootstrap", "").trim() + "\" ");
                } else {
                    sb.append("<button class=\"btn btn-default btn-xs\" ");
                }
                sb.append("onclick='toggleMoreToolbars(this)'");
                sb.append("><i class=\"fa fa-caret-down\"></i><span class=\"bigger-110 no-text-shadow\">????????????</span></button>");
            } else {
                sb.append("<a href=\"javascript:void(0)\" onclick='toggleMoreToolbars(this)' class=\"" + btnCls + " " + toolBar.getIcon() + "\" >????????????</a>");
            }
        } else if (btnCls == null || btnCls.equals("easyui")) {
            sb.append("<a href=\"javascript:void(0)\"  onclick='toggleMoreToolbars(this)' class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-caret-down\">????????????</a> ");
        }
    } else {
        sb.append("<a href='javascript:void(0)' ");
        if (StringUtil.isNotEmpty(toolBar.getId())) {
            sb.append(" id=\"");
            sb.append(toolBar.getId());
            sb.append("\" ");
        }
        if (StringUtil.isNotEmpty(toolBar.getOnclick())) {
            sb.append("onclick=" + toolBar.getOnclick() + "");
        } else {
            sb.append("onclick=\"" + toolBar.getFunname() + "(");
            if (!toolBar.getFunname().equals("doSubmit")) {
                sb.append("\'" + toolBar.getTitle() + "\',");
            }
            String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
            String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
            sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
        }
        if (btnCls == null || btnCls.equals("easyui")) {
            sb.append("><span class=\"easyui-mycon " + toolBar.getIcon() + "\"></span> " + toolBar.getTitle());
        } else {
            sb.append("><i class=\"" + toolBar.getIcon() + "\"></i> " + toolBar.getTitle());
        }
        sb.append("</a>");
    }
}


public boolean isQuery(){
    return query;
}


public String getComplexSuperQuery(){
    return complexSuperQuery;
}


public void setFields(String fields){
    this.fields = fields;
}


public void setComponent(String component){
    this.component = component;
}


public void addSuperQueryBootstrap(StringBuffer sb,String buttonSytle,List<DataGridColumn> columnList){
    FreemarkerHelper free = new FreemarkerHelper();
    Map<String, Object> mainConfig = new HashMap<String, Object>();
    mainConfig.put("fields", columnList);
    mainConfig.put("tableName", name);
    mainConfig.put("valueList", columnValueList);
    String superQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/superqueryBootstrap.ftl", mainConfig);
    appendLine(sb, superQuery);
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public int doEndTag(){
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // long start = System.currentTimeMillis();
    // log.info("================================ DataGridTag ????????????:"+sdf.format(new Date())+"==============================");
    JspWriter out = null;
    try {
        title = MutiLangUtil.doMutiLang(title, langArg);
        out = this.pageContext.getOut();
        // update-start--Author:yugwu  Date:20170830 ???????????????end?????????----
        // update-begin-Author:zhoujf Date:20180504 for:TASK #2687 ???datagrid???????????????freemaker??????datagrid??????
        if ("bootstrap-table".equals(component)) {
            ComponentFactory componentFactory = new BootstrapTableComponent();
            String content = componentFactory.invoke("/org/jeecgframework/tag/core/factory/ftl/component/bootstrapTable.ftl", getDataGridTag());
            // log.debug("  content ===>" + content);
            // update-begin-author:taoYan date:20180622 for:TASK #2815 ?????????????????????datagrid ??????bootstrap ???????????? superQuery="true" ?????????????????????????????????
            StringBuffer bst = new StringBuffer();
            bst.append(content);
            if (superQuery) {
                addSuperQueryBootstrap(bst, btnCls, columnList);
            }
            out.print(bst.toString());
        // update-end-author:taoYan date:20180622 for:TASK #2815 ?????????????????????datagrid ??????bootstrap ???????????? superQuery="true" ?????????????????????????????????
        } else {
            out.print(end().toString());
        // log.debug("  end() ===>" + end().toString());
        }
        // update-end-Author:zhoujf Date:20180504 for:TASK #2687 ???datagrid???????????????freemaker??????datagrid??????
        out.flush();
    // update-end--Author:yugwu  Date:20170830 ???????????????end?????????----
    // String indexStyle =null;
    // -----author:jg_longjb----start-----date:20150408--------for:??????cookie???????????? ace?????????table?????????
    // Cookie[] cookies = ((HttpServletRequest) super.pageContext
    // .getRequest()).getCookies();
    // for (Cookie cookie : cookies) {
    // if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
    // continue;
    // }
    // if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
    // indexStyle = cookie.getValue();
    // }
    // }
    // SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme((HttpServletRequest) super.pageContext.getRequest());
    // if (style.equals("easyui")) {
    // if("ace".equals(sysThemesEnum.getStyle())){
    // out.print(this.aceStyleTable().toString());
    // }else{
    // out.print(end().toString());
    // out.flush();
    // }
    // -----author:jg_longjb----end-----date:20150408--------for:??????cookie???????????? ace?????????table?????????
    // update--begin--author:zhangjiaqiang date:20170408 for:??????jqgrid??????????????????
    // }else if("jqgrid".equals(style)){
    // out.print(jqGrid().toString());
    // out.flush();
    // update--end--author:zhangjiaqiang date:20170408 for:??????jqgrid??????????????????
    // }else{
    // out.print(datatables().toString());
    // out.flush();
    // }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                // update-begin--Author:scott  Date:20160530 for???????????????????????????
                out.clearBuffer();
                // end().setLength(0);
                // ????????????
                urlList.clear();
                toolBarList.clear();
                columnValueList.clear();
                columnStyleList.clear();
                columnList.clear();
                fields = "";
                searchFields = "";
            // update-begin--Author:scott  Date:20160530 for???????????????????????????
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // long end = System.currentTimeMillis();
    // log.info("=============================== DataGridTag ????????????:"+sdf.format(new Date())+"==============================");
    // log.info("================================ DataGridTag ??????:"+(end-start)+"ms==============================");
    return EVAL_PAGE;
}


public void getGroupUrl(StringBuffer sb){
    // ????????????????????????????????????????????????????????????
    List<DataGridUrl> list = urlList;
    // <i class='fa fa-angle-double-right'></i>??????
    sb.append("href+=\"<a href='javascript:void(0)' class='opts-menu-triangle btnmy'> <span class='opts-menu-temp icon-triangle'></span>??????</a>\";");
    /*	sb.append("href+=\"<div class='opts-menu-triangle icon-triangle' title='????????????'></div>\";");*/
    sb.append("href+=\"<div class='opts-menu-container location-left'>\";");
    sb.append("href+=\"<div class='opts-menu-parent'>\";");
    sb.append("href+=\"<div class='opts-menu-box'>\";");
    for (DataGridUrl dataGridUrl : list) {
        if (dataGridUrl.isInGroup()) {
            String url = dataGridUrl.getUrl();
            MessageFormat formatter = new MessageFormat("");
            if (dataGridUrl.getValue() != null) {
                String[] testvalue = dataGridUrl.getValue().split(",");
                List value = new ArrayList<Object>();
                for (String string : testvalue) {
                    value.add("\"+rec." + string + " +\"");
                }
                url = formatter.format(url, value.toArray());
            }
            if (url != null && dataGridUrl.getValue() == null) {
                // add-start--Author:yugwu  Date:20170912 for:formatUrl????????????,???????????????restful??????----
                // url = formatUrl(url);
                url = formatUrlPlus(url);
            // add-end--Author:yugwu  Date:20170912 for:formatUrl????????????,???????????????restful??????----
            }
            // ?????????????????????
            String exp = dataGridUrl.getExp();
            if (StringUtil.isNotEmpty(exp)) {
                String[] ShowbyFields = exp.split("&&");
                for (String ShowbyField : ShowbyFields) {
                    int beginIndex = ShowbyField.indexOf("#");
                    int endIndex = ShowbyField.lastIndexOf("#");
                    // ???????????????
                    String exptype = ShowbyField.substring(beginIndex + 1, endIndex);
                    // ????????????????????????
                    String field = ShowbyField.substring(0, beginIndex);
                    // ???????????????
                    String[] values = ShowbyField.substring(endIndex + 1, ShowbyField.length()).split(",");
                    String value = "";
                    for (int i = 0; i < values.length; i++) {
                        value += "'" + "" + values[i] + "" + "'";
                        if (i < values.length - 1) {
                            value += ",";
                        }
                    }
                    if ("eq".equals(exptype)) {
                        sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
                    }
                    if ("ne".equals(exptype)) {
                        sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
                    }
                    if ("empty".equals(exptype) && value.equals("'true'")) {
                        sb.append("if(rec." + field + "==''){");
                    }
                    if ("empty".equals(exptype) && value.equals("'false'")) {
                        sb.append("if(rec." + field + "!=''){");
                    }
                }
            }
            // update-start--Author: jg_huangxg  Date:20151204 for?????????styke????????????????????????
            StringBuffer style = new StringBuffer();
            if (!StringUtil.isEmpty(dataGridUrl.getUrlStyle())) {
                style.append(" style=\'");
                style.append(dataGridUrl.getUrlStyle());
                style.append("\' ");
            }
            // update-start--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
            StringBuffer urlclass = new StringBuffer();
            // update-begin-author:taoyan date:20180717 for:TASK #2980 ???BUG?????????????????????
            if (StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                dataGridUrl.setUrlclass("btn btn-default ops-more");
            }
            urlclass.append(" class=\'" + dataGridUrl.getUrlclass() + "\'");
            // update-end-author:taoyan date:20180717 for:TASK #2980 ???BUG?????????????????????
            StringBuffer urlfont = new StringBuffer();
            if (!StringUtil.isEmpty(dataGridUrl.getUrlfont())) {
                urlfont.append(" <i class=\' fa ");
                urlfont.append(dataGridUrl.getUrlfont());
                urlfont.append("\'></i>");
            }
            // update-end--Author: chenj  Date:20160815 for???TASK #1040 ???UI????????????ace?????????????????????????????????????????????????????????????????????
            if (OptTypeDirection.Confirm.equals(dataGridUrl.getType())) {
                // update-begin--Author:zhangjq  Date:20160904 for???[1343???]???UI?????????t:dgConfOpt??????ace????????????
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    sb.append("href+=\"<a href=\'#\'  " + urlclass.toString() + "  onclick=confirm(\'" + url + "\',\'" + dataGridUrl.getMessage() + "\',\'" + name + "\')" + style.toString() + "> " + urlfont.toString() + " \";");
                } else {
                    sb.append("href+=\"[<a href=\'#\' onclick=confirm(\'" + url + "\',\'" + dataGridUrl.getMessage() + "\',\'" + name + "\')" + style.toString() + "> \";");
                }
            // update-begin--Author:zhangjq  Date:20160904 for???[1343???]???UI?????????t:dgConfOpt??????ace????????????
            }
            if (OptTypeDirection.Del.equals(dataGridUrl.getType())) {
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    // ??????urlclass????????????????????????????????????"[";
                    sb.append("href+=\"<a href=\'#\'  " + urlclass.toString() + "  onclick=delObj(\'" + url + "\',\'" + name + "\')" + style.toString() + "> " + urlfont.toString() + " \";");
                } else {
                    sb.append("href+=\"[<a href=\'#\'  onclick=delObj(\'" + url + "\',\'" + name + "\')" + style.toString() + ">\";");
                }
            }
            if (OptTypeDirection.Fun.equals(dataGridUrl.getType())) {
                String name = TagUtil.getFunction(dataGridUrl.getFunname());
                String parmars = TagUtil.getFunParams(dataGridUrl.getFunname());
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    // ??????urlclass????????????????????????????????????"[";
                    sb.append("href+=\"<a href=\'#\'  " + urlclass.toString() + "  onclick=" + name + "(" + parmars + ")" + style.toString() + "> " + urlfont.toString() + "\";");
                } else {
                    sb.append("href+=\"[<a href=\'#\'   onclick=" + name + "(" + parmars + ")" + style.toString() + ">\";");
                }
            }
            if (OptTypeDirection.OpenWin.equals(dataGridUrl.getType())) {
                // update--begin--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    // ??????urlclass????????????????????????????????????"[";
                    sb.append("href+=\"<a href=\'#\' " + urlclass.toString() + " onclick=openwindow('" + dataGridUrl.getTitle() + "','" + url + "','" + name + "'," + dataGridUrl.getWidth() + "," + dataGridUrl.getHeight() + ")" + style.toString() + ">" + urlfont.toString() + "\";");
                } else {
                    sb.append("href+=\"[<a href=\'#\' onclick=openwindow('" + dataGridUrl.getTitle() + "','" + url + "','" + name + "'," + dataGridUrl.getWidth() + "," + dataGridUrl.getHeight() + ")" + style.toString() + ">\";");
                }
            // update--end--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
            }
            // update-end--Author:liuht  Date:20130228 for????????????????????????????????????
            if (OptTypeDirection.Deff.equals(dataGridUrl.getType())) {
                // update--begin--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    sb.append("href+=\"<a href=\'" + url + "' " + urlclass.toString() + " title=\'" + dataGridUrl.getTitle() + "\'" + style.toString() + ">" + urlfont.toString() + "\";");
                } else {
                    sb.append("href+=\"[<a href=\'" + url + "' title=\'" + dataGridUrl.getTitle() + "\'" + style.toString() + ">\";");
                }
            // update--end--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
            }
            // update-end--Author: jg_huangxg  Date:20151204 for?????????styke????????????????????????
            if (OptTypeDirection.OpenTab.equals(dataGridUrl.getType())) {
                // update-begin--Author: Yandong  Date:20180529 for???TASK #2744 ??????????????????????????????????????????
                if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                    // ??????urlclass????????????????????????????????????"[";
                    sb.append("href+=\"<a href=\'#\' " + urlclass.toString() + " onclick=addOneTab('" + dataGridUrl.getTitle() + "','" + url + "') " + style.toString() + ">" + urlfont.toString() + "\";");
                } else {
                    sb.append("href+=\"[<a href=\'#\' onclick=addOneTab('" + dataGridUrl.getTitle() + "','" + url + "') " + style.toString() + ">\";");
                }
            // update-end--Author: Yandong  Date:20180529 for???TASK #2744 ??????????????????????????????????????????
            }
            if (!StringUtil.isEmpty(dataGridUrl.getUrlclass())) {
                // ??????urlclass????????????????????????????????????"]";
                sb.append("href+=\"" + dataGridUrl.getTitle() + "</a>&nbsp;\";");
            } else {
                sb.append("href+=\"" + dataGridUrl.getTitle() + "</a>]\";");
            }
            if (StringUtil.isNotEmpty(exp)) {
                for (int i = 0; i < exp.split("&&").length; i++) {
                    sb.append("}");
                }
            }
        }
    }
    sb.append("href+=\"</div></div><em class='ops_shadeEm' style='display: inline;'></em></div></div>\";");
}


public boolean hasQueryColum(List<DataGridColumn> columnList){
    boolean hasQuery = false;
    for (DataGridColumn col : columnList) {
        if (col.isQuery()) {
            hasQuery = true;
        }
    }
    return hasQuery;
}


public void setOpenUrl(String url,String title,String width,String height,String exp,String operationCode,String openModel,String urlStyle,String urlclass,String urlfont,boolean inGroup){
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setUrl(url);
    dataGridUrl.setWidth(width);
    dataGridUrl.setHeight(height);
    dataGridUrl.setType(OptTypeDirection.valueOf(openModel));
    dataGridUrl.setExp(exp);
    // update-begin-author:taoYan date:20180524 for:??????????????????---
    dataGridUrl.setInGroup(inGroup);
    // update-end-author:taoYan date:20180524 for:??????????????????---
    // update--begin--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    if (checkBrowerIsNotIE()) {
        dataGridUrl.setUrlStyle(urlStyle);
        // update--begin--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
        dataGridUrl.setUrlclass(urlclass);
        dataGridUrl.setUrlfont(urlfont);
    // update--end--author:zhangjiaqiang date:20170912 for:TASK 1779 UI??????????????????
    } else if (StringUtil.isEmpty(urlclass) || !"ace_button".equals(urlclass)) {
        dataGridUrl.setUrlStyle(urlStyle);
    }
    // update--end--author:zhangjiaqiang date:20170626 for:??????ie???????????????????????????
    installOperationCode(dataGridUrl, operationCode, urlList);
}


public String dealSyscode(String field,int flag){
    String change = field;
    Iterator it = syscode.keySet().iterator();
    while (it.hasNext()) {
        String key = String.valueOf(it.next());
        String value = String.valueOf(syscode.get(key));
        if (flag == 1) {
            change = field.replaceAll(key, value);
        } else if (flag == 2) {
            change = field.replaceAll(value, key);
        }
    }
    return change;
}


public void setToolbar(String url,String title,String icon,String exp,String onclick,String funname,String operationCode,String width2,String height2,String id,boolean inGroup){
    DataGridUrl dataGridUrl = new DataGridUrl();
    dataGridUrl.setTitle(title);
    dataGridUrl.setUrl(url);
    dataGridUrl.setType(OptTypeDirection.ToolBar);
    // update-begin-author:taoYan date:20180717 for:TASK #2873 ?????????????????????????????????
    dataGridUrl.setInGroup(inGroup);
    // update-end-author:taoYan date:20180717 for:TASK #2873 ?????????????????????????????????
    // update--begin--author:zhangjiaqiang date:20170627 for:TASK #2179 ?????????IE???????????????????????????????????????
    if (!checkBrowerIsNotIE()) {
        // IE?????????
        if (!icon.startsWith("icon")) {
            dataGridUrl.setIcon("icon-add");
        } else {
            dataGridUrl.setIcon(icon);
        }
    } else {
        dataGridUrl.setIcon(icon);
    }
    // update--end--author:zhangjiaqiang date:20170627 for:TASK #2179 ?????????IE???????????????????????????????????????
    dataGridUrl.setOnclick(onclick);
    dataGridUrl.setExp(exp);
    dataGridUrl.setFunname(funname);
    dataGridUrl.setWidth(String.valueOf(width2));
    dataGridUrl.setHeight(String.valueOf(height2));
    // update--begin--author:zhangjiaqiang date:20170620 for:????????????ID
    dataGridUrl.setId(id);
    // update--end--author:zhangjiaqiang date:20170620 for:????????????ID
    installOperationCode(dataGridUrl, operationCode, toolBarList);
}


public void setColumnList(List<DataGridColumn> columnList){
    this.columnList = columnList;
}


public void setIsShowSubGrid(boolean isShowSubGrid){
    this.isShowSubGrid = isShowSubGrid;
}


public boolean isAutoLoadData(){
    return autoLoadData;
}


public String getExtendParams(){
    return extendParams;
}


public void setComplexSuperQuery(String complexSuperQuery){
    this.complexSuperQuery = complexSuperQuery;
}


public void setUrlList(List<DataGridUrl> urlList){
    this.urlList = urlList;
}


public void setTreeField(String treeField){
    this.treeField = treeField;
}


public String getNoAuthOperButton(){
    // update-begin--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
    StringBuffer sb = new StringBuffer();
    if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
    } else {
        Set<String> operationCodes = (Set<String>) super.pageContext.getRequest().getAttribute(Globals.OPERATIONCODES);
        if (null != operationCodes) {
            for (String MyoperationCode : operationCodes) {
                if (oConvertUtils.isEmpty(MyoperationCode))
                    break;
                systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
                TSOperation operation = systemService.getEntity(TSOperation.class, MyoperationCode);
                if (operation.getOperationcode().startsWith(".") || operation.getOperationcode().startsWith("#")) {
                    if (operation.getOperationType().intValue() == Globals.OPERATION_TYPE_HIDE) {
                        // out.append("$(\""+name+"\").find(\"#"+operation.getOperationcode().replaceAll(" ", "")+"\").hide();");
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").hide();");
                    } else {
                        // out.append("$(\""+name+"\").find(\"#"+operation.getOperationcode().replaceAll(" ", "")+"\").find(\":input\").attr(\"disabled\",\"disabled\");");
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").attr(\"disabled\",\"disabled\");");
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                    }
                }
            }
        }
    }
    // update-end--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
    // org.jeecgframework.core.util.LogUtil.info("----getNoAuthOperButton-------"+sb.toString());
    return sb.toString();
}


}