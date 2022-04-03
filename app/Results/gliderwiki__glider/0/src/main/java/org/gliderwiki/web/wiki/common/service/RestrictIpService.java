package org.gliderwiki.web.wiki.common.service;
 import java.util.List;
import javax.annotation.Resource;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeAccess;
import com.google.common.collect.Lists;
public class RestrictIpService {

@Resource(name = "entityService")
 private EntityService entityService;


public List<WeAccess> getRestrictIpList(){
    List<WeAccess> restrictIpList = Lists.newArrayList();
    try {
        restrictIpList = entityService.getListEntity(new WeAccess());
    } catch (Throwable e) {
        throw new GliderwikiException("차단IP목록 가져오는 도중 에러 발생함!", e);
    }
    return restrictIpList;
}


}