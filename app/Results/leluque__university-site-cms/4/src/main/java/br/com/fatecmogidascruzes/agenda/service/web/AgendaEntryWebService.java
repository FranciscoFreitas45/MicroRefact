package br.com.fatecmogidascruzes.agenda.service.web;
 import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.user.User;
public interface AgendaEntryWebService {


public void saveAgendaEntry(AgendaEntryEditDTO agendaEntryEditDTO,User loggedUser)
;

public void generateRepetitions(AgendaEntryRepetitionEditDTO agendaEntryRepetitionDTO,User loggedUser) throws BadRequestException
;

public List<CalendarEntryDTO> getAllocatedAndEnabledAgendaEntriesByPeriod(LocalDate start,LocalDate end)
;

public AgendaEntryEditDTO getAgendaEntryEditDTOByHash(UUID agendaEntryHash) throws InexistentOrDisabledEntity
;

public TableDTO<AgendaEntryTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public List<CalendarEntryDTO> getUnallocatedAndEnabledAgendaEntries()
;

public AgendaEntryRepetitionEditDTO getAgendaEntryRepetitionDTOByHash(UUID agendaEntryHash) throws InexistentOrDisabledEntity
;

}