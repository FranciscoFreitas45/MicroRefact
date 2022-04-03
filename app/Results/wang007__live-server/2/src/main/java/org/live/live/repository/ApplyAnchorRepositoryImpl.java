package org.live.live.repository;
 import org.apache.commons.lang3.StringUtils;
import org.live.common.base.BaseRepositoryImpl;
import org.live.live.vo.ApplyVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.HashMap;
import java.util.Map;
public class ApplyAnchorRepositoryImpl extends BaseRepositoryImpl{

 private  String FIND_APPLYS_NOT_AUDIT_XHQL;

 private  String FIND_APPLYS_AUDITED_XHQL;


public Page<ApplyVo> findApplys(PageRequest page,String searchStr,boolean auditFlag){
    Map<String, Object> filter = new HashMap<>(4);
    if (StringUtils.isNotEmpty(searchStr)) {
        filter.put("account", searchStr);
        filter.put("nickname", searchStr);
        filter.put("realName", searchStr);
    }
    String hql = this.xsqlConvertHql(auditFlag ? FIND_APPLYS_AUDITED_XHQL : FIND_APPLYS_NOT_AUDIT_XHQL, filter);
    hql = hql.replace("()", "");
    return this.find(page, hql, null);
}


}