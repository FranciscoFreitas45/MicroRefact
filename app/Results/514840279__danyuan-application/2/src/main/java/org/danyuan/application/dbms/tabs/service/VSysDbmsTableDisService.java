package org.danyuan.application.dbms.tabs.service;
 import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.config.MultiDatasourceConfig;
import org.danyuan.application.dbms.tabs.po.VSysDbmsTableDis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.MultiDatasourceConfig;
@Service
public class VSysDbmsTableDisService extends BaseServiceImpl<VSysDbmsTableDis>implements BaseService<VSysDbmsTableDis>{

@Autowired
 private MultiDatasourceConfig multiDatasourceConfig;


public String runsql(VSysDbmsTableDis sVSysDbmsTableDis) throws SQLException{
    Connection connection = multiDatasourceConfig.getConnection(sVSysDbmsTableDis.getJdbcUuid());
    Statement statement = connection.createStatement();
    statement.execute(sVSysDbmsTableDis.getDisSql());
    statement.close();
    statement = connection.createStatement();
    statement.addBatch(sVSysDbmsTableDis.getDropSql());
    statement.addBatch(sVSysDbmsTableDis.getRenameSql());
    statement.addBatch(sVSysDbmsTableDis.getResetSql());
    statement.executeBatch();
    return "1";
}


}