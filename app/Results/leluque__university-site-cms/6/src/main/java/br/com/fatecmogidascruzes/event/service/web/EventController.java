package br.com.fatecmogidascruzes.event.service.web;
 import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fatecmogidascruzes.controller.MVCController;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.FormItemDTO;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.event.Event;
import br.com.fatecmogidascruzes.event.service.EventService;
import br.com.fatecmogidascruzes.exception.DoesNotHaveAccessException;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.exception.web.NotFoundException;
import br.com.fatecmogidascruzes.file.File;
import br.com.fatecmogidascruzes.file.FileDTO;
import br.com.fatecmogidascruzes.file.service.web.FileWebService;
import br.com.fatecmogidascruzes.gallery.Album;
import br.com.fatecmogidascruzes.gallery.AlbumService;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.util.Constants;
import br.com.fatecmogidascruzes.Interface.FileWebService;
import br.com.fatecmogidascruzes.Interface.AlbumService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
@Controller
@PreAuthorize("hasRole('ACADEMIC')")
@RequestMapping("/admin/events")
public class EventController extends MVCController{

 private  EventService eventService;

 private  EventWebService eventWebService;

 private  FileWebService fileWebService;

 private  AlbumService albumService;

 private  String[] fields;

@Autowired
public EventController(HttpSession httpSession, UserService userService, EventService eventService, EventWebService eventWebService, FileWebService fileWebService, AlbumService albumService) {
    super(httpSession, userService);
    this.eventService = eventService;
    this.eventWebService = eventWebService;
    this.fileWebService = fileWebService;
    this.albumService = albumService;
}
@RequestMapping(path = "new", method = RequestMethod.GET)
public ModelAndView newEvent(EventEditDTO eventEditDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("event/edit");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    modelAndView.addObject("albums", FormItemDTO.listFrom(this.albumService.getEnabled()));
    modelAndView.addObject("event", eventEditDTO);
    return modelAndView;
}


@RequestMapping(path = "{eventHash}/image", method = RequestMethod.GET)
public String image(UUID eventHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<Event> eventOptional = this.eventService.getEnabledByHash(eventHash);
        if (eventOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getImage(eventOptional.get().getImage().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The event image has not been found.");
            }
        } else {
            throw new BadRequestException("The event does not exist");
        }
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException(e);
    } catch (DoesNotHaveAccessException e) {
        e.printStackTrace();
        throw new ForbiddenException(e);
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException(e);
    }
}


@GetMapping
public ModelAndView search(RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("event/search");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/{eventHash}", method = RequestMethod.GET)
public ModelAndView view(UUID eventHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/event/edit");
    try {
        EventEditDTO eventEditDTO = this.eventWebService.getEventEditDTOByHash(eventHash);
        modelAndView.addObject("isUpdate", true);
        modelAndView.addObject("albums", FormItemDTO.listFrom(this.albumService.getEnabled()));
        modelAndView.addObject("event", eventEditDTO);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return modelAndView;
}


@RequestMapping(path = "table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<EventTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
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
    return eventWebService.getTable(searchCriteria, draw);
}


@RequestMapping(path = "{eventHash}/album/{imageHash}", method = RequestMethod.GET)
public String highlightImage(UUID eventHash,UUID imageHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<Event> eventOptional = this.eventService.getEnabledByHash(eventHash);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (null != event.getAlbum()) {
                Album album = event.getAlbum();
                Optional<File> imageOptional = album.getImage(imageHash);
                if (imageOptional.isPresent()) {
                    File image = imageOptional.get();
                    FileDTO fileDTO = this.fileWebService.getImage(image.getHash(), width, height);
                    if (null != fileDTO) {
                        return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
                    } else {
                        throw new NotFoundException("The event album image has not been found.");
                    }
                } else {
                    throw new BadRequestException("The event album image does not exist");
                }
            } else {
                throw new BadRequestException("The event does not have an album");
            }
        } else {
            throw new BadRequestException("The event does not exist");
        }
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException(e);
    } catch (DoesNotHaveAccessException e) {
        e.printStackTrace();
        throw new ForbiddenException(e);
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException(e);
    }
}


@RequestMapping(path = "{eventHash}/bigImage", method = RequestMethod.GET)
public String bigImage(UUID eventHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<Event> eventOptional = this.eventService.getEnabledByHash(eventHash);
        if (eventOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getImage(eventOptional.get().getBigImage().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The event big image has not been found.");
            }
        } else {
            throw new BadRequestException("The event does not exist");
        }
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException(e);
    } catch (DoesNotHaveAccessException e) {
        e.printStackTrace();
        throw new ForbiddenException(e);
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException(e);
    }
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ModelAndView save(EventEditDTO eventEditDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newEvent(eventEditDTO, redirectAttributes);
    }
    try {
        this.eventWebService.saveEvent(eventEditDTO);
        redirectAttributes.addFlashAttribute("message", "success");
        return new ModelAndView("redirect:/admin/events");
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return newEvent(eventEditDTO, redirectAttributes);
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


@RequestMapping(path = "/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID hash,RedirectAttributes redirectAttributes){
    try {
        Optional<Event> eventOptional = eventService.getByHash(hash);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setEnabled(false);
            eventService.update(event);
            redirectAttributes.addFlashAttribute("message", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return new ModelAndView("redirect:/admin/events");
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


}