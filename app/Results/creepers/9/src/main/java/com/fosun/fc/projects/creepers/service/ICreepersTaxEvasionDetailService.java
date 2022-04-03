package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersTaxEvasionDetailDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasionDetail;
public interface ICreepersTaxEvasionDetailService extends BaseService{


public List<TCreepersTaxEvasionDetail> findListByName(String name)
;

public Page<CreepersTaxEvasionDetailDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public void deleteByName(String name)
;

public void saveEntity(List<TCreepersTaxEvasionDetail> entityList)
;

}