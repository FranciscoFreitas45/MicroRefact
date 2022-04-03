package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import com.fosun.fc.projects.creepers.entity.TCreepersMedical;
public interface ICreepersMedicalService extends BaseService{


public List<TCreepersMedical> findListByKey(String key)
;

public void saveEntity(List<TCreepersMedical> entityList)
;

public void processByJob(String jobName)
;

}