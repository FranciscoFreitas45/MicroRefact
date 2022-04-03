package br.com.fatecmogidascruzes.news.service.web;
 import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
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
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.DoesNotHaveAccessException;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.exception.web.NotFoundException;
import br.com.fatecmogidascruzes.file.FileDTO;
import br.com.fatecmogidascruzes.file.service.web.FileWebService;
import br.com.fatecmogidascruzes.news.News;
import br.com.fatecmogidascruzes.news.service.NewsService;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.util.Constants;
import br.com.fatecmogidascruzes.Interface.FileWebService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
import br.com.fatecmogidascruzes.DTO.SearchCriteria;
import br.com.fatecmogidascruzes.DTO.FileWebService;
@Controller
@PreAuthorize("hasRole('ACADEMIC')")
@RequestMapping("/admin/news")
public class NewsController extends MVCController{

 private  NewsService newsService;

 private  NewsWebService newsWebService;

 private  FileWebService fileWebService;

 private  String[] fields;

@Autowired
public NewsController(HttpSession httpSession, UserService userService, NewsService newsService, NewsWebService newsWebService, FileWebService fileWebService) {
    super(httpSession, userService);
    this.newsService = newsService;
    this.newsWebService = newsWebService;
    this.fileWebService = fileWebService;
}
@RequestMapping(path = "{newsHash}/image", method = RequestMethod.GET)
public String image(UUID newsHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<News> newsOptional = this.newsService.getEnabledByHash(newsHash);
        if (newsOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getImage(newsOptional.get().getImage().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The news image has not been found.");
            }
        } else {
            throw new BadRequestException("The news does not exist");
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


@RequestMapping(method = RequestMethod.GET)
public ModelAndView search(RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/news/search");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/{newsHash}", method = RequestMethod.GET)
public ModelAndView view(UUID newsHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/news/edit");
    try {
        Optional<News> newsOptional = this.newsService.getEnabledByHash(newsHash);
        if (newsOptional.isPresent()) {
            modelAndView.addObject("news", NewsEditDTO.from(newsOptional.get()));
            modelAndView.addObject("isUpdate", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (ForbiddenException forbiddenException) {
        forbiddenException.printStackTrace();
        throw forbiddenException;
    } catch (Exception exception) {
        exception.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
    }
    return modelAndView;
}


@RequestMapping(path = "{newsHash}/file", method = RequestMethod.GET)
public String file(UUID newsHash,HttpServletRequest request){
    try {
        Optional<News> newsOptional = this.newsService.getEnabledByHash(newsHash);
        if (newsOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getFile(newsOptional.get().getFile().getHash());
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The news file has not been found.");
            }
        } else {
            throw new BadRequestException("The news does not exist");
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


@RequestMapping(path = "/table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<NewsTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
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
    return newsWebService.getTable(searchCriteria, draw);
}


@RequestMapping(path = "{newsHash}/highlightImage", method = RequestMethod.GET)
public String highlightImage(UUID newsHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<News> newsOptional = this.newsService.getEnabledByHash(newsHash);
        if (newsOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getImage(newsOptional.get().getHighlightImage().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The news highlight image has not been found.");
            }
        } else {
            throw new BadRequestException("The news does not exist");
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
public ModelAndView save(NewsEditDTO newsDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newNews(newsDTO, redirectAttributes);
    }
    try {
        newsWebService.save(newsDTO);
        redirectAttributes.addFlashAttribute("message", "success");
        return new ModelAndView("redirect:/admin/news");
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
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
                    return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
                } catch (Exception error) {
                    return null;
                }
            } else {
                return null;
            }
        }
    });
}


@RequestMapping(path = "/new", method = RequestMethod.GET)
public ModelAndView newNews(NewsEditDTO newsDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/news/edit");
    modelAndView.addObject("news", newsDTO);
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@Override
public String getAsText() throws IllegalArgumentException{
    if (null != getValue()) {
        try {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
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
        Optional<News> newsOptional = newsService.getByHash(hash);
        if (newsOptional.isPresent()) {
            newsService.removeLogicallyByHash(hash);
            redirectAttributes.addFlashAttribute("message", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return search(redirectAttributes);
}


@Override
public void setAsText(String text) throws IllegalArgumentException{
    if (null != text && !text.isEmpty()) {
        try {
            setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (Exception error) {
        }
    }
}


}