public class DashboardExport {

 private  String areaId;

 private  String indicatorId;

 private  String sourceId;

 private  String timePeriodId;

 private  String area;

 private  String indicator;

 private  String source;

 private  String timePeriod;

 private  Integer childLevel;

 private  String imgPath;


public void setSource(String source){
    this.source = source;
}


public void setAreaId(String areaId){
    this.areaId = areaId;
}


public String getTimePeriodId(){
    return timePeriodId;
}


public String getIndicator(){
    return indicator;
}


public void setArea(String area){
    this.area = area;
}


public Integer getChildLevel(){
    // TODO Auto-generated method stub
    return childLevel;
}


public void setTimePeriod(String timePeriod){
    this.timePeriod = timePeriod;
}


public String getAreaId(){
    return areaId;
}


public void setTimePeriodId(String timePeriodId){
    this.timePeriodId = timePeriodId;
}


public void setSourceId(String sourceId){
    this.sourceId = sourceId;
}


public void setChildLevel(Integer childLevel){
    this.childLevel = childLevel;
}


public String getTimePeriod(){
    return timePeriod;
}


public String getSource(){
    return source;
}


public void setImgPath(String imgPath){
    this.imgPath = imgPath;
}


public String getIndicatorId(){
    return indicatorId;
}


public void setIndicatorId(String indicatorId){
    this.indicatorId = indicatorId;
}


public String getSourceId(){
    return sourceId;
}


public void setIndicator(String indicator){
    this.indicator = indicator;
}


public String getArea(){
    return area;
}


public String getImgPath(){
    return imgPath;
}


}