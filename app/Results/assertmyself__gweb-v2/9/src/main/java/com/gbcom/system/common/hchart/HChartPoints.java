package com.gbcom.system.common.hchart;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
public class HChartPoints {

 private  Logger logger;


public Map<String,List<HChartPoint>> transferSeries(String name,List<Object[]> series){
    if (name == null)
        name = "entry";
    if (series == null)
        return new HashMap<String, List<HChartPoint>>();
    Map<String, List<HChartPoint>> serm = new HashMap<String, List<HChartPoint>>();
    serm.put(name, toHChartPoint(series));
    return serm;
}


public Map<String,List<HChartPoint>> transferVectorSeries(List<String> names,List<Object> serieses){
    Map<String, List<HChartPoint>> serm = new HashMap<String, List<HChartPoint>>();
    if (serieses == null || serieses.size() == 0)
        return new HashMap<String, List<HChartPoint>>();
    for (int i = 0; i < serieses.size(); i++) {
        Object each = serieses.get(i);
        String name = names.get(i);
        if (name == null)
            name = "entry" + i;
        if (each instanceof List) {
            serm.put(name, toHChartPoint((List) each));
        } else if (each instanceof Map) {
            serm.put(name, toHChartPoint((Map) each));
        } else {
            logger.warn("the serieses is not List<Object[] or Map<String,Object>> each= " + each);
        }
    }
    return serm;
}


public List<HChartPoint> toHChartPoint(List<Object[]> series){
    List<HChartPoint> list = new ArrayList<HChartPoint>();
    for (Object[] each : series) {
        if (each.length < 2)
            continue;
        HChartPoint one = new HChartPoint(each[0].toString(), each[1].toString());
        list.add(one);
    }
    return list;
}


}