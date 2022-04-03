package com.gbcom.system.manager;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.utils.DateUtil;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.system.utils.StatisticsUtil;
@Service
public class OperLogStatManager {

 private  Logger logger;

@Autowired
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private  SysLogService sysLogService;


public String statOperFoldLinePlot(String granularity){
    Map<String, Object> transMap = new HashMap<String, Object>();
    Date startDate = StatisticsUtil.statisticStartDate(new Date(), granularity);
    String startTime = StatisticsUtil.statisticTime(startDate, granularity);
    String endTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    Date start = StatisticsUtil.statisticStartDate(new Date(), granularity);
    Date end = new Date();
    String sql = "select event_type as type," + StatisticsUtil.statisticSize(granularity) + "count(event_type) as num from sys_log where enter_time > '" + startTime + "' and enter_time < '" + endTime + "' group by event_type,time";
    logger.info("Oper LinePlot Sql：" + sql);
    Map<String, List<Object[]>> list = queryList(sql, null);
    for (Entry<String, List<Object[]>> map : list.entrySet()) {
        String type = map.getKey();
        List<Object[]> point = map.getValue();
        LinkedHashMap<String, Object> linkMap = StatisticsUtil.fillFullData(point, granularity, start, end);
        transMap.put(type, linkMap);
    }
    String resultData = JsonUtil.mapToJSON(transMap);
    return resultData;
}


public List<Map<String,Object>> queryForList(String sql,Object[] args){
    return jdbcTemplate.queryForList(sql, args);
}


public String statResult(){
    String sql = "select result,count(result) as num from sys_log group by result";
    String result = "";
    try {
        Object[] data = StatisticsUtil.singleStatistics(sql, sysLogService);
        result = StatisticsUtil.parseSingleData(data, "次数");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error(e.getMessage(), e);
    }
    return result;
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public String statMenuClick(){
    String sql = "select moudle as menu,count(moudle) as num from sys_log group by moudle";
    String result = "";
    try {
        Map<String, Map<Object, Object>> data = StatisticsUtil.vectorStatistics(sql, sysLogService);
        List legends = new ArrayList();
        legends.add("访问次数");
        result = StatisticsUtil.parseVectorData(data, legends);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return result;
}


public String statOperClick(){
    String sql = "select event_type as eventType,count(event_type) as num from sys_log group by event_type";
    String result = "";
    try {
        Object[] data = StatisticsUtil.singleStatistics(sql, sysLogService);
        result = StatisticsUtil.parseSingleData(data, "操作次数");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error(e.getMessage(), e);
    }
    return result;
}


public Map<String,List<Object[]>> queryList(String sql,Object[] obj){
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, obj);
    Map<String, List<Object[]>> data = new HashMap<String, List<Object[]>>();
    for (Map<String, Object> map : list) {
        String xTime = "";
        int yNum = 0;
        String type = "";
        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && entry.getKey().equals("num")) {
                // 数量
                yNum = Integer.parseInt(entry.getValue().toString());
            }
            if (entry.getValue() != null && entry.getKey().equals("time")) {
                // 时间
                xTime = entry.getValue().toString();
            }
            // 类型
            if (entry.getKey().equals("type")) {
                type = entry.getValue() == null ? "其他" : entry.getValue().toString();
            }
        }
        Object[] point = { xTime, yNum };
        if (data.get(type) != null) {
            data.get(type).add(point);
        } else {
            if (yNum > 0) {
                List<Object[]> points = new ArrayList<Object[]>();
                points.add(point);
                data.put(type, points);
            }
        }
    }
    return data;
}


}