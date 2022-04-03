package com.gbcom.common.template.xml.sys;
 import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import com.gbcom.omc.si.common.Const;
import com.gbcom.omc.si.discovery.discover.SnmpSingleton;
import com.gbcom.op.util.Assert;
import com.gbcom.op.util.xml.XStreamUtil;
public class SysConfigManager {

 private  Logger LOG;

 private  String CONFIG_PATH;

 private  SacConfig conf;

 private  Map<String,ApSysModel> devMap;

 private  boolean isInit;

 private  SysConfigManager instance;

 public  String CONFIG_PORT;

 public  String CONFIG_HEART;

 public  String CONFIG_ACTTHRESHOLD;

 public  String CONFIG_CLEARTHRESHOLD;

 public  String CONFIG_SNMP_IP;

 public  String CONFIG_REPORT_INTERVAL;

 public  String CONFIG_AUTO_UPGRADE;

 public  String CONFIG_INNERALARM;

 public  String CONFIG_FTPDIR;

private SysConfigManager() {
// init();
}
public void init(){
    /**
     * 解析配置文件
     */
    LOG.info("---- init system config : sac_config.xml file ---");
    parseFile();
    /**
     * 设置SI源ip地址
     */
    try {
        if (!Const.sourceSnmpIp.equals(conf.getSnmpIp())) {
            Const.sourceSnmpIp = conf.getSnmpIp();
            SnmpSingleton.refreshTransportMapping();
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
    }
    if (!isInit) {
    // 从数据库获取配置，，sxp需求
    /*			try {
				SysCfgDBService sysCfgDBService = (SysCfgDBService) SpringUtils
						.getBean("sysCfgDBService");
				SysCfgDB db = sysCfgDBService.getCfg();
				if (db != null) {
					conf.getFtp().setFtpIp(db.getFtpIp());
					conf.getFtp().setFtpWIp(db.getFtpWIp());
					conf.getFtp().setFtpWport(db.getFtpWport());
					conf.setSnmpIp(db.getSnmpIp());
					save();
				}
			} catch (Exception e) {
			}*/
    }
    isInit = true;
}


public ApSysModel getApSysModel(String sysModel){
    return devMap.get(sysModel);
}


public void save(){
    Class<?>[] classContext = { SacConfig.class, ApSysModel.class, TargetVer.class, FtpConfig.class };
    URL url = this.getClass().getClassLoader().getResource(CONFIG_PATH);
    // 保存文件失败
    XStreamUtil.toXML(conf, url.getFile(), classContext);
    LOG.info("SAVE CONFIG_FILE SUCCESS! ");
}


public void parseFile(){
    Class<?>[] classContext = { SacConfig.class, ApSysModel.class, TargetVer.class, FtpConfig.class };
    URL url = this.getClass().getClassLoader().getResource(CONFIG_PATH);
    conf = XStreamUtil.fromXML(SacConfig.class, url.getFile(), classContext);
    for (ApSysModel dev : conf.getDevices()) {
        devMap.put(dev.getSysModel(), dev);
    }
}


public List<String> getSysmodelList(){
    List<String> apModelList = new ArrayList<String>();
    for (Entry<String, ApSysModel> entity : devMap.entrySet()) {
        apModelList.add(entity.getKey());
    }
    return apModelList;
}


public void main(String[] args){
    SysConfigManager.getInstance().init();
    // 加入 升级队列 并交个升级线程处理 ：
    // 调用升级服务
    FtpConfig conf = SysConfigManager.getInstance().getConfig().getFtp();
    // 目标版本
    // 建立ftp连接 FTP验证是否存在该版本
    @SuppressWarnings("unused")
    FTPClient client = new FTPClient();
    System.out.println("--------------------Device-------");
    System.out.println(SysConfigManager.getInstance().getDevMap());
    System.out.println("--------------------Device-------");
}


public SysConfigManager getInstance(){
    return instance;
}


public Map<String,ApSysModel> getDevMap(){
    return devMap;
}


public TargetVer getTargetVer(String type){
    List<TargetVer> tarVers = conf.getTargetVers();
    for (TargetVer ver : tarVers) {
        if (ver.getDeType().equals(type)) {
            return ver;
        }
    }
    return null;
}


public SacConfig getConfig(){
    return conf;
}


public boolean isInit(){
    return isInit;
}


}