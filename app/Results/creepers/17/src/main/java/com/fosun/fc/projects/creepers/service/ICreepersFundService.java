package com.fosun.fc.projects.creepers.service;
 import java.util.Map;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
public interface ICreepersFundService extends BaseService{


public void processByParam(CreepersLoginParamDTO param)
;

public Map<String,Object> findByLoginNameForMap(String loginName)
;

public void deleteAndSave(Map<String,Object> map)
;

public void deleteByLoginName(String loginName)
;

}