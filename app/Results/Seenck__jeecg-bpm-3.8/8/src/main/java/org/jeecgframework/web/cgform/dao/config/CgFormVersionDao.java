package org.jeecgframework.web.cgform.dao.config;
 import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;
@Repository("cgFormVersionDao")
public interface CgFormVersionDao {


@Arguments("tableName")
public String getCgFormVersionByTableName(String tableName)
;

@Arguments("id")
public String getCgFormVersionById(String id)
;

@Arguments({ "newVersion", "formId" })
public void updateVersion(String newVersion,String formId)
;

@Arguments({ "id" })
public CgFormHeadEntity getCgFormById(String id)
;

}