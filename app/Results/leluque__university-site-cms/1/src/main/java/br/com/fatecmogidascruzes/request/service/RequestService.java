package br.com.fatecmogidascruzes.request.service;
 import java.util.Optional;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.request.Request;
import br.com.fatecmogidascruzes.service.BaseService;
public interface RequestService extends BaseService<Request, Long>{


public Page<Request> getEnabledByFilter(SearchCriteria searchCriteria)
;

public Optional<Request> getEnabledByProtocol(Long protocol)
;

}