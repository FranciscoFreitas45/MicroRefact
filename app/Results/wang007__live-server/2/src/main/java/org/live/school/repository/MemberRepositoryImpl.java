package org.live.school.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.school.vo.MemberVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class MemberRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<MemberVo> find(PageRequest pageRequest,Map<String,Object> filter){
    StringBuilder _hql = new StringBuilder();
    _hql.append(hqlTemplate);
    if (filter.size() > 0) {
        _hql.append(" where ");
        _hql.append(block);
    }
    String hql = super.xsqlConvertHql(_hql.toString(), filter);
    System.out.println("hql---> " + hql);
    Page<MemberVo> page = super.find(pageRequest, hql, null);
    return page;
}


}