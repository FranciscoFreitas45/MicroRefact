package com.gbcom.system.service;
 import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Ps;
import com.gbcom.system.common.CpuInfoBean;
import com.gbcom.system.common.MemInfoBean;
import com.gbcom.system.common.ProInfoBean;
public class SysInfoSington {

 private  Logger LOG;

 private  SysInfoSington instance;

private SysInfoSington() {
}
public boolean testDatabase(){
    try {
        // long cpenum = ((ApDeviceService)SpringUtils.getBean("apDeviceService")).getAllCounts();
        return true;
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        return false;
    }
}


public List<CpuInfoBean> getCpuInfo(){
    List<CpuInfoBean> cpuInfos = new ArrayList<CpuInfoBean>();
    Sigar sigar = new Sigar();
    CpuPerc[] cpuList = sigar.getCpuPercList();
    for (int i = 0; i < cpuList.length; i++) {
        CpuInfoBean cpuInfo = new CpuInfoBean();
        CpuPerc cpu = cpuList[i];
        cpuInfo.setCpuUser(CpuPerc.format(cpu.getUser()));
        cpuInfo.setCpuSys(CpuPerc.format(cpu.getSys()));
        cpuInfo.setCpuWait(CpuPerc.format(cpu.getWait()));
        cpuInfo.setCpuIdle(CpuPerc.format(cpu.getIdle()));
        cpuInfo.setCpuCombined(CpuPerc.format(cpu.getCombined()));
        cpuInfos.add(cpuInfo);
    }
    return cpuInfos;
}


public void main(String[] args){
    SysInfoSington sys = new SysInfoSington();
}


public SysInfoSington getInstance(){
    return instance;
}


public List<ProInfoBean> getProInfo(){
    Ps ps = new Ps();
    Sigar sigar = new Sigar();
    List<ProInfoBean> processInfos = new ArrayList<ProInfoBean>();
    try {
        long[] pids = sigar.getProcList();
        for (long pid : pids) {
            List<String> list = ps.getInfo(sigar, pid);
            ProInfoBean info = new ProInfoBean();
            for (int i = 0; i <= list.size(); i++) {
                switch(i) {
                    case 0:
                        info.setPid(list.get(0));
                        break;
                    case 2:
                        info.setStartTime(list.get(2));
                        break;
                    case 4:
                        info.setMemUse(list.get(4));
                        break;
                    case 8:
                        info.setPath(list.get(8));
                        break;
                    default:
                        break;
                }
            }
            String proPath = info.getPath();
            String[] str = proPath.split("\\\\");
            info.setName(str[str.length - 1]);
            // if(info.getName().contains(SysConst.JAVA)){
            processInfos.add(info);
        // }
        }
    } catch (SigarException e) {
        LOG.error(e.getMessage(), e);
    }
    return processInfos;
}


public List<MemInfoBean> getMemInfo(){
    List<MemInfoBean> processInfos = new ArrayList<MemInfoBean>();
    MemInfoBean memInfo = new MemInfoBean();
    Sigar sigar = new Sigar();
    Mem mem = sigar.getMem();
    memInfo.setMemTotal(mem.getTotal() / 1024L / 1024L + "M");
    memInfo.setMemUsed(mem.getUsed() / 1024L / 1024L + "M");
    memInfo.setMemFree(mem.getFree() / 1024L / 1024L + "M");
    processInfos.add(memInfo);
    return processInfos;
}


}