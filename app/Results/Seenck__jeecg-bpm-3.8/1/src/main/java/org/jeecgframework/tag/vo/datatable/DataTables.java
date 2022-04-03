package org.jeecgframework.tag.vo.datatable;
 import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
public class DataTables {

 private  HttpServletRequest request;

 private  String sEchoParameter;

 private  String iDisplayStartParameter;

 private  String iDisplayLengthParameter;

 private  String iColumnsParameter;

 private  String sColumnsParameter;

 private  String sColumns;

 private  String iSortingColsParameter;

 private  String iSortColPrefixParameter;

 private  String sSortDirPrefixParameter;

 private  String bSortablePrefixParameter;

 private  String sSearchParameter;

 private  String bRegexParameter;

 private  String bSearchablePrefixParameter;

 private  String sSearchPrefixParameter;

 private  String bEscapeRegexPrefixParameter;

 private  Integer echo;

 private  int displayStart;

 private  int displayLength;

 private  int sortingCols;

 public  int iSortingCols;

 private  SortInfo[] sortColumns;

 private  int ColumnCount;

 private  ColumnInfo[] columns;

 private  String search;

 private  Boolean regex;

public DataTables(// 用于 MVC 模式下的构造函数
HttpServletRequest request) {
    this.request = request;
    this.echo = this.ParseIntParameter(sEchoParameter);
    this.displayStart = this.ParseIntParameter(iDisplayStartParameter);
    this.displayLength = this.ParseIntParameter(iDisplayLengthParameter);
    this.sortingCols = this.ParseIntParameter(iSortingColsParameter);
    this.search = this.ParseStringParameter(sSearchParameter);
    this.regex = this.ParseStringParameter(bRegexParameter) == "true";
    // 排序的列
    int count = sortingCols;
    this.sortColumns = new SortInfo[count];
    MessageFormat formatter = new MessageFormat("");
    for (int i = 0; i < count; i++) {
        SortInfo sortInfo = new SortInfo();
        sortInfo.setColumnId(this.ParseIntParameter(formatter.format("iSortCol_{0}", i)));
        String aString = this.ParseStringParameter(formatter.format("sSortDir_{0}", i));
        if (this.ParseStringParameter(formatter.format("sSortDir_{0}", i)).equals("desc")) {
            sortInfo.setSortOrder(SortDirection.asc);
        } else {
            sortInfo.setSortOrder(SortDirection.desc);
        }
        this.sortColumns[i] = sortInfo;
    }
    this.ColumnCount = this.ParseIntParameter(iColumnsParameter);
    count = this.ColumnCount;
    this.columns = new ColumnInfo[count];
    String[] names = this.ParseStringParameter(sColumnsParameter).split(",");
    this.sColumns = this.ParseStringParameter(sColumnsParameter);
    for (int i = 0; i < names.length; i++) {
        ColumnInfo col = new ColumnInfo();
        col.setName(names[i]);
        col.setSortable(this.ParseBooleanParameter(formatter.format("bSortable_{0}", i)));
        col.setSearchable(this.ParseBooleanParameter(formatter.format("bSearchable_{0}", i)));
        col.setSearch(this.ParseStringParameter(formatter.format("sSearch_{0}", i)));
        col.setRegex(this.ParseStringParameter(formatter.format("bRegex_{0}", i)) == "true");
        columns[i] = col;
    }
}
public void setColumns(ColumnInfo[] columns){
    this.columns = columns;
}


public Boolean getRegex(){
    return regex;
}


public void setRegex(Boolean regex){
    this.regex = regex;
}


public Integer getEcho(){
    return echo;
}


public int getDisplayStart(){
    return displayStart;
}


public SortInfo[] getSortColumns(){
    return sortColumns;
}


public int ParseIntParameter(String name){
    int result = 0;
    String parameter = this.request.getParameter(name);
    if (parameter != null) {
        result = Integer.parseInt(parameter);
    }
    return result;
}


public int getColumnCount(){
    return ColumnCount;
}


public String ParseStringParameter(String name){
    return this.request.getParameter(name);
}


public void setSearch(String search){
    this.search = search;
}


public int getDisplayLength(){
    return displayLength;
}


public Boolean ParseBooleanParameter(String name){
    Boolean result = false;
    String parameter = this.request.getParameter(name);
    if (parameter != null) {
        result = Boolean.parseBoolean(parameter);
    }
    return result;
}


public void DataTablePram(HttpServletRequest httpRequest){
    this.request = httpRequest;
}


public ColumnInfo[] getColumns(){
    return columns;
}


public String getSearch(){
    return search;
}


public void setColumnCount(int columnCount){
    ColumnCount = columnCount;
}


public int getSortingCols(){
    return sortingCols;
}


public void setSortColumns(SortInfo[] sortColumns){
    this.sortColumns = sortColumns;
}


public String getsColumns(){
    return sColumns;
}


public void setsColumns(String sColumns){
    this.sColumns = sColumns;
}


}