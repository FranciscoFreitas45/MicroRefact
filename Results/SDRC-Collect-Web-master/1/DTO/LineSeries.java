public class LineSeries {

 private  String source;

 private  String date;

 private  Object value;

 private  String rank;

 private  String percentageChange;

 private  Boolean isPositive;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getPercentageChange(){
    return percentageChange;
}


public String getRank(){
    return rank;
}


public Object getValue(){
    return value;
}


public Boolean getIsPositive(){
    return isPositive;
}


public String getSource(){
    return source;
}


public String getDate(){
    return date;
}


public void setDate(String date){
    this.date = date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDate"));

.queryParam("date",date);
restTemplate.put(builder.toUriString(),null);
}


public void setValue(Object value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"));

.queryParam("value",value);
restTemplate.put(builder.toUriString(),null);
}


public void setSource(String source){
    this.source = source;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSource"));

.queryParam("source",source);
restTemplate.put(builder.toUriString(),null);
}


public void setPercentageChange(String percentageChange){
    this.percentageChange = percentageChange;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPercentageChange"));

.queryParam("percentageChange",percentageChange);
restTemplate.put(builder.toUriString(),null);
}


public void setIsPositive(Boolean isPositive){
    this.isPositive = isPositive;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsPositive"));

.queryParam("isPositive",isPositive);
restTemplate.put(builder.toUriString(),null);
}


}