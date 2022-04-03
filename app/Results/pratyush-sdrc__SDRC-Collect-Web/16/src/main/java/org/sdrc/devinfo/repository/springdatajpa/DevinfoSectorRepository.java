package org.sdrc.devinfo.repository.springdatajpa;
 import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.sdrc.devinfo.repository.SectorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface DevinfoSectorRepository extends SectorRepository, JpaRepository<UtIndicatorClassificationsEn, Long>{


@Override
@Query("SELECT MAX(sector.IC_NId) from UtIndicatorClassificationsEn sector where sector.IC_Type = :IC_Type ")
public int findIcNIdbySourceType(String IC_Type)
;

@Override
@Query("SELECT distinct(ic.IC_NId),ic.IC_Name,ic.IC_Parent_NId" + " FROM UtIcIus icius, UtIndicatorClassificationsEn ic,UtIndicatorUnitSubgroup ius" + " WHERE ic.IC_Type='SC' AND icius.IC_NId = ic.IC_NId AND ius.IUSNId = icius.IUSNId " + " AND ius.indicator_NId in (SELECT indicator_NId " + " FROM UtIndicatorEn ind WHERE ind.short_Name = :indicatorType)")
public List<Object[]> findByIC_Type(String indicatorType)
;

@Override
@Query("SELECT uice FROM UtIndicatorClassificationsEn uice where uice.IC_Parent_NId = :IC_Parent_NId")
public List<UtIndicatorClassificationsEn> findByIC_Parent_NId(int IC_Parent_NId)
;

@Override
@Query("SELECT uice FROM UtIndicatorClassificationsEn uice where uice.IC_NId = :IC_NId")
public List<UtIndicatorClassificationsEn> findByIC_NId(int IC_Parent_NId)
;

}