package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
public interface ICreepersCcDetailService extends BaseService{


public void saveTCreepersCcDetail(TCreepersCcDetail entity)
;

public List<TCreepersCcDetail> queryAllCcDetail()
;

public List<TCreepersCcDetail> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

}