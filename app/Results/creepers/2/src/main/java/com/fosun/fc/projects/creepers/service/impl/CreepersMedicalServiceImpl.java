package com.fosun.fc.projects.creepers.service.impl;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersMedicalDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersMedical;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalGMPGDProcessor;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalGSPGDProcessor;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalInstrumentDomesticProcessor;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalInstrumentForeignProcessor;
import com.fosun.fc.projects.creepers.pipeline.CFDA.MedicalPipline;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.service.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import com.fosun.fc.projects.creepers.Interface.MedicalGMPGDProcessor;
import com.fosun.fc.projects.creepers.Interface.MedicalGSPGDProcessor;
@Service("creepersMedicalServiceImpl")
public class CreepersMedicalServiceImpl extends CreepersBaseServiceImplimplements ICreepersMedicalService{

 private Logger logger;

@Autowired
 private  CreepersMedicalDao creepersMedicalDao;

@Autowired
 private  MedicalInstrumentForeignProcessor medicalInstrumentForeignProcessor;

@Autowired
 private  MedicalInstrumentDomesticProcessor medicalInstrumentDomesticProcessor;

@Autowired
 private  MedicalGMPGDProcessor medicalGMPGDProcessor;

@Autowired
 private  MedicalGSPGDProcessor medicalGSPGDProcessor;

@Autowired
 private  MedicalPipline medicalPipline;

@Autowired
 private  ICreepersJobService creepersJobServiceImpl;


public void processByJobMedicalInstrumentForeign(String jobName,CreepersJobDTO job){
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalInstrumentForeign start!");
    // 查询任务
    Request request = null;
    String threadNum = "1";
    CreepersParamDTO param = new CreepersParamDTO();
    param.setTaskType(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue());
    param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
    if (StringUtils.isBlank(job.getMemo())) {
        // http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=27&bcId=118103063506935484150101953610
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=27&bcId=118103063506935484150101953610");
        // 初始化Request
        request = CommonMethodUtils.buildDefaultRequest(param, param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        threadNum = String.valueOf(job.getThreadNum());
    } else {
        // 反序列化Request
        request = JSON.parseObject(job.getMemo(), Request.class);
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, request.getUrl());
        threadNum = String.valueOf(request.getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM));
        creepersJobServiceImpl.updateResumeRequestByJobName(jobName, StringUtils.EMPTY);
    }
    if (StringUtils.isBlank(threadNum)) {
        threadNum = "1";
    }
    request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, threadNum);
    // 启动爬虫
    logger.info("=============>>启动爬虫!");
    Spider.create(medicalInstrumentForeignProcessor).addPipeline(medicalPipline).setDownloader(new HttpRequestDownloader().setParam(param)).thread(Integer.parseInt(threadNum.trim())).addRequest(request).run();
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalInstrumentForeign end!");
}


public void processByJobMedicalInstrumentDomestic(String jobName,CreepersJobDTO job){
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalInstrumentDomestic start!");
    // 查询任务
    Request request = null;
    String threadNum = "1";
    CreepersParamDTO param = new CreepersParamDTO();
    param.setTaskType(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue());
    param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
    if (StringUtils.isBlank(job.getMemo())) {
        // http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=26&bcId=118103058617027083838706701567
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=26&bcId=118103058617027083838706701567");
        // 初始化Request
        request = CommonMethodUtils.buildDefaultRequest(param, param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        threadNum = String.valueOf(job.getThreadNum());
    } else {
        // 反序列化Request
        request = JSON.parseObject(job.getMemo(), Request.class);
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, request.getUrl());
        threadNum = String.valueOf(request.getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM));
        creepersJobServiceImpl.updateResumeRequestByJobName(jobName, StringUtils.EMPTY);
    }
    if (StringUtils.isBlank(threadNum)) {
        threadNum = "1";
    }
    request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, threadNum);
    // 启动爬虫
    logger.info("=============>>启动爬虫!");
    Spider.create(medicalInstrumentDomesticProcessor).addPipeline(medicalPipline).setDownloader(new HttpRequestDownloader().setParam(param)).thread(Integer.parseInt(threadNum)).addRequest(request).run();
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalInstrumentDomestic end!");
}


public void processByJobMedicalGSP(String jobName,CreepersJobDTO job){
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalGSP start!");
    // 查询任务
    Request request = null;
    String threadNum = "1";
    CreepersParamDTO param = new CreepersParamDTO();
    param.setTaskType(BaseConstant.TaskListType.MEDICAL_GSP_LIST.getValue());
    param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
    param.putOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX, "http://www.sda.gov.cn/directory/web/WS01/images/(.*).doc");
    if (StringUtils.isBlank(job.getMemo())) {
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, MedicalGSPGDProcessor.URL_PAGE_TEMPLATE + "1&index");
        // 初始化Request
        request = CommonMethodUtils.buildGetRequestCarryMap(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        threadNum = String.valueOf(job.getThreadNum());
    } else {
        // 反序列化Request
        request = JSON.parseObject(job.getMemo(), Request.class);
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, request.getUrl());
        threadNum = String.valueOf(request.getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM));
        creepersJobServiceImpl.updateResumeRequestByJobName(jobName, StringUtils.EMPTY);
    }
    if (StringUtils.isBlank(threadNum)) {
        threadNum = "1";
    }
    request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, threadNum);
    // 启动爬虫
    logger.info("=============>>启动爬虫!");
    Spider.create(medicalGSPGDProcessor).addPipeline(medicalPipline).setDownloader(new HttpRequestDownloader().setParam(param)).thread(Integer.parseInt(threadNum)).addRequest(request).run();
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalGSP end!");
}


@Override
public List<TCreepersMedical> findListByKey(String key){
    List<TCreepersMedical> list = new ArrayList<TCreepersMedical>();
    if (StringUtils.isBlank(key)) {
        return list;
    }
    return creepersMedicalDao.findByKey(key);
}


@Override
public void processByJob(String jobName){
    if (StringUtils.isBlank(jobName)) {
        return;
    }
    // 查询任务
    CreepersJobDTO job = creepersJobServiceImpl.findJob(jobName);
    if (job == null) {
        return;
    }
    if (BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue().equals(job.getJobGroup())) {
        this.processByJobMedicalInstrumentDomestic(jobName, job);
    } else if (BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue().equals(job.getJobGroup())) {
        this.processByJobMedicalInstrumentForeign(jobName, job);
    } else if (BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue().equals(job.getJobGroup())) {
        this.processByJobMedicalGMP(jobName, job);
    } else if (BaseConstant.TaskListType.MEDICAL_GSP_LIST.getValue().equals(job.getJobGroup())) {
        this.processByJobMedicalGSP(jobName, job);
    }
}


public void processByJobMedicalGMP(String jobName,CreepersJobDTO job){
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalGMP start!");
    // 查询任务
    Request request = null;
    String threadNum = "1";
    CreepersParamDTO param = new CreepersParamDTO();
    param.setTaskType(BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue());
    param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
    param.putOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX, "http://www.sda.gov.cn/directory/web/WS01/images/(.*).xls");
    if (StringUtils.isBlank(job.getMemo())) {
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, MedicalGMPGDProcessor.URL_PAGE_TEMPLATE + "1&index");
        // 初始化Request
        request = CommonMethodUtils.buildGetRequestCarryMap(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        threadNum = String.valueOf(job.getThreadNum());
    } else {
        // 反序列化Request
        request = JSON.parseObject(job.getMemo(), Request.class);
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, request.getUrl());
        threadNum = String.valueOf(request.getExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM));
        creepersJobServiceImpl.updateResumeRequestByJobName(jobName, StringUtils.EMPTY);
    }
    if (StringUtils.isBlank(threadNum)) {
        threadNum = "1";
    }
    request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, threadNum);
    // 启动爬虫
    logger.info("=============>>启动爬虫!");
    Spider.create(medicalGMPGDProcessor).addPipeline(medicalPipline).setDownloader(new HttpRequestDownloader().setParam(param)).thread(Integer.parseInt(threadNum)).addRequest(request).run();
    logger.info("=============>>CreepersMedicalServiceImpl.processByJob.processByJobMedicalGMP end!");
}


@Override
public void saveEntity(List<TCreepersMedical> entityList){
    for (TCreepersMedical entity : entityList) {
        this.saveEntity(entity);
    }
}


}