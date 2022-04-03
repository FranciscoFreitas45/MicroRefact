package cn.gson.oasys.model.dao.taskdao;
 import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.system.SystemStatusList;
import cn.gson.oasys.model.entity.task.Taskuser;
import cn.gson.oasys.model.entity.user.User;
public interface TaskuserDao extends PagingAndSortingRepository<Taskuser, Long>{


@Query("select tu.statusId from Taskuser tu where tu.taskId.taskId=:id ")
public List<Integer> findByTaskId(Long id)
;

@Query("update Taskuser ta set ta.statusId=:statusid where ta.taskId.taskId=:taskid")
@Modifying
public int updatestatus(Long taskid,Integer statusid)
;

@Query("select tu.statusId from Taskuser tu where tu.userId.userId=:userid and tu.taskId.taskId=:taskid ")
public Long findByuserIdAndTaskId(Long userid,Long taskid)
;

@Query("select tu.taskId.taskId from Taskuser tu where tu.userId.userId=:userid ")
public List<Long> findByUserId(Long userid)
;

public List<Taskuser> findByUserIdAndStatusId(User user,Integer id)
;

}