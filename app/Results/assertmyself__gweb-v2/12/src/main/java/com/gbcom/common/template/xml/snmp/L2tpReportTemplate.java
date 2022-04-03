package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("L2tpReportTemplate")
public class L2tpReportTemplate extends BaseReportTemplate{

@XStreamAlias("manageMac")
 public  String manageMac;

@XStreamAlias("l2tpServerIp")
 public  String l2tpServerIp;

@XStreamAlias("l2tpUsrName")
 public  String l2tpUsrName;

@XStreamAlias("l2tpUsrPass")
 public  String l2tpUsrPass;

@XStreamAlias("l2tpClientIp")
 public  String l2tpClientIp;

@XStreamAlias("l2tpClientStatue")
 public  String l2tpClientStatue;

@XStreamAlias("l2tpClientMsg")
 public  String l2tpClientMsg;


}