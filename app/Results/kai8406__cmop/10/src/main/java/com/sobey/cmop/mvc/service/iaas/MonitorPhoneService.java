package com.sobey.cmop.mvc.service.iaas;
 import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.dao.MonitorPhoneDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.MonitorPhone;
import com.sobey.cmop.mvc.entity.User;
@Service
@Transactional(readOnly = true)
public class MonitorPhoneService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  MonitorPhoneDao monitorPhoneDao;


public List<MonitorPhone> getMonitorPhoneByApplyList(Integer applyId){
    return monitorPhoneDao.findByApplyId(applyId);
}


@Transactional(readOnly = false)
public MonitorPhone saveOrUpdate(MonitorPhone monitorPhone){
    return monitorPhoneDao.save(monitorPhone);
}


@Transactional(readOnly = false)
public void deleteMonitorPhone(List<MonitorPhone> monitorPhones){
    monitorPhoneDao.delete(monitorPhones);
}


@Transactional(readOnly = false)
public void updateMonitorPhoneToApply(Integer applyId,String[] monitorPhones){
    // Step.1
    this.deleteMonitorPhone(this.getMonitorPhoneByApplyList(applyId));
    Apply apply = comm.applyService.getApply(applyId);
    User user = comm.accountService.getCurrentUser();
    // Step.2
    for (int i = 0; i < monitorPhones.length; i++) {
        MonitorPhone monitorPhone = new MonitorPhone();
        monitorPhone.setApply(apply);
        monitorPhone.setUser(user);
        monitorPhone.setTelephone(monitorPhones[i]);
        this.saveOrUpdate(monitorPhone);
    }
}


}