package org.live.dictionary.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.dictionary.entity.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class DictTypeRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<DictType> find(PageRequest pageRequest,Map<String,Object> filter){
    String hql = hqlTemplate;
    StringBuilder xsql = new StringBuilder();
    if (filter.size() > 0) {
        xsql.append(hqlTemplate);
        xsql.append(" where");
        xsql.append(block);
        hql = super.xsqlConvertHql(xsql.toString(), filter);
    }
    System.out.println("hql---> " + hql);
    Page<DictType> page = super.find(pageRequest, hql, null);
    return page;
}


}