package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersJudgementDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;
public interface ICreepersJudgementService extends BaseService{


public Map<String,Object> findByMerNoForMap(String merNo)
;

public void deleteByMerName(String merName)
;

public List<TCreepersJudgement> findByMerName(String merName)
;

public Page<CreepersJudgementDTO> queryJudgementList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void processByMerName(String merName)
;

public List<TCreepersJudgement> queryAll()
;

public void saveEntity(List<TCreepersJudgement> entityList)
;

}