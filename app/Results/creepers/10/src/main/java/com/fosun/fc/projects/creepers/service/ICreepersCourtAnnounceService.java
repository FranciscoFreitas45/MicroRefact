package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersCourtAnnounceDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;
public interface ICreepersCourtAnnounceService extends BaseService{


public Page<CreepersCourtAnnounceDTO> findCourtAnnounceList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void deleteByMerName(String name)
;

public List<TCreepersCourtAnnounce> findByMerName(String merName)
;

public void processByMerName(String merName)
;

public void saveEntity(List<TCreepersCourtAnnounce> entityList)
;

}