package com.fosun.fc.projects.creepers.redis.service.Impl;
 import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
public class MedicalSubServiceImpl implements IRedisSubService{

 private  Logger logger;

@Autowired
 private  ICreepersMedicalService creepersMedicalServiceImpl;

@Autowired
 private  ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;


@Override
public void handleMessage(Serializable message){
    logger.info("=============>MedicalSubServiceImpl.handleMessage start!");
    // 接收消息
    String jobName = message.toString();
    // 增加异常处理
    try {
        creepersMedicalServiceImpl.processByJob(jobName);
        logger.info("=============>MedicalSubServiceImpl.handmleMessage end!");
    } catch (Exception e) {
        e.printStackTrace();
        // 异常处理
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord("jobName:" + jobName);
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_GROUP.getValue());
        param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
        param.setErrorInfo(e.getMessage());
        creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
        logger.error("=============>MedicalSubServiceImpl.handleMessage end!");
    }
}


}