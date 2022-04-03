package com.zis.common.excel;
 import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
public class FileImporter {

 protected  String basePath;

 protected  Integer headerRowNums;

 protected  String templateName;

 protected  InputStream inputStream;

/**
 * @param inputStream
 *            �����ļ�
 * @param templateName
 *            ģ�����
 */
public FileImporter(InputStream inputStream, String templateName) {
    this.templateName = templateName;
    this.inputStream = inputStream;
}
public void setFieldValue(T obj,String propName,Integer rowNums,Integer index){
    Field field = obj.getClass().getDeclaredField(propName);
    String fieldType = field.getType().getSimpleName();
    field.setAccessible(true);
    if (fieldType.equals("String")) {
        field.set(obj, getString(rowNums, index));
    } else if (fieldType.equals("Integer") || fieldType.equals("int")) {
        field.set(obj, getInteger(rowNums, index));
    } else if (fieldType.equals("Double") || fieldType.equals("double") || fieldType.equals("Float") || fieldType.equals("float")) {
        field.set(obj, getDouble(rowNums, index));
    } else if (fieldType.equals("Date")) {
        field.set(obj, getDate(rowNums, index));
    }
}


public void setHeaderRowNums(Integer headerRowNums){
    this.headerRowNums = headerRowNums;
}


public Double getDouble(Integer rowNums,int index)


public Integer getInteger(Integer rowNums,int index)


public void setTemplateName(String templateName){
    this.templateName = templateName;
}


@SuppressWarnings("unchecked")
public List<T> parse(T t,Map<String,Integer> propMapping){
    List<T> list = new ArrayList<T>();
    for (int i = headerRowNums; i < getRowNums() + 1; i++) {
        T obj = (T) t.getClass().newInstance();
        int emptyCount = 0;
        for (String propName : propMapping.keySet()) {
            String data = getString(i, propMapping.get(propName));
            if (StringUtils.isBlank(data)) {
                emptyCount++;
            }
            setFieldValue(obj, propName, i, propMapping.get(propName));
        }
        // 过滤掉整行为空的记录
        if (emptyCount >= propMapping.size()) {
            continue;
        }
        list.add(obj);
    }
    return list;
}


public String getString(Integer rowNums,int index)


public Integer getHeaderRowNums(){
    return headerRowNums;
}


public InputStream getInputStream(){
    return inputStream;
}


public List<String> loadFactHeader()


public void setInputStream(InputStream inputStream){
    this.inputStream = inputStream;
}


public String getTemplateName(){
    return templateName;
}


public Object getDate(Integer rowNums,Integer index)


public Integer getRowNums()


public String validate(){
    // 如果未指定模板，则不进行检查
    if (templateName == null) {
        return null;
    }
    // 加载预设模板
    ResourceLoader loader = new DefaultResourceLoader();
    Resource r = loader.getResource(basePath + templateName);
    if (!r.exists()) {
        throw new IOException("没找到资源文件:" + templateName);
    }
    List<String> headerTpl = loadFileFormat(r.getInputStream());
    // 加载导入的文件表头
    List<String> factList = loadFactHeader();
    // 检验文件格式
    for (int i = 0; i < headerTpl.size(); i++) {
        String expect = headerTpl.get(i);
        String fact = factList.get(i);
        if (!expect.equals(fact)) {
            String fmt = "文件不合法，第%1$s列应该为%2$s，而导入文件是%3$s。";
            return String.format(fmt, (i + 1), expect, fact);
        }
    }
    return null;
}


public List<String> loadFileFormat(InputStream input)


}