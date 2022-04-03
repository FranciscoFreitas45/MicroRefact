package org.jeecgframework.web.system.sms.service.impl;
 import org.jeecgframework.web.system.sms.service.TSSmsTemplateServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;
@Service("tSSmsTemplateService")
@Transactional
public class TSSmsTemplateServiceImpl extends CommonServiceImplimplements TSSmsTemplateServiceI{


public Serializable save(T entity){
    Serializable t = super.save(entity);
    // 执行新增操作配置的sql增强
    this.doAddSql((TSSmsTemplateEntity) entity);
    return t;
}


public boolean doDelSql(TSSmsTemplateEntity t){
    return true;
}


public boolean doUpdateSql(TSSmsTemplateEntity t){
    return true;
}


public boolean doAddSql(TSSmsTemplateEntity t){
    return true;
}


public String replaceVal(String sql,TSSmsTemplateEntity t){
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
    sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
    sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
    sql = sql.replace("#{template_type}", String.valueOf(t.getTemplateType()));
    sql = sql.replace("#{template_name}", String.valueOf(t.getTemplateName()));
    sql = sql.replace("#{template_content}", String.valueOf(t.getTemplateContent()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
}


public void delete(T entity){
    super.delete(entity);
    // 执行删除操作配置的sql增强
    this.doDelSql((TSSmsTemplateEntity) entity);
}


public void saveOrUpdate(T entity){
    super.saveOrUpdate(entity);
    // 执行更新操作配置的sql增强
    this.doUpdateSql((TSSmsTemplateEntity) entity);
}


}