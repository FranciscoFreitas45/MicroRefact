package com.ukefu.util.bi.model;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.ukefu.util.UKTools;
import com.ukefu.webim.web.model.ColumnProperties;
public class ValueData implements Cloneable{

 private  long serialVersionUID;

 private  String name;

 private  Object value;

 private  ValueData mergevalue;

 private  int rowspan;

 private  int colspan;

 private  boolean merge;

 private  String foramatValue;

 private  String formatStr;

 private  Level row;

 private  Level col;

 private  String valueType;

 private  boolean canbedrill;

 private  String cellmergeid;

 private  String drillthroughsql;

 private  Object tempValue;

 private  String url;

 private  String target;

 private  String vtclass;

 private  String valueStyle;

 private  String style;

public ValueData(Object value, String foramatValue, String valueType, boolean canbedrill, String sql, String name, String formatStr, List<ColumnProperties> cols) {
    this.foramatValue = foramatValue;
    this.value = value;
    if (!StringUtils.isBlank(foramatValue) && foramatValue.toLowerCase().indexOf("nan") >= 0) {
        this.foramatValue = "";
        this.value = null;
    }
    this.valueType = valueType;
    this.drillthroughsql = sql;
    this.canbedrill = canbedrill;
    this.name = name;
    this.formatStr = formatStr;
    if (cols != null) {
        for (ColumnProperties col : cols) {
            if (col.getDataname().equals(name)) {
                this.name = col.getTitle();
            }
        }
    }
}public ValueData(String name, Object value, String foramatValue, String valueType) {
    this.name = name;
    this.value = value;
    this.valueType = valueType;
    this.foramatValue = foramatValue;
}public ValueData(String name, Object value, String foramatValue, String valueType, String coltype) {
    this.name = name;
    this.value = value;
    this.valueType = valueType;
    this.foramatValue = foramatValue;
}
public void setName(String name){
    this.name = name;
}


public String getVtclass(){
    return vtclass;
}


public boolean isCanbedrill(){
    return canbedrill;
}


public Level getCol(){
    return col;
}


public String getName(){
    return name;
}


public void setFormatStr(String formatStr){
    this.formatStr = formatStr;
}


public Level getRowData(String title){
    Level level = this.row;
    if (level != null && level.getDimname() != null) {
        while (!level.getDimname().equals(title)) {
            level = level.getParent();
            if (level != null && level.getParent() == null) {
                level = null;
                break;
            }
        }
    }
    return level;
}


public void setValueStyle(String valueStyle){
    this.valueStyle = valueStyle;
}


public void setCanbedrill(boolean canbedrill){
    this.canbedrill = canbedrill;
}


public void setDrillthroughsql(String drillthroughsql){
    this.drillthroughsql = drillthroughsql;
}


public String getCellmergeid(){
    return cellmergeid;
}


public void setVtclass(String vtclass){
    this.vtclass = vtclass;
}


public void setColspan(int colspan){
    this.colspan = colspan;
}


public Object getTempValue(){
    return tempValue;
}


public void setTempValue(Object tempValue){
    this.tempValue = tempValue;
}


public String getTarget(){
    return target;
}


public void setRowspan(int rowspan){
    this.rowspan = rowspan;
}


public String getStyle(){
    return style;
}


public void setValueType(String valueType){
    this.valueType = valueType;
}


public String getValueStyle(){
    return valueStyle;
}


public void setStyle(String style){
    this.style = style;
}


public void setTarget(String target){
    this.target = target;
}


public void setRow(Level row){
    this.row = row;
}


public String getValueType(){
    return valueType;
}


public ValueData getMergevalue(){
    return mergevalue;
}


public String getForamatValue(){
    return foramatValue;
}


public String getFormatStr(){
    return formatStr;
}


public void setForamatValue(String foramatValue){
    this.foramatValue = foramatValue;
}


public Level getRow(){
    return row;
}


public void setMergevalue(ValueData mergevalue){
    this.mergevalue = mergevalue;
}


public void setMerge(boolean merge){
    this.merge = merge;
}


public int getRowspan(){
    return rowspan == 0 ? 1 : rowspan;
}


public void setUrl(String url){
    this.url = url;
}


public Object getValue(){
    return this.mergevalue != null && this.mergevalue != this ? mergevalue.getValue() : value;
}


public String getUrl(){
    return url;
}


public int getColspan(){
    return colspan == 0 ? 1 : colspan;
}


public String getDataid(){
    return UKTools.md5(this.name != null ? this.name : "");
}


public void setCellmergeid(String cellmergeid){
    this.cellmergeid = cellmergeid;
}


public boolean isMerge(){
    return merge;
}


public void setValue(Object value){
    this.value = value;
}


public String getDrillthroughsql(){
    return drillthroughsql;
}


public String toString(){
    return this.foramatValue;
}


public void setCol(Level col){
    this.col = col;
}


}