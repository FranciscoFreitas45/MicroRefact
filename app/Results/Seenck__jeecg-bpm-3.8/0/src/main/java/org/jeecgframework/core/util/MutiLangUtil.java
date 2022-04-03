package org.jeecgframework.core.util;
 import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.model.json.ComboTree;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import DTO.ReflectHelper;
public class MutiLangUtil {

 private  Log logger;


public String paramUpdSuccess(String param_lang_key){
    String message = getLang("common.edit.success.param", param_lang_key);
    return message;
}


public void setMutiTree(List<?> treeList){
    if (ListUtils.isNullOrEmpty(treeList))
        return;
    for (Object treeItem : treeList) {
        ReflectHelper reflectHelper = new ReflectHelper(treeItem);
        // treeItem.getText();
        String lang_key = (String) reflectHelper.getMethodValue("text");
        String lang_context = getLang(lang_key);
        reflectHelper.setMethodValue("text", lang_context);
    }
}


public String getLang(String langKey){
    // 如果为空，返回空串，防止返回null
    if (langKey == null) {
        return "";
    }
    HttpServletRequest request = ContextHolderUtils.getRequest();
    String language = BrowserUtils.getBrowserLanguage(request);
    if (request.getSession().getAttribute("lang") != null) {
        language = (String) request.getSession().getAttribute("lang");
    }
    String langContext = ResourceUtil.getMutiLan(langKey + "_" + language);
    if (StringUtil.isEmpty(langContext)) {
        langContext = ResourceUtil.getMutiLan("common.notfind.langkey" + "_" + language);
        if ("null".equals(langContext) || langContext == null || langKey.startsWith("?")) {
            langContext = "";
        }
        langContext = langContext + langKey;
    }
    return langContext;
}


public boolean existLangKey(String lang_key,String langCode){
    String langContext = ResourceUtil.getMutiLan(lang_key + "_" + langCode);
    if (oConvertUtils.isNotEmpty(langContext)) {
        return true;
    }
    return false;
}


public String paramDelSuccess(String param_lang_key){
    String message = getLang("common.delete.success.param", param_lang_key);
    return message;
}


public String paramAddSuccess(String param_lang_key){
    String message = getLang("common.add.success.param", param_lang_key);
    return message;
}


public String paramDelFail(String param_lang_key){
    String message = getLang("common.delete.fail.param", param_lang_key);
    return message;
}


public String doMutiLang(String title,String langArg){
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // long start = System.currentTimeMillis();
    // logger.info("================================ doMutiLang 开始时间:"+sdf.format(new Date())+"==============================");
    String context = getLang(title, langArg);
    // long end = System.currentTimeMillis();
    // logger.info("=============================== doMutiLang 结束时间:"+sdf.format(new Date())+"==============================");
    // logger.info("================================ doMutiLang 耗时:"+(end-start)+"ms==============================");
    return context;
}


public void setMutiLangValueForList(List<Object> list,String attributes){
    if (ListUtils.isNullOrEmpty(list)) {
        return;
    }
    if (attributes == null || attributes.length == 0) {
        return;
    }
    List<Object> newList = new ArrayList<Object>();
    for (Object obj : list) {
        // 如果直接操作列表中的原始对象，则会触发Hibernate的update操作，所以使用类似克隆的方式进行处理；
        Object cloneObj = null;
        try {
            cloneObj = Class.forName(obj.getClass().getName()).newInstance();
            MyBeanUtils.copyBean2Bean(cloneObj, obj);
        } catch (Exception e) {
            e.printStackTrace();
            continue;
        }
        ReflectHelper reflectHelper = new ReflectHelper(cloneObj);
        for (String attribute : attributes) {
            String lang_key = (String) reflectHelper.getMethodValue(attribute);
            String lang_context = getLang(lang_key);
            reflectHelper.setMethodValue(attribute, lang_context);
        }
        newList.add(cloneObj);
    }
    list.clear();
    list.addAll(newList);
}


public String paramUpdFail(String param_lang_key){
    String message = getLang("common.edit.fail.param", param_lang_key);
    return message;
}


public void setMutiComboTree(List<ComboTree> treeList){
    for (ComboTree index : treeList) {
        index.setText(getLang(index.getText()));
        if (index.getChildren() != null && index.getChildren().size() > 0) {
            setMutiComboTree(index.getChildren());
        }
    }
}


}