package org.live.dictionary.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.dictionary.vo.DictionaryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
public class DictRepositoryImpl extends BaseRepositoryImpl{

 private String hqlTemplate;


public Page<DictionaryVo> find(PageRequest pageRequest,Map<String,Object> filter){
    String hql = super.xsqlConvertHql(hqlTemplate.toString(), filter);
    System.out.println("hql---> " + hql);
    Page<DictionaryVo> page = super.find(pageRequest, hql, null);
    return page;
}


}