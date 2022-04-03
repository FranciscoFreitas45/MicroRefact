package com.gbcom.system.common.hchart;
 import java.util.List;
import java.util.Map;
public class HChartResult {

 public  int RST_OK;

 public  int RST_FAILED;

 private  int errno;

 private  int total;

 private  String errmsg;

 private  List<T> grid;

 private  Map<String,List<HChartPoint>> data;


public void setTotal(int total){
    this.total = total;
}


public void add(String name,List<HChartPoint> each){
    if (each == null)
        return;
    if (name == null)
        name = "entry";
    data.put(name, each);
}


public int getErrno(){
    return errno;
}


public void setGrid(List<T> grid){
    this.grid = grid;
}


public List<T> getGrid(){
    return grid;
}


public void setErrno(int errno){
    this.errno = errno;
    if (errno != 0) {
        this.errmsg = "failed";
        this.total = 0;
    }
}


public void setData(Map<String,List<HChartPoint>> data){
    this.data = data;
}


public HChartResult valueOf(Map<String,List<HChartPoint>> map){
    HChartResult rst = new HChartResult();
    rst.setData(map);
    rst.setTotal(map.size());
    return rst;
}


public int getTotal(){
    return total;
}


public String getErrmsg(){
    return errmsg;
}


public Map<String,List<HChartPoint>> getData(){
    return data;
}


public void setErrmsg(String errmsg){
    this.errmsg = errmsg;
}


}