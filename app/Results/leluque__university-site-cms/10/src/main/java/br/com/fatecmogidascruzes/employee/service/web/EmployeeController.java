package br.com.fatecmogidascruzes.employee.service.web;
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
import br.com.fatecmogidascruzes.employee.Employee;
import br.com.fatecmogidascruzes.employee.service.EmployeeService;
import br.com.fatecmogidascruzes.exception.DoesNotHaveAccessException;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.exception.web.NotFoundException;
import br.com.fatecmogidascruzes.file.FileDTO;
import br.com.fatecmogidascruzes.file.service.web.FileWebService;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.util.Constants;
import br.com.fatecmogidascruzes.Interface.FileWebService;
import br.com.fatecmogidascruzes.DTO.FileWebService;
import br.com.fatecmogidascruzes.DTO.SearchCriteria;
@Controller
@PreAuthorize("hasRole('ADMINISTRATIVE')")
@RequestMapping("/admin/employees")
public class EmployeeController extends MVCController{

 private  EmployeeService employeeService;

 private  EmployeeWebService employeeWebService;

 private  FileWebService fileWebService;

 private  String[] fields;

@Autowired
public EmployeeController(HttpSession httpSession, UserService userService, EmployeeService employeeService, EmployeeWebService employeeWebService, FileWebService fileWebService) {
    super(httpSession, userService);
    this.employeeService = employeeService;
    this.employeeWebService = employeeWebService;
    this.fileWebService = fileWebService;
}
@RequestMapping(path = "{employeeHash}/image", method = RequestMethod.GET)
public String image(UUID employeeHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<Employee> employeeOptional = this.employeeService.getEnabledByHash(employeeHash);
        if (employeeOptional.isPresent()) {
            FileDTO fileDTO = this.fileWebService.getImage(employeeOptional.get().getImage().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                throw new NotFoundException("The employee image has not been found.");
            }
        } else {
            throw new BadRequestException("The employee does not exist");
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
    ModelAndView modelAndView = new ModelAndView("/employee/search");
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/{employeeHash}", method = RequestMethod.GET)
public ModelAndView view(UUID employeeHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/employee/edit");
    try {
        Optional<Employee> employeeOptional = this.employeeService.getEnabledByHash(employeeHash);
        if (employeeOptional.isPresent()) {
            modelAndView.addObject("employee", EmployeeEditDTO.from(employeeOptional.get()));
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


@RequestMapping(path = "/new", method = RequestMethod.GET)
public ModelAndView newEmployee(EmployeeEditDTO employeeDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/employee/edit");
    modelAndView.addObject("employee", employeeDTO);
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/table", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public TableDTO<EmployeeTableRowDTO> getTable(Integer draw,Integer initialPage,Integer numberOfRegisters,String filter,Integer columnsToSort,String columnsOrder){
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
    return employeeWebService.getTable(searchCriteria, draw);
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ModelAndView save(EmployeeEditDTO employeeDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newEmployee(employeeDTO, redirectAttributes);
    }
    try {
        employeeWebService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "success");
        return new ModelAndView("redirect:/admin/employees");
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
    }
}


@RequestMapping(path = "/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID hash,RedirectAttributes redirectAttributes){
    try {
        Optional<Employee> employeeOptional = employeeService.getByHash(hash);
        if (employeeOptional.isPresent()) {
            employeeService.removeLogicallyByHash(hash);
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


}