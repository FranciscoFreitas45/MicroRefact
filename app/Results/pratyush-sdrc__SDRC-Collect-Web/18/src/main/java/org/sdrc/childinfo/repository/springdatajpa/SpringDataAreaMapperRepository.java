package org.sdrc.childinfo.repository.springdatajpa;
 import java.util.List;
import org.sdrc.childinfo.domain.AreaMapper;
import org.sdrc.childinfo.repository.AreaMapperRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface SpringDataAreaMapperRepository extends Repository<AreaMapper, Integer>, AreaMapperRepository{


@Override
@Query("SELECT area from AreaMapper area WHERE area.childCode = :areaCode OR area.parentCode = :areaCode  ORDER BY area.childCode ASC")
public List<AreaMapper> getAreaNameAndItsChilds(String areaCode)
;

@Override
@Query("SELECT area from AreaMapper area WHERE area.parentCode = :areaCode ORDER BY area.childCode ASC")
public List<AreaMapper> findChildByParentCode(String areaCode)
;

@Override
@Query("SELECT area from AreaMapper area WHERE area.childCode = :childCode")
public List<AreaMapper> getAreaByAreaCode(String childCode)
;

@Override
@Query("SELECT area from AreaMapper area WHERE area.area_name = :areaName")
public List<AreaMapper> getAreaCodeByName(String areaName)
;

@Override
@Query("SELECT area from AreaMapper area WHERE area.area_name = :districtName and area.parentCode =:stateCode")
public List<AreaMapper> getAreaCodeByNameAndParentCode(String state,String district)
;

}