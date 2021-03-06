package org.danyuan.application.dbms.tabs.controller;
 import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.danyuan.application.common.config.MultiDatasourceConfig;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsJdbcInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsInfoService;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsJdbcInfoService;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsInfoVo;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsJdbcInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysDbmsTabsInfo")
public class SysDbmsTabsInfoController {

 private  Logger logger;

@Autowired
 private  SysDbmsTabsInfoService sysDbmsTabsInfoService;

@Autowired
 private  SysDbmsTabsJdbcInfoService sysDbmsTabsJdbcInfoService;

@Autowired
 private MultiDatasourceConfig multiDatasourceConfig;


@RequestMapping(path = "/pagev", method = { RequestMethod.GET, RequestMethod.POST })
public List<SysDbmsTabsInfo> pagev(SysDbmsTabsJdbcInfoVo vo) throws SQLException{
    logger.info("pagev", SysDbmsTabsInfoController.class);
    // Map<String, DataSource> multiDatasource = multiDatasourceConfig.multiDatasource();
    List<SysDbmsTabsInfo> list = null;
    SysDbmsTabsJdbcInfo info = sysDbmsTabsJdbcInfoService.findOne(vo.getInfo());
    Connection connection = multiDatasourceConfig.getConnection(info.getUuid());
    Statement statement = connection.createStatement();
    // JdbcTemplate template = new JdbcTemplate(dataSource);
    RowMapperResultSetExtractor<SysDbmsTabsInfo> rse = new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
    // NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(connection);
    if (info != null && info.getType().equals("mysql")) {
        // List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        StringBuilder pageSql = new StringBuilder();
        pageSql.append(" SELECT UUID() AS uuid,   ");
        pageSql.append("  '" + info.getUuid() + "' as jdbc_uuid,  ");
        pageSql.append(" CONCAT(T.`TABLE_SCHEMA`,'.' ,T.`TABLE_NAME`) AS tabs_name,");
        pageSql.append("  'WEIFENLEI' as type_uuid, ");
        pageSql.append(" T.`TABLE_COMMENT`  AS tabs_desc, ");
        pageSql.append(" 'mysql'  AS db_type, ");
        pageSql.append(" T.`TABLE_ROWS` AS tabs_rows ");
        pageSql.append(" FROM `INFORMATION_SCHEMA`.`TABLES` T ");
        pageSql.append(" WHERE T.`TABLE_SCHEMA` = '" + info.getDatabaseName() + "'");
        pageSql.append(" and T.`TABLE_NAME` like '%" + ("".equals(vo.getSearchText()) ? "" : vo.getSearchText().toUpperCase()) + "%'");
        pageSql.append(" AND NOT EXISTS ( ");
        pageSql.append("	SELECT 1 FROM application.`sys_dbms_tabs_info` a ");
        pageSql.append("	WHERE CONCAT(    T.`TABLE_SCHEMA`,    '.',    T.`TABLE_NAME`  ) = a.`tabs_name` ");
        pageSql.append(" )");
        pageSql.append(" order by CONCAT(T.`TABLE_SCHEMA`,'.' ,T.`TABLE_NAME`),TABLE_ROWS desc");
        // list = template.query(pageSql.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
        logger.debug(pageSql.toString(), SysDbmsTabsInfoController.class);
        list = rse.extractData(statement.executeQuery(pageSql.toString()));
    } else {
        SysDbmsTabsInfo tabsInfo = new SysDbmsTabsInfo();
        tabsInfo.setJdbcUuid(info.getUuid());
        List<SysDbmsTabsInfo> list2 = sysDbmsTabsInfoService.findAll(tabsInfo);
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(" select  ");
        sBuilder.append("  t.owner||'_'||t.table_name as UUID, ");
        sBuilder.append("  '" + info.getUuid() + "' as jdbc_uuid,  ");
        sBuilder.append("  'WEIFENLEI' as type_uuid, ");
        sBuilder.append("  t1.comments as tabs_desc, ");
        sBuilder.append("  t.num_rows as tabs_rows, ");
        sBuilder.append("  t.blocks*8*1024 as tab_space, ");
        sBuilder.append("  rownum as tabs_order, ");
        sBuilder.append("  '0' as delete_flag, ");
        sBuilder.append("  'oracle' as db_type, ");
        sBuilder.append("  t.owner||'.'||t.table_name  as tabs_name  ,");
        sBuilder.append("  '" + info.getUuid() + "'  as addr_uUid  ");
        sBuilder.append("  from all_tables  t ");
        sBuilder.append(" left join all_tab_comments  t1   on t1.owner = t.owner   and t1.table_name=t.table_name ");
        sBuilder.append("  where  t.owner = '" + info.getUsername().toUpperCase() + "' ");
        sBuilder.append("  and  t.table_name  like '%" + ("".equals(vo.getSearchText()) ? "" : vo.getSearchText().toUpperCase()) + "%'");
        sBuilder.append("  and t.owner||'.'||t.table_name not in('");
        for (int i = 0; i < list2.size(); i++) {
            sBuilder.append(list2.get(i).getTabsName());
            if (i < list2.size() - 1) {
                sBuilder.append("','");
            }
        }
        sBuilder.append("') ");
        // list = template.query(sBuilder.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
        logger.debug(statement.toString(), SysDbmsTabsInfoController.class);
        list = rse.extractData(statement.executeQuery(sBuilder.toString()));
    }
    statement.close();
    connection.close();
    return list;
}


@RequestMapping(path = "/drop", method = RequestMethod.POST)
public String drop(SysDbmsTabsInfoVo vo){
    logger.info("drop", SysDbmsTabsInfoController.class);
    sysDbmsTabsInfoService.drop(vo.getList());
    return "1";
}


@RequestMapping(path = "/updBeforEdit", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView updBeforEdit(HttpServletRequest request){
    logger.info("updBeforEdit", SysDbmsTabsInfoController.class);
    ModelAndView view = new ModelAndView("dbms/table/upd_table");
    SysDbmsTabsInfo info = new SysDbmsTabsInfo();
    if (request.getParameter("uuid") != null || !"".equals(request.getParameter("uuid"))) {
        info.setUuid(request.getParameter("uuid"));
        info = sysDbmsTabsInfoService.findOne(info);
    }
    view.addObject("sysTableInfo", info);
    return view;
}


@RequestMapping(path = "/updBefor", method = RequestMethod.GET)
public ModelAndView updBefor(HttpServletRequest request){
    logger.info("updBefor", SysDbmsTabsInfoController.class);
    ModelAndView view = new ModelAndView("dbms/table/index_column");
    SysDbmsTabsInfo info = new SysDbmsTabsInfo();
    info.setUuid(request.getParameter("uuid"));
    info = sysDbmsTabsInfoService.findOne(info);
    view.addObject("sysTableInfo", info);
    return view;
}


@RequestMapping(path = "/change", method = RequestMethod.POST)
public SysDbmsTabsInfo change(SysDbmsTabsInfoVo vo){
    logger.info("save", SysDbmsTabsInfoController.class);
    sysDbmsTabsInfoService.change(vo);
    return vo.getNow();
}


@RequestMapping(path = "/savev", method = RequestMethod.POST)
public String save(SysDbmsTabsInfoVo vo) throws SQLException{
    logger.info("savev", SysDbmsTabsInfoController.class);
    for (SysDbmsTabsInfo info : vo.getList()) {
        SysDbmsTabsInfo infot = new SysDbmsTabsInfo();
        infot.setTabsName(info.getTabsName());
        infot = sysDbmsTabsInfoService.findOne(infot);
        if (infot == null) {
            info.setDeleteFlag(0);
            info.setDissql(0);
            info.setCreateUser(vo.getUsername());
            info.setUpdateUser(vo.getUsername());
            sysDbmsTabsInfoService.savev(info);
        }
    }
    return "1";
}


@RequestMapping(path = "/updateSysTableInfo", method = RequestMethod.POST)
public String updateSysTableInfo(SysDbmsTabsInfoVo vo){
    logger.info("updateSysTableInfo", SysDbmsTabsInfoController.class);
    sysDbmsTabsInfoService.saveAll(vo.getList());
    return "1";
}


@RequestMapping(path = "/page", method = { RequestMethod.GET, RequestMethod.POST })
public Page<SysDbmsTabsInfo> page(SysDbmsTabsInfoVo vo){
    logger.info("page", SysDbmsTabsInfoController.class);
    return sysDbmsTabsInfoService.page(vo);
}


@RequestMapping(path = "/findAllBySysTableInfo", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllBySysTableInfo(SysDbmsTabsInfo sysDbmsTabsInfo){
    logger.info("findAll", SysDbmsTabsInfoController.class);
    return sysDbmsTabsInfoService.findAll(sysDbmsTabsInfo);
}


@RequestMapping(path = "/findAll", method = { RequestMethod.GET, RequestMethod.POST })
public List<SysDbmsTabsInfo> findAll(){
    logger.info("findAll", SysDbmsTabsInfoController.class);
    return sysDbmsTabsInfoService.findAll();
}


@RequestMapping(path = "/delete", method = RequestMethod.POST)
public String delete(SysDbmsTabsInfoVo vo){
    logger.info("delete", SysDbmsTabsInfoController.class);
    sysDbmsTabsInfoService.deleteAll(vo.getList());
    return "1";
}


@RequestMapping(path = "/findAllBySysTableInfoAndUsername", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllBySysTableInfoAndUsername(SysDbmsTabsInfo sysDbmsTabsInfo){
    logger.info("findAll", SysDbmsTabsInfoController.class);
    return sysDbmsTabsInfoService.findAllBySysTableInfoAndUsername(sysDbmsTabsInfo);
}


}