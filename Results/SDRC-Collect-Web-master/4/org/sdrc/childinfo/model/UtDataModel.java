import java.util.List;
import java.util.Map;
public class UtDataModel implements Comparable<UtDataModel>{

 private  String id;

 private  String areaCode;

 private  String areaName;

 private  Integer areaNid;

 private  String value;

 private  String unit;

 private  String rank;

 private  String cssClass;

 private  String percentageChange;

 private  Boolean isPositiveTrend;

 private  List<ValueObject> dataSeries;

 private  List<LineSeries> lineSeries;

 private  List<Map<Object,String>> columnSeries;

 private  Boolean isColumnVisible;


public void setIsPositiveTrend(Boolean isPositiveTrend){
    this.isPositiveTrend = isPositiveTrend;
}


public void setCssClass(String cssClass){
    this.cssClass = cssClass;
}


public String getPercentageChange(){
    return percentageChange;
}


public Integer getAreaNid(){
    return areaNid;
}


public String getId(){
    return id;
}


public List<LineSeries> getLineSeries(){
    return lineSeries;
}


@Override
public int compareTo(UtDataModel o){
    // TODO Auto-generated method stub
    return 0;
}


public void setPercentageChange(String percentageChange){
    this.percentageChange = percentageChange;
}


public String getCssClass(){
    return cssClass;
}


public String getAreaCode(){
    return areaCode;
}


public void setIsColumnVisible(Boolean isColumnVisible){
    this.isColumnVisible = isColumnVisible;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setId(String id){
    this.id = id;
}


public void setRank(String rank){
    this.rank = rank;
}


public void setColumnSeries(List<Map<Object,String>> columnSeries){
    this.columnSeries = columnSeries;
}


public void setLineSeries(List<LineSeries> lineSeries){
    this.lineSeries = lineSeries;
}


public Boolean getIsPositiveTrend(){
    return isPositiveTrend;
}


public String getRank(){
    return rank;
}


public List<Map<Object,String>> getColumnSeries(){
    return columnSeries;
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
}


public String getAreaName(){
    return areaName;
}


public void setDataSeries(List<ValueObject> dataSeries){
    this.dataSeries = dataSeries;
}


public String getValue(){
    return value;
}


public Boolean getIsColumnVisible(){
    return isColumnVisible;
}


public void setValue(String value){
    this.value = value;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


@Override
public String toString(){
    return "UtDataModel [id=" + id + ", areaCode=" + areaCode + ", areaName=" + areaName + ", areaNid=" + areaNid + ", value=" + value + ", unit=" + unit + ", rank=" + rank + ", cssClass=" + cssClass + ", percentageChange=" + percentageChange + ", isPositiveTrend=" + isPositiveTrend + ", dataSeries=" + dataSeries + ", lineSeries=" + lineSeries + ", columnSeries=" + columnSeries + ", isColumnVisible=" + isColumnVisible + "]";
}


public void setAreaNid(Integer areaNid){
    this.areaNid = areaNid;
}


public String getUnit(){
    return unit;
}


public List<ValueObject> getDataSeries(){
    return dataSeries;
}


}