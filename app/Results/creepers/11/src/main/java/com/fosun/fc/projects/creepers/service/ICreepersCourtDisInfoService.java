package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersCourtDisInfoDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;
public interface ICreepersCourtDisInfoService extends BaseService{


public List<TCreepersCourtDisInfo> findListByName(String name)
;

public Page<CreepersCourtDisInfoDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void deleteByName(String name)
;

public void saveEntity(List<TCreepersCourtDisInfo> entityList)
;

}