package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.GlobalNews;
import org.springframework.data.domain.Page;
public interface GlobalNewsRepository extends BaseRepository<GlobalNews>{


public Page<GlobalNews> getPage(int pageSize,int pageNo)
;

}