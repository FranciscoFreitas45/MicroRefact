package cn.gson.oasys.model.dao.system;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.system.SystemTypeList;
@Repository
public interface TypeDao extends PagingAndSortingRepository<SystemTypeList, Long>{


public List<SystemTypeList> findByTypeNameLikeOrTypeModelLike(String name,String name2)
;

public SystemTypeList findByTypeModelAndTypeName(String typeModel,String typeName)
;

public List<SystemTypeList> findByTypeModel(String typeModel)
;

@Query("select type.typeName from SystemTypeList type where type.typeId=:id")
public String findname(Long id)
;

}