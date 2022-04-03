package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersTradeMarkDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;
public interface ICreepersTradeMarkService extends BaseService{


public Page<CreepersTradeMarkDTO> queryTradeMarkList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void deleteByMerName(String merName)
;

public List<TCreepersTradeMark> findByMerName(String merName)
;

public void processByMerName(String merName)
;

public void saveEntity(List<TCreepersTradeMark> entity)
;

}