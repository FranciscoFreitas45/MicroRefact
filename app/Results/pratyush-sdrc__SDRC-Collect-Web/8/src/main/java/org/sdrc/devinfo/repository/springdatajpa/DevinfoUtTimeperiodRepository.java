package org.sdrc.devinfo.repository.springdatajpa;
 import java.util.Date;
import java.util.List;
import org.sdrc.devinfo.domain.UtTimeperiod;
import org.sdrc.devinfo.repository.UtTimeperiodRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface DevinfoUtTimeperiodRepository extends Repository<UtTimeperiod, Long>, UtTimeperiodRepository{


@Override
@Query("SELECT utTime FROM UtTimeperiod utTime WHERE utTime.timePeriod_NId " + " IN ( SELECT distinct utdata.timePeriod_NId FROM UtData utdata  WHERE utdata.IUSNId = :iusNid AND utdata.source_NId = :sourceNid )")
public List<UtTimeperiod> findBySource_Nid(Integer iusNid,Integer sourceNid)
;

@Override
@Query("SELECT timePeriod_NId  FROM UtTimeperiod where timePeriod in (SELECT Max(tm.timePeriod)" + " FROM UtData ut , UtTimeperiod tm   where ut.timePeriod_NId=tm.timePeriod_NId and ut.IUSNId=:iusNid and " + " ut.area_NId IN (:areaNid) ) ")
public Object findLatestTimePeriodNId(Integer iusNid,Integer[] areaNid)
;

@Override
@Query("SELECT utTime FROM UtTimeperiod utTime ORDER BY utTime.startDate DESC ")
public List<UtTimeperiod> getTimeperiodByLimit(Pageable pageable)
;

@Override
@Query("SELECT utTime FROM UtTimeperiod utTime WHERE utTime.startDate=:startDate AND utTime.endDate=:endDate")
public UtTimeperiod getByStartDateAndEndDate(Date startDate,Date endDate)
;

public void setStartDate(int id,Date startDate);

public void setEndDate(int id,Date endDate);

public void setPeriodicity(int id,String periodicity);

public void setTimePeriod(int id,String timePeriod);

}