package com.fosun.fc.projects.creepers.service;
 import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
public interface ICreepersExceptionHandleService {


public void handleJobExceptionAndPrintLog(CreepersParamDTO param)
;

public void insertOrUpdateErrorList(CreepersLoginParamDTO param)
;

public void handleExceptionAndPrintLog(CreepersLoginParamDTO param)
;

}