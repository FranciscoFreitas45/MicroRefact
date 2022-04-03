package com.gbcom.common.im;
 import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import com.gbcom.common.im.desc.IAttributeDesc;
import com.gbcom.common.im.desc.IClassDesc;
import com.gbcom.common.im.exception.IMInitialException;
import com.gbcom.common.im.exception.IllegalCmArgumentException;
import com.gbcom.common.im.parse.DefaultParserFactory;
import com.gbcom.common.im.parse.alarm.IAlarmParser;
public class IMSvrService implements IIMService{

 private  Logger log;

 private  boolean init;

 private  IMSvrService instance;

 private  HashMap<String,IMVersionChain> imVersionMap;

/**
 * 构造函数
 */
private IMSvrService() {
}
public IIM getDefaultIIM(){
    IMVersionChain vc = (IMVersionChain) this.imVersionMap.get("AP");
    if (vc == null) {
        log.error("can't find IMVersionChain by type:AP", new IllegalCmArgumentException("the input type not exist:" + "AP"));
        throw new IllegalCmArgumentException("the input type not exist:" + "AP");
    }
    return vc.getIM("1.0.0");
}


public HashMap<String,IMVersionChain> getIM(){
    return this.imVersionMap;
}


public void putIM(IIM iim){
    if (this.imVersionMap.containsKey(iim.getType())) {
        this.imVersionMap.get(iim.getType()).putIm(iim.getVersion(), iim);
    } else {
        IMVersionChain imVersionChain = new IMVersionChain(iim.getType());
        imVersionChain.putIm(iim.getVersion(), iim);
        this.imVersionMap.put(imVersionChain.getType(), /*
												 * + "_" +
												 * iim.getRoot().getName()
												 */
        imVersionChain);
    }
}


public boolean initial(){
    if (this.imVersionMap == null) {
        this.imVersionMap = new HashMap<String, IMVersionChain>();
    }
    IMConfiguration imConfig = IMConfiguration.getInstance();
    String filePath = this.getClass().getClassLoader().getResource("IM/").getFile();
    log.info("IVM init .0......" + filePath);
    File file = new File(filePath);
    File[] files = file.listFiles();
    if (files == null) {
        throw new IMInitialException("not exit im file in directory:im");
    }
    for (File f : files) {
        if (f.isDirectory()) {
        /*
								 * File[] devFiles = f.listFiles(); if (devFiles
								 * != null) { for (File devFile : devFiles) { if
								 * (
								 * devFile.getName().toLowerCase().endsWith("im.xml"
								 * )) { this.putIM(imConfig.buildIM(devFile)); }
								 * } }
								 */
        } else {
            if (f.getName().toLowerCase().endsWith("im.xml")) {
                this.putIM(imConfig.buildIM(f));
            }
        }
    }
    // 信息模型解析完成后调用JointManager进行内模型联接
    JointManager.startJoint();
    this.init = true;
    return true;
}


public IMVersionChain getIMChain(String type){
    if (this.imVersionMap.get(type) != null) {
        return this.imVersionMap.get(type);
    }
    log.error("can't find VIMVersionChain by type:" + type, new IllegalCmArgumentException("the input type not exist:" + type));
    throw new IllegalCmArgumentException("the input type not exist:" + type);
}


public IMSvrService getInstance(){
    // off checkstyle
    if (instance == null) {
        synchronized (IMSvrService.class) {
            if (instance == null) {
                instance = new IMSvrService();
            }
        }
    }
    return instance;
}


public LinkedHashMap<String,String[]> getKeys(){
    LinkedHashMap<String, String[]> keys = new LinkedHashMap<String, String[]>();
    for (IClassDesc desc : getDefaultIIM().getRoot().getChildren()) {
        if (desc.isVector()) {
            // 矢量，需要全集。
            for (int i = 0; i < desc.getMaxIndex(); i++) {
                IAttributeDesc[] attris = desc.getAttributeDescs();
                String[] classKey = new String[attris.length];
                for (int y = 0; y < attris.length; y++) {
                    classKey[y] = attris[y].getName().replace("{index}", i + "");
                }
                keys.put(desc.getName() + "-" + i, classKey);
            }
        } else {
            IAttributeDesc[] attris = desc.getAttributeDescs();
            String[] classKey = new String[attris.length];
            for (int y = 0; y < attris.length; y++) {
                classKey[y] = attris[y].getName();
            }
            keys.put(desc.getName(), classKey);
        }
    }
    return keys;
}


public boolean isInit(){
    return this.init;
}


public IIM getIIM(String type,String version){
    IMVersionChain vc = (IMVersionChain) this.imVersionMap.get(type);
    if (vc == null) {
        log.error("can't find IMVersionChain by type:" + type, new IllegalCmArgumentException("the input type not exist:" + type));
        throw new IllegalCmArgumentException("the input type not exist:" + type);
    }
    return vc.getIM(version);
}


public IAlarmParser getAlarmParserParser(String type,String model,String version){
    IIM iim = getIIM(type + "_" + model, version);
    if (iim == null) {
        return null;
    }
    String className = iim.getRoot().getAlarmParserClassName();
    return DefaultParserFactory.getInstance().getAlarmParser(className);
}


}