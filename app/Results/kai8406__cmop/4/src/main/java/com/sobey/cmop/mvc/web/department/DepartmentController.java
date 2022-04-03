package com.sobey.cmop.mvc.web.department;
 import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Department;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/department")
public class DepartmentController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    model.addAttribute("department", new Department());
    return "department/departmentForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Department department,RedirectAttributes redirectAttributes){
    comm.departmentService.saveOrUpdateDepartment(department);
    redirectAttributes.addFlashAttribute("message", "创建部门 " + department.getName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Department department,RedirectAttributes redirectAttributes){
    comm.departmentService.saveOrUpdateDepartment(department);
    redirectAttributes.addFlashAttribute("message", "修改部门 " + department.getName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("department", comm.departmentService.getDepartment(id));
    return "department/departmentForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.departmentService.getDepartmentPageable(searchParams, pageNumber, pageSize));
    return "department/departmentList";
}


@RequestMapping(value = "/delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    Department department = comm.departmentService.getDepartment(id);
    if (department.getUsers().size() > 0) {
        redirectAttributes.addFlashAttribute("message", "部门使用中,无法删除");
    } else {
        comm.departmentService.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("message", "删除部门成功");
    }
    return REDIRECT_SUCCESS_URL;
}


}