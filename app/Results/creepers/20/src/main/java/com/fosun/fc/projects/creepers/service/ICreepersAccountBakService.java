package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersAccountBakDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
public interface ICreepersAccountBakService extends BaseService{


public void updateTCreepersAccountBak(TCreepersAccountBak entity)
;

public void saveTCreepersAccountBak(TCreepersAccountBak entity)
;

public List<TCreepersAccountBak> findByStatus(String status)
;

public void saveOrIfNotAccountBak(TCreepersAccountBak entity)
;

public void updateStatusByRptNo(String rptNo)
;

public List<TCreepersAccountBak> findByUsr(String usr)
;

public Page<CreepersAccountBakDTO> geTCreepersAccountBakList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public TCreepersAccountBak findTop1ByUsrAndCde(String usr,String cde)
;

public List<TCreepersAccountBak> findByRptNo(String rptNo)
;

public Map<String,Object> findByRptNoForMap(String rptNo)
;

public List<TCreepersAccountBak> queryAllCreepersAccount()
;

}