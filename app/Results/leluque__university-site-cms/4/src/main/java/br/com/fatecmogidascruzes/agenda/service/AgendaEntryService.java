package br.com.fatecmogidascruzes.agenda.service;
 import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.agenda.AgendaEntry;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.service.BaseService;
public interface AgendaEntryService extends BaseService<AgendaEntry, Long>{


public void changeAgendaEntryStartDate(UUID agendaEntryHash,LocalDate start) throws InexistentOrDisabledEntity
;

public void unallocateAgendaEntry(UUID agendaEntryHash) throws InexistentOrDisabledEntity
;

public void moveAgendaEntry(UUID agendaEntryHash,long changeInDays) throws InexistentOrDisabledEntity
;

public List<AgendaEntry> getEnabledByMonth(int year,int month)
;

public void changeAgendaEntryDuration(UUID agendaEntryHash,long changeInDays) throws InexistentOrDisabledEntity
;

public List<AgendaEntry> getEnabledAndUnallocated()
;

public Page<AgendaEntry> getEnabledByFilter(SearchCriteria searchCriteria)
;

public void removeCopies(UUID agendaEntryHash) throws InexistentOrDisabledEntity
;

public List<AgendaEntry> getEnabledByYear(int year)
;

public List<AgendaEntry> getEnabledAndAllocatedByPeriod(LocalDate start,LocalDate end)
;

public List<AgendaEntry> getEnabledByDay(LocalDate date)
;

}