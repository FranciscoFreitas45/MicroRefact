package com.ukefu.util.bi;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryparser.flexible.core.nodes.PathQueryNode.QueryText;
import com.ukefu.util.bi.model.Level;
import com.ukefu.util.bi.model.RequestData;
import com.ukefu.util.bi.model.ValueData;
public class CubeReportData implements ReportData,Cloneable{

 private  long serialVersionUID;

 private  Exception exception;

 private  Level row;

 private  Level col;

 private  int pageSize;

 private  int page;

 private  long numFound;

 private  RequestData reqdata;

 private  QueryText queryText;

 private  Map<String,Object> options;

 private  long queryTime;

 private  long total;

 private  Date createTime;

 private  List<List<ValueData>> data;

 private  String viewData;


public void setTotal(long total){
    this.total = total;
}


@Override
public Level getCol(){
    return col;
}


public void setNumFound(long l){
    this.numFound = l;
}


public long getQueryTime(){
    return queryTime;
}


public int getPage(){
    return page;
}


@Override
public void setRequestData(RequestData data){
    reqdata = data;
}


@Override
public void merge(ReportData data){
    this.col.getChilderen().addAll(data.getCol().getChilderen());
    if (this.row != null && this.row.getTitle() != null && data.getRow() != null && data.getRow().getTitle() != null) {
        List<?> titles = this.row.getTitle().get(this.row.getTitle().size() - 1);
        List<?> otitles = data.getRow().getTitle().get(data.getRow().getTitle().size() - 1);
        for (int i = 0; i < titles.size(); i++) {
            for (int j = 0; j < otitles.size(); j++) {
                if (titles.get(i).toString().equals(otitles.get(j).toString())) {
                    this.data.get(i).addAll(data.getData().get(j));
                    break;
                }
            }
        }
    }
    this.col.init();
}


public void setOptions(Map<String,Object> options){
    this.options = options;
}


public void setRow(Level row){
    this.row = row;
}


@Override
public Date getDate(){
    // TODO Auto-generated method stub
    return createTime;
}


public void setException(Exception exception){
    this.exception = exception;
}


public void setQueryText(QueryText queryText){
    this.queryText = queryText;
}


@Override
public List<List<ValueData>> getData(){
    return data;
}


public void setPage(int page){
    this.page = page;
}


public void setViewData(String viewData){
    this.viewData = viewData;
}


public Exception getException(){
    return exception;
}


public void setData(List<List<ValueData>> data){
    this.data = data;
}


public QueryText getQueryText(){
    return queryText;
}


@Override
public void exchangeColRow(){
    Level temp = this.col;
    this.col = this.row;
    this.row = temp;
    int colspan = this.col.getColspan();
    this.col.setColspan(this.col.getRowspan());
    this.col.setRowspan(colspan);
    colspan = this.row.getColspan();
    this.row.setColspan(this.row.getRowspan());
    this.row.setRowspan(colspan);
    List<List<ValueData>> tempList = new ArrayList<List<ValueData>>();
    for (int i = 0; i < data.size(); i++) {
        for (int j = 0; j < data.get(i).size(); j++) {
            if (tempList.size() <= j) {
                tempList.add(new ArrayList<ValueData>());
            }
            if (tempList.get(j) != null) {
                tempList.get(j).add(data.get(i).get(j));
            }
        }
    }
    this.data.clear();
    this.data = tempList;
}


@Override
public Level getRow(){
    return row;
}


public long getNumFound(){
    return numFound;
}


public int getPageSize(){
    return pageSize;
}


public ReportData clone(){
    try {
        return (ReportData) super.clone();
    } catch (CloneNotSupportedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}


@Override
public RequestData getRequestData(){
    return reqdata;
}


@Override
public void setDate(Date createtime){
    // TODO Auto-generated method stub
    this.createTime = createtime;
}


public long getTotal(){
    return total;
}


public Map<String,Object> getOptions(){
    return options;
}


public void setCol(Level col){
    this.col = col;
}


public void setQueryTime(long queryTime){
    this.queryTime = queryTime;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public String getViewData(){
    return viewData;
}


}