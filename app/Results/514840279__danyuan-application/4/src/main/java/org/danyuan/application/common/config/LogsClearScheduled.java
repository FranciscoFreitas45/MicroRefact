package org.danyuan.application.common.config;
 import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.sql.DataSource;
import org.danyuan.application.common.utils.files.FileDelete;
import org.danyuan.application.dbms.tabs.dao.SysDbmsAdviMessInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.danyuan.application.dbms.tabs.service.ZhcxAdviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.Interface.SysDbmsAdviMessInfoDao;
@Component
public class LogsClearScheduled {

 private  Logger logger;

@Autowired
 private JdbcTemplate jdbcTemplate;

@Autowired
 private SysDbmsTabsColsInfoDao sysDbmsTabsColsInfoDao;

@Autowired
 private SysDbmsTabsJdbcInfoDao sysDbmsTabsJdbcInfoDao;

@Autowired
 private SysDbmsAdviMessInfoDao sysDbmsAdviMessInfoDao;

@Autowired
 private SysDbmsTabsInfoDao sysDbmsTabsInfoDao;

 public  String OUTPUTFILE;

@Autowired
 private MultiDatasourceConfig multiDatasourceConfig;

 private  SimpleDateFormat dateFormat;

 private long nd;

 private long nh;


public long getDatePoor(Date endDate,Date nowDate){
    long nd = 1000 * 24 * 60 * 60;
    // long ns = 1000;
    // 获得两个时间的毫秒时间差异
    long diff = endDate.getTime() - nowDate.getTime();
    // 计算差多少天
    return diff / nd;
}


@Scheduled(cron = "0 0 8-18/1 * * *")
public void zhcxConfix() throws SQLException{
    sysDbmsAdviMessInfoDao.deleteAllInBatch();
    List<SysDbmsTabsInfo> list = sysDbmsTabsInfoDao.findByAddrUuidIsNotNullAndUpdateTimeGreaterThan();
    Map<String, DataSource> multiDatasource = multiDatasourceConfig.multiDatasource();
    for (SysDbmsTabsInfo sysZhcxTab : list) {
        System.err.println(sysZhcxTab.getUpdateTime());
        // if (getDatePoor(date, sysZhcxTab.getUpdateTime()) > 1) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        logger.info("当前时间：" + dateFormat.format(calendar.getTime()) + " , " + sysZhcxTab.getTabsName());
        if ("oracle".equals(sysZhcxTab.getDbType().toLowerCase())) {
            // if (calendar.get(Calendar.HOUR_OF_DAY) <= 8 || calendar.get(Calendar.HOUR_OF_DAY) > 16) {
            // 表配置比较建议修正 (表修改，表配置修改)
            ZhcxAdviceService.startConfixOracleTableConfig(sysZhcxTab, multiDatasource, sysDbmsAdviMessInfoDao, jdbcTemplate);
            SysDbmsTabsColsInfo info = new SysDbmsTabsColsInfo();
            info.setTabsUuid(sysZhcxTab.getUuid());
            Example<SysDbmsTabsColsInfo> example = Example.of(info);
            List<SysDbmsTabsColsInfo> colList = sysDbmsTabsColsInfoDao.findAll(example);
            // 列配置比较建议修正(列修改，列配置修改,列统计配置修改，列长度修改)
            ZhcxAdviceService.startConfixOracleTableColumnsConfig(sysZhcxTab, multiDatasource, sysDbmsAdviMessInfoDao, jdbcTemplate, colList);
        // }
        } else if ("mysql".equals(sysZhcxTab.getDbType().toLowerCase())) {
            // 表配置比较建议修正 (表修改，表配置修改)
            ZhcxAdviceService.startConfixMysqlTableConfig(sysZhcxTab, multiDatasource, sysDbmsAdviMessInfoDao, jdbcTemplate);
            SysDbmsTabsColsInfo info = new SysDbmsTabsColsInfo();
            info.setTabsUuid(sysZhcxTab.getUuid());
            Example<SysDbmsTabsColsInfo> example = Example.of(info);
            List<SysDbmsTabsColsInfo> colList = sysDbmsTabsColsInfoDao.findAll(example);
            // 列配置比较建议修正(列修改，列配置修改,列统计配置修改，列长度修改)
            ZhcxAdviceService.startConfixMysqlTableColumnsConfig(sysZhcxTab, multiDatasource, sysDbmsAdviMessInfoDao, jdbcTemplate, colList);
        }
    // }
    }
    try {
        multiDatasourceConfig.destroyMultiDatasource(multiDatasource);
    } catch (SQLException e) {
        logger.error(e.getMessage(), LogsClearScheduled.class);
    }
    logger.info("本次处理配置表信息执行完毕！", LogsClearScheduled.class);
}


@Scheduled(cron = "1 0 0 * * *")
public void delete(){
    String sql = "DELETE FROM sys_comn_logs WHERE TIMESTAMPDIFF(DAY,create_time,NOW())>30";
    jdbcTemplate.update(sql);
    File file = new File(OUTPUTFILE);
    File[] files = file.listFiles();
    for (File file2 : files) {
        if (file2.isDirectory()) {
            FileDelete.delFolder(file2.getAbsolutePath());
        } else {
            file2.delete();
        }
    }
}


}