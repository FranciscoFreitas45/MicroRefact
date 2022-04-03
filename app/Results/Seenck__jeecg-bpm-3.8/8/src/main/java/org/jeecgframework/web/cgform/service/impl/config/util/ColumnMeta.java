package org.jeecgframework.web.cgform.service.impl.config.util;
 import org.apache.commons.lang.StringUtils;
public class ColumnMeta {

 private  String tableName;

 private  String columnId;

 private  String columnName;

 private  int columnSize;

 private  String colunmType;

 private  String comment;

 private  String fieldDefault;

 private  int decimalDigits;

 private  String isNullable;

 private  String pkType;

 private  String oldColumnName;


public void setTableName(String tableName){
    this.tableName = tableName;
}


public void setFieldDefault(String fieldDefault){
    this.fieldDefault = fieldDefault;
}


public boolean equalsDefault(ColumnMeta meta){
    if (meta == this) {
        return true;
    }
    return isEquals(comment, meta.getComment());
}


public boolean equalsByDataType(Object obj,String dataType){
    if (obj == this) {
        return true;
    }
    if (!(obj instanceof ColumnMeta)) {
        return false;
    }
    ColumnMeta meta = (ColumnMeta) obj;
    if ("SQLSERVER".equals(dataType)) {
        // 时间类型不比较长度
        if (colunmType.contains("date") || colunmType.contains("blob") || colunmType.contains("text")) {
            return columnName.equals(meta.getColumnName()) && isNullable.equals(meta.isNullable);
        } else {
            return colunmType.equals(meta.getColunmType()) && isNullable.equals(meta.isNullable) && columnSize == meta.getColumnSize();
        }
    } else {
        if (colunmType.contains("date") || colunmType.contains("blob") || colunmType.contains("text")) {
            // update-begin--Author:Yandong----  Date:20180521 ----for：TASK #2727 【online问题】UE编辑器图片 回显问题----blob类型报错 图片上传问题-----
            return colunmType.equals(meta.getColunmType()) && columnName.equals(meta.getColumnName()) && isNullable.equals(meta.isNullable) && isEquals(comment, meta.getComment()) && isEquals(fieldDefault, meta.getFieldDefault());
        // update-end--Author:Yandong----  Date:20180521 ----for：TASK #2727 【online问题】UE编辑器图片 回显问题----blob类型报错 图片上传问题-----
        } else {
            return colunmType.equals(meta.getColunmType()) && isNullable.equals(meta.isNullable) && columnSize == meta.getColumnSize() && isEquals(comment, meta.getComment()) && isEquals(fieldDefault, meta.getFieldDefault());
        }
    }
}


public String getColumnName(){
    return columnName;
}


public String getPkType(){
    return pkType;
}


public String getOldColumnName(){
    return oldColumnName;
}


public int hashCode(){
    return columnSize + colunmType.hashCode() * columnName.hashCode();
}


public String getComment(){
    return comment;
}


public void setOldColumnName(String oldColumnName){
    this.oldColumnName = oldColumnName;
}


public void setColumnSize(int columnSize){
    this.columnSize = columnSize;
}


public void setColunmType(String colunmType){
    this.colunmType = colunmType;
}


public void setColumnName(String columnName){
    this.columnName = columnName;
}


public void setPkType(String pkType){
    this.pkType = pkType;
}


public String getTableName(){
    return tableName;
}


public int getColumnSize(){
    return columnSize;
}


public String getColunmType(){
    return colunmType;
}


public void setIsNullable(String isNullable){
    this.isNullable = isNullable;
}


public int getDecimalDigits(){
    return decimalDigits;
}


public void setDecimalDigits(int decimalDigits){
    this.decimalDigits = decimalDigits;
}


public String getColumnId(){
    return columnId;
}


public boolean equals(Object obj){
    if (obj == this) {
        return true;
    }
    if (!(obj instanceof ColumnMeta)) {
        return false;
    }
    ColumnMeta meta = (ColumnMeta) obj;
    // 时间类型不比较长度
    if (colunmType.contains("date") || colunmType.contains("blob") || colunmType.contains("text")) {
        return columnName.equals(meta.getColumnName()) && isNullable.equals(meta.isNullable) && isEquals(comment, meta.getComment()) && isEquals(fieldDefault, meta.getFieldDefault());
    } else /*else if (colunmType.contains("int")) {
				 return columnName.equals(meta.getColumnName())&& colunmType.equals(meta.getColunmType())
		        &&isNullable.equals(meta.isNullable);
			} */
    {
        return colunmType.equals(meta.getColunmType()) && isNullable.equals(meta.isNullable) && columnSize == meta.getColumnSize() && isEquals(comment, meta.getComment()) && isEquals(fieldDefault, meta.getFieldDefault());
    }
}


public boolean equalsComment(ColumnMeta meta){
    if (meta == this) {
        return true;
    }
    return isEquals(comment, meta.getComment());
}


public void setComment(String comment){
    this.comment = comment;
}


public String toString(){
    return columnName + "," + colunmType + "," + isNullable + "," + columnSize;
}


public void setColumnId(String columnId){
    this.columnId = columnId;
}


public boolean isEquals(String newS,String oldS){
    boolean booN = StringUtils.isNotEmpty(newS);
    boolean booO = StringUtils.isNotEmpty(oldS);
    if (booN != booO) {
        return false;
    }
    if (booN) {
        return newS.equals(oldS);
    }
    return true;
}


public String getIsNullable(){
    return isNullable;
}


public String getFieldDefault(){
    return fieldDefault;
}


}