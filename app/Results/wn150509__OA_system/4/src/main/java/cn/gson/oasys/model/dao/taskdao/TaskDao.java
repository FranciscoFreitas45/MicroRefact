package cn.gson.oasys.model.dao.taskdao;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.task.Tasklist;
import cn.gson.oasys.model.entity.user.User;
public interface TaskDao extends JpaRepository<Tasklist, Long>{


public Page<Tasklist> findByUsersIdOrderByStatusId(User userId,Pageable pa)
;

@Query("from Tasklist t where t.typeId = ?1  and t.taskId in (?2)")
public Page<Tasklist> findtaskTypeIdAndTaskId(Long typeId,List<Long> taskids,Pageable pa)
;

public Page<Tasklist> findByTickingIsNotNull(Pageable pa)
;

@Query("update Tasklist ta set ta.statusId=:statusid where ta.taskId=:taskid")
@Modifying
public int update(Long taskid,Integer statusid)
;

@Query("select tl from Tasklist tl where tl.taskId=:taskid and tl.publishTime like  %:title%")
public Tasklist findByPublishTimeLikeAndTaskId(Long taskid,String title)
;

@Query("from Tasklist t where t.usersId	= ?1  and t.taskId in (?2)")
public Page<Tasklist> findtaskUsersIdAndTaskId(User user,List<Long> taskids,Pageable pa)
;

@Query("from Tasklist t where t.taskId in (?1)")
public Page<Tasklist> findTaskByTaskIds(List<Long> taskids,Pageable pa)
;

@Query(nativeQuery = true, value = "SELECT COUNT(*) from aoa_task_list where aoa_task_list.status_id=?1 and aoa_task_list.task_push_user_id=?2 ")
public Integer countfinish(Long status,Long userid)
;

@Query("from Tasklist t where t.cancel = ?1  and t.taskId in (?2)")
public Page<Tasklist> findtaskCancelAndTaskId(Boolean b,List<Long> taskids,Pageable pa)
;

public Page<Tasklist> findByUsersIdOrderByTypeId(User userId,Pageable page)
;

public Page<Tasklist> findByUsersIdOrderByPublishTimeDesc(User userId,Pageable pa)
;

@Query("from Tasklist tl where tl.taskId in (?1) and tl.title like  %?2%")
public Page<Tasklist> findtaskByTitleLikeAndTaskId(List<Long> taskids,String title,Pageable pa)
;

public Page<Tasklist> findByUsersId(User userId,Pageable page)
;

@Query("from Tasklist t where t.statusId = ?1  and t.taskId in (?2)")
public Page<Tasklist> findtaskStatusIdAndCancelAndTaskId(Integer statusId,List<Long> taskids,Pageable pa)
;

@Query("select tl from Tasklist tl where tl.usersId=:userId and tl.title like %:title%")
public Page<Tasklist> findByTitleLikeAndUsersId(String title,User userId,Pageable pa)
;

}