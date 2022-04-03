package br.com.fatecmogidascruzes.request.service.web;
 import java.util.UUID;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.request.Request;
public interface RequestWebService {


public TableDTO<RequestTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public RequestEditDTO find(UUID postHash) throws InexistentOrDisabledEntity
;

public Request save(RequestEditDTO requestEditDTO)
;

public RequestEditDTO getRequestEditDTOByHash(UUID requestHash) throws InexistentOrDisabledEntity
;

public RequestEditDTO getEnabledByProtocol(Long protocol)
;

}