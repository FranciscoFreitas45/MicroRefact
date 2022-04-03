package br.com.fatecmogidascruzes.user.service.web;
 import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import br.com.fatecmogidascruzes.user.User;
import br.com.fatecmogidascruzes.user.exception.AlreadyUsedUsernameException;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.DTO.SearchCriteria;
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/users")
public class UserController extends MVCController{

 private  UserService userService;

 private  UserWebService userWebService;

 private  String[] fields;

@Autowired
public UserController(HttpSession httpSession, UserService userService, UserWebService userWebService) {
    super(httpSession, userService);
    this.userService = userService;
    this.userWebService = userWebService;
}
@RequestMapping(method = RequestMethod.GET)
public ModelAndView search(RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("user/search");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    modelAndView.addObject("messageType", redirectAttributes.getFlashAttributes().get("messageType"));
    return modelAndView;
}


@RequestMapping(path = "/{userHash}", method = RequestMethod.GET)
public ModelAndView view(UUID userHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/user/edit");
    try {
        UserEditDTO userEditDTO = this.userWebService.findUser(userHash);
        modelAndView.addObject("isUpdate", true);
        modelAndView.addObject("user", userEditDTO);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
    }
    return modelAndView;
}


@RequestMapping(path = "/table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<UserTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
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
    return userWebService.getUserTable(searchCriteria, draw);
}


@RequestMapping(path = "/new", method = RequestMethod.GET)
public ModelAndView newUser(UserEditDTO userEditDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("user/edit");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    modelAndView.addObject("user", userEditDTO);
    return modelAndView;
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ModelAndView save(UserEditDTO userEditDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newUser(userEditDTO, redirectAttributes);
    }
    try {
        this.userWebService.saveUser(userEditDTO, getLoggedUser());
        redirectAttributes.addFlashAttribute("message", "success");
        return search(redirectAttributes);
    } catch (AlreadyUsedUsernameException error) {
        binding.addError(new FieldError("user", "name", "Já existe outro usuário com o mesmo nome"));
        redirectAttributes.addFlashAttribute("message", "error");
        return newUser(userEditDTO, redirectAttributes);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return newUser(userEditDTO, redirectAttributes);
    }
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (null != text && !text.isEmpty()) {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
            if (null != getValue()) {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            } else {
                return null;
            }
        }
    });
}


@Override
public String getAsText() throws IllegalArgumentException{
    if (null != getValue()) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
    } else {
        return null;
    }
}


@RequestMapping(path = "/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID hash,RedirectAttributes redirectAttributes){
    try {
        Optional<User> userOptional = userService.getByHash(hash);
        if (userOptional.isPresent()) {
            // Check whether the user is trying to delete himself/herself.
            if (!getLoggedUser().getHash().equals(userOptional.get().getHash())) {
                User user = userOptional.get();
                user.setEnabled(false);
                userService.update(user);
                redirectAttributes.addFlashAttribute("message", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "error");
                redirectAttributes.addFlashAttribute("messageType", "tryingDeleteHerself");
            }
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
        setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}


}