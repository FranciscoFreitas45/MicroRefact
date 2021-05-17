public class LineSeries {

 private  String source;

 private  String date;

 private  Object value;

 private  String rank;

 private  String percentageChange;

 private  Boolean isPositive;


public void setSource(String source){
    this.source = source;
}


public void setRank(String rank){
    this.rank = rank;
}


public String getPercentageChange(){
    return percentageChange;
}


public String getRank(){
    return rank;
}


public void setIsPositive(Boolean isPositive){
    this.isPositive = isPositive;
}


public void setPercentageChange(String percentageChange){
    this.percentageChange = percentageChange;
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


public void setValue(Object value){
    this.value = value;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "LineSeries [source=" + source + ", date=" + date + ", value=" + value + ", rank=" + rank + ", percentageChange=" + percentageChange + "]";
}


}