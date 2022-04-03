package br.com.fatecmogidascruzes.request.service.web;
 import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
import br.com.fatecmogidascruzes.request.Request;
import br.com.fatecmogidascruzes.request.Request.RequestSituation;
import br.com.fatecmogidascruzes.request.service.RequestService;
import br.com.fatecmogidascruzes.user.service.UserService;
@Controller
@PreAuthorize("hasRole('REQUEST')")
@RequestMapping("/admin/requests")
public class RequestController extends MVCController{

 private  RequestService requestService;

 private  RequestWebService requestWebService;

 private  String[] fields;

@Autowired
public RequestController(HttpSession httpSession, UserService userService, RequestService requestService, RequestWebService requestWebService) {
    super(httpSession, userService);
    this.requestService = requestService;
    this.requestWebService = requestWebService;
}
@GetMapping
public ModelAndView search(RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("request/search");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/{requestHash}", method = RequestMethod.GET)
public ModelAndView view(UUID requestHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/request/edit");
    try {
        RequestEditDTO requestEditDTO = this.requestWebService.find(requestHash);
        modelAndView.addObject("isUpdate", true);
        modelAndView.addObject("request", requestEditDTO);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return modelAndView;
}


@RequestMapping(path = "table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<RequestTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
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
    return requestWebService.getTable(searchCriteria, draw);
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ModelAndView save(RequestEditDTO requestEditDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newRequest(requestEditDTO, redirectAttributes);
    }
    try {
        this.requestWebService.save(requestEditDTO);
        redirectAttributes.addFlashAttribute("message", "success");
        return new ModelAndView("redirect:/admin/requests");
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return newRequest(requestEditDTO, redirectAttributes);
    }
}


@RequestMapping(path = "/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID hash,RedirectAttributes redirectAttributes){
    try {
        Optional<Request> requestOptional = requestService.getByHash(hash);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            request.setEnabled(false);
            requestService.update(request);
            redirectAttributes.addFlashAttribute("message", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return new ModelAndView("redirect:/admin/requests");
}


@RequestMapping(path = "new", method = RequestMethod.GET)
public ModelAndView newRequest(RequestEditDTO requestEditDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("request/edit");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    modelAndView.addObject("request", requestEditDTO);
    return modelAndView;
}


}