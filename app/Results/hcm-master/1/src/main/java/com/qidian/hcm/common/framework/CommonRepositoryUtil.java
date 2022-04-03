package com.qidian.hcm.common.framework;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Slf4j
@Component
public class CommonRepositoryUtil {

 private  String PAGE_SIZE;

@Autowired
 private  EntityManager entityManager;


public int nativeCountResult(String querySql,String countSql,Set<Map.Entry<String,Object>> entrySet){
    String countQuerySql = countSql;
    if (StringUtils.isEmpty(countSql)) {
        countQuerySql = "select count(*) from ( " + querySql + " ) _t_c";
    }
    log.info("执行sql：{}", countQuerySql);
    Query query = entityManager.createNativeQuery(countQuerySql);
    if (null != entrySet) {
        for (Map.Entry<String, Object> entry : entrySet) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
    Object obj = query.getSingleResult();
    return Integer.parseInt(obj.toString());
}


public int getRowsTotal(int rowCount,Pageable pager){
    return rowCount % pager.getPageSize() == 0 ? rowCount / pager.getPageSize() : rowCount / pager.getPageSize() + 1;
}


public Page<T> pageByNative(Class clazz,String querySql,String countSql,Map<String,Object> params,int pageNo,int pageSize){
    Set<Map.Entry<String, Object>> entrySet = null;
    if (null != params) {
        entrySet = params.entrySet();
    }
    int count = nativeCountResult(querySql, countSql, entrySet);
    if (count > 0) {
        Pageable pager = PageRequest.of(pageNo - 1 < 0 ? 0 : pageNo - 1, pageSize);
        int page = pager.getPageNumber();
        int pageTotal = getRowsTotal(count, pager);
        if (page > pageTotal) {
            page = pageTotal;
        }
        long startIndex = (long) page * pageSize;
        String sqlString = "select * from (" + querySql + ")  _t_c  limit :startIndex,:pageSize";
        Query query = entityManager.createNativeQuery(sqlString, clazz);
        query.setParameter("startIndex", startIndex).setParameter(PAGE_SIZE, pageSize);
        if (null != params) {
            for (Map.Entry<String, Object> entry : entrySet) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        List list = query.getResultList();
        return (Page<T>) new PageImpl(list, pager, count);
    }
    return (Page<T>) new PageImpl(Collections.emptyList());
}


}