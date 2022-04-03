package org.jeecgframework.core.util;
 import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.system.manager.ClientManager;
public class FormUtil {

 private  String temp_view;


public String GetPopUp(JSONObject item,Map<String,Object> formData,String action){
    String dictionary = item.getString("dictionary").toString();
    String[] dic = new String[] { "", "", "" };
    if (dictionary.split(",").length > 1)
        dic = dictionary.split(",");
    String temp = "<input type=\"text\" value=\"{0}\"   class=\"searchbox-inputtext\" value=\"\"  name=\"{1}\"  style=\"{2}\" onClick=\"inputClick(this,''{3}'',''{4}'');\" />";
    String name = item.getString("name").toString();
    String value = "";
    // if (value == null)
    // value = item.getString("value") == null ? "" : item.getString(
    // "value").toString();
    String style = item.getString("style") == null ? "" : item.getString("style").toString();
    String temp_html = MessageFormat.format(temp, value, name, style, dic[1], dic[0]);
    if ("view".equals(action))
        return MessageFormat.format(temp_view, style, value, dic[1], dic[0]);
    else
        return temp_html;
}


public String GetListctrl(JSONObject item,Map<String,Object> formData,String action){
    String valuetest = "{\"data_110\":[\"1\",\"2\"],\"data_111\":[\"21\",\"22\",\"22\"]}";
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    String temp_html = "";
    String orgSum = item.getString("orgsum").toString();
    String orgUnit = item.getString("orgunit").toString();
    String orgTitle = item.getString("orgtitle").toString();
    String title = item.getString("title").toString();
    String style = item.getString("style").toString();
    String orgcolvalue = item.getString("orgcolvalue").toString();
    String orgcoltype = item.getString("orgcoltype").toString();
    List<String> listTitle = Arrays.asList(orgTitle.split("`"));
    List<String> listSum = Arrays.asList(orgSum.split("`"));
    List<String> listUnit = Arrays.asList(orgUnit.split("`"));
    List<String> listValue = Arrays.asList(orgcolvalue.split("`"));
    List<String> listType = Arrays.asList(orgcoltype.split("`"));
    int tdCount = listTitle.size();
    String temp = "<table id=\"" + name + "_table\" bindTable=\"true\" cellspacing=\"0\" class=\"table table-bordered table-condensed\" style=\"" + style + "\"><thead>{0}</thead><tbody>{1}</tbody>{2}</table>";
    String btnAdd = "<span class=\"pull-right\"><button class=\"btn btn-small btn-success listAdd\" type=\"button\" tbname=\"" + name + // 添加按钮
    "\">添加一行</button></span>";
    // 头部模版
    String theader = "<tr><th colspan=\"{0}\">{1}{2}</th></tr>{3}";
    // 标题
    String trTitle = "";
    for (int i = 0; i < tdCount; i++) {
        if (i == tdCount - 1)
            listTitle.set(i, "操作");
        if ("view".equals(action) && i == tdCount - 1)
            // 如果是查看最后一列不显示
            continue;
        trTitle += MessageFormat.format("<th>{0}</th>", listTitle.get(i));
    }
    trTitle = "<tr>" + trTitle + "</tr>";
    JSONObject dataValue = JSONObject.fromObject(valuetest);
    int rowCount = dataValue != null ? dataValue.size() : 1;
    StringBuilder sbTr = new StringBuilder();
    // 如果有统计增加一行
    String tdSum = "";
    TreeMap<Integer, Float> SumValueDic = new TreeMap<Integer, Float>();
    for (int row = 0; row < rowCount; row++) {
        JSONArray rowValue = (dataValue != null && dataValue.has(name + row)) ? dataValue.getJSONArray(name + row) : null;
        // 默认一行
        String tr = "";
        for (int i = 0; i < tdCount; i++) {
            String tdname = name + "[" + i + "]";
            String sum = "1".equals(listSum.get(i)) ? "sum=\"" + tdname + "\"" : // 是否参与统计
            "";
            String tdValue = null;
            if (i < listValue.size()) {
                tdValue = listValue.get(i);
            }
            tdValue = (rowValue != null && rowValue.size() > i) ? rowValue.getString(i).toString() : tdValue;
            // 类型
            String type = listType.get(i);
            if (// 一次循环计算该列的值
            sum != "") {
                // region 计算统计值
                float tempTdValue = 0;
                if (SumValueDic.containsKey(i))
                    tempTdValue = SumValueDic.get(i);
                try {
                    float resultTdTemp = 0;
                    resultTdTemp = Float.parseFloat(tdValue);
                    // float.TryParse(tdValue, out resultTdTemp);
                    tempTdValue += resultTdTemp;
                } catch (Exception e) {
                    tdValue = "0";
                }
                if (SumValueDic.containsKey(i))
                    SumValueDic.subMap(i, (int) tempTdValue);
                else
                    SumValueDic.put(i, tempTdValue);
            // endregion
            }
            if (// 最后一列不显示
            i == tdCount - 1) {
                if ("view".equals(action))
                    continue;
                else
                    // tr += "<td></td>";
                    tr += "<td><a href=\"javascript:void(0);\" class=\"delrow \">删除</a></td>";
            // tr +=
            // string.Format("<td><a href=\"javascript:void(0);\" class=\"delrow {0}\">删除</a></td>",
            // dataValue != null ? "" : "hide");
            } else {
                if ("view".equals(action)) {
                    tr += MessageFormat.format("<td>{0}</td>", tdValue);
                } else {
                    if ("text".equals(type))
                        tr += MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", tdValue, tdname, sum);
                    else if ("int".equals(type))
                        tr += MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", tdValue, tdname, sum);
                    else if ("textarea".equals(type))
                        tr += MessageFormat.format("<td><textarea class=\"input-medium\" name=\"{0}\" >{1}</textarea></td>", tdname, tdValue, sum);
                    else if ("calc".equals(type))
                        tr += MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", tdValue, tdname, sum);
                }
            }
            if (// 统计的行只有一行
            row == 0) {
                // region
                if (sum != "") {
                    if ("view".equals(action))
                        tdSum += MessageFormat.format("<td>合计：value{0}{1}</td>", i, listUnit.get(i));
                    else
                        tdSum += MessageFormat.format("<td>合计：<input class=\"input-small\" type=\"text\" value=\"value{0}\" name=\"{1}[total]\" {2}\">{3}</td>", i, tdname, sum, listUnit.get(i));
                } else {
                    tdSum += "<td></td>";
                }
            // endregion
            }
        }
        sbTr.append(MessageFormat.format("<tr class=\"template\">{0}</tr>", tr));
    }
    /*
		 * if(!StringUtils.isBlank(tdSum)){
		 * 
		 * }
		 */
    if (!StringUtils.isBlank(tdSum)) {
        for (Integer i : SumValueDic.keySet()) {
            tdSum = tdSum.replace("value" + i, SumValueDic.get(i).toString());
            tdSum = MessageFormat.format("<tbody class=\"sum\"><tr>{0}</tr></tbody>", tdSum);
        }
    }
    if ("view".equals(action))
        theader = MessageFormat.format(theader, tdCount, title, "", trTitle);
    else
        theader = MessageFormat.format(theader, tdCount, title, btnAdd, trTitle);
    temp_html = MessageFormat.format(temp, theader, sbTr.toString(), tdSum);
    return temp_html;
}


public String GetMacros(JSONObject item,Map<String,Object> formData,String action){
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    String temp_html = item.getString("content").toString();
    String microtype = "text";
    if (value == null) {
        // region 制造规则值
        String type = item.getString("orgtype").toString();
        String date_format = "";
        // date”、“week”、“month”、“time”、“datetime”和“datetime-local
        Date date = new Date();
        if (type.equals("sys_date")) {
            date_format = "yyyy-MM-dd";
            value = DateUtils.formatDate(date, date_format);
            microtype = "date";
        } else if (type.equals("sys_date_cn")) {
            date_format = "yyyy年MM月dd日";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_date_cn_short3")) {
            date_format = "yyyy年";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_date_cn_short4")) {
            date_format = "yyyy";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_date_cn_short1")) {
            date_format = "yyyy年MM月";
            value = DateUtils.formatDate(date, date_format);
            microtype = "month";
        } else if (type.equals("sys_date_cn_short2")) {
            date_format = "MM月dd日";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_time")) {
            date_format = "HH:mm:ss";
            microtype = "time";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_datetime")) {
            date_format = "yyyy-MM-dd'T'HH:mm";
            microtype = "datetime-local";
            value = DateUtils.formatDate(date, date_format);
        } else if (type.equals("sys_week")) {
            // String[] Day = new String[] { "星期日", "星期一", "星期二", "星期三",
            // "星期四", "星期五", "星期六" };
            // value =
            // Day[Convert.ToInt32(DateTime.Now.DayOfWeek.ToString("d"))].ToString();
            value = DateUtils.formatDate(date, "EEEE");
        // microtype = "week";
        } else if (type.equals("sys_userid")) {
            // if(!$def_value)
            // $def_value = $controller["user"]["uid"];
            // $tpl = str_replace("{macros}",$def_value,$tpl);
            value = "${userId}";
        } else if (type.equals("sys_realname")) {
            // if(!$def_value)
            // $def_value = $controller["user"]["real_name"];
            value = "${userName}";
        } else {
        }
    // endregion
    }
    if ("view".equals(action))
        return value.replace("${userId}", ClientManager.getInstance().getClient().getUser().getId()).replace("${userName}", ClientManager.getInstance().getClient().getUser().getUserName());
    if (value != null) {
        temp_html = temp_html.replace("type=\"text\"", "type=\"" + microtype + "\" ");
        return temp_html.replace("{macros}", value);
    } else {
        return temp_html;
    }
}


public String GetTextBox(JSONObject item,Map<String,Object> formData,String action){
    String temp = "<input type=\"text\" value=\"{0}\"  name=\"{1}\"  style=\"{2}\"/>";
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    if (value == null)
        value = item.getString("value") == null ? "" : item.getString("value").toString();
    String style = item.getString("style") == null ? "" : item.getString("style").toString();
    String temp_html = MessageFormat.format(temp, value, name, style);
    if ("view".equals(action))
        return MessageFormat.format(temp_view, style, value);
    else
        return temp_html;
}


public String GetQrcode(JSONObject item,Map<String,Object> formData,String action){
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    String temp_html = "";
    String temp = "";
    String orgType = item.getString("orgtype").toString();
    String style = item.getString("style").toString();
    if ("text".equals(orgType)) {
        orgType = "文本";
    } else if ("url".equals(orgType)) {
        orgType = "超链接";
    } else if ("tel".equals(orgType)) {
        orgType = "电话";
    }
    String qrcode_value = "";
    if (item.getString("value") != null)
        qrcode_value = item.getString("value").toString();
    // print_R($qrcode_value);exit; //array(value,qrcode_url)
    if ("edit".equals(action)) {
        temp = orgType + "二维码 <input type=\"text\" name=\"{0}\" value=\"{1}\"/>";
        temp_html = MessageFormat.format(temp, name, value);
    } else if ("view".equals(action)) {
        // 可以采用 http://qrcode.leipi.org/
        style = "";
        if (item.getString("orgwidth") != null) {
            style = "width:" + item.getString("orgwidth").toString() + "px;";
        }
        if (item.getString("orgheight") != null) {
            style += "height:" + item.getString("orgheight").toString() + "px;";
        }
        temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
        temp_html = MessageFormat.format(temp_html, name, value, style);
    } else if ("preview".equals(action)) {
        style = "";
        if (item.getString("orgwidth") != null) {
            style = "width:" + item.getString("orgwidth").toString() + "px;";
        }
        if (item.getString("orgheight") != null) {
            style += "height:" + item.getString("orgheight").toString() + "px;";
        }
        temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
        temp_html = MessageFormat.format(temp_html, name, value, style);
    }
    return temp_html;
}


public String GetHtml(String parseHtml,String contentData,String action){
    // action=action!=null && !"".equals(action)?action:"view";
    // 表单数据
    Map<String, Object> tableData = new HashMap<String, Object>();
    String html = parseHtml;
    JSONArray jsonArray = new JSONArray().fromObject(contentData);
    for (int f = 0; f < jsonArray.size(); f++) {
        if (jsonArray.getJSONObject(f) == null || "".equals(jsonArray.getJSONObject(f)))
            continue;
        // 获取对象
        JSONObject json = jsonArray.getJSONObject(f);
        String name = "";
        if (json == null) {
            continue;
        }
        String leipiplugins = json.getString("leipiplugins").toString();
        if ("checkboxs".equals(leipiplugins))
            name = json.getString("parse_name").toString();
        else
            name = json.getString("name").toString();
        String temp_html = "";
        if ("text".equals(leipiplugins)) {
            temp_html = GetTextBox(json, tableData, action);
        } else if ("textarea".equals(leipiplugins)) {
            temp_html = GetTextArea(json, tableData, action);
        } else if ("radios".equals(leipiplugins)) {
            temp_html = GetRadios(json, tableData, action);
        } else if ("select".equals(leipiplugins)) {
            temp_html = GetSelect(json, tableData, action);
        } else if ("checkboxs".equals(leipiplugins)) {
            temp_html = GetCheckboxs(json, tableData, action);
        } else if ("macros".equals(leipiplugins)) {
            temp_html = GetMacros(json, tableData, action);
        } else if ("qrcode".equals(leipiplugins)) {
            temp_html = GetQrcode(json, tableData, action);
        } else if ("listctrl".equals(leipiplugins)) {
            temp_html = GetListctrl(json, tableData, action);
        } else if ("progressbar".equals(leipiplugins)) {
            // case ""://进度条 (未做处理)
            /* temp_html = GetProgressbar(json, tableData, action); */
            temp_html = json.getString("content").toString();
        } else if ("popup".equals(leipiplugins)) {
            temp_html = GetPopUp(json, tableData, action);
        } else {
            temp_html = json.getString("content").toString();
        }
        html = html.replace("{" + name + "}", temp_html);
    }
    return html;
}


public String GetTextArea(JSONObject item,Map<String,Object> formData,String action){
    String script = "";
    if (item.getString("orgrich") != null && "1".equals(item.getString("orgrich").toString()))
        script = "orgrich=\"true\" ";
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    if (value == null)
        value = item.getString("value") == null ? "" : item.getString("value").toString();
    String style = item.getString("style") == null ? "" : item.getString("style").toString();
    String temp = "<textarea  name=\"{0}\" id=\"{1}\"  style=\"{2}\" {3}>{4}</textarea>";
    String temp_html = MessageFormat.format(temp, name, name, style, script, value);
    if ("view".equals(action))
        return MessageFormat.format(temp_view, style, value);
    else
        return temp_html;
}


public String GetRadios(JSONObject item,Map<String,Object> formData,String action){
    JSONArray radiosOptions = item.getJSONArray("options");
    // JArray radiosOptions = item["options"] as JArray;
    String temp = "<input type=\"radio\" name=\"{0}\" value=\"{1}\"  {2}>{3}&nbsp;";
    String temp_html = "";
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    String cvalue_ = "";
    for (int f = 0; f < radiosOptions.size(); f++) {
        // 获取对象
        JSONObject json = radiosOptions.getJSONObject(f);
        String cvalue = json.getString("value").toString();
        String Ischecked = "";
        if (value == null) {
            String check = (json.has("checked") && json.getString("checked") != null) ? json.getString("checked").toString() : "";
            if ("checked".equals(check) || "true".equals(check)) {
                Ischecked = " checked=\"checked\" ";
                cvalue_ = cvalue;
            }
        }
        temp_html += MessageFormat.format(temp, name, cvalue, Ischecked, cvalue);
    }
    if ("view".equals(action))
        return MessageFormat.format(temp_view, "&nbsp;", cvalue_);
    else
        return temp_html;
}


public void main(String[] arg){
    // test.replaceAll(regex, replacement);
    String test = "都 `发````";
    System.out.print(test.split("`").length);
}


public String GetSelect(JSONObject item,Map<String,Object> formData,String action){
    String name = item.getString("name").toString();
    String value = formData.get(name) == null ? null : formData.get(name).toString();
    String temp_html = item.getString("content").toString();
    if (// 用户设置过值
    value != null) {
        temp_html = temp_html.replace("selected=\"selected\"", "");
        value = "value=\"" + value + "\"";
        String r = value + " selected=\"selected\"";
        temp_html = temp_html.replace(value, r);
    }
    if ("view".equals(action)) {
        // 查看
        return MessageFormat.format(temp_view, "&nbsp;", value != null ? value : item.getString("value").toString().split(",")[0]);
    } else
        return temp_html;
}


public String GetCheckboxs(JSONObject item,Map<String,Object> formData,String action){
    String temp_html = "";
    String temp = "<input type=\"checkbox\" name=\"{0}\" value=\"{1}\" {2}>{3}&nbsp;";
    // view 查看值
    String view_value = "";
    JSONArray checkOptions = item.getJSONArray("options");
    for (int f = 0; f < checkOptions.size(); f++) {
        // 获取对象
        JSONObject json = checkOptions.getJSONObject(f);
        String name = json.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name).toString();
        String cvalue = json.getString("value").toString();
        String Ischecked = "";
        if (value == null) {
            String check = (json.has("checked") && json.getString("checked") != null) ? json.getString("checked").toString() : "";
            if (check.equals("checked") || check.equals("true")) {
                Ischecked = " checked=\"checked\" ";
                // view 查看值
                view_value += cvalue + "&nbsp";
            }
        } else if (value != null && value.equals(cvalue)) {
            Ischecked = " checked=\"checked\" ";
            // view 查看值
            view_value += cvalue + "&nbsp";
        }
        temp_html += MessageFormat.format(temp, name, cvalue, Ischecked, cvalue);
    }
    if ("view".equals(action))
        return MessageFormat.format(temp_view, "&nbsp;", view_value);
    else
        return temp_html;
}


}