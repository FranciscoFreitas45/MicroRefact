package cn.gson.oasys.model.dao.system;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.system.SystemStatusList;
@Repository
public interface StatusDao extends PagingAndSortingRepository<SystemStatusList, Long>{


public List<SystemStatusList> findByStatusModel(String statusModel)
;

@Query("select sl.statusColor from SystemStatusList sl where sl.statusId=:id")
public String findcolor(Long id)
;

public List<SystemStatusList> findByStatusNameLikeOrStatusModelLike(String name,String name2)
;

public SystemStatusList findByStatusModelAndStatusName(String statusModel,String statusName)
;

@Query("select sl.statusName from SystemStatusList sl where sl.statusId=:id")
public String findname(Long id)
;

}