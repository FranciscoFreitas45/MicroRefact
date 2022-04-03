package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;
public interface ICreepersTouristGuideService extends BaseService{


public void processByParam(CreepersLoginParamDTO param)
;

public void saveEntity(TCreepersTourGuide entity)
;

public List<TCreepersTourGuide> findByEntityForMap(TCreepersTourGuide entity)
;

public Map<String,Object> findByParamForMap(CreepersLoginParamDTO param)
;

}