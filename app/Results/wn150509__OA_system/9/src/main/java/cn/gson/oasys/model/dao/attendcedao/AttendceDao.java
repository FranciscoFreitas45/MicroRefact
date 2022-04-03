package cn.gson.oasys.model.dao.attendcedao;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.attendce.Attends;
import cn.gson.oasys.model.entity.note.Attachment;
import cn.gson.oasys.model.entity.note.Note;
import cn.gson.oasys.model.entity.user.User;
@Repository
public interface AttendceDao extends JpaRepository<Attends, Long>{


@Query("from Attends a where  (a.attendsRemark like %?1% or DATE_format(a.attendsTime,'%Y-%m-%d') like %?1% or a.user.userName like %?1% or " + "a.typeId in (select t.typeId from SystemTypeList t where t.typeName like %?1%) or " + "a.statusId in (select s.statusId from SystemStatusList s where s.statusName like %?1%)) and a.user.userId in ?2")
public Page<Attends> findsomemohu(String baseKey,List<Long> user,Pageable pa)
;

@Query("update Attends a set a.attendsTime=?1 ,a.attendHmtime=?2 ,a.statusId=?3 where a.attendsId=?4 ")
@Modifying(clearAutomatically = true)
public Integer updateatttime(Date date,String hourmin,Long statusIdlong,long attid)
;

@Query("from Attends a where a.user.userId=?1  ORDER BY a.statusId DESC ")
public Page<Attends> findByUserOrderByStatusIdDesc(long userid,Pageable pa)
;

@Query("SELECT count(*) from Attends a where DATE_FORMAT(a.attendsTime,'%Y-%m') like %?1% and a.statusId=?2 and a.user.userId=?3")
public Integer countnum(String month,long statusId,long userid)
;

@Query("SELECT count(*) from Attends a where DATE_FORMAT(a.attendsTime,'%Y-%m') like %?1% and a.user.userId=?2 and a.typeId=9")
public Integer countoffwork(String month,long userid)
;

@Query("from Attends a where a.user.userId=?1  ORDER BY a.statusId ASC ")
public Page<Attends> findByUserOrderByStatusIdAsc(long userid,Pageable pa)
;

@Query(nativeQuery = true, value = "select a.attends_id from aoa_attends_list a WHERE DATE_format(a.attends_time,'%Y-%m-%d') like %?1% and a.attends_user_id=?2 and a.type_id=9 ")
public Long findoffworkid(String date,long userid)
;

@Query(nativeQuery = true, value = "SELECT COUNT(*) from aoa_attends_list a WHERE DATE_format(a.attends_time,'%Y-%m-%d') like %?1% and a.attends_user_id=?2 ")
public Integer countrecord(String date,long userid)
;

@Query("from Attends a where a.user.userId=?1  ORDER BY a.attendsTime ASC ")
public Page<Attends> findByUserOrderByAttendsTimeAsc(long userid,Pageable pa)
;

@Query("FROM Attends a where a.attendsTime>?1 and a.attendsTime<?2 and a.user.userId in ?3")
public List<Attends> findoneweek(Date start,Date end,List<Long> user)
;

@Query("update Attends a set a.attendsRemark=?1 where a.attendsId=?2")
@Modifying
public Integer updateremark(String attendsRemark,long attendsId)
;

@Query("delete from Attends a where a.attendsId=?1")
@Modifying
public Integer delete(long aid)
;

@Query("from Attends a where a.user.userId in (:ids) ORDER BY a.attendsTime DESC ")
public Page<Attends> findByUserOrderByAttendsTimeDesc(List<Long> user,Pageable pa)
;

@Query("SELECT count(*) from Attends a where DATE_FORMAT(a.attendsTime,'%Y-%m') like %?1%  and a.user.userId=?2 and a.typeId=8")
public Integer counttowork(String month,long userid)
;

@Query("from Attends a where a.user.userId=?1  ORDER BY a.typeId DESC ")
public Page<Attends> findByUserOrderByTypeIdDesc(long userid,Pageable pa)
;

@Query(nativeQuery = true, value = "SELECT * from aoa_attends_list a WHERE DATE_format(a.attends_time,'%Y-%m-%d') like %?1% and a.attends_user_id=?2 ORDER  BY a.attends_time DESC  LIMIT 1")
public Attends findlastest(String date,long userid)
;

@Query("from Attends a where  (a.attendsRemark like %?1% or DATE_format(a.attendsTime,'%Y-%m-%d') like %?1% or a.user.userName like %?1% or " + "a.typeId in (select t.typeId from SystemTypeList t where t.typeName like %?1%) or " + "a.statusId in (select s.statusId from SystemStatusList s where s.statusName like %?1%)) and a.user.userId=?2")
public Page<Attends> findonemohu(String baseKey,long userid,Pageable pa)
;

@Query("from Attends a where a.user.userId=?1  ORDER BY a.typeId ASC ")
public Page<Attends> findByUserOrderByTypeIdAsc(long userid,Pageable pa)
;

@Query("SELECT sum(a.holidayDays) from Attends a where DATE_FORMAT(a.holidayStart,'%Y-%m') like %?1% and a.statusId=?2 and a.user.userId=?3")
public Integer countothernum(String month,long statusId,long userid)
;

public Set<Attends> getaSet(Long userId);

public void setaSet(Long userId,Set<Attends> aSet);

public void setHolidayStart(Long id,Date holidayStart);

public void setUser(Long id,User user);

public void setStatusId(Long id,Long statusId);

}