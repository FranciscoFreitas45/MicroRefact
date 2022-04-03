package com.fosun.fc.projects.creepers.service;
 import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;
public interface ICreepersConfigService extends BaseService{


public void updateEntity(TCreepersConfig entity)
;

public Page<CreepersConfigDTO> queryForListByRequestType(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public long countEntity(String requestType)
;

public TCreepersConfig queryEntity(String requestType)
;

}