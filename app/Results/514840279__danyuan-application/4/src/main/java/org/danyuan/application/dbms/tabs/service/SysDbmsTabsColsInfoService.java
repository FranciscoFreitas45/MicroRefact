package org.danyuan.application.dbms.tabs.service;
 import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.common.config.MultiDatasourceConfig;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsJdbcInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;
@Service("sysDbmsTabsColsInfoService")
public class SysDbmsTabsColsInfoService extends BaseServiceImpl<SysDbmsTabsColsInfo>implements BaseService<SysDbmsTabsColsInfo>{

 private  Logger logger;

@Autowired
 private  SysDbmsTabsColsInfoDao sysDbmsTabsColsInfoDao;

@Autowired
 private  SysDbmsTabsInfoDao sysDbmsTabsInfoDao;

@Autowired
 private  SysDbmsTabsInfoService sysDbmsTabsInfoService;

@Autowired
 private  SysDbmsTabsJdbcInfoService sysDbmsTabsJdbcInfoService;

@Autowired
 private JdbcTemplate jdbcTemplate;

@Autowired
 private MultiDatasourceConfig multiDatasourceConfig;


public List<SysDbmsTabsColsInfo> pagev(String uuid) throws SQLException{
    SysDbmsTabsInfo tabs = new SysDbmsTabsInfo();
    tabs.setUuid(uuid);
    tabs = sysDbmsTabsInfoService.findOne(tabs);
    SysDbmsTabsJdbcInfo info = sysDbmsTabsJdbcInfoService.findById(tabs.getJdbcUuid());
    // Map<String, DataSource> multiDatasource = multiDatasourceConfig.multiDatasource();
    Connection connection = multiDatasourceConfig.getConnection(info.getUuid());
    Statement statement = connection.createStatement();
    RowMapperResultSetExtractor<SysDbmsTabsColsInfo> rse = new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(SysDbmsTabsColsInfo.class));
    // JdbcTemplate jdbcTemplate = new JdbcTemplate(connection);
    // NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    StringBuilder pageSql = new StringBuilder();
    if (info != null && info.getType().equals("mysql")) {
        pageSql.append(" SELECT  ");
        pageSql.append("   UUID() AS 'uuid' ");
        pageSql.append("   ,'" + tabs.getUuid() + "' as TABS_UUID");
        pageSql.append("   ,CONCAT(t.`TABLE_SCHEMA`, '.', t.`TABLE_NAME`) AS TABS_NAME ");
        pageSql.append("   ,t.`COLUMN_NAME` AS COLS_NAME ");
        pageSql.append("   ,t.`ORDINAL_POSITION` AS COLS_ORDER ");
        pageSql.append("   ,t.`DATA_TYPE` AS COLS_TYPE ");
        pageSql.append("   ,t.`CHARACTER_MAXIMUM_LENGTH` AS COLS_LENGTH ");
        pageSql.append("   ,t.`COLUMN_COMMENT` AS COLS_DESC  ");
        // pageSql.append(" ,ifnull(t.NUMERIC_PRECISION,t.DATETIME_PRECISION ) as DATA_PRECISION ");
        pageSql.append("   ,t.NUMERIC_PRECISION as DATA_PRECISION  ");
        pageSql.append("   ,t.NUMERIC_SCALE as DATA_SCALE ");
        pageSql.append("   ,t.IS_NULLABLE as NULLABLESTR  ");
        pageSql.append("   ,1 as DIME_FLAG  ");
        pageSql.append("   ,0 as USEFUL  ");
        pageSql.append("   ,t.`COLUMN_DEFAULT` AS COLS_DEFAULT  ");
        pageSql.append(" FROM  `information_schema`.`COLUMNS` t  ");
        pageSql.append(" WHERE CONCAT(t.`TABLE_SCHEMA`,'.',t.`TABLE_NAME`) = '" + tabs.getTabsName() + "'  ");
        pageSql.append(" and t.`COLUMN_NAME` not in( ");
        pageSql.append(" 		select c.cols_name from application.sys_dbms_tabs_cols_info c ");
        pageSql.append(" 		where c.tabs_uuid='" + tabs.getUuid() + "'");
        pageSql.append(" 	)");
        pageSql.append(" ORDER BY t.`ORDINAL_POSITION`  ");
        logger.debug(pageSql.toString(), SysDbmsTabsColsInfoService.class);
        List<SysDbmsTabsColsInfo> list = rse.extractData(statement.executeQuery(pageSql.toString()));
        statement.close();
        connection.close();
        return list;
    } else {
        pageSql.append(" SELECT  ");
        pageSql.append("   sys_guid() AS uuid ");
        pageSql.append("   ,'" + tabs.getUuid() + "' as TABS_UUID");
        pageSql.append("   ,t.OWNER|| '.'|| t. TABLE_NAME AS TABS_NAME ");
        pageSql.append("   ,t.COLUMN_NAME AS COLS_NAME ");
        pageSql.append("   ,t.COLUMN_ID  AS COLS_ORDER ");
        pageSql.append("   ,t.DATA_TYPE AS COLS_TYPE ");
        pageSql.append("   ,t.DATA_LENGTH  AS COLS_LENGTH");
        pageSql.append("   ,t.DATA_PRECISION");
        pageSql.append("   ,t.NULLABLE as NULLABLESTR");
        pageSql.append("   ,t.DATA_SCALE ");
        pageSql.append("   ,1 as DIME_FLAG  ");
        pageSql.append("   ,0 as USEFUL  ");
        pageSql.append("   ,d.DATA_DEFAULT  AS COLS_DEFAULT");
        pageSql.append("   ,c.COMMENTS AS COLS_DESC  ");
        pageSql.append(" FROM  all_tab_columns t  ");
        pageSql.append(" inner join all_col_comments c on t.OWNER = c.OWNER and t.TABLE_NAME = c.TABLE_NAME and t.COLUMN_NAME = c.COLUMN_NAME ");
        pageSql.append(" inner join USER_TAB_COLUMNS d on d.TABLE_NAME = t.TABLE_NAME and t.COLUMN_NAME = d.COLUMN_NAME ");
        pageSql.append(" where t.OWNER|| '.'|| t. TABLE_NAME  = '" + tabs.getTabsName() + "'  ");
        pageSql.append(" ORDER BY t.COLUMN_ID  ");
        // List<SysDbmsTabsColsInfo> list = template.getJdbcOperations().query(pageSql.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsColsInfo.class));
        // multiDatasourceConfig.destroyMultiDatasource(multiDatasource);
        logger.debug(pageSql.toString(), SysDbmsTabsColsInfoService.class);
        // 
        List<SysDbmsTabsColsInfo> list = rse.extractData(statement.executeQuery(pageSql.toString()));
        statement.close();
        connection.close();
        if (list == null) {
            return list;
        }
        // 数据2
        List<SysDbmsTabsColsInfo> list2 = findAll(new SysDbmsTabsColsInfo(tabs.getUuid()));
        if (list2 == null) {
            return list;
        } else {
            for (SysDbmsTabsColsInfo sysDbmsTabsColsInfo : list2) {
                for (int i = 0; i < list.size(); i++) {
                    if (sysDbmsTabsColsInfo.getColsName().equals(list.get(i).getColsName())) {
                        list.remove(i);
                    }
                }
            }
            return list;
        }
    }
}


@Override
public void saveAll(List<SysDbmsTabsColsInfo> list){
    for (SysDbmsTabsColsInfo sysDbmsTabsColsInfo : list) {
        if (sysDbmsTabsColsInfo.getUuid() == null) {
            sysDbmsTabsColsInfo.setUuid(UUID.randomUUID().toString());
        }
        change(sysDbmsTabsColsInfo);
    }
}


public PageRequest buildPageRequest(int pageNumber,int pageSize,Sort sort){
    return PageRequest.of(pageNumber - 1, pageSize, sort);
}


public void change(SysDbmsTabsColsInfo info){
    try {
        SysDbmsTabsInfo tab = sysDbmsTabsInfoDao.findById(info.getTabsUuid()).get();
        Optional<SysDbmsTabsColsInfo> old = sysDbmsTabsColsInfoDao.findById(info.getUuid());
        if (old != null && old.isPresent()) {
            String sql = "alter table " + tab.getTabsName() + " CHANGE " + old.get().getColsName() + " " + info.getColsName() + " " + info.getColsType() + "(" + info.getColsLength() + ")";
            jdbcTemplate.execute(sql);
        } else {
            String sql = "alter table " + tab.getTabsName() + " add " + info.getColsName() + " " + info.getColsType() + "(" + info.getColsLength() + ")";
            jdbcTemplate.execute(sql);
        }
    } finally {
        sysDbmsTabsColsInfoDao.save(info);
    }
}


@Override
public void deleteAll(List<SysDbmsTabsColsInfo> list){
    sysDbmsTabsColsInfoDao.deleteAll(list);
}


public Page<SysDbmsTabsColsInfo> findAllByTableUuid(int pageNumber,int pageSize,String searchText,String tableUuid){
    logger.info(tableUuid, SysDbmsTabsColsInfoService.class);
    // Page<SysDbmsTabsColsInfo> list = sysDbmsTabsColsInfoDao.findAllByTableUuid(tableUuid);
    SysDbmsTabsColsInfo info = new SysDbmsTabsColsInfo();
    info.setTabsUuid(tableUuid);
    Example<SysDbmsTabsColsInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.ASC, "colsOrder"));
    PageRequest request = this.buildPageRequest(pageNumber, pageSize, sort);
    Page<SysDbmsTabsColsInfo> sourceCodes = sysDbmsTabsColsInfoDao.findAll(example, request);
    return sourceCodes;
}


public void saveSysDbmsTabsColsInfo(List<SysDbmsTabsColsInfo> list){
    sysDbmsTabsColsInfoDao.saveAll(list);
}


public void deleteSysDbmsTabsColsInfo(List<SysDbmsTabsColsInfo> list){
    Optional<SysDbmsTabsInfo> tab = sysDbmsTabsInfoDao.findById(list.get(0).getTabsUuid());
    if (tab.isPresent()) {
        for (SysDbmsTabsColsInfo SysDbmsTabsColsInfo : list) {
            try {
                // alter table user DROP COLUMN new2;
                String sql = "alter table " + tab.get().getTabsName() + " DROP COLUMN " + SysDbmsTabsColsInfo.getColsName();
                jdbcTemplate.execute(sql);
            } finally {
                sysDbmsTabsColsInfoDao.delete(SysDbmsTabsColsInfo);
            }
        }
    }
}


@Override
public Page<SysDbmsTabsColsInfo> page(Pagination<SysDbmsTabsColsInfo> vo){
    Order order;
    if (vo.getSortName() != null) {
        if (vo.getSortOrder().equals("desc")) {
            order = Order.desc(vo.getSortName());
        } else {
            order = Order.asc(vo.getSortName());
        }
    } else {
        order = new Order(Direction.DESC, "createTime");
    }
    if (vo.getInfo() == null) {
        vo.setInfo(new SysDbmsTabsColsInfo());
    }
    Example<SysDbmsTabsColsInfo> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(order);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysDbmsTabsColsInfo> page = sysDbmsTabsColsInfoDao.findAll(example, request);
    return page;
}


public List<SysDbmsTabsColsInfo> findAllBySysDbmsTabsColsInfo(SysDbmsTabsColsInfo info){
    Example<SysDbmsTabsColsInfo> example = Example.of(info);
    return sysDbmsTabsColsInfoDao.findAll(example);
}


@Override
public List<SysDbmsTabsColsInfo> findAll(SysDbmsTabsColsInfo info){
    Sort sort = Sort.by(Order.asc("colsOrder"));
    Example<SysDbmsTabsColsInfo> example = Example.of(info);
    return sysDbmsTabsColsInfoDao.findAll(example, sort);
}


}