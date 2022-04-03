package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("AccessReportTemplate")
public class AccessReportTemplate extends BaseReportTemplate{

@XStreamAlias("sysMacAddress")
 public  String sysMacAddress;

@XStreamAlias("sysIpAddress")
 public  String sysIpAddress;

@XStreamAlias("sysManageState")
 public  String sysManageState;

@XStreamAlias("modelId")
 public  String modelId;

@XStreamAlias("modelName")
 public  String modelName;

@XStreamAlias("sysModel")
 public  String sysModel;

@XStreamAlias("hardwareType")
 public  String hardwareType;

@XStreamAlias("boardVersion")
 public  String boardVersion;

@XStreamAlias("softwareVersion")
 public  String softwareVersion;

@XStreamAlias("oemSwVersion")
 public  String oemSwVersion;

@XStreamAlias("hardwareVersion")
 public  String hardwareVersion;

@XStreamAlias("assocRssi")
 public  String assocRssi;

@XStreamAlias("radioChannelUsing")
 public  String radioChannelUsing;

@XStreamAlias("devType")
 public  String devType;

@XStreamAlias("sysHotId")
 public  String sysHotId;

@XStreamAlias("sysName")
 public  String sysName;

@XStreamAlias("tplSequence")
 public  String tplSequence;

@XStreamAlias("workMode")
 public  String workMode;


}