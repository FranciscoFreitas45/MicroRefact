package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("FileTransReportTemplate")
public class FileTransReportTemplate extends BaseReportTemplate{

@XStreamAlias("sysMacAddress")
 public  String sysMacAddress;

@XStreamAlias("softwareVersion")
 public  String softwareVersion;

@XStreamAlias("targetVersion")
 public  String targetVersion;

@XStreamAlias("transStatus")
 public  String transStatus;

@XStreamAlias("apFileOperType")
 public  String apFileOperType;


}