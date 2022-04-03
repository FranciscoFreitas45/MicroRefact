package com.gbcom.system.utils;
 import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.hc.core.orm.hibernate.EntityService;
public class StatisticsUtil {


@SuppressWarnings("unchecked")
public Map<String,Object> parseMultiChartData(String type,String name,Object data){
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("type", type);
    resultMap.put("name", name);
    if (data instanceof Map) {
        Map map = (Map) data;
        resultMap.put("categories", map.keySet().toArray());
        resultMap.put("data", map.values().toArray());
    }
    if (data instanceof Object[]) {
        resultMap.put("data", data);
    }
    return resultMap;
}


public String statisticTime(Date date,String granularity){
    DateFormat df = null;
    String dateFomat = "";
    if (PmConst.UNIT_HOUR.equals(granularity)) {
        df = new SimpleDateFormat("yyyy-MM-dd HH");
        dateFomat = df.format(date);
    } else if (PmConst.UNIT_DAY.equals(granularity)) {
        df = new SimpleDateFormat("yyyy-MM-dd");
        dateFomat = df.format(date);
    } else if (PmConst.UNIT_WEEK.equals(granularity)) {
        df = new SimpleDateFormat("yyyy-MM-dd");
        dateFomat = df.format(date);
    } else if (PmConst.UNIT_MONTH.equals(granularity)) {
        df = new SimpleDateFormat("yyyy-MM");
        dateFomat = df.format(date);
    } else {
        df = new SimpleDateFormat("yyyy-MM-dd HH");
        dateFomat = df.format(date);
    }
    return dateFomat;
}


@Override
public int compare(Entry<String,Object> arg1,Entry<String,Object> arg2){
    return arg1.getKey().compareTo(arg2.getKey());
}


public String parseSingleData(Object[] data,String legend){
    Map<String, Object> transferData = new HashMap<String, Object>();
    transferData.put(legend, data);
    return JsonUtil.mapToJSON(transferData);
}


public int statisticGranularity(String granularity){
    int field = 0;
    if (PmConst.UNIT_HOUR.equals(granularity)) {
        field = Calendar.HOUR_OF_DAY;
    } else if (PmConst.UNIT_DAY.equals(granularity)) {
        field = Calendar.DAY_OF_MONTH;
    } else if (PmConst.UNIT_WEEK.equals(granularity)) {
        field = Calendar.DAY_OF_MONTH;
    } else if (PmConst.UNIT_MONTH.equals(granularity)) {
        field = Calendar.MONTH;
    } else {
        field = Calendar.HOUR_OF_DAY;
    }
    return field;
}


public LinkedHashMap<String,Object> fillFullData(List<Object[]> data,String granularity,Date start,Date end){
    Calendar startCalendar = Calendar.getInstance();
    startCalendar.setTime(start);
    Calendar endCalendar = Calendar.getInstance();
    endCalendar.setTime(end);
    String startTime = statisticTime(startCalendar.getTime(), granularity);
    String endTime = statisticTime(endCalendar.getTime(), granularity);
    String time = "";
    Map<String, Object> dataTemp = new HashMap<String, Object>();
    List<Map.Entry<String, Object>> mapList = null;
    try {
        for (int i = 0; i < data.size(); i++) {
            Object[] row = data.get(i);
            time = row[0].toString();
            if (time == null) {
                continue;
            }
            dataTemp.put(time, row[1]);
            while (time.compareTo(startTime) > 0) {
                dataTemp.put(startTime, 0);
                startCalendar.add(statisticGranularity(granularity), 1);
                startTime = statisticTime(startCalendar.getTime(), granularity);
            }
            startCalendar.add(statisticGranularity(granularity), 1);
            startTime = statisticTime(startCalendar.getTime(), granularity);
        }
        // time = statisticTime(startCalendar.getTime(), granularity);
        while (startTime.compareTo(endTime) <= 0) {
            dataTemp.put(startTime, 0);
            startCalendar.add(statisticGranularity(granularity), 1);
            startTime = statisticTime(startCalendar.getTime(), granularity);
        }
        mapList = new ArrayList<Map.Entry<String, Object>>(dataTemp.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<String, Object>>() {

            @Override
            public int compare(Entry<String, Object> arg1, Entry<String, Object> arg2) {
                return arg1.getKey().compareTo(arg2.getKey());
            }
        });
        LinkedHashMap<String, Object> dataMap = new LinkedHashMap<String, Object>();
        for (int i = 0; i < mapList.size(); i++) {
            Map.Entry<String, Object> entry = mapList.get(i);
            dataMap.put(entry.getKey(), entry.getValue());
        }
        return dataMap;
    } catch (Exception e) {
        return new LinkedHashMap<String, Object>();
    }
}


public Date statisticStartDate(Date date,String granularity){
    Date startDate = null;
    if (PmConst.UNIT_HOUR.equals(granularity)) {
        startDate = DateUtil.getCurDayStart(date);
    } else if (PmConst.UNIT_DAY.equals(granularity)) {
        // startDate = DateUtil.getCurMonthStart(date);
        // 按日显示前15天
        startDate = DateUtil.getDate(date, -15);
    } else if (PmConst.UNIT_WEEK.equals(granularity)) {
        startDate = DateUtil.getCurWeekStart(date);
    } else if (PmConst.UNIT_MONTH.equals(granularity)) {
        startDate = DateUtil.getCurYearStart(date);
    } else {
        startDate = DateUtil.getCurDayStart(date);
    }
    return startDate;
}


@SuppressWarnings("unchecked")
public Map<String,Map<Object,Object>> vectorStatistics(String sql,EntityService service){
    List<Object[]> list = service.findBySql(sql);
    Map<String, Map<Object, Object>> rowData = new HashMap<String, Map<Object, Object>>();
    Map<Object, Object> data = null;
    for (int i = 0; i < list.size(); i++) {
        Object[] row = list.get(i);
        for (int j = 0; j < row.length; j++) {
            Object column = row[0];
            if (column == null) {
                continue;
            }
            if (j > 0) {
                data = rowData.get(String.valueOf(j));
                if (data == null) {
                    data = new HashMap<Object, Object>();
                }
                data.put(column, row[j]);
                rowData.put(String.valueOf(j), data);
            }
        }
    }
    // 排序
    Set<String> keys = rowData.keySet();
    for (String key : keys) {
        Map<Object, Object> map = rowData.get(key);
        List<Map.Entry<Object, Object>> mapList = new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<Object, Object>>() {

            @Override
            public int compare(Map.Entry<Object, Object> obj1, Map.Entry<Object, Object> obj2) {
                return Integer.parseInt(obj2.getValue().toString()) - Integer.parseInt(obj1.getValue().toString());
            }
        });
        Map<Object, Object> sortMap = new LinkedHashMap<Object, Object>();
        for (int i = 0; i < mapList.size(); i++) {
            Entry<Object, Object> entry = mapList.get(i);
            sortMap.put(entry.getKey(), entry.getValue());
        }
        rowData.put(key, sortMap);
    }
    return rowData;
}


public String parseVectorData(Map<String,Map<Object,Object>> data,List<Object> legends){
    Set<String> keys = data.keySet();
    if (keys.size() != legends.size()) {
        throw new Exception("数据格式错误！");
    }
    List<String> keyList = new ArrayList<String>(keys);
    Map<String, Map<Object, Object>> trasferMap = new HashMap<String, Map<Object, Object>>();
    for (int i = 0; i < keyList.size(); i++) {
        String key = keyList.get(i);
        Map<Object, Object> tempData = data.get(key);
        String legend = legends.get(i).toString();
        trasferMap.put(legend, tempData);
    }
    return JsonUtil.mapToJSON(trasferMap);
}


public String statisticSize(String unit){
    String sql = "";
    if (PmConst.UNIT_HOUR.equals(unit)) {
        // 按小时统计
        sql = "DATE_FORMAT(enter_time,'%Y-%m-%d %H') time,";
    } else if (PmConst.UNIT_DAY.equals(unit)) {
        // 按天统计
        sql = "DATE_FORMAT(enter_time,'%Y-%m-%d') time,";
    } else if (PmConst.UNIT_WEEK.equals(unit)) {
        // 按周统计
        sql = "DATE_FORMAT(enter_time,'%Y-%m-%d') time,";
    } else if (PmConst.UNIT_MONTH.equals(unit)) {
        // 按月统计
        sql = "DATE_FORMAT(enter_time,'%Y-%m') time,";
    } else if (PmConst.UNIT_YEAR.equals(unit)) {
        // 按年统计
        sql = "DATE_FORMAT(enter_time,'%Y') time,";
    } else {
        sql = "DATE_FORMAT(enter_time,'%Y-%m-%d %H') time,";
    }
    return sql;
}


@SuppressWarnings("unchecked")
public Map<String,Object> multiChartStatistics(String sql,EntityService service,String type){
    Map<String, Map<Object, Object>> data = vectorStatistics(sql, service);
    Map<String, Object> tempData = new LinkedHashMap<String, Object>();
    Set<String> keys = data.keySet();
    for (String key : keys) {
        Map<Object, Object> row = data.get(key);
        tempData.put("type", type);
        tempData.put("name", key);
        tempData.put("data", row);
    }
    return tempData;
}


@SuppressWarnings("unchecked")
public Object[] singleStatistics(String sql,EntityService service){
    List<Object[]> list = service.findBySql(sql);
    List<Object> data = new ArrayList<Object>();
    for (int i = 0; i < list.size(); i++) {
        Object[] row = list.get(i);
        if (row[0] == null) {
            continue;
        }
        List<Object> rowData = new ArrayList<Object>();
        rowData.add(0, row[0]);
        rowData.add(1, row[1]);
        data.add(rowData.toArray());
    }
    Object[] arr = data.toArray();
    return arr;
}


}