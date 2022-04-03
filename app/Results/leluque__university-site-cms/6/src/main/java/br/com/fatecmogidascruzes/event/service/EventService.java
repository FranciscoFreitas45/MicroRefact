package br.com.fatecmogidascruzes.event.service;
 import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.event.Event;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.service.BaseService;
public interface EventService extends BaseService<Event, Long>{


public void unallocateEvent(UUID eventHash) throws InexistentOrDisabledEntity
;

public List<Event> getEnabledByMonth(int year,int month)
;

public void changeEventStartDate(UUID eventHash,LocalDate start) throws InexistentOrDisabledEntity
;

public List<Event> getEnabledAndUnallocated()
;

public void changeEventDuration(UUID eventHash,long changeInDays) throws InexistentOrDisabledEntity
;

public Page<Event> getEnabledByFilter(SearchCriteria searchCriteria)
;

public void moveEvent(UUID eventHash,long changeInDays) throws InexistentOrDisabledEntity
;

public List<Event> getHomeEvents()
;

public List<Event> getEnabledByYear(int year)
;

public List<Event> getEnabledAndAllocatedByPeriod(LocalDate start,LocalDate end)
;

public List<Event> getEnabledByDay(LocalDate date)
;

}