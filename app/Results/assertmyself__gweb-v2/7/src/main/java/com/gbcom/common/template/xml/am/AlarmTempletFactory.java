package com.gbcom.common.template.xml.am;
 import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import com.gbcom.omc.platform.da.xml.IXmlParser;
import com.gbcom.omc.platform.da.xml.MyXMLException;
import com.gbcom.omc.platform.da.xml.XmlFactory;
import com.gbcom.system.utils.XmlFileUtil;
public class AlarmTempletFactory implements Serializable,IAlarmTemplet{

 private  long serialVersionUID;

 private  Logger LOG;

 private  AlarmTempletFactory instance;

 private  AlarmTempletBean alarmTempletBean;

 private  AlarmReportTempletBean alarmReportTempletBean;

 private  Map<String,List<Integer>> alarmFilterMap;

 private  boolean isLoad;

 private  String MODLE_FILE_PATH;

/**
 * private Constructor
 */
private AlarmTempletFactory() {
}
public void reLoad(){
    final String[] files = getAlarmTempletFiles();
    final String language = Locale.getDefault().getLanguage();
    if (files.length != 0) {
        AlarmTemplet.getInstance().getAlarmTempletMap().clear();
        for (int i = 0; i < files.length; i++) {
            String file = Thread.currentThread().getContextClassLoader().getResource("config/am/" + files[i] + "_" + language + ".xml").getFile();
            loadAlarmTemplet(file);
        }
        LOG.info("*reload Alarm Templet ok!*");
    }
}


public void setAlarmFilterMap(Map<String,List<Integer>> alarmFilterMap){
    this.alarmFilterMap = alarmFilterMap;
}


@Override
public AlarmTempletBean getAlarmTemplet(){
    return alarmTempletBean;
}


public void main(String[] args){
    AlarmTempletFactory.getInstance().load();
}


public void updateAlarmTemplate(String vendor){
    // 获取有多少个模板文件，在配置文件alarm_config.xml中描述
    final String language = Locale.getDefault().getLanguage();
    // 读取各版本的模版
    final String file = Thread.currentThread().getContextClassLoader().getResource("config/am/alarm_templet_" + vendor + "_" + language + ".xml").getPath();
    final File xml = new File(file);
    if (xml.exists()) {
        XmlFileUtil.marshallerObjectToXml(AlarmTemplet.getInstance().getAlarmTemplet(vendor), AlarmTempletBean.class, xml);
    }
}


public String[] getAlarmTemplets(){
    return getAlarmTempletFiles();
}


public AlarmReportTempletBean getAlarmReportTemplateBean(){
    return alarmReportTempletBean;
}


public void setAlarmTempletBean(AlarmTempletBean alarmTempletBean){
    this.alarmTempletBean = alarmTempletBean;
}


public void loadAlarmReportTemplate(String file){
    final File xml = new File(file);
    if (xml.exists()) {
        alarmReportTempletBean = XmlFileUtil.unmarshallerObjectFromXml(AlarmReportTempletBean.class, xml);
        final List<AlarmReportTemplet> alarmReportTemplets = alarmReportTempletBean.getAlarmReportTemplets();
        for (AlarmReportTemplet alarmReportTemplet : alarmReportTemplets) {
            AlarmTemplet.getInstance().addAlarmReportTemplet(alarmReportTemplet.getVendor(), alarmReportTemplet);
        }
    }
}


public String[] getAlarmTempletFiles(){
    final String file = Thread.currentThread().getContextClassLoader().getResource(MODLE_FILE_PATH).getFile();
    final IXmlParser xmlParser = XmlFactory.getXmlParser();
    xmlParser.loadFile(file);
    final NodeList nodeList = (NodeList) xmlParser.getNodeList("/conf/templetFile/fileName");
    final int size = nodeList.getLength();
    String[] templetFiles = new String[size];
    for (int i = 0; i < size; i++) {
        templetFiles[i] = nodeList.item(i).getTextContent();
    }
    return templetFiles;
}


public void load(){
    if (isLoad) {
        return;
    }
    // 获取有多少个模板文件，在配置文件alarm_config.xml中描述
    final String[] files = getAlarmTempletFiles();
    if (files.length == 0) {
        return;
    }
    final String language = Locale.getDefault().getLanguage();
    // 读取各版本的模版
    for (int i = 0; i < files.length; i++) {
        final String file = Thread.currentThread().getContextClassLoader().getResource("config/am/" + files[i] + "_" + language + ".xml").getPath();
        loadAlarmTemplet(file);
    }
    loadAlarmReportTemplate(Thread.currentThread().getContextClassLoader().getResource("config/am/alarm_report_templet_" + language + ".xml").getPath());
    isLoad = true;
    LOG.info("****load Alarm Templet ok!***");
}


public Map<String,List<Integer>> getAlarmFilterMap(){
    return alarmFilterMap;
}


public AlarmTempletFactory getInstance(){
    return instance;
}


public void loadAlarmTemplet(String file){
    final File xml = new File(file);
    if (xml.exists()) {
        final AlarmTempletBean templetBean = XmlFileUtil.unmarshallerObjectFromXml(AlarmTempletBean.class, xml);
        final List<AlarmInfoBean> alarmInfoBeans = templetBean.getAlarmInfoBeans();
        for (AlarmInfoBean alarmInfoBean : alarmInfoBeans) {
            alarmInfoBean.initAlarmReasonMap();
            final int alarmCode = alarmInfoBean.getAlarmCode();
            templetBean.addAlarmInfo(alarmCode, alarmInfoBean);
        }
        AlarmTemplet.getInstance().addAlarmTemplet(templetBean.getVendor(), templetBean);
    }
}


}