package com.gbcom.system.manager;
 import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.Interface.SysLogService;
@Service("MysqlManager")
public class MysqlManager {

@Autowired
 private SysLogService sysLogService;


@SuppressWarnings("unchecked")
public String getRealMySqlPath(){
    List list = sysLogService.findBySql("select @@basedir as basePath from dual");
    String mysqlPath = (String) list.get(0);
    return mysqlPath;
}


@SuppressWarnings("unchecked")
public String getMySqlPath(){
    Map map = System.getenv();
    String path = map.get("Path").toString();
    String temp = path.substring(0, path.indexOf("MySQL"));
    temp = path.substring(temp.lastIndexOf(":") - 1);
    String mysqlPath = temp.substring(0, temp.indexOf(";"));
    return mysqlPath;
}


}