package com.metservice.kanban.csv;
 import com.metservice.kanban.utils.DateUtils.parseIsoDate;
import java.lang.Integer.parseInt;
import org.joda.time.LocalDate;
public class CsvRow {

 public  int INTEGER_DEFAULT;

 private  boolean BOOLEAN_DEFAULT;

 private  String STRING_DEFAULT;

 private  CsvColumnNames columnNames;

 private  String[] cells;

public CsvRow(CsvColumnNames columnNames, String[] cells) {
    this.columnNames = columnNames;
    this.cells = cells;
}
public int getInt(String columnName){
    if (!columnNames.hasColumn(columnName)) {
        return INTEGER_DEFAULT;
    }
    return parseInt(getString(columnName));
}


public void setString(String columnName,String string){
    int index = columnNames.getColumnIndex(columnName);
    cells[index] = string;
}


public LocalDate getDate(String columnName){
    if (!columnNames.hasColumn(columnName)) {
        return null;
    }
    String stringValue = getString(columnName);
    return stringValue == null ? null : parseIsoDate(stringValue);
}


public Boolean getBoolean(String columnName){
    if (!columnNames.hasColumn(columnName)) {
        return BOOLEAN_DEFAULT;
    }
    return Boolean.parseBoolean(getString(columnName));
}


public String getString(String columnName){
    if (!columnNames.hasColumn(columnName)) {
        return STRING_DEFAULT;
    }
    int index = columnNames.getColumnIndex(columnName);
    String unparsedString = cells[index];
    return unparsedString.isEmpty() ? null : unparsedString;
}


}