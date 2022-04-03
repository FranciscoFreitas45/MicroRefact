package com.sobey.cmop.mvc.service.iaas;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.MonitorMail;
import com.sobey.cmop.mvc.entity.MonitorPhone;
import com.sobey.cmop.mvc.entity.User;
@Service
@Transactional(readOnly = true)
public class MonitorServcie extends BaseSevcie{

 private  Logger logger;


@Transactional(readOnly = false)
public void saveMonitorToApply(Apply apply,String[] monitorMails,String[] monitorPhones,String[] elbIds,String[] computeIds,String[] cpuWarns,String[] cpuCriticals,String[] memoryWarns,String[] memoryCriticals,String[] pingLossWarns,String[] pingLossCriticals,String[] diskWarns,String[] diskCriticals,String[] pingDelayWarns,String[] pingDelayCriticals,String[] maxProcessWarns,String[] maxProcessCriticals,String[] ports,String[] processes,String[] mountPoints){
    // Step1. 创建一个服务申请Apply
    comm.applyService.saveApplyByServiceType(apply, ApplyConstant.ServiceType.监控.toInteger());
    // Step2. 创建邮件和电话监控列表
    User user = comm.accountService.getCurrentUser();
    for (int i = 0; i < monitorMails.length; i++) {
        MonitorMail monitorMail = new MonitorMail();
        monitorMail.setApply(apply);
        monitorMail.setUser(user);
        monitorMail.setEmail(monitorMails[i]);
        comm.monitorMailService.saveOrUpdate(monitorMail);
    }
    for (int i = 0; i < monitorPhones.length; i++) {
        MonitorPhone monitorPhone = new MonitorPhone();
        monitorPhone.setApply(apply);
        monitorPhone.setUser(user);
        monitorPhone.setTelephone(monitorPhones[i]);
        comm.monitorPhoneService.saveOrUpdate(monitorPhone);
    }
    // Step3.
    // 创建ELB监控
    comm.monitorElbServcie.saveMonitorElbToApply(apply, elbIds);
    // 创建实例Compute监控
    comm.monitorComputeServcie.saveMonitorComputeToApply(apply, computeIds, cpuWarns, cpuCriticals, memoryWarns, memoryCriticals, pingLossWarns, pingLossCriticals, diskWarns, diskCriticals, pingDelayWarns, pingDelayCriticals, maxProcessWarns, maxProcessCriticals, ports, processes, mountPoints);
}


}