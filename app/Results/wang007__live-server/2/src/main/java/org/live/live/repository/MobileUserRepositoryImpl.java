package org.live.live.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.live.vo.MobileUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class MobileUserRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;

 private String block;


public Page<MobileUserVo> find(PageRequest pageRequest,Map<String,Object> filter){
    StringBuilder _hql = new StringBuilder();
    _hql.append(hqlTemplate);
    if (filter.size() > 0) {
        _hql.append(" and ( ");
        _hql.append(block);
        _hql.append(" ) ");
    }
    String hql = super.xsqlConvertHql(_hql.toString(), filter);
    Page<MobileUserVo> page = super.find(pageRequest, hql, null);
    return page;
}


}