package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
public interface ICreepersPublicTaxService extends BaseService{


public List<TCreepersPublicTax> queryAllPublicTax()
;

public void saveTCreepersPublicTax(TCreepersPublicTax entity)
;

public List<TCreepersPublicTax> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}