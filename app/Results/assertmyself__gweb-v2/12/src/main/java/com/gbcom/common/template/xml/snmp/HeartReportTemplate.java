package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("HeartReportTemplate")
public class HeartReportTemplate extends BaseReportTemplate{

@XStreamAlias("sysMacAddress")
 public  String sysMacAddress;

@XStreamAlias("channelUsing")
 public  String channelUsing;

@XStreamAlias("transmitPower")
 public  String transmitPower;

@XStreamAlias("wifi5ChannelUsing")
 public  String wifi5ChannelUsing;

@XStreamAlias("wifi5TransmitPower")
 public  String wifi5TransmitPower;

@XStreamAlias("tplSequence")
 public  String tplSequence;


}