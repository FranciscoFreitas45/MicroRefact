package com.sobey.cmop.mvc.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class MdnLiveItem {

 private  Integer id;

 private  MdnItem mdnItem;

 private  String liveDomain;

 private  String liveProtocol;

 private  Integer streamOutMode;

 private  String name;

 private  String guid;

 private  String bandwidth;

 private  Integer encoderMode;

 private  String httpUrl;

 private  String httpBitrate;

 private  String hlsUrl;

 private  String hlsBitrate;

// Constructors
/**
 * default constructor
 */
public MdnLiveItem() {
}/**
 * minimal constructor
 */
public MdnLiveItem(MdnItem mdnItem, String liveDomain, String liveProtocol, Integer streamOutMode, String name, String guid, String bandwidth) {
    this.mdnItem = mdnItem;
    this.liveDomain = liveDomain;
    this.liveProtocol = liveProtocol;
    this.streamOutMode = streamOutMode;
    this.name = name;
    this.guid = guid;
    this.bandwidth = bandwidth;
}/**
 * full constructor
 */
public MdnLiveItem(MdnItem mdnItem, String liveDomain, String liveProtocol, Integer streamOutMode, String name, String guid, String bandwidth, Integer encoderMode, String httpUrl, String httpBitrate, String hlsUrl, String hlsBitrate) {
    this.mdnItem = mdnItem;
    this.liveDomain = liveDomain;
    this.liveProtocol = liveProtocol;
    this.streamOutMode = streamOutMode;
    this.name = name;
    this.guid = guid;
    this.bandwidth = bandwidth;
    this.encoderMode = encoderMode;
    this.httpUrl = httpUrl;
    this.httpBitrate = httpBitrate;
    this.hlsUrl = hlsUrl;
    this.hlsBitrate = hlsBitrate;
}
@Column(name = "live_domain", nullable = false, length = 100)
public String getLiveDomain(){
    return this.liveDomain;
}


@Column(name = "guid", nullable = false, length = 64)
public String getGuid(){
    return this.guid;
}


@Column(name = "hls_url", length = 100)
public String getHlsUrl(){
    return this.hlsUrl;
}


@Column(name = "name", nullable = false, length = 45)
public String getName(){
    return this.name;
}


@Column(name = "encoder_mode")
public Integer getEncoderMode(){
    return this.encoderMode;
}


@Column(name = "hls_bitrate", length = 45)
public String getHlsBitrate(){
    return this.hlsBitrate;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "http_url", length = 100)
public String getHttpUrl(){
    return this.httpUrl;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "mdn_item_id", nullable = false)
public MdnItem getMdnItem(){
    return this.mdnItem;
}


@Column(name = "http_bitrate", length = 45)
public String getHttpBitrate(){
    return this.httpBitrate;
}


@Column(name = "live_protocol", nullable = false, length = 45)
public String getLiveProtocol(){
    return this.liveProtocol;
}


@Column(name = "stream_out_mode", nullable = false)
public Integer getStreamOutMode(){
    return this.streamOutMode;
}


@Column(name = "bandwidth", nullable = false, length = 45)
public String getBandwidth(){
    return this.bandwidth;
}


}