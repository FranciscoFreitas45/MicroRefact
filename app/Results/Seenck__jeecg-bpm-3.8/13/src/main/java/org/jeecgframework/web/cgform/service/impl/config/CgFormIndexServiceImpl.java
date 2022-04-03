package org.jeecgframework.web.cgform.service.impl.config;
 import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.codegenerate.util.def.ConvertDef;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DTO.CgFormHeadEntity;
@Service("cgFormIndexService")
@Transactional
public class CgFormIndexServiceImpl extends CommonServiceImplimplements CgFormIndexServiceI{

 private  Logger logger;


public void dropIndexs(CgFormIndexEntity cgform,CgFormHeadEntity formhead){
    if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_MYSQL)) {
        // mysql
        String sql = "DROP INDEX " + cgform.getIndexName() + " ON " + formhead.getTableName();
        this.getSession().createSQLQuery(sql).executeUpdate();
    } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_ORACLE)) {
        // oracle
        String sql = "DROP INDEX " + cgform.getIndexName();
        this.getSession().createSQLQuery(sql).executeUpdate();
    } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_postgresql)) {
        // postgresql
        String sql = "DROP INDEX " + cgform.getIndexName();
        this.getSession().createSQLQuery(sql).executeUpdate();
    } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_SQL_SERVER)) {
        // sqlserver
        String sql = "DROP INDEX " + cgform.getIndexName() + " ON " + formhead.getTableName();
        this.getSession().createSQLQuery(sql).executeUpdate();
    }
}


public Serializable save(T entity){
    Serializable t = super.save(entity);
    // 执行新增操作配置的sql增强
    this.doAddSql((CgFormIndexEntity) entity);
    return t;
}


public boolean doDelSql(CgFormIndexEntity t){
    return true;
}


public boolean doUpdateSql(CgFormIndexEntity t){
    return true;
}


public boolean doAddSql(CgFormIndexEntity t){
    return true;
}


public String replaceVal(String sql,CgFormIndexEntity t){
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
    sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
    sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
    sql = sql.replace("#{index_name}", String.valueOf(t.getIndexName()));
    sql = sql.replace("#{index_field}", String.valueOf(t.getIndexField()));
    sql = sql.replace("#{index_type}", String.valueOf(t.getIndexType()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
}


public void delete(T entity){
    super.delete(entity);
    // 执行删除操作配置的sql增强
    this.doDelSql((CgFormIndexEntity) entity);
}


public void saveOrUpdate(T entity){
    super.saveOrUpdate(entity);
    // 执行更新操作配置的sql增强
    this.doUpdateSql((CgFormIndexEntity) entity);
}


@Override
public boolean updateIndexes(CgFormHeadEntity cgFormHead){
    boolean isChange = false;
    List<CgFormIndexEntity> indexes = cgFormHead.getIndexes();
    // 判断新的indexes和旧的indexes的差异,如果有差异，就设置cgFormHead的数据库同步字段为N
    List<CgFormIndexEntity> oldindexes = this.getSession().createSQLQuery("select * from cgform_index where table_id = '" + cgFormHead.getId() + "'").addEntity(CgFormIndexEntity.class).list();
    if (oldindexes.size() != 0 && indexes != null) {
        if (oldindexes.size() != indexes.size()) {
            isChange = true;
        } else {
            for (int i = 0; i < indexes.size(); i++) {
                CgFormIndexEntity oldindex = oldindexes.get(i);
                CgFormIndexEntity newindex = indexes.get(i);
                if (oldindex.getIndexField().equals(newindex.getIndexField()) && oldindex.getIndexName().equals(newindex.getIndexName()) && oldindex.getIndexType().equals(newindex.getIndexType())) {
                } else {
                    isChange = true;
                }
            }
        }
    } else if (oldindexes.size() == 0 && indexes == null) {
        isChange = false;
    } else {
        isChange = true;
    }
    cgFormHead.setIsDbSynch(isChange ? "N" : cgFormHead.getIsDbSynch());
    String id = cgFormHead.getId();
    CgFormHeadEntity formhead = this.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
    // 根据名称先删除索引
    /*List<CgFormIndexEntity> oldindexes = this.getSession().
				createSQLQuery("select * from cgform_index where table_id = '" + cgFormHead.getId() + "'").addEntity(CgFormIndexEntity.class).list();*/
    if (isChange) {
        // 删除表的索引
        try {
            if (oldindexes != null) {
                for (CgFormIndexEntity cgform : oldindexes) {
                    // TODO 索引的创建和删除，需要兼容多数据库
                    dropIndexs(cgform, formhead);
                }
            }
        } catch (HibernateException e) {
            // e.printStackTrace();
            logger.error(e.toString());
        }
        // 删除索引后重新保存 【物理表】
        this.getSession().createSQLQuery("delete from cgform_index where table_id = '" + id + "'").executeUpdate();
        if (indexes != null) {
            for (CgFormIndexEntity cgform : indexes) {
                cgform.setTable(cgFormHead);
                this.save(cgform);
            }
        }
    }
    return isChange;
}


@Override
public void createIndexes(CgFormHeadEntity cgFormHead){
    CgFormHeadEntity formhead = this.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
    List<CgFormIndexEntity> indexes = this.getSession().createSQLQuery("select * from cgform_index where table_id = '" + cgFormHead.getId() + "'").addEntity(CgFormIndexEntity.class).list();
    if (indexes.size() != 0) {
        for (CgFormIndexEntity cgform : indexes) {
            if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_MYSQL)) {
                // mysql
                String sql = "";
                if (cgform.getIndexType().equals("normal")) {
                    sql = "create index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                } else {
                    sql = "create " + cgform.getIndexType() + " index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                }
                this.getSession().createSQLQuery(sql).executeUpdate();
            } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_ORACLE)) {
                // oracle
                String sql = "";
                if (cgform.getIndexType().equals("normal")) {
                    sql = "create index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                } else {
                    sql = "create " + cgform.getIndexType() + " index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                }
                this.getSession().createSQLQuery(sql).executeUpdate();
            } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_postgresql)) {
                // postgresql
                String sql = "";
                if (cgform.getIndexType().equals("normal")) {
                    sql = "create index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                } else {
                    sql = "create " + cgform.getIndexType() + " index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                }
                this.getSession().createSQLQuery(sql).executeUpdate();
            } else if (CodeResourceUtil.DATABASE_TYPE.equals(ConvertDef.DATABASE_TYPE_SQL_SERVER)) {
                // sqlserver
                String sql = "";
                if (cgform.getIndexType().equals("normal")) {
                    sql = "create index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                } else {
                    sql = "create " + cgform.getIndexType() + " index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                }
                this.getSession().createSQLQuery(sql).executeUpdate();
            }
        }
    }
}


}