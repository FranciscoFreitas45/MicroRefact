package cn.gson.oasys.model.dao.user;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.user.UserLog;
@Repository
public interface UserLogDao extends JpaRepository<UserLog, Long>{


@Query("from UserLog u where u.user.userId=?1 order by u.logTime DESC")
public Page<UserLog> findByUserOrderBylogTimeDesc(long userid,Pageable pa)
;

@Query("from UserLog u where u.user.userId=?1 order by u.logTime ASC")
public Page<UserLog> findByUserOrderBylogTimeAsc(long userid,Pageable pa)
;

@Query(nativeQuery = true, value = ("SELECT * from aoa_user_log where aoa_user_log.user_id=?1 ORDER BY aoa_user_log.log_time DESC LIMIT 0,1"))
public UserLog findByUserlaset(long userid)
;

@Query(nativeQuery = true, value = ("SELECT * from aoa_user_log where aoa_user_log.user_id=?1 ORDER BY aoa_user_log.log_time DESC LIMIT 0,13"))
public List<UserLog> findByUser(long userid)
;

@Query("from UserLog u where u.user.userId=?1 and (u.title like %?2% or DATE_format(u.logTime,'%Y-%m-%d ') like %?2% or u.ipAddr like %?2%) order by u.logTime DESC")
public Page<UserLog> findbasekey(long userid,String baseKey,Pageable pa)
;

}