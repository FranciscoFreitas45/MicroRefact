package com.sobey.cmop.mvc.service.paas;
 import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.CPConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.CpItemDao;
import com.sobey.cmop.mvc.dao.CpProgramItemDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.CpProgramItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.DTO.ServiceTag;
@Service
@Transactional(readOnly = true)
public class CPService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  CpItemDao cpItemDao;

@Resource
 private  CpProgramItemDao cpProgramItemDao;


@Transactional(readOnly = false)
public void updateCPToApply(CpItem cpItem,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia){
    cpItem.setRecordStreamUrl(recordStreamUrl);
    cpItem.setRecordBitrate(recordBitrate);
    cpItem.setExportEncode(exportEncode);
    cpItem.setRecordType(recordType);
    cpItem.setRecordTime(recordTime);
    cpItem.setRecordDuration(recordDuration);
    cpItem.setPublishUrl(publishUrl);
    cpItem.setIsPushCtp(CPConstant.IsPushCtp.推送.toString().equals(isPushCtp) ? true : false);
    // video
    cpItem.setVideoFtpIp(videoFtpIp);
    cpItem.setVideoFtpPort(videoFtpPort);
    cpItem.setVideoFtpUsername(videoFtpUsername);
    cpItem.setVideoFtpPassword(videoFtpPassword);
    cpItem.setVideoFtpRootpath(videoFtpRootpath);
    cpItem.setVideoFtpUploadpath(videoFtpUploadpath);
    cpItem.setVideoOutputGroup(videoOutputGroup);
    cpItem.setVideoOutputWay(videoOutputWay);
    // pictrue
    cpItem.setPictrueFtpIp(pictrueFtpIp);
    cpItem.setPictrueFtpPort(pictrueFtpPort);
    cpItem.setPictrueFtpUsername(pictrueFtpUsername);
    cpItem.setPictrueFtpPassword(pictrueFtpPassword);
    cpItem.setPictrueFtpRootpath(pictrueFtpRootpath);
    cpItem.setPictrueFtpUploadpath(pictrueFtpUploadpath);
    cpItem.setPictrueOutputGroup(pictrueOutputGroup);
    cpItem.setPictrueOutputMedia(pictrueOutputMedia);
    this.saveOrUpdate(cpItem);
}


@Transactional(readOnly = false)
public void saveResourcesByCP(Resources resources,Integer serviceTagId,String changeDescription,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    CpItem cpItem = this.getCpItem(resources.getServiceId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareCP(resources, change, cpItem, recordStreamUrl, recordBitrate, exportEncode, recordType, recordTime, recordDuration, publishUrl, isPushCtp, videoFtpIp, videoFtpPort, videoFtpUsername, videoFtpPassword, videoFtpRootpath, videoFtpUploadpath, videoOutputGroup, videoOutputWay, pictrueFtpIp, pictrueFtpPort, pictrueFtpUsername, pictrueFtpPassword, pictrueFtpRootpath, pictrueFtpUploadpath, pictrueOutputGroup, pictrueOutputMedia);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    this.updateCPToApply(cpItem, recordStreamUrl, recordBitrate, exportEncode, recordType, recordTime, recordDuration, publishUrl, isPushCtp, videoFtpIp, videoFtpPort, videoFtpUsername, videoFtpPassword, videoFtpRootpath, videoFtpUploadpath, videoOutputGroup, videoOutputWay, pictrueFtpIp, pictrueFtpPort, pictrueFtpUsername, pictrueFtpPassword, pictrueFtpRootpath, pictrueFtpUploadpath, pictrueOutputGroup, pictrueOutputMedia);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


public CpItem getCpItem(Integer id){
    return cpItemDao.findOne(id);
}


public void deletecpProgramItem(Integer id){
    cpProgramItemDao.delete(id);
}


@Transactional(readOnly = false)
public void delete(Integer id){
    cpItemDao.delete(id);
}


@Transactional(readOnly = false)
public void saveCPToApply(Apply apply,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia,String[] fileNames,String[] fileSizes){
    // Step1. 创建一个服务申请Apply
    comm.applyService.saveApplyByServiceType(apply, ApplyConstant.ServiceType.云生产.toInteger());
    CpItem cpItem = new CpItem();
    cpItem.setIdentifier(comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.CP.toInteger()));
    cpItem.setApply(apply);
    cpItem.setRecordStreamUrl(recordStreamUrl);
    cpItem.setRecordBitrate(recordBitrate);
    cpItem.setExportEncode(exportEncode);
    cpItem.setRecordDuration(recordDuration);
    cpItem.setRecordType(recordType);
    cpItem.setRecordTime(recordTime);
    cpItem.setPublishUrl(publishUrl);
    cpItem.setIsPushCtp(CPConstant.IsPushCtp.推送.toString().equals(isPushCtp) ? true : false);
    // video
    cpItem.setVideoFtpIp(videoFtpIp);
    cpItem.setVideoFtpPort(videoFtpPort);
    cpItem.setVideoFtpUsername(videoFtpUsername);
    cpItem.setVideoFtpPassword(videoFtpPassword);
    cpItem.setVideoFtpRootpath(videoFtpRootpath);
    cpItem.setVideoFtpUploadpath(videoFtpUploadpath);
    cpItem.setVideoOutputGroup(videoOutputGroup);
    cpItem.setVideoOutputWay(videoOutputWay);
    // pictrue
    cpItem.setPictrueFtpIp(pictrueFtpIp);
    cpItem.setPictrueFtpPort(pictrueFtpPort);
    cpItem.setPictrueFtpUsername(pictrueFtpUsername);
    cpItem.setPictrueFtpPassword(pictrueFtpPassword);
    cpItem.setPictrueFtpRootpath(pictrueFtpRootpath);
    cpItem.setPictrueFtpUploadpath(pictrueFtpUploadpath);
    cpItem.setPictrueOutputGroup(pictrueOutputGroup);
    cpItem.setPictrueOutputMedia(pictrueOutputMedia);
    this.saveOrUpdate(cpItem);
    // TODO 附件.暂时未完成,还要考虑到邮件发送.
    if (fileNames != null) {
        for (int i = 0; i < fileNames.length; i++) {
            CpProgramItem cpProgramItem = new CpProgramItem();
            cpProgramItem.setCpItem(cpItem);
            cpProgramItem.setName(fileNames[i]);
            cpProgramItem.setSize(Integer.valueOf(fileSizes[i]));
            this.saveOrUpdate(cpProgramItem);
        }
    }
}


@Transactional(readOnly = false)
public CpItem saveOrUpdate(CpItem cpItem){
    return cpItemDao.save(cpItem);
}


}