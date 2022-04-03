package cn.gson.oasys.model.dao.user;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.user.Position;
public interface PositionDao extends PagingAndSortingRepository<Position, Long>{


@Query("select po.name from Position po where po.id=:id")
public String findById(Long id)
;

public List<Position> findByDeptidAndNameLike(Long deptid,String name)
;

public List<Position> findByDeptid(Long deletedeptid)
;

public List<Position> findByDeptidAndNameNotLike(Long deptid,String name)
;

public void setPosition(Long id,Position position);

public Position getPosition(Long id);

}