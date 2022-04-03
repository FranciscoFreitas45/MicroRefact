package org.live.school.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.dictionary.entity.DictType;
import org.live.school.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Map;
public class DepartmentRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<Department> find(PageRequest pageRequest,Map<String,Object> filter){
    String hql = hqlTemplate;
    StringBuilder xsql = new StringBuilder();
    if (filter.size() > 0) {
        xsql.append(hqlTemplate);
        xsql.append(" where");
        xsql.append(block);
        hql = super.xsqlConvertHql(xsql.toString(), filter);
    }
    System.out.println("hql---> " + hql);
    Page<Department> page = super.find(pageRequest, hql, null);
    return page;
}


}