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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


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


public String getCssClass(){
    return cssClass;
}


public String getAreaCode(){
    return areaCode;
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


public String getAreaName(){
    return areaName;
}


public String getValue(){
    return value;
}


public Boolean getIsColumnVisible(){
    return isColumnVisible;
}


public String getUnit(){
    return unit;
}


public List<ValueObject> getDataSeries(){
    return dataSeries;
}


public void setValue(String value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"));

.queryParam("value",value);
restTemplate.put(builder.toUriString(),null);
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAreaCode"));

.queryParam("areaCode",areaCode);
restTemplate.put(builder.toUriString(),null);
}


public void setAreaName(String areaName){
    this.areaName = areaName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAreaName"));

.queryParam("areaName",areaName);
restTemplate.put(builder.toUriString(),null);
}


public void setAreaNid(Integer areaNid){
    this.areaNid = areaNid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAreaNid"));

.queryParam("areaNid",areaNid);
restTemplate.put(builder.toUriString(),null);
}


public void setRank(String rank){
    this.rank = rank;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRank"));

.queryParam("rank",rank);
restTemplate.put(builder.toUriString(),null);
}


public void setUnit(String unit){
    this.unit = unit;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUnit"));

.queryParam("unit",unit);
restTemplate.put(builder.toUriString(),null);
}


public void setCssClass(String cssClass){
    this.cssClass = cssClass;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCssClass"));

.queryParam("cssClass",cssClass);
restTemplate.put(builder.toUriString(),null);
}


}