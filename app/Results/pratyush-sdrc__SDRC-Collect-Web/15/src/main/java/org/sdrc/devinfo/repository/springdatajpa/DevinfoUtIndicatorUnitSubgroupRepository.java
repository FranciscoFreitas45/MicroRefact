package org.sdrc.devinfo.repository.springdatajpa;
 import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorUnitSubgroup;
import org.sdrc.devinfo.repository.UtIndicatorUnitSubgroupRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface DevinfoUtIndicatorUnitSubgroupRepository extends UtIndicatorUnitSubgroupRepository, Repository<UtIndicatorUnitSubgroup, Integer>{


@Override
@Query("SELECT utIus FROM UtIndicatorUnitSubgroup utIus WHERE utIus.IUSNId=:IUSNId")
public UtIndicatorUnitSubgroup findByIUSNId(int IUSNId)
;

@Override
@Query("SELECT utIus FROM UtIndicatorUnitSubgroup utIus WHERE utIus.indicator_NId=:indicator_NId " + "AND utIus.subgroup_Val_NId=:subgroup_Val_NId " + "AND utIus.unit_NId=:unit_NId")
public UtIndicatorUnitSubgroup findByIndicatorNIdAndSubgroupNidAndUnitNid(int indicatorNid,int subgroupNid,int unitNid)
;

@Override
@Query("SELECT utIus FROM UtIndicatorUnitSubgroup utIus WHERE utIus.unit_NId=:unit_NId")
public List<UtIndicatorUnitSubgroup> findByUnitNid(int unitNid)
;

@Override
@Query(value = "select ius.IUSNId as iusNId, ius.Indicator_NId as indicatorId,cast(ie.Indicator_Name as varchar(250)) as indicatorName, " + "ius.Unit_NId as unitId,cast(ue.Unit_Name as varchar) as unitName," + " ius.Subgroup_Val_NId as subgroupId,cast(se.Subgroup_Val as varchar) as subgroupName " + "from ut_indicator_unit_subgroup ius, ut_indicator_en ie, ut_unit_en ue, " + "ut_subgroup_vals_en se, ut_ic_ius ii where ii.IC_NId=:id and ii.IUSNId = ius.IUSNId " + "and ie.Indicator_NId = ius.Indicator_NId and ue.Unit_NId = ius.Unit_NId " + "and ius.Subgroup_Val_NId = se.Subgroup_Val_NId", nativeQuery = true)
public List<Object[]> getIUS(int id)
;

@Override
@Query("SELECT utIus FROM UtIndicatorUnitSubgroup utIus " + " WHERE utIus.indicator_NId=(SELECT utIus.indicator_NId FROM UtIndicatorUnitSubgroup utIus WHERE utIus.IUSNId=:IUSNId) " + " AND utIus.subgroup_Val_NId=(SELECT utIus.subgroup_Val_NId FROM UtIndicatorUnitSubgroup utIus WHERE utIus.IUSNId=:IUSNId) " + " AND utIus.unit_NId=:unit_NId ")
public UtIndicatorUnitSubgroup findByIUSNIdAndUnitNid(int IUSNId,int unitNid)
;

}