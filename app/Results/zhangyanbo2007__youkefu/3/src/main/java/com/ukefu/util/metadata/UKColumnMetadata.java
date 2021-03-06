package com.ukefu.util.metadata;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
public class UKColumnMetadata {

 private  boolean pk;

 private  String name;

 private  String title;

 private  String typeName;

 private  int columnSize;

 private  int decimalDigits;

 private  String isNullable;

 private  int typeCode;

UKColumnMetadata(ResultSet rs, boolean upcase) throws SQLException {
    name = rs.getString("COLUMN_NAME");
    if (upcase) {
        name = name != null ? name.toUpperCase() : name;
    }
    columnSize = rs.getInt("COLUMN_SIZE");
    decimalDigits = rs.getInt("DECIMAL_DIGITS");
    isNullable = rs.getString("IS_NULLABLE");
    typeCode = rs.getInt("DATA_TYPE");
    StringTokenizer typeNameStr = new StringTokenizer(rs.getString("TYPE_NAME"), "() ");
    if (typeNameStr.hasMoreTokens()) {
        typeName = typeNameStr.nextToken();
    }
    this.title = rs.getString("REMARKS");
    if (StringUtils.isBlank(title)) {
        this.title = this.name;
    }
}UKColumnMetadata(String name, String typeName, int typeCode, int colunmSize) throws SQLException {
    this.name = name;
    this.typeCode = typeCode;
    this.typeName = typeName;
    this.columnSize = colunmSize;
}
public String getName(){
    return name;
}


public String getTitle(){
    return !StringUtils.isBlank(title) ? title : this.name;
}


public boolean isPk(){
    return pk;
}


public String getNullable(){
    return isNullable;
}


public void setPk(boolean pk){
    this.pk = pk;
}


public void setTitle(String title){
    this.title = title;
}


public String toString(){
    return "ColumnMetadata(" + name + ')';
}


public String getTypeName(){
    return typeName;
}


public int getColumnSize(){
    return columnSize;
}


public int getDecimalDigits(){
    return decimalDigits;
}


public int getTypeCode(){
    return typeCode;
}


}