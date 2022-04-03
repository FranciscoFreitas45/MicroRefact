package org.gliderwiki.web.system.notification.service;
 import javax.annotation.Resource;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeAlarmInfo;
import org.springframework.stereotype.Service;
@Service("notifiationService")
public class NotifiationService {

@Resource(name = "entityService")
 private EntityService entityService;


public int getNewNotiCount(int userIdx){
    WeAlarmInfo weAlarmInfo = new WeAlarmInfo();
    weAlarmInfo.setWe_target_user_idx(userIdx);
    weAlarmInfo.setWe_read_yn(false);
    weAlarmInfo.setWe_use_yn("Y");
    return entityService.getCountEntity(weAlarmInfo);
}


}