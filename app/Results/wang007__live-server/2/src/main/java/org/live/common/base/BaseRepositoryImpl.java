package org.live.common.base;
 import com.mool.xsqlbuilder.XsqlBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class BaseRepositoryImpl {

@PersistenceContext
 private  EntityManager em;

 private  XsqlBuilder builder;

public BaseRepositoryImpl() {
    this.builder = new XsqlBuilder();
}
public String getCountQl(String ql){
    int fromIndex = ql.indexOf("from ");
    fromIndex = fromIndex == -1 ? ql.indexOf("FROM ") : fromIndex;
    // 截取from后的语句
    String hqlFrom = ql.substring(fromIndex);
    return "select count(*) " + hqlFrom;
}


public String xsqlConvertSql(String xsql,Map<String,Object> filter){
    return getBuilder().generateSql(xsql, filter).getXsql();
}


@SuppressWarnings("unchecked")
public List<T> findBySql(String sql,Object[] params,Class<T> resultClass){
    Query query = getEntityManager().createNativeQuery(sql);
    query.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(resultClass));
    if (params != null) {
        for (int i = 0, length = params.length; i < length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    return (List<T>) query.getResultList();
}


public XsqlBuilder getBuilder(){
    return this.builder;
}


public String xsqlConvertHql(String xsql,Map<String,Object> filter){
    return getBuilder().generateHql(xsql, filter).getXsql();
}


@SuppressWarnings("unchecked")
public List<T> find(String hql,Object[] params){
    Query query = getEntityManager().createQuery(hql);
    if (params != null) {
        for (int i = 0, length = params.length; i < length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    return (List<T>) query.getResultList();
}


public long count(String hql,Object[] params){
    Query query = getEntityManager().createQuery(this.getCountQl(hql));
    if (params != null) {
        for (int i = 0, length = params.length; i < length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    return (long) query.getSingleResult();
}


public String createOrderQl(Pageable pageable,String qlString){
    if (pageable != null && pageable.getSort() != null) {
        if (!StringUtils.containsIgnoreCase(qlString, "order by")) {
            // 查询中有排序的话，就不再构建
            StringBuilder qlStringSb = new StringBuilder(qlString);
            Sort sort = pageable.getSort();
            Iterator<Sort.Order> iter = sort.iterator();
            boolean flag = true;
            while (iter.hasNext()) {
                Sort.Order order = iter.next();
                String property = order.getProperty();
                String strDirection = order.getDirection().name();
                if (flag) {
                    qlStringSb.append(" ORDER BY ");
                    flag = false;
                }
                qlStringSb.append(property).append(" ").append(strDirection).append(",");
            }
            qlString = qlStringSb.substring(0, qlStringSb.length() - 1);
        }
    }
    return qlString;
}


public EntityManager getEntityManager(){
    return this.em;
}


public long countBySql(String sql,Object[] params){
    Query query = getEntityManager().createNativeQuery(this.getCountQl(sql));
    if (params != null) {
        for (int i = 0, length = params.length; i < length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    BigInteger count = (BigInteger) query.getSingleResult();
    return count.longValue();
}


}