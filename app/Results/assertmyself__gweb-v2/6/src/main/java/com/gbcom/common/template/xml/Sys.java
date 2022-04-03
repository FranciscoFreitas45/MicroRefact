package com.gbcom.common.template.xml;
 import com.gbcom.common.template.xml.guide.DeviceInfoCenter;
import com.gbcom.common.template.xml.jobm.JobWrapperManager;
import com.gbcom.common.template.xml.mail.MailSender;
import com.gbcom.common.template.xml.msg.MsgSender;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.common.template.xml.snmp.MibNodeManager;
import com.gbcom.common.template.xml.snmp.SnmpTempManager;
import com.gbcom.common.template.xml.sys.CfgListManager;
import com.gbcom.common.template.xml.sys.SysConfigManager;
import com.gbcom.common.template.xml.tpl.TplInfoManager;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.server.xml.UpdateServerContextManager;
import com.gbcom.DTO.JobWrapperManager;
import com.gbcom.DTO.MsgSender;
import com.gbcom.DTO.UpdateClientContextManager;
import com.gbcom.DTO.SnmpTempManager;
import com.gbcom.DTO.MibNodeManager;
import com.gbcom.DTO.MailSender;
import com.gbcom.DTO.UpdateServerContextManager;
import com.gbcom.DTO.CfgListManager;
import com.gbcom.DTO.DeviceInfoCenter;
public class Sys {


public JobWrapperManager jobM(){
    return JobWrapperManager.getInstance();
}


public MsgSender msgS(){
    return MsgSender.getInstance();
}


public LogConfigUtil logU(){
    return LogConfigUtil.getInstance();
}


public OemManager oemM(){
    return OemManager.getInstance();
}


public UpdateClientContextManager updateCM(){
    return UpdateClientContextManager.getInstance();
}


public SnmpTempManager snmpM(){
    return SnmpTempManager.getInstance();
}


public TplInfoManager tplM(){
    return TplInfoManager.getInstance();
}


public SysConfigManager sysCfgM(){
    return SysConfigManager.getInstance();
}


public MibNodeManager mibNodeM(){
    return MibNodeManager.getInstance();
}


public MailSender mailS(){
    return MailSender.getInstance();
}


public SecConfigUtil secU(){
    return SecConfigUtil.getInstance();
}


public UpdateServerContextManager updateSM(){
    return UpdateServerContextManager.getInstance();
}


public CfgListManager cfgListM(){
    return CfgListManager.getInstance();
}


public DeviceInfoCenter guideCenter(){
    return DeviceInfoCenter.getInstance();
}


}