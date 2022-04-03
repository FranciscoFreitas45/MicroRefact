package com.sobey.cmop.mvc.entity;
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
@Entity
@Table(name = "cp_item", catalog = "cmop")
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


public void setVideoFtpUploadpath(String videoFtpUploadpath){
    this.videoFtpUploadpath = videoFtpUploadpath;
}


public void setExportEncode(String exportEncode){
    this.exportEncode = exportEncode;
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


public void setId(Integer id){
    this.id = id;
}


public void setApply(Apply apply){
    this.apply = apply;
}


public void setPictrueFtpRootpath(String pictrueFtpRootpath){
    this.pictrueFtpRootpath = pictrueFtpRootpath;
}


public void setRecordType(Integer recordType){
    this.recordType = recordType;
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


public void setVideoFtpPassword(String videoFtpPassword){
    this.videoFtpPassword = videoFtpPassword;
}


@Column(name = "pictrue_ftp_ip", nullable = false, length = 45)
public String getPictrueFtpIp(){
    return this.pictrueFtpIp;
}


@Column(name = "record_type", nullable = false)
public Integer getRecordType(){
    return this.recordType;
}


public void setVideoFtpRootpath(String videoFtpRootpath){
    this.videoFtpRootpath = videoFtpRootpath;
}


public void setPublishUrl(String publishUrl){
    this.publishUrl = publishUrl;
}


public void setCpProgramItems(Set<CpProgramItem> cpProgramItems){
    this.cpProgramItems = cpProgramItems;
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


public void setIsPushCtp(Boolean isPushCtp){
    this.isPushCtp = isPushCtp;
}


public void setPictrueOutputGroup(String pictrueOutputGroup){
    this.pictrueOutputGroup = pictrueOutputGroup;
}


public void setPictrueFtpUploadpath(String pictrueFtpUploadpath){
    this.pictrueFtpUploadpath = pictrueFtpUploadpath;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
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


public void setVideoOutputGroup(String videoOutputGroup){
    this.videoOutputGroup = videoOutputGroup;
}


public void setPictrueFtpPassword(String pictrueFtpPassword){
    this.pictrueFtpPassword = pictrueFtpPassword;
}


@Column(name = "video_ftp_uploadpath", nullable = false, length = 45)
public String getVideoFtpUploadpath(){
    return this.videoFtpUploadpath;
}


public void setPictrueFtpIp(String pictrueFtpIp){
    this.pictrueFtpIp = pictrueFtpIp;
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


public void setVideoFtpIp(String videoFtpIp){
    this.videoFtpIp = videoFtpIp;
}


public void setRecordBitrate(String recordBitrate){
    this.recordBitrate = recordBitrate;
}


public void setRecordDuration(Integer recordDuration){
    this.recordDuration = recordDuration;
}


public void setVideoOutputWay(String videoOutputWay){
    this.videoOutputWay = videoOutputWay;
}


public void setVideoFtpUsername(String videoFtpUsername){
    this.videoFtpUsername = videoFtpUsername;
}


public void setRecordTime(String recordTime){
    this.recordTime = recordTime;
}


public void setPictrueOutputMedia(String pictrueOutputMedia){
    this.pictrueOutputMedia = pictrueOutputMedia;
}


public void setRecordStreamUrl(String recordStreamUrl){
    this.recordStreamUrl = recordStreamUrl;
}


@Column(name = "publish_url", length = 100)
public String getPublishUrl(){
    return this.publishUrl;
}


@Column(name = "pictrue_ftp_port", nullable = false, length = 10)
public String getPictrueFtpPort(){
    return this.pictrueFtpPort;
}


public void setPictrueFtpPort(String pictrueFtpPort){
    this.pictrueFtpPort = pictrueFtpPort;
}


@Column(name = "export_encode", nullable = false, length = 200)
public String getExportEncode(){
    return this.exportEncode;
}


public void setVideoFtpPort(String videoFtpPort){
    this.videoFtpPort = videoFtpPort;
}


public void setPictrueFtpUsername(String pictrueFtpUsername){
    this.pictrueFtpUsername = pictrueFtpUsername;
}


}