package com.zis.common.actiontemplate;
 import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
public class CommonImportAction extends ActionSupport{

 protected  long serialVersionUID;

 protected  File excelFile;

 protected  String excelFileFileName;

 protected  String templatePath;

 protected  Integer headerRownums;

 protected  List<T> cannotImport;

 protected  Logger logger;


public String initTemplatePath()


public void saveImportRecord(List<T> list)


@Validations(/* �������� */
requiredFields = { @RequiredFieldValidator(fieldName = "excelFile", key = "文件必须输入") })
public String upload(){
    // 设置模板文件，用于检验导入文件是否合法
    templatePath = initTemplatePath();
    headerRownums = setHeaderRowNums(1);
    try {
        // 初始化导入器
        FileImporter<T> im = new ExcelImporter<T>(new FileInputStream(excelFile), templatePath);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            this.addActionError(errMsg);
            return INPUT;
        }
        String subCheck = subCheckFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            this.addActionError(subCheck);
            return INPUT;
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropMapping();
        T instance = getInstance();
        List<T> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            this.addActionError("导入失败，文件为空");
            return INPUT;
        }
        saveImportRecord(list);
        // 导入失败的数据处理
        if (!cannotImport.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (T failRecord : cannotImport) {
                builder.append(getFaildRecordMessage(failRecord));
            }
            this.addActionError("导入失败的记录有：" + builder.toString());
            return INPUT;
        }
        return SUCCESS;
    } catch (Exception e) {
        this.addActionError("导入失败: " + e.getMessage());
        logger.error(e.getMessage(), e);
        return INPUT;
    }
}


public Integer setHeaderRowNums(Integer num){
    return num;
}


public String getFaildRecordMessage(T failRecord)


public Logger initLogger()


public void setExcelFile(File excelFile){
    this.excelFile = excelFile;
}


public File getExcelFile(){
    return excelFile;
}


public String getExcelFileFileName(){
    return excelFileFileName;
}


public Map<String,Integer> initPropMapping()


public void setExcelFileFileName(String excelFileFileName){
    this.excelFileFileName = excelFileFileName;
}


public String subCheckFileFormat(List<String> factHeader){
    return null;
}


public T getInstance()


}