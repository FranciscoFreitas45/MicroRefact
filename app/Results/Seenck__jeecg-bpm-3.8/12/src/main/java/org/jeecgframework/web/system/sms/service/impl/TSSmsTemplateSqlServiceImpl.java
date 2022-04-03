package org.jeecgframework.web.system.sms.service.impl;
 import org.jeecgframework.web.system.sms.service.TSSmsTemplateSqlServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;
@Service("tSSmsTemplateSqlService")
@Transactional
public class TSSmsTemplateSqlServiceImpl extends CommonServiceImplimplements TSSmsTemplateSqlServiceI{


public Serializable save(T entity){
    Serializable t = super.save(entity);
    // 执行新增操作配置的sql增强
    this.doAddSql((TSSmsTemplateSqlEntity) entity);
    return t;
}


public boolean doDelSql(TSSmsTemplateSqlEntity t){
    return true;
}


public boolean doUpdateSql(TSSmsTemplateSqlEntity t){
    return true;
}


public boolean doAddSql(TSSmsTemplateSqlEntity t){
    return true;
}


public String replaceVal(String sql,TSSmsTemplateSqlEntity t){
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
    sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
    sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
    sql = sql.replace("#{code}", String.valueOf(t.getCode()));
    sql = sql.replace("#{name}", String.valueOf(t.getName()));
    sql = sql.replace("#{sql_id}", String.valueOf(t.getSqlId()));
    sql = sql.replace("#{template_id}", String.valueOf(t.getTemplateId()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
}


public void delete(T entity){
    super.delete(entity);
    // 执行删除操作配置的sql增强
    this.doDelSql((TSSmsTemplateSqlEntity) entity);
}


public void saveOrUpdate(T entity){
    super.saveOrUpdate(entity);
    // 执行更新操作配置的sql增强
    this.doUpdateSql((TSSmsTemplateSqlEntity) entity);
}


}