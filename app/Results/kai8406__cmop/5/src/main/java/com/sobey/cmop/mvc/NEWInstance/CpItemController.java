package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CpItemController {

 private CpItem cpitem;

 private CpItem cpitem;


@PutMapping
("/setRecordBitrate")
public void setRecordBitrate(@RequestParam(name = "recordBitrate") String recordBitrate){
cpitem.setRecordBitrate(recordBitrate);
}


@PutMapping
("/setExportEncode")
public void setExportEncode(@RequestParam(name = "exportEncode") String exportEncode){
cpitem.setExportEncode(exportEncode);
}


@PutMapping
("/setRecordType")
public void setRecordType(@RequestParam(name = "recordType") Integer recordType){
cpitem.setRecordType(recordType);
}


@PutMapping
("/setRecordTime")
public void setRecordTime(@RequestParam(name = "recordTime") String recordTime){
cpitem.setRecordTime(recordTime);
}


@PutMapping
("/setRecordDuration")
public void setRecordDuration(@RequestParam(name = "recordDuration") Integer recordDuration){
cpitem.setRecordDuration(recordDuration);
}


@PutMapping
("/setPublishUrl")
public void setPublishUrl(@RequestParam(name = "publishUrl") String publishUrl){
cpitem.setPublishUrl(publishUrl);
}


@PutMapping
("/setIsPushCtp")
public void setIsPushCtp(@RequestParam(name = "isPushCtp") Boolean isPushCtp){
cpitem.setIsPushCtp(isPushCtp);
}


@PutMapping
("/setVideoFtpIp")
public void setVideoFtpIp(@RequestParam(name = "videoFtpIp") String videoFtpIp){
cpitem.setVideoFtpIp(videoFtpIp);
}


@PutMapping
("/setVideoFtpPort")
public void setVideoFtpPort(@RequestParam(name = "videoFtpPort") String videoFtpPort){
cpitem.setVideoFtpPort(videoFtpPort);
}


@PutMapping
("/setVideoFtpUsername")
public void setVideoFtpUsername(@RequestParam(name = "videoFtpUsername") String videoFtpUsername){
cpitem.setVideoFtpUsername(videoFtpUsername);
}


@PutMapping
("/setVideoFtpPassword")
public void setVideoFtpPassword(@RequestParam(name = "videoFtpPassword") String videoFtpPassword){
cpitem.setVideoFtpPassword(videoFtpPassword);
}


@PutMapping
("/setVideoFtpRootpath")
public void setVideoFtpRootpath(@RequestParam(name = "videoFtpRootpath") String videoFtpRootpath){
cpitem.setVideoFtpRootpath(videoFtpRootpath);
}


@PutMapping
("/setVideoFtpUploadpath")
public void setVideoFtpUploadpath(@RequestParam(name = "videoFtpUploadpath") String videoFtpUploadpath){
cpitem.setVideoFtpUploadpath(videoFtpUploadpath);
}


@PutMapping
("/setVideoOutputGroup")
public void setVideoOutputGroup(@RequestParam(name = "videoOutputGroup") String videoOutputGroup){
cpitem.setVideoOutputGroup(videoOutputGroup);
}


@PutMapping
("/setVideoOutputWay")
public void setVideoOutputWay(@RequestParam(name = "videoOutputWay") String videoOutputWay){
cpitem.setVideoOutputWay(videoOutputWay);
}


@PutMapping
("/setPictrueFtpIp")
public void setPictrueFtpIp(@RequestParam(name = "pictrueFtpIp") String pictrueFtpIp){
cpitem.setPictrueFtpIp(pictrueFtpIp);
}


@PutMapping
("/setPictrueFtpPort")
public void setPictrueFtpPort(@RequestParam(name = "pictrueFtpPort") String pictrueFtpPort){
cpitem.setPictrueFtpPort(pictrueFtpPort);
}


@PutMapping
("/setPictrueFtpUsername")
public void setPictrueFtpUsername(@RequestParam(name = "pictrueFtpUsername") String pictrueFtpUsername){
cpitem.setPictrueFtpUsername(pictrueFtpUsername);
}


@PutMapping
("/setPictrueFtpPassword")
public void setPictrueFtpPassword(@RequestParam(name = "pictrueFtpPassword") String pictrueFtpPassword){
cpitem.setPictrueFtpPassword(pictrueFtpPassword);
}


@PutMapping
("/setPictrueFtpRootpath")
public void setPictrueFtpRootpath(@RequestParam(name = "pictrueFtpRootpath") String pictrueFtpRootpath){
cpitem.setPictrueFtpRootpath(pictrueFtpRootpath);
}


@PutMapping
("/setPictrueFtpUploadpath")
public void setPictrueFtpUploadpath(@RequestParam(name = "pictrueFtpUploadpath") String pictrueFtpUploadpath){
cpitem.setPictrueFtpUploadpath(pictrueFtpUploadpath);
}


@PutMapping
("/setPictrueOutputGroup")
public void setPictrueOutputGroup(@RequestParam(name = "pictrueOutputGroup") String pictrueOutputGroup){
cpitem.setPictrueOutputGroup(pictrueOutputGroup);
}


@PutMapping
("/setPictrueOutputMedia")
public void setPictrueOutputMedia(@RequestParam(name = "pictrueOutputMedia") String pictrueOutputMedia){
cpitem.setPictrueOutputMedia(pictrueOutputMedia);
}


}