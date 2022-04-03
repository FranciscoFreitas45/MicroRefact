package cn.gson.oasys.model.dao.processdao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.gson.oasys.model.entity.process.ProcessList;
import cn.gson.oasys.model.entity.user.User;
public interface ProcessListDao extends PagingAndSortingRepository<ProcessList, Long>{


@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and (pro.typeNmae like %?2% or pro.processName like %?2% or pro.shenuser like %?2%) order by pro.applyTime desc")
public Page<ProcessList> findByuserIdandstr(Long userid,String val,Pageable pa)
;

@Query(nativeQuery = true, value = "select * from aoa_process_list  where aoa_process_list.process_user_id=?1 ORDER BY aoa_process_list.apply_time DESC LIMIT 0,3")
public List<ProcessList> findlastthree(long userid)
;

@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and pro.statusId=?2 order by pro.applyTime desc")
public Page<ProcessList> findByuserIdandstatus(Long userid,Long statusId,Pageable pa)
;

@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and pro.processId=?2")
public ProcessList findbyuseridandtitle(Long userid,Long proid)
;

@Query("select pro from ProcessList as pro where pro.userId.userId=?1 order by pro.applyTime desc")
public Page<ProcessList> findByuserId(Long userid,Pageable pa)
;

}