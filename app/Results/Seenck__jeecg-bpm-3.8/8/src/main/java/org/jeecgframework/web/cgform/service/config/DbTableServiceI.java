package org.jeecgframework.web.cgform.service.config;
 import org.springframework.jdbc.core.JdbcTemplate;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
public interface DbTableServiceI {


public String dropTableSQL(CgFormHeadEntity tableProperty)
;

public String createTableSQL(CgFormHeadEntity tableProperty)
;

public String updateTableSQL(CgFormHeadEntity cgFormHead,JdbcTemplate jdbcTemplate)
;

public String createIsExitSql(String tableName)
;

}