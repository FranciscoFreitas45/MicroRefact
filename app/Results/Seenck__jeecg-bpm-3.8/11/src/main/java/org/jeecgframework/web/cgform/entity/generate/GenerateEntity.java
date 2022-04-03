package org.jeecgframework.web.cgform.entity.generate;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import Interface.CgFormHeadEntity;
public class GenerateEntity {

 private  long serialVersionUID;

 private  String entityPackage;

 private  String entityName;

 private  String tableName;

 private  String ftlDescription;

 private  String primaryKeyPolicy;

 private  String[] foreignKeys;

 private  Integer fieldRowNum;

 private  String projectPath;

 private  String packageStyle;

 private  String supportRestful;

 private  CgFormHeadEntity cgFormHead;

 private  List<CgformButtonEntity> buttons;

 private  Map<String,String[]> buttonSqlMap;

 private  CgformEnhanceJsEntity listJs;

 private  CgformEnhanceJsEntity formJs;

 private  Map<String,CgformEnhanceJavaEntity> buttonJavaMap;


public void setTableName(String tableName){
    this.tableName = tableName;
}


public String getPrimaryKeyPolicy(){
    return primaryKeyPolicy;
}


public void setForeignKeys(String[] foreignKeys){
    this.foreignKeys = foreignKeys;
}


public void setFormJs(CgformEnhanceJsEntity formJs){
    this.formJs = formJs;
}


public void setSupportRestful(String supportRestful){
    this.supportRestful = supportRestful;
}


public void setFieldRowNum(Integer fieldRowNum){
    this.fieldRowNum = fieldRowNum;
}


public String getSupportRestful(){
    return supportRestful;
}


public String getFtlDescription(){
    return ftlDescription;
}


public void setPrimaryKeyPolicy(String primaryKeyPolicy){
    this.primaryKeyPolicy = primaryKeyPolicy;
}


public CgformEnhanceJsEntity getFormJs(){
    return formJs == null ? new CgformEnhanceJsEntity() : formJs;
}


public String getEntityPackage(){
    return entityPackage;
}


public String getEntityName(){
    return entityName;
}


public void setFtlDescription(String ftlDescription){
    this.ftlDescription = ftlDescription;
}


public void setListJs(CgformEnhanceJsEntity listJs){
    this.listJs = listJs;
}


public CgformEnhanceJsEntity getListJs(){
    return listJs == null ? new CgformEnhanceJsEntity() : listJs;
}


public void setButtons(List<CgformButtonEntity> buttons){
    this.buttons = buttons;
}


public Map<String,CgformEnhanceJavaEntity> getButtonJavaMap(){
    return buttonJavaMap;
}


public void setButtonJavaMap(Map<String,CgformEnhanceJavaEntity> buttonJavaMap){
    this.buttonJavaMap = buttonJavaMap;
}


public List<CgformButtonEntity> getButtons(){
    return buttons;
}


public String getTableName(){
    return tableName;
}


public String getProjectPath(){
    String pt = projectPath;
    if (pt != null) {
        pt = pt.replace("\\", "/");
        if (!pt.endsWith("/")) {
            pt = pt + "/";
        }
    }
    return pt;
}


public void setProjectPath(String projectPath){
    this.projectPath = projectPath;
}


public String getPackageStyle(){
    return packageStyle;
}


public Map<String,String[]> getButtonSqlMap(){
    return buttonSqlMap;
}


public void setEntityPackage(String entityPackage){
    this.entityPackage = entityPackage;
}


public void setButtonSqlMap(Map<String,String[]> buttonSqlMap){
    this.buttonSqlMap = buttonSqlMap;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public void setCgFormHead(CgFormHeadEntity cgFormHead){
    this.cgFormHead = cgFormHead;
}


public GenerateEntity deepCopy(){
    // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(this);
    // 将流序列化成对象
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return (GenerateEntity) ois.readObject();
}


public String[] getForeignKeys(){
    return foreignKeys;
}


public Integer getFieldRowNum(){
    return fieldRowNum;
}


public Object clone(){
    return super.clone();
}


public CgFormHeadEntity getCgFormHead(){
    return cgFormHead;
}


public void setPackageStyle(String packageStyle){
    this.packageStyle = packageStyle;
}


}