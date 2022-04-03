package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersCourtCorpBondsDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;
public interface ICreepersCourtCorpBondsService extends BaseService{


public void queryByName(String name)
;

public List<TCreepersCourtCorpBonds> findListByName(String name)
;

public List<TCreepersCourtCorpBonds> findListByNameAndCode(String name,String code)
;

public Page<CreepersCourtCorpBondsDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void processByJob(String jobName)
;

public void saveEntity(List<TCreepersCourtCorpBonds> entityList)
;

public void saveOrUpdate(TCreepersCourtCorpBonds entity)
;

}