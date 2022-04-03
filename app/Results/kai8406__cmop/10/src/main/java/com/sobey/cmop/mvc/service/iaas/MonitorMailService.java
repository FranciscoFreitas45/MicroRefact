package com.sobey.cmop.mvc.service.iaas;
 import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.dao.MonitorMailDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.MonitorMail;
import com.sobey.cmop.mvc.entity.User;
@Service
@Transactional(readOnly = true)
public class MonitorMailService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  MonitorMailDao monitorMailDao;


@Transactional(readOnly = false)
public void updateMonitorEmailToApply(Integer applyId,String[] monitorMails){
    // Step.1
    this.deleteMonitorMail(this.getMonitorMailByApplyList(applyId));
    Apply apply = comm.applyService.getApply(applyId);
    User user = comm.accountService.getCurrentUser();
    // Step.2
    for (int i = 0; i < monitorMails.length; i++) {
        MonitorMail monitorMail = new MonitorMail();
        monitorMail.setApply(apply);
        monitorMail.setEmail(monitorMails[i]);
        monitorMail.setUser(user);
        comm.monitorMailService.saveOrUpdate(monitorMail);
    }
}


public List<MonitorMail> getMonitorMailByApplyList(Integer applyId){
    return monitorMailDao.findByApplyId(applyId);
}


@Transactional(readOnly = false)
public void deleteMonitorMail(List<MonitorMail> monitorMails){
    monitorMailDao.delete(monitorMails);
}


@Transactional(readOnly = false)
public MonitorMail saveOrUpdate(MonitorMail monitorMail){
    return monitorMailDao.save(monitorMail);
}


}