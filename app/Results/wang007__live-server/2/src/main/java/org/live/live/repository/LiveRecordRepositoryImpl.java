package org.live.live.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.live.vo.LiveRecordVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class LiveRecordRepositoryImpl extends BaseRepositoryImpl{

 final  String hqlTemplate;

 final  String xsqlBlock;


public Page<LiveRecordVo> find(PageRequest pageRequest,Map<String,Object> filter){
    StringBuilder _hql = new StringBuilder();
    _hql.append(hqlTemplate);
    if (filter.size() > 0) {
        _hql.append(" where ");
        _hql.append(xsqlBlock);
    }
    String hql = super.xsqlConvertHql(_hql.toString(), filter);
    Page<LiveRecordVo> page = super.find(pageRequest, hql, null);
    return page;
}


}