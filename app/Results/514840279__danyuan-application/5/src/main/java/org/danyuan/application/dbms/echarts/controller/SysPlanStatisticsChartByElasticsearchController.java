package org.danyuan.application.dbms.echarts.controller;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.common.utils.string.StringUtils;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimension;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimensionData;
import org.danyuan.application.dbms.echarts.service.SysDbmsChartDimensionDataService;
import org.danyuan.application.dbms.echarts.service.SysDbmsChartDimensionService;
import org.danyuan.application.dbms.echarts.service.SysPlantBarOrLineStatisticsChartByElasticsearchService;
import org.danyuan.application.dbms.echarts.service.SysPlantMapStatisticsChartByElasticsearchService;
import org.danyuan.application.dbms.echarts.service.SysPlantPieStatisticsChartByElasticsearchService;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsColsInfoService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoService;
@RestController
@RequestMapping("/sysPlanStatisticsChartByElasticsearch")
@Api(value = "/SysPlanStatisticsChartByElasticsearch", description = "平台管理")
public class SysPlanStatisticsChartByElasticsearchController {

 private  Logger logger;

@Autowired
 private SysDbmsChartDimensionService sysPlantChartDimensionService;

@Autowired
 private SysDbmsChartDimensionDataService sysPlantChartDimensionDataService;

@Autowired
 private SysPlantBarOrLineStatisticsChartByElasticsearchService sysPlantBarOrLineStatisticsChartByElasticsearchService;

@Autowired
 private SysPlantPieStatisticsChartByElasticsearchService sysPlantPieStatisticsChartByElasticsearchService;

@Autowired
 private SysPlantMapStatisticsChartByElasticsearchService sysPlantMapStatisticsChartByElasticsearchService;

@Autowired
 private  SysDbmsTabsColsInfoService sysDbmsTabsColsInfoService;


public void buildTree(Map<String,Object> map,SysDbmsChartDimension info,List<SysDbmsChartDimensionData> list){
}


@ApiOperation(value = "构建图形数据", notes = "")
@RequestMapping(path = "/build", method = RequestMethod.POST)
public Map<String,Object> build(SysDbmsChartDimension info){
    logger.info("build", SysPlanStatisticsChartByElasticsearchController.class);
    info = sysPlantChartDimensionService.findOne(info);
    SysDbmsChartDimensionData sysPlantChartDimensionData = new SysDbmsChartDimensionData();
    sysPlantChartDimensionData.setDimeUuid(info.getUuid());
    List<SysDbmsChartDimensionData> listParam = sysPlantChartDimensionDataService.findAll(sysPlantChartDimensionData);
    SysDbmsTabsColsInfo conf = new SysDbmsTabsColsInfo();
    List<SysDbmsTabsColsInfo> confs = sysDbmsTabsColsInfoService.findAll(conf);
    String type3 = findTypeName(info.getLableUuid2(), confs);
    String type2 = findTypeName(info.getLableUuid(), confs);
    String type1 = info.getChartType();
    if (type3 != null && type2 == null) {
        type2 = type3;
        type3 = null;
    }
    if (type2 != null && type1 == null) {
        type1 = type2;
        type2 = type3;
        type3 = null;
    }
    Map<String, Object> map = new HashMap<>();
    if (type1 == null && !"map".equals(info.getChartType())) {
        return map;
    }
    // 组织条件语句
    QueryBuilder queryBuilder = buildWhereSql(listParam, confs);
    switch(info.getChartType()) {
        case "pie":
        case "rompie":
        case "nanpie":
            // 饼图
            sysPlantPieStatisticsChartByElasticsearchService.buildPie(map, info, queryBuilder, type1);
            break;
        case "bar":
        case "tbar":
        case "line":
            // 柱图、线图 param map返回结果 info 图表属性 ，list 图表条件
            sysPlantBarOrLineStatisticsChartByElasticsearchService.buildBarOrLine(map, info, queryBuilder, type1, type2, type3);
            break;
        case "map":
            // 地图
            sysPlantMapStatisticsChartByElasticsearchService.buildMap(map, info, queryBuilder, type1);
            break;
        case "tree":
            // 树图
            buildTree(map, info, listParam);
            break;
        case "sunburst":
            // 旭日图
            buildSunburst(map, info, listParam);
            break;
        default:
            break;
    }
    return map;
}


public String findTypeName(String lableUuid2,List<SysDbmsTabsColsInfo> confs){
    if (StringUtils.isNullOrNone(lableUuid2)) {
        return null;
    }
    for (SysDbmsTabsColsInfo sysPlantBindConf : confs) {
        if (lableUuid2.equals(sysPlantBindConf.getUuid())) {
            return sysPlantBindConf.getColsName();
        }
    }
    return null;
}


public void buildSunburst(Map<String,Object> map,SysDbmsChartDimension info,List<SysDbmsChartDimensionData> list){
    map.put("series_data", "");
}


public QueryBuilder buildWhereSql(List<SysDbmsChartDimensionData> listParam,List<SysDbmsTabsColsInfo> confs){
    if (listParam.size() == 0) {
        return null;
    }
    // 参数分组
    List<List<SysDbmsChartDimensionData>> pList = new ArrayList<>();
    for (SysDbmsChartDimensionData data : listParam) {
        if (data.getConditions() == null || "".equals(data.getConditions().trim())) {
            continue;
        }
        if ("in".equals(data.getSymbol())) {
            if (pList.size() == 0) {
                List<SysDbmsChartDimensionData> temlist = new ArrayList<>();
                temlist.add(data);
                pList.add(temlist);
                continue;
            }
            boolean check = true;
            for (int i = 0; i < pList.size(); i++) {
                if (data.getColsUuid().equals(pList.get(i).get(0).getColsUuid())) {
                    pList.get(i).add(data);
                    check = false;
                }
            }
            if (check) {
                List<SysDbmsChartDimensionData> temlist = new ArrayList<>();
                temlist.add(data);
                pList.add(temlist);
            }
        } else {
            List<SysDbmsChartDimensionData> temlist = new ArrayList<>();
            temlist.add(data);
            pList.add(temlist);
        }
    }
    // 组织条件语句
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    for (List<SysDbmsChartDimensionData> list2 : pList) {
        String colsUuid = list2.get(0).getColsUuid();
        String colsName = "";
        if ("keyword".equals(colsUuid)) {
            colsName = "keyword";
        } else {
            for (SysDbmsTabsColsInfo conf : confs) {
                if (colsUuid.equals(conf.getUuid())) {
                    colsName = conf.getColsName();
                    break;
                }
            }
        }
        List<String> keyword = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            keyword.add(list2.get(i).getConditions());
        }
        // 根据smbol 组织 条件
        String symbol = list2.get(0).getSymbol();
        if ("in".equals(symbol)) {
            if (colsName.equals("keyword")) {
                QueryBuilder queryBuildert = QueryBuilders.termsQuery("display_content", keyword);
                queryBuilder.must(queryBuildert);
            } else {
                QueryBuilder queryBuildert = QueryBuilders.termsQuery(colsName, keyword);
                queryBuilder.must(queryBuildert);
            }
        } else if ("lt".equals(symbol)) {
            QueryBuilder queryBuildert = // 
            QueryBuilders.rangeQuery(colsName).to(// 
            list2.get(0).getConditions()).includeUpper(// 包含下届
            false);
            queryBuilder.must(queryBuildert);
        } else if ("gt".equals(symbol)) {
            QueryBuilder queryBuildert = // 
            QueryBuilders.rangeQuery("公告日期").from(// 
            list2.get(0).getConditions()).includeLower(// 包含上界
            true);
            queryBuilder.must(queryBuildert);
        }
    }
    return queryBuilder;
}


}