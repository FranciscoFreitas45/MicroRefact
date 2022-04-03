package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersErrorList;
public interface ICreepersErrorListService extends BaseService{


public void saveError(String merName,String errorDesc,String taskType)
;

public Page<CreepersErrorListDTO> queryErrorList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public List<TCreepersErrorList> queryList(CreepersErrorListDTO param)
;

}