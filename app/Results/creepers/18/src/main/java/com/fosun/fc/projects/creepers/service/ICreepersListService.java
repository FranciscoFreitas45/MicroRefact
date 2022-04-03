package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
public interface ICreepersListService extends BaseService{


public void deleteList(CreepersLoginParamDTO param)
;

@SuppressWarnings("rawtypes")
public JsonResult addTaskByParam(CreepersLoginParamDTO param)
;

public void doRecycleByParam(CreepersLoginParamDTO param)
;

@SuppressWarnings("rawtypes")
public List selectList(CreepersLoginParamDTO param)
;

@SuppressWarnings("rawtypes")
public List queryListByKey(CreepersLoginParamDTO param)
;

@SuppressWarnings("rawtypes")
public JsonResult processByParam(CreepersLoginParamDTO param)
;

public void insertList(CreepersLoginParamDTO param)
;

public void doRecycleByMerName(String requestType,String merName)
;

public Page<?> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType,String taskType)
;

@SuppressWarnings("rawtypes")
public JsonResult addTaskByMerName(String requestType,String merName)
;

@SuppressWarnings("rawtypes")
public JsonResult processByMerName(String requestType,String merName)
;

public void updateList(CreepersLoginParamDTO param)
;

}