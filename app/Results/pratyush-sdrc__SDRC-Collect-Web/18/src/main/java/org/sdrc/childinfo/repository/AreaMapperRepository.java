package org.sdrc.childinfo.repository;
 import java.util.List;
import org.sdrc.childinfo.domain.AreaMapper;
import org.springframework.data.repository.query.Param;
public interface AreaMapperRepository {


public List<AreaMapper> getAreaNameAndItsChilds(String areaCode)
;

public List<AreaMapper> findChildByParentCode(String areaId)
;

public List<AreaMapper> getAreaByAreaCode(String childCode)
;

public List<AreaMapper> getAreaCodeByName(String areaName)
;

public List<AreaMapper> getAreaCodeByNameAndParentCode(String state,String district)
;

}