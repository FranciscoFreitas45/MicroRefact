package org.live.school.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.school.vo.MajorVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class MajorRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<MajorVo> find(PageRequest pageRequest,Map<String,Object> filter){
    StringBuilder _hql = new StringBuilder();
    _hql.append(hqlTemplate);
    if (filter.size() > 0) {
        _hql.append(" and ( ");
        _hql.append(block);
        _hql.append(" )");
    }
    String hql = super.xsqlConvertHql(_hql.toString(), filter);
    System.out.println("hql---> " + hql);
    Page<MajorVo> page = super.find(pageRequest, hql, null);
    return page;
}


}