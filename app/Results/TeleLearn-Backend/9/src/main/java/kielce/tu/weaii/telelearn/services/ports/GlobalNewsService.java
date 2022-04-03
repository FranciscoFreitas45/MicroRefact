package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.GlobalNews;
import kielce.tu.weaii.telelearn.requests.GlobalNewsRequest;
import org.springframework.data.domain.Page;
public interface GlobalNewsService {


public GlobalNews add(GlobalNewsRequest request)
;

public GlobalNews getById(Long id)
;

public GlobalNews edit(Long id,GlobalNewsRequest request)
;

public Page<GlobalNews> getPage(int pageSize,int pageNo)
;

public void delete(Long id)
;

}