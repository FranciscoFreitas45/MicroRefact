package br.com.fatecmogidascruzes.event.service.web;
 import java.util.List;
import java.util.UUID;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.event.Event;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
public interface EventWebService {


public void saveEvent(EventEditDTO eventEditDTO)
;

public TableDTO<EventTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public List<EventHomeDTO> getEnabledEvents()
;

public EventDetailDTO getEventDetail(UUID hash)
;

public EventEditDTO getEventEditDTOByHash(UUID eventHash) throws InexistentOrDisabledEntity
;

public List<EventHomeDTO> getHomeEvents()
;

public Event abrirLink(UUID hash)
;

}