package com.gbcom.system.service;
 import java.io.File;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import p1.DOGException;
import com.gbcom.common.template.res.BasicResManager;
import com.gbcom.common.template.xml.sys.SysConfigManager;
import com.gbcom.omc.verify.SoftDogLicense;
import com.gbcom.omc.verify.SoftDogVerfier;
import com.gbcom.system.service.support.DogServerDlg;
public class DogService {

 private  DogService instance;

 private  Logger LOG;

 private  int accessNum;

 private  int MAX_ACCESS_NUM;

 private  boolean validate;

 private  String message;

private DogService() {
    final int number = SysConfigManager.getInstance().getConfig().getNumber();
    accessNum = number > MAX_ACCESS_NUM ? MAX_ACCESS_NUM : number;
}
public boolean isValidate(){
    return validate;
}


public void init(){
    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
        // 非window平台 不进行加密狗检测  ,so the
        validate = true;
        return;
    }
    LOG.info("INIT DogService");
    // 任务调度线程池
    final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
    schedule.scheduleWithFixedDelay(new Runnable() {

        @Override
        public void run() {
            try {
                // long cpenum = ApListSingleton.getInstance().getService().getAllCounts();
                // long cpenum = ((ApDeviceService)SpringUtils.getBean("apDeviceService")).getAllCounts();
                long cpenum = 0;
                if (LOG.isDebugEnabled()) {
                    LOG.info("Check DOG ::: THE TOTAL AP COUNT IS " + cpenum + " ;  TIME = " + new Date());
                }
                if (cpenum <= accessNum) {
                    DogServerDlg.getInstance().showDailog("success");
                    DogServerDlg.setValidate(true);
                    setValidate(true);
                    setMessage("success");
                } else {
                    long result = SoftDogVerfier.validate();
                    if (result != 0) {
                        DogServerDlg.getInstance().showDailog(DOGException.getErrorMsg((int) result, 0));
                        DogServerDlg.setValidate(false);
                        setValidate(false);
                        LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                        setMessage(DOGException.getErrorMsg((int) result, 0));
                    } else {
                        int cpeAuthorNum = 0;
                        try {
                            cpeAuthorNum = Integer.valueOf(SoftDogVerfier.readAuthorNum());
                        } catch (NumberFormatException e) {
                            DogServerDlg.getInstance().showDailog(" ---  加密狗读取授权数失败!  --");
                            DogServerDlg.setValidate(false);
                            setValidate(false);
                            LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                            setMessage(" ---  加密狗读取授权数失败!  --");
                        }
                        if (cpenum > cpeAuthorNum) {
                            DogServerDlg.getInstance().showDailog("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
                            DogServerDlg.setValidate(false);
                            setValidate(false);
                            LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                            setMessage("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
                        } else {
                            DogServerDlg.getInstance().showDailog("success");
                            DogServerDlg.setValidate(true);
                            setValidate(true);
                            if (LOG.isDebugEnabled()) {
                                LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                            }
                            setMessage("success");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("ERROR", e);
            }
        }
    }, 1, 3, TimeUnit.SECONDS);
    LOG.info("INIT DogService  end！");
}


public String getDogAccessNum(){
    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
        // linux 为无穷
        return "----";
    }
    String authorNum = "";
    long result = SoftDogVerfier.validate();
    if (result != 0L) {
        authorNum = BasicResManager.getString("Basic_dog_number") + accessNum;
    } else {
        try {
            int readAuthorNumber = Integer.valueOf(SoftDogVerfier.readAuthorNum());
            authorNum = "" + readAuthorNumber;
        } catch (NumberFormatException e) {
            authorNum = BasicResManager.getString("Basic_dog_number") + accessNum;
            LOG.error("NumberFormatException：", e);
        }
    }
    return authorNum;
}


public void setValidate(boolean validate){
    this.validate = validate;
}


public boolean upgradelicenseFile(String fileName){
    boolean flag = false;
    try {
        flag = SoftDogLicense.upgradeLicenseStr(new File(fileName));
    } catch (Exception e) {
        LOG.error("DOG -- upgradelicenseFile  failed!  fileName=" + fileName, e);
        throw new RuntimeException(BasicResManager.getString("license_upgrade_failed") + " ; fileName=" + fileName);
    }
    LOG.info("DOG -- upgradelicenseFile :: fileName=" + fileName + "; resutl=" + flag);
    return flag;
}


public void start(){
    init();
}


public String getMessage(){
    return message;
}


public boolean upgradeLicenseStr(String character){
    LOG.info("DOG -- upgradeLicenseStr :: character=" + character);
    boolean flag = false;
    try {
        flag = SoftDogLicense.upgradeLicenseStr(character);
    } catch (Exception e) {
        LOG.error("DOG -- upgradeLicenseStr  failed!  character=" + character, e);
        throw new RuntimeException(BasicResManager.getString("license_upgrade_failed") + " ; character=" + character);
    }
    LOG.info("DOG -- upgradeLicenseStr :: character=" + character + "; resutl=" + flag);
    return flag;
}


@Override
public void run(){
    try {
        // long cpenum = ApListSingleton.getInstance().getService().getAllCounts();
        // long cpenum = ((ApDeviceService)SpringUtils.getBean("apDeviceService")).getAllCounts();
        long cpenum = 0;
        if (LOG.isDebugEnabled()) {
            LOG.info("Check DOG ::: THE TOTAL AP COUNT IS " + cpenum + " ;  TIME = " + new Date());
        }
        if (cpenum <= accessNum) {
            DogServerDlg.getInstance().showDailog("success");
            DogServerDlg.setValidate(true);
            setValidate(true);
            setMessage("success");
        } else {
            long result = SoftDogVerfier.validate();
            if (result != 0) {
                DogServerDlg.getInstance().showDailog(DOGException.getErrorMsg((int) result, 0));
                DogServerDlg.setValidate(false);
                setValidate(false);
                LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                setMessage(DOGException.getErrorMsg((int) result, 0));
            } else {
                int cpeAuthorNum = 0;
                try {
                    cpeAuthorNum = Integer.valueOf(SoftDogVerfier.readAuthorNum());
                } catch (NumberFormatException e) {
                    DogServerDlg.getInstance().showDailog(" ---  加密狗读取授权数失败!  --");
                    DogServerDlg.setValidate(false);
                    setValidate(false);
                    LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                    setMessage(" ---  加密狗读取授权数失败!  --");
                }
                if (cpenum > cpeAuthorNum) {
                    DogServerDlg.getInstance().showDailog("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
                    DogServerDlg.setValidate(false);
                    setValidate(false);
                    LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                    setMessage("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值" + cpenum);
                } else {
                    DogServerDlg.getInstance().showDailog("success");
                    DogServerDlg.setValidate(true);
                    setValidate(true);
                    if (LOG.isDebugEnabled()) {
                        LOG.info("DogService : " + DOGException.getErrorMsg((int) result, 0));
                    }
                    setMessage("success");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        LOG.error("ERROR", e);
    }
}


public DogService getInstance(){
    return instance;
}


public void setMessage(String message){
    this.message = message;
}


}