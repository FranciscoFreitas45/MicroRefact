package com.sobey.cmop.mvc.service.iaas;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.MonitorComputeDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.framework.utils.StringCommonUtils;
@Service
@Transactional(readOnly = true)
public class MonitorComputeServcie extends BaseSevcie{

 private  Logger logger;

@Resource
 private  MonitorComputeDao monitorComputeDao;


@Transactional(readOnly = false)
public void updateMonitorComputeToApply(MonitorCompute monitorCompute,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint){
    monitorCompute.setCpuWarn(cpuWarn);
    monitorCompute.setCpuCritical(cpuCritical);
    monitorCompute.setMemoryWarn(memoryWarn);
    monitorCompute.setMemoryCritical(memoryCritical);
    monitorCompute.setDiskWarn(diskWarn);
    monitorCompute.setDiskCritical(diskCritical);
    monitorCompute.setPingDelayWarn(pingDelayWarn);
    monitorCompute.setPingDelayCritical(pingDelayCritical);
    monitorCompute.setPingLossWarn(pingLossWarn);
    monitorCompute.setPingLossCritical(pingLossCritical);
    monitorCompute.setMaxProcessWarn(maxProcessWarn);
    monitorCompute.setMaxProcessCritical(maxProcessCritical);
    monitorCompute.setPort(port);
    monitorCompute.setProcess(process);
    monitorCompute.setMountPoint(mountPoint);
    monitorCompute.setIpAddress(ipAddress);
    this.saveOrUpdate(monitorCompute);
}


public MonitorCompute getMonitorCompute(Integer id){
    return monitorComputeDao.findOne(id);
}


@Transactional(readOnly = false)
public void deleteMonitorCompute(Integer id){
    monitorComputeDao.delete(id);
}


@Transactional(readOnly = false)
public void saveResourcesByMonitorCompute(Resources resources,Integer serviceTagId,String changeDescription,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    MonitorCompute monitorCompute = this.getMonitorCompute(resources.getServiceId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareMonitorCompute(resources, change, monitorCompute, ipAddress, cpuWarn, cpuCritical, memoryWarn, memoryCritical, pingLossWarn, pingLossCritical, diskWarn, diskCritical, pingDelayWarn, pingDelayCritical, maxProcessWarn, maxProcessCritical, port, process, mountPoint);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    monitorCompute.setCpuWarn(cpuWarn);
    monitorCompute.setCpuCritical(cpuCritical);
    monitorCompute.setMemoryWarn(memoryWarn);
    monitorCompute.setMemoryCritical(memoryCritical);
    monitorCompute.setDiskWarn(diskWarn);
    monitorCompute.setDiskCritical(diskCritical);
    monitorCompute.setPingDelayWarn(pingDelayWarn);
    monitorCompute.setPingDelayCritical(pingDelayCritical);
    monitorCompute.setPingLossWarn(pingLossWarn);
    monitorCompute.setPingLossCritical(pingLossCritical);
    monitorCompute.setMaxProcessWarn(maxProcessWarn);
    monitorCompute.setMaxProcessCritical(maxProcessCritical);
    monitorCompute.setPort(port);
    monitorCompute.setProcess(process);
    monitorCompute.setMountPoint(mountPoint);
    monitorCompute.setIpAddress(ipAddress);
    this.saveOrUpdate(monitorCompute);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


public List<String> wrapMonitorComputeParametToList(String str){
    if (StringUtils.isNotBlank(str)) {
        // 按","切割成数组后转换成List
        return Arrays.asList(StringUtils.split(str, ","));
    } else {
        // 返回一个只有""值的list
        List<String> list = new ArrayList<String>();
        list.add("");
        return list;
    }
}


@Transactional(readOnly = false)
public void saveMonitorComputeToApply(Apply apply,String[] computeIds,String[] cpuWarns,String[] cpuCriticals,String[] memoryWarns,String[] memoryCriticals,String[] pingLossWarns,String[] pingLossCriticals,String[] diskWarns,String[] diskCriticals,String[] pingDelayWarns,String[] pingDelayCriticals,String[] maxProcessWarns,String[] maxProcessCriticals,String[] ports,String[] processes,String[] mountPoints){
    if (computeIds != null) {
        for (int i = 0; i < computeIds.length; i++) {
            MonitorCompute monitorCompute = new MonitorCompute();
            monitorCompute.setApply(apply);
            monitorCompute.setIdentifier(comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger()));
            monitorCompute.setIpAddress(comm.computeService.getComputeItem(Integer.valueOf(computeIds[i])).getInnerIp());
            monitorCompute.setCpuWarn(cpuWarns[i]);
            monitorCompute.setCpuCritical(cpuCriticals[i]);
            monitorCompute.setMemoryWarn(memoryWarns[i]);
            monitorCompute.setMemoryCritical(memoryCriticals[i]);
            monitorCompute.setDiskWarn(diskWarns[i]);
            monitorCompute.setDiskCritical(diskCriticals[i]);
            monitorCompute.setPingLossWarn(pingLossWarns[i]);
            monitorCompute.setPingLossCritical(pingLossCriticals[i]);
            monitorCompute.setPingDelayWarn(pingDelayWarns[i]);
            monitorCompute.setPingDelayCritical(pingDelayCriticals[i]);
            monitorCompute.setMaxProcessWarn(maxProcessWarns[i]);
            monitorCompute.setMaxProcessCritical(maxProcessCriticals[i]);
            // 页面传递到后台的数据格式是类似: 1-2-3-,3-4-5-6-,7-8- .
            // 遍历后的字符串1-2-3-.而我们需要用","将监控端口,监控进程,挂载路径中的数据隔开.
            // 因此要将字符串中的'-'换成','
            monitorCompute.setPort(StringCommonUtils.replaceAndSubstringText((ports[i]), "-", ","));
            monitorCompute.setProcess(StringCommonUtils.replaceAndSubstringText((processes[i]), "-", ","));
            monitorCompute.setMountPoint(StringCommonUtils.replaceAndSubstringText((mountPoints[i]), "-", ","));
            this.saveOrUpdate(monitorCompute);
        }
    }
}


@Transactional(readOnly = false)
public MonitorCompute saveOrUpdate(MonitorCompute monitorCompute){
    return monitorComputeDao.save(monitorCompute);
}


}