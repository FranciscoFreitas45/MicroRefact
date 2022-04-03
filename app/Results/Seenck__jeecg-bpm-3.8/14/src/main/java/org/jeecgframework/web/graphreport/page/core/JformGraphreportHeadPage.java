package org.jeecgframework.web.graphreport.page.core;
 import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelIgnore;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.web.graphreport.entity.core.JformGraphreportHeadEntity;
import org.jeecgframework.web.graphreport.entity.core.JformGraphreportItemEntity;
import java.util.ArrayList;
import java.util.List;
@ExcelTarget("jformGraphreportHeadPage")
public class JformGraphreportHeadPage {

@ExcelCollection(name = "图表配置", orderNum = "9")
 private  List<JformGraphreportItemEntity> jformGraphreportItemList;

@ExcelEntity
 private  JformGraphreportHeadEntity jformGraphreportHeadEntity;

@ExcelIgnore
 private  String id;

@ExcelIgnore
 private  String name;

@ExcelIgnore
 private  String code;

@ExcelIgnore
 private  String cgrSql;

@ExcelIgnore
 private  String content;

@ExcelIgnore
 private  String ytext;

@ExcelIgnore
 private  String categories;

@ExcelIgnore
 private  String isShowList;

@ExcelIgnore
 private  String xPageJs;

public JformGraphreportHeadPage() {
}public JformGraphreportHeadPage(List<JformGraphreportItemEntity> jformGraphreportItemList, JformGraphreportHeadEntity jformGraphreportHeadEntity) {
    this.jformGraphreportItemList = jformGraphreportItemList;
    this.jformGraphreportHeadEntity = jformGraphreportHeadEntity;
}
public void setName(String name){
    this.name = name;
}


public void setIsShowList(String isShowList){
    this.isShowList = isShowList;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return this.name;
}


public void setJformGraphreportItemList(List<JformGraphreportItemEntity> jformGraphreportItemList){
    this.jformGraphreportItemList = jformGraphreportItemList;
}


public void setCode(String code){
    this.code = code;
}


public String getContent(){
    return this.content;
}


public List<JformGraphreportItemEntity> getJformGraphreportItemList(){
    return jformGraphreportItemList;
}


public JformGraphreportHeadEntity getJformGraphreportHeadEntity(){
    return jformGraphreportHeadEntity;
}


public String getId(){
    return this.id;
}


public String getYtext(){
    return this.ytext;
}


public void setYtext(String ytext){
    this.ytext = ytext;
}


public String getCgrSql(){
    return this.cgrSql;
}


public void setCategories(String categories){
    this.categories = categories;
}


public String getCategories(){
    return this.categories;
}


public void setId(String id){
    this.id = id;
}


public String getIsShowList(){
    return this.isShowList;
}


public String getXPageJs(){
    return this.xPageJs;
}


public void setJformGraphreportHeadEntity(JformGraphreportHeadEntity jformGraphreportHeadEntity){
    this.jformGraphreportHeadEntity = jformGraphreportHeadEntity;
}


public void setXPageJs(String xPageJs){
    this.xPageJs = xPageJs;
}


public String getCode(){
    return this.code;
}


public void setCgrSql(String cgrSql){
    this.cgrSql = cgrSql;
}


}