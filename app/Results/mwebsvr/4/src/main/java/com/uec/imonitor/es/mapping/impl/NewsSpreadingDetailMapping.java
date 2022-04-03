package com.uec.imonitor.es.mapping.impl;
 import java.io.IOException;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.es.bean.index.IndexConfig;
import com.uec.imonitor.es.mapping.IDataMapping;
@Service("newsSpreadingMapping")
public class NewsSpreadingDetailMapping implements IDataMapping{

 private  Logger logger;


@Override
public XContentBuilder mappingConfig(IndexConfig iConfig){
    XContentBuilder builder = null;
    if (iConfig == null) {
        logger.error("分析结果详情对象映射参数IndexConfig为空");
    } else if (iConfig.getTypeName() == null) {
        logger.error("分析结果详情对象映射参数IndexConfig中索引对象名称typeName为空");
    } else {
        try {
            builder = XContentFactory.jsonBuilder().startObject().startObject(iConfig.getTypeName()).startObject("_all").field("type", "string").field("store", "false").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("index", "analyzed").endObject().startObject("properties").startObject("innerid").field("type", "integer").field("store", "yes").endObject().startObject("requestId").field("type", "integer").field("store", "yes").endObject().startObject("newsId").field("type", "integer").field("store", "yes").endObject().startObject("status").field("type", "integer").field("store", "yes").endObject().startObject("reqNewsTitle").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("reprintType").field("type", "integer").field("store", "yes").endObject().startObject("webpageUrl").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("webpageCode").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("newsSection").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("title").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("keywords").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("sourceReport").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("sourceCrawl").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("region").field("type", "integer").field("store", "yes").endObject().startObject("classification").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("status").field("type", "integer").field("store", "yes").endObject().startObject("author").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("createDatetime").field("type", "date").field("store", "yes").endObject().startObject("crawlDatetime").field("type", "date").field("store", "yes").endObject().startObject("releaseDatetime").field("type", "date").field("store", "yes").endObject().startObject("updateDatetime").field("type", "date").field("store", "yes").endObject().startObject("picPath").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("isDeleted").field("type", "integer").field("store", "yes").endObject().startObject("weiboId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("weiboRootId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("content").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("noTagContent").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("description").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("sourceCrawlLevelOne").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("sourceCrawlLevelOneValue").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("sourceCrawlLevelTwo").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("sourceCrawlLevelTwoValue").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("regionName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("country").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("province").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("provinceShort").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("city").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("cityShort").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("statusName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("reprintTypeName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("newsTypeName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("titleSimilarity").field("type", "integer").field("store", "yes").endObject().startObject("contentSimilarity").field("type", "integer").field("store", "yes").endObject().endObject().endObject().endObject();
        } catch (IOException e) {
            logger.error("最新新闻对象映射创建失败，失败异常信息如下：" + e);
        }
    }
    return builder;
}


}