package org.jeecgframework.web.cgform.service.impl.enhance;
 import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJavaServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("cgformEnhanceJavaService")
@Transactional
public class CgformEnhanceJavaServiceImpl extends CommonServiceImplimplements CgformEnhanceJavaServiceI{


@Override
public CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String buttonCode,String formId){
    StringBuilder hql = new StringBuilder("");
    hql.append(" from CgformEnhanceJavaEntity t");
    // update--begin--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    hql.append(" where t.formId=?");
    hql.append(" and  t.buttonCode =?");
    List<CgformEnhanceJavaEntity> list = this.findHql(hql.toString(), formId, buttonCode);
    // update--end--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    if (list != null && list.size() > 0) {
        return list.get(0);
    }
    return null;
}


@Override
public boolean checkClassOrSpringBeanIsExist(CgformEnhanceJavaEntity cgformEnhanceJavaEntity){
    String cgJavaType = cgformEnhanceJavaEntity.getCgJavaType();
    String cgJavaValue = cgformEnhanceJavaEntity.getCgJavaValue();
    // update-start--Author:luobaoli  Date:20150701 for： 如果springKey或者javaClass值为空时，不做操作
    if (StringUtil.isNotEmpty(cgJavaValue)) {
        try {
            if ("class".equals(cgJavaType)) {
                Class clazz = Class.forName(cgJavaValue);
                if (clazz == null || clazz.newInstance() == null)
                    return false;
            }
            if ("spring".equals(cgJavaType)) {
                Object obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                if (obj == null)
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // update-end--Author:luobaoli  Date:20150701 for： 如果springKey或者javaClass值为空时，不做操作
    return true;
}


public Serializable save(T entity){
    Serializable t = super.save(entity);
    // 执行新增操作配置的sql增强
    this.doAddSql((CgformEnhanceJavaEntity) entity);
    return t;
}


@Override
public List<CgformEnhanceJavaEntity> checkCgformEnhanceJavaEntity(CgformEnhanceJavaEntity cgformEnhanceJavaEntity){
    StringBuilder hql = new StringBuilder("");
    hql.append(" from CgformEnhanceJavaEntity t");
    // update--begin--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    hql.append(" where t.formId=?");
    hql.append(" and  t.buttonCode =?");
    List<CgformEnhanceJavaEntity> list = null;
    if (cgformEnhanceJavaEntity.getId() != null) {
        hql.append(" and t.id !=?");
        list = this.findHql(hql.toString(), cgformEnhanceJavaEntity.getFormId(), cgformEnhanceJavaEntity.getButtonCode(), cgformEnhanceJavaEntity.getId());
    } else {
        list = this.findHql(hql.toString(), cgformEnhanceJavaEntity.getFormId(), cgformEnhanceJavaEntity.getButtonCode());
    }
    // update--end--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    return list;
}


public boolean doDelSql(CgformEnhanceJavaEntity t){
    return true;
}


public boolean doUpdateSql(CgformEnhanceJavaEntity t){
    return true;
}


public boolean doAddSql(CgformEnhanceJavaEntity t){
    return true;
}


public String replaceVal(String sql,CgformEnhanceJavaEntity t){
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{cg_java_type}", String.valueOf(t.getCgJavaType()));
    sql = sql.replace("#{cg_java_value}", String.valueOf(t.getCgJavaValue()));
    sql = sql.replace("#{form_id}", String.valueOf(t.getFormId()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    sql = sql.replace("#{active_status}", String.valueOf(t.getActiveStatus()));
    return sql;
}


public void delete(T entity){
    super.delete(entity);
    // 执行删除操作配置的sql增强
    this.doDelSql((CgformEnhanceJavaEntity) entity);
}


public void saveOrUpdate(T entity){
    super.saveOrUpdate(entity);
    // 执行更新操作配置的sql增强
    this.doUpdateSql((CgformEnhanceJavaEntity) entity);
}


}