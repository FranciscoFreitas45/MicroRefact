package org.live.live.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.live.vo.AnchorVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.HashMap;
import java.util.Map;
public class AnchorRepositoryImpl extends BaseRepositoryImpl{

 private  String FIND_ANCHOR_XSQL;


public Page<AnchorVo> findAnchors(PageRequest page,String searchStr){
    Map<String, Object> filter = new HashMap<>(3);
    filter.put("account", searchStr);
    filter.put("nickname", searchStr);
    filter.put("realName", searchStr);
    String hql = this.xsqlConvertHql(FIND_ANCHOR_XSQL, filter);
    hql = hql.replace("where ()", "").replace("( or", "(");
    Page<AnchorVo> pageResult = this.find(page, hql, null);
    return pageResult;
}


}