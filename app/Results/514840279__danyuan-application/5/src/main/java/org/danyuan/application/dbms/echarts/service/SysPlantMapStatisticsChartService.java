package org.danyuan.application.dbms.echarts.service;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class SysPlantMapStatisticsChartService {

@Autowired
 private JdbcTemplate jdbcTemplate;


public void buildMapSum(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String type1,String tableName){
    if (type1 == null) {
        buildMapNoTypeSum(map, info, sbWhere, tableName);
    } else {
        buildMapTypeSum(map, info, sbWhere, type1, tableName);
    }
}


public void buildMapNoTypeSum(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String tableName){
    StringBuilder sql = new StringBuilder();
    Map<String, Object> param = new HashMap<>();
    sql.append(" SELECT  行政区域_省 AS PROVINCE ,SUM(`总中标金额`) AS NUM");
    sql.append(" FROM " + tableName + " t ");
    sql.append(" WHERE  DELETE_FLAG = 0  ");
    sql.append(" AND  公告类型  IN ('中标公告','成交公告') ");
    sql.append(" AND  行政区域_省  IS NOT NULL ");
    sql.append(" AND  行政区域_省 <> ''  ");
    sql.append(sbWhere.toString());
    sql.append(" GROUP BY  行政区域_省 ");
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<Map<String, Object>> listMap = template.queryForList(sql.toString(), param);
    // series_data=[{name:'安徽',value:5483043}, ];
    List<Map<String, Object>> series_data = new ArrayList<>();
    Map<String, Object> mapt = new HashMap<>();
    mapt.put("name", "金额");
    mapt.put("type", "map");
    mapt.put("mapType", "china");
    mapt.put("roam", false);
    Map<String, Boolean> emap = new HashMap<>();
    Map<String, Object> label = new HashMap<>();
    emap.put("show", true);
    label.put("emphasis", emap);
    Map<String, Boolean> normal = new HashMap<>();
    normal.put("show", false);
    label.put("normal", normal);
    mapt.put("label", label);
    List<Map<String, Object>> series_data_data = new ArrayList<>();
    for (Map<String, Object> map2 : listMap) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", map2.get("PROVINCE").toString());
        data.put("value", Double.valueOf(map2.get("NUM") == null ? "0" : map2.get("NUM").toString()).longValue());
        series_data_data.add(data);
    }
    mapt.put("data", series_data_data);
    series_data.add(mapt);
    map.put("series_data", series_data);
    String[] legend_data = { "金额" };
    map.put("legend_data", legend_data);
    map.put("chartType", info.getChartType());
}


public void buildGroupByType1(List<Map<String,Object>> listMap,List<List<Map<String,Object>>> listGroupMap){
    if (listMap == null || listMap.size() == 0) {
        return;
    }
    for (Map<String, Object> map : listMap) {
        if (listGroupMap.size() == 0) {
            List<Map<String, Object>> listMaptemp = new ArrayList<>();
            listMaptemp.add(map);
            listGroupMap.add(listMaptemp);
        }
        boolean check = true;
        for (int i = 0; i < listGroupMap.size(); i++) {
            if (listGroupMap.get(i).get(0).get("ask1").equals(map.get("ask1"))) {
                listGroupMap.get(i).add(map);
                check = false;
                break;
            }
        }
        if (check) {
            List<Map<String, Object>> listMaptemp = new ArrayList<>();
            listMaptemp.add(map);
            listGroupMap.add(listMaptemp);
        }
    }
}


public void buildMap(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String type1,String tableName){
    if (type1 == null) {
        buildMapNoType(map, info, sbWhere, tableName);
    } else {
        buildMapType(map, info, sbWhere, type1, tableName);
    }
}


public void buildMapNoType(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String tableName){
    StringBuilder sql = new StringBuilder();
    Map<String, Object> param = new HashMap<>();
    sql.append(" select  行政区域_省 as province ,count(1) as num");
    sql.append(" from " + tableName + " t ");
    sql.append(" where 1=1 ");
    sql.append(" and  行政区域_省  is not null ");
    sql.append(" and  行政区域_省 <> ''  ");
    sql.append(sbWhere.toString());
    sql.append(" group by  行政区域_省 ");
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<Map<String, Object>> listMap = template.queryForList(sql.toString(), param);
    // series_data=[{name:'安徽',value:5483043}, ];
    List<Map<String, Object>> series_data = new ArrayList<>();
    Map<String, Object> mapt = new HashMap<>();
    mapt.put("name", "数量");
    mapt.put("type", "map");
    mapt.put("mapType", "china");
    mapt.put("roam", false);
    Map<String, Boolean> emap = new HashMap<>();
    Map<String, Object> label = new HashMap<>();
    emap.put("show", true);
    label.put("emphasis", emap);
    label.put("normal", emap);
    mapt.put("label", label);
    List<Map<String, Object>> series_data_data = new ArrayList<>();
    for (Map<String, Object> map2 : listMap) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", map2.get("province").toString());
        data.put("value", Integer.valueOf(map2.get("num").toString()));
        series_data_data.add(data);
    }
    mapt.put("data", series_data_data);
    series_data.add(mapt);
    map.put("series_data", series_data);
    String[] legend_data = { "数量" };
    map.put("legend_data", legend_data);
    map.put("chartType", info.getChartType());
}


public void buildMapType(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String type1,String tableName){
    StringBuilder sql = new StringBuilder();
    Map<String, Object> param = new HashMap<>();
    sql.append(" select  行政区域_省 as province ," + type1 + " as ask1 ,count(1) as num");
    sql.append(" from " + tableName + " t ");
    sql.append(" where 1=1 ");
    sql.append(" and  行政区域_省  is not null ");
    sql.append(" and  行政区域_省 <> ''  ");
    sql.append(" and  " + type1 + "  is not null ");
    sql.append(" and  " + type1 + " <> ''  ");
    sql.append(sbWhere.toString());
    sql.append(" group by  行政区域_省 ," + type1);
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<Map<String, Object>> listMap = template.queryForList(sql.toString(), param);
    // series_data=[{name:'安徽',value:5483043}, ];
    // 按type1 分组
    List<List<Map<String, Object>>> listGroupMap = new ArrayList<>();
    buildGroupByType1(listMap, listGroupMap);
    List<Map<String, Object>> series_data = new ArrayList<>();
    List<String> legend_data = new ArrayList<>();
    for (List<Map<String, Object>> map2 : listGroupMap) {
        legend_data.add(map2.get(0).get("ask1").toString());
        Map<String, Object> mapt = new HashMap<>();
        mapt.put("name", map2.get(0).get("ask1").toString());
        mapt.put("type", "map");
        mapt.put("mapType", "china");
        mapt.put("roam", false);
        Map<String, Boolean> emap = new HashMap<>();
        Map<String, Object> label = new HashMap<>();
        emap.put("show", true);
        label.put("emphasis", emap);
        label.put("normal", emap);
        mapt.put("label", label);
        List<Map<String, Object>> series_data_data = new ArrayList<>();
        for (Map<String, Object> map3 : map2) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", map3.get("province").toString());
            data.put("value", Integer.valueOf(map3.get("num").toString()));
            series_data_data.add(data);
        }
        mapt.put("data", series_data_data);
        series_data.add(mapt);
    }
    map.put("series_data", series_data);
    map.put("legend_data", legend_data);
    map.put("chartType", info.getChartType());
}


@SuppressWarnings("unchecked")
public void buildMapTypeSum(Map<String,Object> map,SysDbmsChartDimension info,StringBuilder sbWhere,String type1,String tableName){
    StringBuilder sql = new StringBuilder();
    Map<String, Object> param = new HashMap<>();
    sql.append(" SELECT  行政区域_省 AS PROVINCE ," + type1 + " AS ASK1 ,SUM(`总中标金额`)  AS NUM");
    sql.append(" FROM " + tableName + " t ");
    sql.append(" WHERE  DELETE_FLAG = 0  ");
    sql.append(" AND  公告类型  IN ('中标公告','成交公告') ");
    sql.append(" AND  行政区域_省  IS NOT NULL ");
    sql.append(" AND  行政区域_省 <> ''  ");
    sql.append(" AND  " + type1 + "  IS NOT NULL ");
    sql.append(" AND  " + type1 + " <> ''  ");
    sql.append(sbWhere.toString());
    sql.append(" GROUP BY  行政区域_省 ," + type1);
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<Map<String, Object>> listMap = template.queryForList(sql.toString(), param);
    // series_data=[{name:'安徽',value:5483043}, ];
    // 按type1 分组
    List<List<Map<String, Object>>> listGroupMap = new ArrayList<>();
    buildGroupByType1(listMap, listGroupMap);
    List<Map<String, Object>> series_data = new ArrayList<>();
    List<String> legend_data = new ArrayList<>();
    List<String> tempList = new ArrayList<>();
    for (List<Map<String, Object>> map2 : listGroupMap) {
        legend_data.add(map2.get(0).get("ASK1").toString());
        Map<String, Object> mapt = new HashMap<>();
        mapt.put("name", map2.get(0).get("ASK1").toString());
        mapt.put("type", "map");
        mapt.put("mapType", "china");
        Map<String, Boolean> emap = new HashMap<>();
        Map<String, Object> label = new HashMap<>();
        emap.put("show", true);
        label.put("emphasis", emap);
        Map<String, Boolean> normal = new HashMap<>();
        normal.put("show", false);
        label.put("normal", normal);
        mapt.put("label", label);
        List<Map<String, Object>> series_data_data = new ArrayList<>();
        for (Map<String, Object> map3 : map2) {
            if (!tempList.contains(map3.get("PROVINCE").toString())) {
                tempList.add(map3.get("PROVINCE").toString());
            }
            Map<String, Object> data = new HashMap<>();
            data.put("name", map3.get("PROVINCE").toString());
            data.put("value", Double.valueOf(map3.get("NUM") == null ? "0" : map3.get("NUM").toString()).longValue());
            series_data_data.add(data);
        }
        mapt.put("data", series_data_data);
        series_data.add(mapt);
    }
    // 补齐没有数据的项 ，值是0 ，没有项显示为 “-”
    for (Map<String, Object> map1 : series_data) {
        for (String string : tempList) {
            boolean exitflag = false;
            for (Map<String, Object> map2 : (List<Map<String, Object>>) map1.get("data")) {
                if (map2.get("name").toString().equals(string)) {
                    exitflag = true;
                }
            }
            if (!exitflag) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", string);
                data.put("value", 0);
                ((List<Map<String, Object>>) map1.get("data")).add(data);
            }
        }
    }
    map.put("series_data", series_data);
    map.put("legend_data", legend_data);
    map.put("chartType", info.getChartType());
}


}