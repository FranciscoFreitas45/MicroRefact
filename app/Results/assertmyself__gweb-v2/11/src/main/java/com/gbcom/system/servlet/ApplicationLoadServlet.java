package com.gbcom.system.servlet;
 import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.gbcom.common.im.IMSvrService;
import com.gbcom.common.im.exception.IMInitialException;
import com.gbcom.common.template.res.BasicResManager;
import com.gbcom.common.template.xml.Sys;
import com.gbcom.common.template.xml.am.AlarmTempletFactory;
import com.gbcom.common.template.xml.jobm.JobWrapperManager;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.common.template.xml.sys.SysConfigManager;
import com.gbcom.omc.platform.da.xml.MyXMLException;
import com.gbcom.system.service.DogService;
import com.gbcom.system.servlet.init.InitServerDlg;
import com.gbcom.system.servlet.init.InitServerInfo;
import com.gbcom.update.server.AutoZipVersionTask;
public class ApplicationLoadServlet extends HttpServlet{

 private  long serialVersionUID;

 private  Logger LOG;


@Override
public void init(){
    super.init();
    // 开启snmp日志
    // System.setProperty("snmp4j.LogFactory", "org.snmp4j.log.Log4jLogFactory");
    OemManager.getInstance();
    try {
        /**
         * 初始化配置文件
         */
        LOG.info("ConfigSington INIT begin");
        SysConfigManager.getInstance().init();
        /**
         * 初始化snmp模板
         */
        LOG.info("SNMP Templet init  begin");
    // SnmpTempManager.getInstance().init();
    } catch (Exception e2) {
        LOG.error(BasicResManager.getString("Basic_application_load_message2") + e2.getMessage(), e2);
        InitServerInfo.getInstance().addMessage(BasicResManager.getString("Basic_application_load_message2") + e2.getMessage());
    }
    /**
     * SI初始化
     */
    LOG.info("SI init  begin");
    try {
    // new StandardInterfaceInit().init();
    } catch (Exception e1) {
        LOG.error("SI init faild!! ", e1);
        InitServerInfo.getInstance().addMessage(BasicResManager.getString("Basic_application_load_message4") + e1.getMessage());
    }
    /**
     * 初始化告警模板
     */
    LOG.info("AlarmTemplet init  begin");
    try {
        AlarmTempletFactory.getInstance().load();
    } catch (MyXMLException e1) {
        LOG.error(AlarmTempletFactory.class + " : Parse Alarm template failed!!!", e1);
        InitServerInfo.getInstance().addMessage(BasicResManager.getString("Basic_application_load_message3") + e1.getMessage());
    }
    try {
        IMSvrService.getInstance().initial();
    } catch (IMInitialException e1) {
        LOG.error("IMInitialException", e1);
    }
    // 升级策略使用计划方式。
    try {
        LOG.info("JobWrapperManager thread BEGIN.!");
        JobWrapperManager.getInstance().register();
    } catch (Exception e) {
        LOG.error("JobWrapperManager init faild!! ", e);
    }
    if (Sys.updateSM().getUpdateServerContext().isUpdateSwitch()) {
        // 开启压缩任务
        LOG.info("---------- automatic zip file task start!----------------------");
        AutoZipVersionTask autoZipVersionTask = new AutoZipVersionTask();
        autoZipVersionTask.start();
    }
    LOG.info("DogService task begin");
    DogService.getInstance().start();
    try {
        InitServerDlg.getInstance().showDailog(InitServerInfo.getInstance().getMessage());
    } catch (Exception e) {
        LOG.error("InitServerDlg", e);
    }
    LOG.info("*-*-*-*-*-*-*-   is over of sys information. .-*-*-*-*-*-*");
}


}