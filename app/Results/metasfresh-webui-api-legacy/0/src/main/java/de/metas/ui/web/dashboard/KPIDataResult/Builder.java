package de.metas.ui.web.dashboard.KPIDataResult;
 import java.util.LinkedHashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
public class Builder {

 private  LinkedHashMap<String,KPIDataSet> datasets;

 private  TimeRange range;

 private  String took;


public Builder setRange(TimeRange timeRange){
    range = timeRange;
    return this;
}


public KPIDataResult build(){
    return new KPIDataResult(this);
}


public TimeRange getRange(){
    return range;
}


public Builder putValueIfAbsent(String dataSetName,Object dataSetValueKey,String fieldName,Object jsonValue){
    dataSet(dataSetName).putValueIfAbsent(dataSetValueKey, fieldName, jsonValue);
    return this;
}


public Builder setTook(Stopwatch took){
    this.took = took.toString();
    return this;
}


public Builder putValue(String dataSetName,Object dataSetValueKey,String fieldName,Object jsonValue){
    dataSet(dataSetName).putValue(dataSetValueKey, fieldName, jsonValue);
    return this;
}


public KPIDataSet dataSet(String name){
    return datasets.computeIfAbsent(name, KPIDataSet::new);
}


}