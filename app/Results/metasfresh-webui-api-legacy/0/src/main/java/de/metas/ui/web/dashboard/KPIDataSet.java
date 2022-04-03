package de.metas.ui.web.dashboard;
 import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class KPIDataSet {

@JsonProperty("name")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String name;

@JsonProperty("unit")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String unit;

@JsonProperty("values")
 private  List<KPIDataSetValue> values;

 private  Map<Object,KPIDataSetValue> _valuesByKey;


public String getName(){
    return name;
}


public void putValueIfAbsent(Object dataSetValueKey,String fieldName,Object jsonValue){
    dataSetValue(dataSetValueKey).putIfAbsent(fieldName, jsonValue);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("name", name).add("unit", unit).add("values", values).toString();
}


public void putValue(Object dataSetValueKey,String fieldName,Object jsonValue){
    dataSetValue(dataSetValueKey).put(fieldName, jsonValue);
}


public KPIDataSetValue dataSetValue(Object dataSetValueKey){
    return _valuesByKey.computeIfAbsent(dataSetValueKey, k -> {
        final KPIDataSetValue value = new KPIDataSetValue(dataSetValueKey);
        values.add(value);
        return value;
    });
}


}