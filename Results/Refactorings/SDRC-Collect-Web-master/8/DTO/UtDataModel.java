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


@Override
public int compareTo(UtDataModel o){
    // TODO Auto-generated method stub
    return 0;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/compareTo"));

.queryParam("o",o);
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux


}