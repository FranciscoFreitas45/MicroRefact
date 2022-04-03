package br.com.fatecmogidascruzes.agenda.service.web;
 import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devskiller.friendly_id.FriendlyId;
import br.com.fatecmogidascruzes.agenda.AgendaEntry;
import br.com.fatecmogidascruzes.agenda.service.AgendaEntryService;
import br.com.fatecmogidascruzes.controller.MVCController;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.ResultDTO;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.DTO.SearchCriteria;
@Controller
@PreAuthorize("hasRole('ACADEMIC')")
@RequestMapping("/admin/agenda")
public class AgendaEntryController extends MVCController{

 private  AgendaEntryService agendaEntryService;

 private  AgendaEntryWebService agendaEntryWebService;

 private  String[] fields;

@Autowired
public AgendaEntryController(HttpSession httpSession, UserService userService, AgendaEntryService agendaEntryService, AgendaEntryWebService agendaEntryWebService) {
    super(httpSession, userService);
    this.agendaEntryService = agendaEntryService;
    this.agendaEntryWebService = agendaEntryWebService;
}
@RequestMapping(path = "/agendaEntries/{agendaEntryHash}", method = RequestMethod.POST)
public ModelAndView saveAgendaEntry(AgendaEntryEditDTO agendaEntryEditDTO,BindingResult binding,RedirectAttributes redirectAttributes,UUID agendaEntryHash){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return viewAgendaEntry(agendaEntryHash, agendaEntryEditDTO, redirectAttributes, true);
    }
    this.agendaEntryWebService.saveAgendaEntry(agendaEntryEditDTO, getLoggedUser());
    return getAgendaEntries();
}


@RequestMapping(path = "/agendaEntries/repetition/{agendaEntryHash}", method = RequestMethod.POST)
public ModelAndView generateRepetition(AgendaEntryRepetitionEditDTO agendaEntryRepetitionDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return agendaEntryRepetititon(FriendlyId.toUuid(agendaEntryRepetitionDTO.getHashString()), agendaEntryRepetitionDTO, redirectAttributes, true);
    }
    this.agendaEntryWebService.generateRepetitions(agendaEntryRepetitionDTO, getLoggedUser());
    return getAgendaEntries();
}


@RequestMapping(path = "/agendaEntries/repetition/{agendaEntryHash}", method = RequestMethod.GET)
public ModelAndView agendaEntryRepetititon(UUID agendaEntryHash,AgendaEntryRepetitionEditDTO agendaEntryRepetitionDTO,RedirectAttributes redirectAttributes,boolean commingFromSave){
    try {
        ModelAndView modelAndView = new ModelAndView("/agendaEntry/repetition");
        if (commingFromSave) {
            modelAndView.addObject("agendaEntryRepetition", agendaEntryRepetitionDTO);
        } else {
            modelAndView.addObject("agendaEntryRepetition", this.agendaEntryWebService.getAgendaEntryRepetitionDTOByHash(agendaEntryHash));
        }
        modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
        return modelAndView;
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}/adjustDuration", method = RequestMethod.POST)
@ResponseBody
public ResultDTO adjustDuration(UUID agendaEntryHash,long differenceInDays){
    try {
        this.agendaEntryService.changeAgendaEntryDuration(agendaEntryHash, differenceInDays);
        return new ResultDTO(true, "");
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (null != text && !text.isEmpty()) {
                try {
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                } catch (Exception error) {
                }
            }
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
            if (null != getValue()) {
                try {
                    return DateTimeFormatter.ofPattern("dd/MM/yyyy").format((LocalDate) getValue());
                } catch (Exception error) {
                    return null;
                }
            } else {
                return null;
            }
        }
    });
    binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (null != text && !text.isEmpty()) {
                try {
                    setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                } catch (Exception error) {
                }
            }
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
            if (null != getValue()) {
                try {
                    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format((LocalDateTime) getValue());
                } catch (Exception error) {
                    return null;
                }
            } else {
                return null;
            }
        }
    });
}


@Override
public String getAsText() throws IllegalArgumentException{
    if (null != getValue()) {
        try {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format((LocalDateTime) getValue());
        } catch (Exception error) {
            return null;
        }
    } else {
        return null;
    }
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID agendaEntryHash,boolean deleteCopiesAfter,RedirectAttributes redirectAttributes){
    try {
        if (deleteCopiesAfter) {
            this.agendaEntryService.removeCopies(agendaEntryHash);
        }
        this.agendaEntryService.removeLogicallyByHash(agendaEntryHash);
        redirectAttributes.addFlashAttribute("message", "AgendaEntryo excluído com sucesso!");
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
    return new ModelAndView("redirect:/admin/agenda/agendaEntries");
}


@Override
public void setAsText(String text) throws IllegalArgumentException{
    if (null != text && !text.isEmpty()) {
        try {
            setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        } catch (Exception error) {
        }
    }
}


@RequestMapping(path = "/agendaEntries/filter", method = RequestMethod.GET)
@ResponseBody
public List<CalendarEntryDTO> getAgendaEntriesFromPeriod(LocalDate start,LocalDate end){
    return this.agendaEntryWebService.getAllocatedAndEnabledAgendaEntriesByPeriod(start, end);
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}/reallocate", method = RequestMethod.POST)
@ResponseBody
public ResultDTO reallocateAgendaEntry(UUID agendaEntryHash,long differenceInMinutes){
    try {
        this.agendaEntryService.moveAgendaEntry(agendaEntryHash, differenceInMinutes);
        return new ResultDTO(true, "");
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}", method = RequestMethod.GET)
public ModelAndView viewAgendaEntry(UUID agendaEntryHash,AgendaEntryEditDTO agendaEntryEditDTO,RedirectAttributes redirectAttributes,boolean commingFromSave){
    try {
        ModelAndView modelAndView = new ModelAndView("/agendaEntry/edit");
        if (commingFromSave) {
            modelAndView.addObject("agendaEntry", agendaEntryEditDTO);
        } else {
            modelAndView.addObject("agendaEntry", this.agendaEntryWebService.getAgendaEntryEditDTOByHash(agendaEntryHash));
        }
        modelAndView.addObject("isUpdate", true);
        modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
        return modelAndView;
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@RequestMapping(path = "/agendaEntries/table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<AgendaEntryTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
    SearchCriteria searchCriteria = new SearchCriteria();
    if (filter != null && !filter.equals("")) {
        searchCriteria.setFilter(filter);
    }
    if (columnsToSort < fields.length) {
        String fieldName = fields[columnsToSort];
        searchCriteria.addSortBy(fieldName);
        searchCriteria.setOrder(columnsOrder.equalsIgnoreCase("asc") ? SearchCriteria.Order.ASCENDING : SearchCriteria.Order.DESCENDING);
    }
    searchCriteria.setWhatToFilter(Arrays.asList(fields));
    searchCriteria.setInitialRegister(initialPage / numberOfRegisters);
    searchCriteria.setNumberOfRegisters(numberOfRegisters);
    return agendaEntryWebService.getTable(searchCriteria, draw);
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}/unallocate", method = RequestMethod.GET)
public ModelAndView unallocate(UUID agendaEntryHash,RedirectAttributes redirectAttributes){
    try {
        this.agendaEntryService.unallocateAgendaEntry(agendaEntryHash);
        redirectAttributes.addFlashAttribute("message", "AgendaEntryo excluído com sucesso!");
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
    return new ModelAndView("redirect:/admin/agenda/agendaEntry");
}


@RequestMapping(path = "/agendaEntries/{agendaEntryHash}/allocate", method = RequestMethod.POST)
@ResponseBody
public ResultDTO allocateAgendaEntry(UUID agendaEntryHash,LocalDate start){
    try {
        this.agendaEntryService.changeAgendaEntryStartDate(agendaEntryHash, start);
        return new ResultDTO(true, "");
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException();
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@RequestMapping(path = "/agendaEntries", method = RequestMethod.GET)
public ModelAndView getAgendaEntries(){
    ModelAndView modelAndView = new ModelAndView("/agendaEntry/calendar");
    List<CalendarEntryDTO> calendarAgendaEntryDTOs = this.agendaEntryWebService.getUnallocatedAndEnabledAgendaEntries();
    modelAndView.addObject("unallocatedAgendaEntries", calendarAgendaEntryDTOs);
    return modelAndView;
}


@RequestMapping(path = "/agendaEntries/delete/redirect", method = RequestMethod.GET)
public ModelAndView deleteHash(String hash,boolean deleteCopiesAfter,RedirectAttributes redirectAttributes){
    return delete(FriendlyId.toUuid(hash), deleteCopiesAfter, redirectAttributes);
}


}