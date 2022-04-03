package com.ukefu.util.metadata;
 import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.annotations.common.util.StringHelper;
public class UKTableMetaData {

 private  String catalog;

 private  String schema;

 private  String name;

 private  List<UKColumnMetadata> columnMetaData;

 private  Map<String,Object> columName;

/**
 * @param rs
 * @param meta
 * @param extras
 * @throws SQLException
 */
public UKTableMetaData(ResultSet rs, DatabaseMetaData meta, boolean extras, boolean upcase, boolean loadColumns, String dbtype) throws SQLException {
    if (dbtype != null && dbtype.equals("hive")) {
        catalog = null;
        schema = null;
        if (upcase) {
            name = rs.getObject("tab_name").toString();
        } else {
            name = rs.getObject("tab_name").toString();
        }
    } else {
        catalog = rs.getString("TABLE_CAT");
        schema = rs.getString("TABLE_SCHEM");
        if (upcase) {
            name = rs.getString("TABLE_NAME").toUpperCase();
        } else {
            name = rs.getString("TABLE_NAME");
        }
    }
    if (loadColumns) {
        initColumns(meta, upcase);
    }
}/**
 * @param tablename
 * @param meta
 * @param extras
 * @throws SQLException
 */
public UKTableMetaData(String tableName, String tableCatalog, String tableSchema, ResultSetMetaData meta, boolean extras) throws SQLException {
    catalog = tableCatalog;
    schema = tableSchema;
    name = tableName;
    initColumns(meta);
}
public void setName(String name){
    this.name = name;
}


public void addColumn(ResultSet rs,boolean upcase){
    String column = rs.getString("COLUMN_NAME");
    if (upcase) {
        column = column != null ? column.toUpperCase() : column;
    }
    if (column == null)
        return;
    if (columName.get(column) == null) {
        UKColumnMetadata info = new UKColumnMetadata(rs, upcase);
        columnMetaData.add(info);
        if (upcase) {
            columName.put(info.getName().toUpperCase(), "");
        } else {
            columName.put(info.getName().toLowerCase(), "");
        }
    }
}


public String getName(){
    return name;
}


public void initColumns(ResultSetMetaData meta){
    for (int i = 1; i <= meta.getColumnCount(); i++) {
        Object tbName = meta.getColumnName(i);
        if (tbName != null && String.valueOf(tbName).toLowerCase().indexOf("rownum") < 0) {
            addSqlColumn(meta.getColumnName(i), meta.getColumnTypeName(i), meta.getColumnType(i), meta.getColumnDisplaySize(i));
        }
    }
}


public List<UKColumnMetadata> getColumnMetadatas(){
    return columnMetaData;
}


public String toString(){
    return "TableMetadata(" + name + ')';
}


public void addSqlColumn(String name,String typeName,int typeCode,int columSize){
    if (name == null)
        return;
    if (columName.get(name) == null) {
        UKColumnMetadata info = new UKColumnMetadata(name, typeName, typeCode, columSize);
        columnMetaData.add(info);
        columName.put(info.getName().toLowerCase(), "");
    }
}


}