package org.live.school.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.school.entity.Grade;
import org.live.school.vo.GradeVo;
import org.live.school.vo.MajorVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class GradeRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<GradeVo> find(PageRequest pageRequest,Map<String,Object> filter){
    StringBuilder _hql = new StringBuilder();
    _hql.append(hqlTemplate);
    if (filter.size() > 0) {
        _hql.append(" and ( ");
        _hql.append(block);
        _hql.append(" )");
    }
    String hql = super.xsqlConvertHql(_hql.toString(), filter);
    System.out.println("hql---> " + hql);
    Page<GradeVo> page = super.find(pageRequest, hql, null);
    return page;
}


}