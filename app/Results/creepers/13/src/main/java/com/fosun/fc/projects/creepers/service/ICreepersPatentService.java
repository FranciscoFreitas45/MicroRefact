package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersPatentDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPatent;
public interface ICreepersPatentService extends BaseService{


public List<TCreepersPatent> queryAllOlDetail()
;

public void deleteByMerName(String merName)
;

public List<TCreepersPatent> findByMerName(String rptNo)
;

public Page<CreepersPatentDTO> queryPatentList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void processByMerName(String merName)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

public void saveEntity(List<TCreepersPatent> entity)
;

}