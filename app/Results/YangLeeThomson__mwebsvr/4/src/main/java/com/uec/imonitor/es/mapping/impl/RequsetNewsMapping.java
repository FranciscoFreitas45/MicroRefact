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
@Service("requestNewsMapping")
public class RequsetNewsMapping implements IDataMapping{

 private  Logger logger;


@Override
public XContentBuilder mappingConfig(IndexConfig iConfig){
    XContentBuilder builder = null;
    if (iConfig == null) {
        logger.error("需求新闻对象映射参数IndexConfig为空");
    } else if (iConfig.getTypeName() == null) {
        logger.error("需求新闻对象映射参数IndexConfig中索引对象名称typeName为空");
    } else {
        try {
            builder = XContentFactory.jsonBuilder().startObject().startObject(iConfig.getTypeName()).startObject("_all").field("type", "string").field("store", "false").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("index", "analyzed").endObject().startObject("properties").startObject("newsId").field("type", "Integer").field("store", "yes").endObject().startObject("requestId").field("type", "Integer").field("store", "yes").endObject().startObject("webpageCode").field("type", "string").field("store", "yes").field("index", "not_analyzed").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("index", "analyzed").endObject().startObject("originalCode").field("type", "string").field("store", "yes").field("index", "not_analyzed").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("index", "analyzed").endObject().startObject("webpageUrl").field("type", "string").field("store", "yes").field("index", "not_analyzed").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("index", "analyzed").endObject().startObject("title").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("content").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("classification").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("newsType").field("type", "integer").field("store", "yes").endObject().startObject("newsAuthor").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("authorId").field("type", "integer").field("store", "yes").endObject().startObject("newsSource").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("newsSection").field("type", "string").field("index", "not_analyzed").endObject().startObject("reportDatetime").field("type", "date").field("store", "yes").endObject().startObject("createDatetime").field("type", "date").field("store", "yes").endObject().startObject("startDatetime").field("type", "date").field("store", "yes").endObject().startObject("endDatetime").field("type", "date").field("store", "yes").endObject().startObject("comment").field("type", "string").field("store", "yes").field("analyzer", ESConstantUtil.ANALYZER_IK_MAX).field("search_analyzer", ESConstantUtil.ANALYZER_IK_SMART).field("index", "analyzed").endObject().startObject("picPath").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().startObject("picDatetime").field("type", "date").field("store", "yes").endObject().startObject("isDeleted").field("type", "integer").field("store", "yes").endObject().startObject("newsTypeName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject().endObject().endObject().endObject();
        } catch (IOException e) {
            logger.error("最新新闻对象映射创建失败，失败异常信息如下：" + e);
        }
    }
    return builder;
}


}