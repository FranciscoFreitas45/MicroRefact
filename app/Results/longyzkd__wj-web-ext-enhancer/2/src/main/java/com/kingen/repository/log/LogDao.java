package com.kingen.repository.log;
 import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.kingen.bean.Log;
import com.kingen.bean.SysOrgMenu;
import com.kingen.bean.SysOrgMenuId;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
@Component
public class LogDao extends CommonDao<Log, String>{


public List<Log> findLogs(Log vo){
    Map<String, Object> parameter = Maps.newHashMap();
    // 超级管理员不在页面显示
    StringBuilder hql = new StringBuilder("from Log where 1=1 ");
    if (vo != null && !StringUtils.isBlank(vo.getUserAgent())) {
        hql.append(" and userAgent like :p1");
        parameter.put("p1", "%" + vo.getUserAgent() + "%");
    }
    if (vo != null && !StringUtils.isBlank(vo.getFromDate())) {
        hql.append(" and createDate >:p2");
        parameter.put("p2", vo.getFromDate());
    }
    if (vo != null && !StringUtils.isBlank(vo.getToDate())) {
        hql.append(" and createDate <= :p3");
        parameter.put("p3", vo.getToDate());
    }
    hql.append(" order by createDate desc");
    return find(hql.toString(), parameter);
}


}