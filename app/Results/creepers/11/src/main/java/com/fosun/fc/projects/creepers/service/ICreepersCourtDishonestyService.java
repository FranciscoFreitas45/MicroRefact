package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersCourtDishonestDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDishonest;
public interface ICreepersCourtDishonestyService extends BaseService{


public void queryByName(String name)
;

public List<TCreepersCourtDishonest> findListByName(String name)
;

public List<TCreepersCourtDishonest> findListByNameAndCode(String name,String code)
;

public Page<CreepersCourtDishonestDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void processByJob(String JobName)
;

public void saveEntity(List<TCreepersCourtDishonest> entityList)
;

public void saveOrUpdate(TCreepersCourtDishonest entity)
;

}