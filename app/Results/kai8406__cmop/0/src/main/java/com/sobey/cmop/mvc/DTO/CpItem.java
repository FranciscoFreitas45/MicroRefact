package com.sobey.cmop.mvc.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class CpItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  String recordStreamUrl;

 private  String recordBitrate;

 private  String exportEncode;

 private  Integer recordType;

 private  String recordTime;

 private  Integer recordDuration;

 private  String publishUrl;

 private  Boolean isPushCtp;

 private  String videoFtpIp;

 private  String videoFtpPort;

 private  String videoFtpUsername;

 private  String videoFtpPassword;

 private  String videoFtpRootpath;

 private  String videoFtpUploadpath;

 private  String videoOutputGroup;

 private  String videoOutputWay;

 private  String pictrueFtpIp;

 private  String pictrueFtpPort;

 private  String pictrueFtpUsername;

 private  String pictrueFtpPassword;

 private  String pictrueFtpRootpath;

 private  String pictrueFtpUploadpath;

 private  String pictrueOutputGroup;

 private  String pictrueOutputMedia;

 private  Set<CpProgramItem> cpProgramItems;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

// Constructors
/**
 * default constructor
 */
public CpItem() {
}/**
 * minimal constructor
 */
public CpItem(Apply apply, String identifier, String recordStreamUrl, String recordBitrate, String exportEncode, Integer recordType, String recordTime, Integer recordDuration, String videoFtpIp, String videoFtpPort, String videoFtpUsername, String videoFtpPassword, String videoFtpRootpath, String videoFtpUploadpath, String videoOutputGroup, String videoOutputWay, String pictrueFtpIp, String pictrueFtpPort, String pictrueFtpUsername, String pictrueFtpPassword, String pictrueFtpRootpath, String pictrueFtpUploadpath, String pictrueOutputGroup, String pictrueOutputMedia) {
    this.apply = apply;
    this.recordDuration = recordDuration;
    this.identifier = identifier;
    this.recordStreamUrl = recordStreamUrl;
    this.recordBitrate = recordBitrate;
    this.exportEncode = exportEncode;
    this.recordType = recordType;
    this.recordTime = recordTime;
    this.videoFtpIp = videoFtpIp;
    this.videoFtpPort = videoFtpPort;
    this.videoFtpUsername = videoFtpUsername;
    this.videoFtpPassword = videoFtpPassword;
    this.videoFtpRootpath = videoFtpRootpath;
    this.videoFtpUploadpath = videoFtpUploadpath;
    this.videoOutputGroup = videoOutputGroup;
    this.videoOutputWay = videoOutputWay;
    this.pictrueFtpIp = pictrueFtpIp;
    this.pictrueFtpPort = pictrueFtpPort;
    this.pictrueFtpUsername = pictrueFtpUsername;
    this.pictrueFtpPassword = pictrueFtpPassword;
    this.pictrueFtpRootpath = pictrueFtpRootpath;
    this.pictrueFtpUploadpath = pictrueFtpUploadpath;
    this.pictrueOutputGroup = pictrueOutputGroup;
    this.pictrueOutputMedia = pictrueOutputMedia;
}/**
 * full constructor
 */
public CpItem(Apply apply, String identifier, String recordStreamUrl, String recordBitrate, String exportEncode, Integer recordType, String recordTime, Integer recordDuration, String publishUrl, Boolean isPushCtp, String videoFtpIp, String videoFtpPort, String videoFtpUsername, String videoFtpPassword, String videoFtpRootpath, String videoFtpUploadpath, String videoOutputGroup, String videoOutputWay, String pictrueFtpIp, String pictrueFtpPort, String pictrueFtpUsername, String pictrueFtpPassword, String pictrueFtpRootpath, String pictrueFtpUploadpath, String pictrueOutputGroup, String pictrueOutputMedia, Set<CpProgramItem> cpProgramItems) {
    this.apply = apply;
    this.recordDuration = recordDuration;
    this.identifier = identifier;
    this.recordStreamUrl = recordStreamUrl;
    this.recordBitrate = recordBitrate;
    this.exportEncode = exportEncode;
    this.recordType = recordType;
    this.recordTime = recordTime;
    this.publishUrl = publishUrl;
    this.isPushCtp = isPushCtp;
    this.videoFtpIp = videoFtpIp;
    this.videoFtpPort = videoFtpPort;
    this.videoFtpUsername = videoFtpUsername;
    this.videoFtpPassword = videoFtpPassword;
    this.videoFtpRootpath = videoFtpRootpath;
    this.videoFtpUploadpath = videoFtpUploadpath;
    this.videoOutputGroup = videoOutputGroup;
    this.videoOutputWay = videoOutputWay;
    this.pictrueFtpIp = pictrueFtpIp;
    this.pictrueFtpPort = pictrueFtpPort;
    this.pictrueFtpUsername = pictrueFtpUsername;
    this.pictrueFtpPassword = pictrueFtpPassword;
    this.pictrueFtpRootpath = pictrueFtpRootpath;
    this.pictrueFtpUploadpath = pictrueFtpUploadpath;
    this.pictrueOutputGroup = pictrueOutputGroup;
    this.pictrueOutputMedia = pictrueOutputMedia;
    this.cpProgramItems = cpProgramItems;
}
@Column(name = "record_bitrate", nullable = false, length = 10)
public String getRecordBitrate(){
    return this.recordBitrate;
}


@Column(name = "record_duration", nullable = false, length = 11)
public Integer getRecordDuration(){
    return recordDuration;
}


@Column(name = "video_ftp_password", nullable = false, length = 45)
public String getVideoFtpPassword(){
    return this.videoFtpPassword;
}


@Column(name = "pictrue_output_media", nullable = false, length = 100)
public String getPictrueOutputMedia(){
    return this.pictrueOutputMedia;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


@Column(name = "video_ftp_ip", nullable = false, length = 45)
public String getVideoFtpIp(){
    return this.videoFtpIp;
}


@Column(name = "video_output_group", nullable = false, length = 100)
public String getVideoOutputGroup(){
    return this.videoOutputGroup;
}


@Column(name = "video_ftp_rootpath", nullable = false, length = 45)
public String getVideoFtpRootpath(){
    return this.videoFtpRootpath;
}


@Column(name = "is_push_ctp")
public Boolean getIsPushCtp(){
    return this.isPushCtp;
}


@Column(name = "pictrue_ftp_username", nullable = false, length = 45)
public String getPictrueFtpUsername(){
    return this.pictrueFtpUsername;
}


@Column(name = "pictrue_ftp_ip", nullable = false, length = 45)
public String getPictrueFtpIp(){
    return this.pictrueFtpIp;
}


@Column(name = "record_type", nullable = false)
public Integer getRecordType(){
    return this.recordType;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cpItem")
public Set<CpProgramItem> getCpProgramItems(){
    return this.cpProgramItems;
}


@Column(name = "record_time", nullable = false, length = 45)
public String getRecordTime(){
    return this.recordTime;
}


@Column(name = "record_stream_url", nullable = false, length = 100)
public String getRecordStreamUrl(){
    return this.recordStreamUrl;
}


@Column(name = "pictrue_ftp_uploadpath", nullable = false, length = 45)
public String getPictrueFtpUploadpath(){
    return this.pictrueFtpUploadpath;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "pictrue_ftp_password", nullable = false, length = 45)
public String getPictrueFtpPassword(){
    return this.pictrueFtpPassword;
}


@Column(name = "video_output_way", nullable = false, length = 100)
public String getVideoOutputWay(){
    return this.videoOutputWay;
}


@Column(name = "video_ftp_port", nullable = false, length = 10)
public String getVideoFtpPort(){
    return this.videoFtpPort;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "video_ftp_uploadpath", nullable = false, length = 45)
public String getVideoFtpUploadpath(){
    return this.videoFtpUploadpath;
}


@Column(name = "video_ftp_username", nullable = false, length = 45)
public String getVideoFtpUsername(){
    return this.videoFtpUsername;
}


@Column(name = "pictrue_output_group", nullable = false, length = 100)
public String getPictrueOutputGroup(){
    return this.pictrueOutputGroup;
}


@Column(name = "pictrue_ftp_rootpath", nullable = false, length = 45)
public String getPictrueFtpRootpath(){
    return this.pictrueFtpRootpath;
}


@Column(name = "publish_url", length = 100)
public String getPublishUrl(){
    return this.publishUrl;
}


@Column(name = "pictrue_ftp_port", nullable = false, length = 10)
public String getPictrueFtpPort(){
    return this.pictrueFtpPort;
}


@Column(name = "export_encode", nullable = false, length = 200)
public String getExportEncode(){
    return this.exportEncode;
}


public void setRecordStreamUrl(String recordStreamUrl){
    this.recordStreamUrl = recordStreamUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordStreamUrl"))

.queryParam("recordStreamUrl",recordStreamUrl)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRecordBitrate(String recordBitrate){
    this.recordBitrate = recordBitrate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordBitrate"))

.queryParam("recordBitrate",recordBitrate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExportEncode(String exportEncode){
    this.exportEncode = exportEncode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExportEncode"))

.queryParam("exportEncode",exportEncode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRecordType(Integer recordType){
    this.recordType = recordType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordType"))

.queryParam("recordType",recordType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRecordTime(String recordTime){
    this.recordTime = recordTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordTime"))

.queryParam("recordTime",recordTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRecordDuration(Integer recordDuration){
    this.recordDuration = recordDuration;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordDuration"))

.queryParam("recordDuration",recordDuration)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPublishUrl(String publishUrl){
    this.publishUrl = publishUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPublishUrl"))

.queryParam("publishUrl",publishUrl)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsPushCtp(Boolean isPushCtp){
    this.isPushCtp = isPushCtp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsPushCtp"))

.queryParam("isPushCtp",isPushCtp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpIp(String videoFtpIp){
    this.videoFtpIp = videoFtpIp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpIp"))

.queryParam("videoFtpIp",videoFtpIp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpPort(String videoFtpPort){
    this.videoFtpPort = videoFtpPort;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpPort"))

.queryParam("videoFtpPort",videoFtpPort)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpUsername(String videoFtpUsername){
    this.videoFtpUsername = videoFtpUsername;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpUsername"))

.queryParam("videoFtpUsername",videoFtpUsername)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpPassword(String videoFtpPassword){
    this.videoFtpPassword = videoFtpPassword;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpPassword"))

.queryParam("videoFtpPassword",videoFtpPassword)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpRootpath(String videoFtpRootpath){
    this.videoFtpRootpath = videoFtpRootpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpRootpath"))

.queryParam("videoFtpRootpath",videoFtpRootpath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoFtpUploadpath(String videoFtpUploadpath){
    this.videoFtpUploadpath = videoFtpUploadpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoFtpUploadpath"))

.queryParam("videoFtpUploadpath",videoFtpUploadpath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoOutputGroup(String videoOutputGroup){
    this.videoOutputGroup = videoOutputGroup;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoOutputGroup"))

.queryParam("videoOutputGroup",videoOutputGroup)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVideoOutputWay(String videoOutputWay){
    this.videoOutputWay = videoOutputWay;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVideoOutputWay"))

.queryParam("videoOutputWay",videoOutputWay)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpIp(String pictrueFtpIp){
    this.pictrueFtpIp = pictrueFtpIp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpIp"))

.queryParam("pictrueFtpIp",pictrueFtpIp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpPort(String pictrueFtpPort){
    this.pictrueFtpPort = pictrueFtpPort;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpPort"))

.queryParam("pictrueFtpPort",pictrueFtpPort)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpUsername(String pictrueFtpUsername){
    this.pictrueFtpUsername = pictrueFtpUsername;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpUsername"))

.queryParam("pictrueFtpUsername",pictrueFtpUsername)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpPassword(String pictrueFtpPassword){
    this.pictrueFtpPassword = pictrueFtpPassword;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpPassword"))

.queryParam("pictrueFtpPassword",pictrueFtpPassword)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpRootpath(String pictrueFtpRootpath){
    this.pictrueFtpRootpath = pictrueFtpRootpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpRootpath"))

.queryParam("pictrueFtpRootpath",pictrueFtpRootpath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueFtpUploadpath(String pictrueFtpUploadpath){
    this.pictrueFtpUploadpath = pictrueFtpUploadpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueFtpUploadpath"))

.queryParam("pictrueFtpUploadpath",pictrueFtpUploadpath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueOutputGroup(String pictrueOutputGroup){
    this.pictrueOutputGroup = pictrueOutputGroup;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueOutputGroup"))

.queryParam("pictrueOutputGroup",pictrueOutputGroup)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPictrueOutputMedia(String pictrueOutputMedia){
    this.pictrueOutputMedia = pictrueOutputMedia;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictrueOutputMedia"))

.queryParam("pictrueOutputMedia",pictrueOutputMedia)
;
restTemplate.put(builder.toUriString(),null);
}


}