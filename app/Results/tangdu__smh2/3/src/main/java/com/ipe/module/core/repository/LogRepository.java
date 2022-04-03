package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Log;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
public interface LogRepository extends CustomerRepository<Log, String>{


@Modifying
@Query("update Log u set u.leaveTime=:leaveTime where u.accessTime=:accessTime and u.accessUserid=:accessUserid")
public int updateLogoutTime(Date leaveTime,Date accessTime,String accessUserid)
;

@Query("select max(accessTime) from Log where accessUserid=:accessUserid")
public Date findMaxAccessTime(String accessUserid)
;

}