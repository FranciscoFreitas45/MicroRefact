package org.live.common.response;
 import java.util.List;
public class DataTableModel {

 private  Integer draw;

 private  Long recordsTotal;

 private  Long recordsFiltered;

 private  List<?> data;

 private  String error;

public DataTableModel(Integer draw, Long recordsTotal, Long recordsFiltered, List<?> data) {
    this.draw = draw;
    this.recordsTotal = recordsTotal;
    this.recordsFiltered = recordsFiltered;
    this.data = data;
}
public String getError(){
    return error;
}


public void setRecordsFiltered(Long recordsFiltered){
    this.recordsFiltered = recordsFiltered;
}


public Integer getDraw(){
    return draw;
}


public void setData(List<?> data){
    this.data = data;
}


public void setError(String error){
    this.error = error;
}


public Long getRecordsTotal(){
    return recordsTotal;
}


public void setRecordsTotal(Long recordsTotal){
    this.recordsTotal = recordsTotal;
}


public Long getRecordsFiltered(){
    return recordsFiltered;
}


public List<?> getData(){
    return data;
}


public void setDraw(Integer draw){
    this.draw = draw;
}


}