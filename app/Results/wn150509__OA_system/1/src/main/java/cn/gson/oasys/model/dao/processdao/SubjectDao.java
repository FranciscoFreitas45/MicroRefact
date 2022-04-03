package cn.gson.oasys.model.dao.processdao;
 import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.gson.oasys.model.entity.process.Subject;
public interface SubjectDao extends PagingAndSortingRepository<Subject, Long>{


public List<Subject> findByParentId(Long id)
;

public List<Subject> findByParentIdNot(Long id)
;

}