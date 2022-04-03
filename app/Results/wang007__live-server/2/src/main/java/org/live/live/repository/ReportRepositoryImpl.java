package org.live.live.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.live.controller.ReportController;
import org.live.live.entity.Report;
import org.live.live.vo.ReportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Map;
public class ReportRepositoryImpl extends BaseRepositoryImpl{

 private  Logger LOGGER;

 private  String FIND_REPORT_XHQL;

 private  String STATEMENT_BLOCK;


public Page<ReportVo> findReports(PageRequest pageRequest,Map<String,Object> filter,boolean handleType){
    StringBuilder xSqlBuilder = new StringBuilder();
    xSqlBuilder.append(FIND_REPORT_XHQL);
    if (filter.size() > 0) {
        xSqlBuilder.append(" and (");
        xSqlBuilder.append(STATEMENT_BLOCK);
        xSqlBuilder.append(")");
    }
    String hql = this.xsqlConvertHql(xSqlBuilder.toString(), filter);
    LOGGER.debug(hql);
    return this.find(pageRequest, hql, new Object[] { handleType });
}


}