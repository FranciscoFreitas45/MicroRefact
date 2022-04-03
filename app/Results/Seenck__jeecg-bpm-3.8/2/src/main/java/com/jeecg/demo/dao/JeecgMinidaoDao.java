package com.jeecg.demo.dao;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation;
import com.jeecg.demo.entity.JeecgDemoEntity;
import com.jeecg.demo.entity.JeecgLogReport;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
@MiniDao
public interface JeecgMinidaoDao {


@Sql("select id,name,pid from t_s_region order by name_en")
public List<Map<String,String>> getAllRegions()
;

@Arguments("log")
@ResultType(JeecgLogReport.class)
public List<JeecgLogReport> getLogReportData(JeecgLogReport log)
;

@Sql("select sum(salary) from jeecg_demo")
public Integer getSumSalary()
;

@Arguments("pid")
@Sql("select id,name,pid from t_s_region where pid=:pid order by name_en")
public List<Map<String,String>> getProCity(String pid)
;

@ResultType(JeecgDemoEntity.class)
public MiniDaoPage<JeecgDemoEntity> getAllEntities(JeecgDemoEntity jeecgDemo,int page,int rows,String sort,String order,String authSql)
;

@Sql("select count(*) from jeecg_demo")
public Integer getCount()
;

@Arguments("id")
@ResultType(String.class)
@Sql("select org_code from t_s_depart where id=:id")
public java.lang.String getOrgCode(String id)
;

@Arguments("log")
public List<Map<String,Object>> getLogChartData(JeecgLogReport log)
;

}