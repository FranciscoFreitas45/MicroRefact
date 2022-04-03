package org.jeecgframework.web.system.sms.util;
 import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.entity.TSSmsSqlEntity;
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateEntity;
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.jeecgframework.web.system.sms.service.TSSmsSqlServiceI;
import org.jeecgframework.web.system.sms.service.TSSmsTemplateServiceI;
import org.jeecgframework.web.system.sms.service.TSSmsTemplateSqlServiceI;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
public class TuiSongMsgUtil {

 private  Configuration configuration;


public Map<String,Object> getRootMapBySql(String templateSql,Map<String,Object> map){
    // 调用查询sql的方法执行此sql
    SqlParameterSource sqlp = new MapSqlParameterSource(map);
    return getNamedParameterJdbcTemplate().queryForMap(templateSql, sqlp);
}


public String getTemplateContent(String templateId){
    String hql = "from TSSmsTemplateEntity as template where template.id=? ";
    List<TSSmsTemplateEntity> tSSmsTemplateList = getTssmsTemplateInstance().findHql(hql, templateId);
    String templateConetent = "";
    for (TSSmsTemplateEntity tsSmsTemplateEntity : tSSmsTemplateList) {
        templateConetent = tsSmsTemplateEntity.getTemplateContent();
    }
    return templateConetent;
}


public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = ApplicationContextUtil.getContext().getBean(NamedParameterJdbcTemplate.class);
    return namedParameterJdbcTemplate;
}


public Configuration getConfiguration(){
    if (configuration == null) {
        configuration = ApplicationContextUtil.getContext().getBean(Configuration.class);
    }
    return configuration;
}


public String getTempletContent(String content,Map<String,Object> data){
    StringReader strR = new StringReader(content);
    Template template = new Template("strTemplate", strR, new Configuration());
    StringWriter stringWriter = new StringWriter();
    BufferedWriter writer = new BufferedWriter(stringWriter);
    template.process(data, writer);
    return stringWriter.toString();
}


public TSSmsTemplateServiceI getTssmsTemplateInstance(){
    TSSmsTemplateServiceI tSSmsTemplateService = ApplicationContextUtil.getContext().getBean(TSSmsTemplateServiceI.class);
    return tSSmsTemplateService;
}


public String sendMessage(String title,String msgType,String code,Map<String,Object> map,String sentTo){
    try {
        TSSmsEntity tss = new TSSmsEntity();
        tss.setEsType(msgType);
        tss.setEsTitle(title);
        tss.setEsReceiver(sentTo);
        tss.setEsStatus(Constants.SMS_SEND_STATUS_1);
        // 0未读1已读
        tss.setIsRead(0);
        String hql = "from TSSmsTemplateSqlEntity as tempSql where tempSql.code=? ";
        List<TSSmsTemplateSqlEntity> tssmsTemplateSqlList = getTssmsTemplateSqlInstance().findHql(hql, code);
        for (TSSmsTemplateSqlEntity tsSmsTemplateSqlEntity : tssmsTemplateSqlList) {
            TSSmsSqlEntity tsSmsSqlEntity = getTSSmsServiceInstance().getEntity(TSSmsSqlEntity.class, tsSmsTemplateSqlEntity.getSqlId());
            // 获取对应业务sql表中的sql语句
            String templateSql = tsSmsSqlEntity.getSqlContent();
            TSSmsTemplateEntity tsSmsTemplateEntity = getTSSmsServiceInstance().getEntity(TSSmsTemplateEntity.class, tsSmsTemplateSqlEntity.getTemplateId());
            if (Constants.SMS_SEND_TYPE_3.equals(tsSmsTemplateEntity.getTemplateType())) {
                tss.setEsStatus(Constants.SMS_SEND_STATUS_2);
                tss.setEsSendtime(new Date());
            } else {
                tss.setEsStatus(Constants.SMS_SEND_STATUS_1);
            }
            // 执行查询出来的模板sql
            Map<String, Object> rootMap = getRootMapBySql(templateSql, map);
            // title = getTempletContent(title,rootMap);
            // tss.setEsTitle(title);
            // 获取模板表的对应的模板内容
            String templateContent = tsSmsTemplateEntity.getTemplateContent();
            templateContent = getTempletContent(templateContent, rootMap);
            tss.setEsContent(templateContent);
        }
        // 对库进行查询操作
        getTSSmsServiceInstance().save(tss);
        return "success";
    } catch (Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}


public TSSmsSqlServiceI getTSSmsSqlInstance(){
    TSSmsSqlServiceI tSSmsSqlService = ApplicationContextUtil.getContext().getBean(TSSmsSqlServiceI.class);
    return tSSmsSqlService;
}


public String getTemplateSql(String sqlId){
    String hql = "from TSSmsSqlEntity as tssSql where tssSql.id=?";
    List<TSSmsSqlEntity> tssmsSqlList = getTSSmsSqlInstance().findHql(hql, sqlId);
    String sqlContent = "";
    for (TSSmsSqlEntity tsSmsSqlEntity : tssmsSqlList) {
        sqlContent = tsSmsSqlEntity.getSqlContent();
    }
    return sqlContent;
}


public TSSmsServiceI getTSSmsServiceInstance(){
    TSSmsServiceI tSSmsService = ApplicationContextUtil.getContext().getBean(TSSmsServiceI.class);
    return tSSmsService;
}


public TSSmsTemplateSqlServiceI getTssmsTemplateSqlInstance(){
    TSSmsTemplateSqlServiceI tSSmsTemplateSqlService = ApplicationContextUtil.getContext().getBean(TSSmsTemplateSqlServiceI.class);
    return tSSmsTemplateSqlService;
}


}