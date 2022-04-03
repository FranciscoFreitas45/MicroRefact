package com.uec.imonitor.task.dao;
 import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uec.imonitor.task.bean.ScheduledTaskEntity;
@Repository("scheduledTaskJPARepository")
public interface IScheduledTaskJPARepository extends Serializable, JpaRepository<ScheduledTaskEntity, Long>{


@Query("from ScheduledTaskEntity a where a.enable = :enable")
public List<ScheduledTaskEntity> listAllByEnable(Boolean enable)
;

@Query("from ScheduledTaskEntity a where a.identification = :identification")
public ScheduledTaskEntity findByIdentification(String identification)
;

}