package com.zis.common.controllertemplate;
 import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
public class CommonImportController {

 protected  Logger logger;


public String getFailPage()


public String initTemplatePath()


public Logger initLogger()


public void saveImportRecord(List<T> list,String memo)


public Map<String,Integer> initPropMapping()


public String upload(InputStream fileInputStream,String memo,ModelMap map){
    // 设置模板文件，用于检验导入文件是否合法
    String templatePath = initTemplatePath();
    Integer headerRownums = setHeaderRowNums(1);
    try {
        // 初始化导入器
        FileImporter<T> im = new ExcelImporter<T>(fileInputStream, templatePath);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            map.put("actionError", errMsg);
            return getFailPage();
        }
        String subCheck = subCheckFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            map.put("actionError", subCheck);
            return getFailPage();
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropMapping();
        T instance = getInstance();
        List<T> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            map.put("actionError", "导入失败，文件为空");
            return getFailPage();
        }
        saveImportRecord(list, memo);
        return getSuccessPage();
    } catch (Exception e) {
        map.put("actionError", "导入失败: " + e.getMessage());
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return getFailPage();
    }
}


public String getSuccessPage()


public Integer setHeaderRowNums(Integer num){
    return num;
}


public String subCheckFileFormat(List<String> factHeader){
    return null;
}


public T getInstance()


public String getFaildRecordMessage(T failRecord)


}