package com.hmm.employee.controller;
 import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.department.entity.Department;
import com.hmm.department.service.DeptService;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeDTO;
import com.hmm.employee.entity.EmployeeQueryDTO;
import com.hmm.employee.service.EmployeeService;
import com.hmm.employee.util.ExportExcel;
import com.hmm.employee.util.ExtForm;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.userRole.service.GroupRoleService;
import jodd.io.upload.FileUpload;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.IWorkflowService;
@Controller
@RestController
@RequestMapping("employ")
public class EmployeeController {

@Autowired
 private  EmployeeService employServiceImpl;

@Autowired
 private  DeptService iDeptService;

@Autowired
 private  GroupRoleService groupRoleService;

@Autowired
 private  IWorkflowService workflowService;

@Autowired
 private  IdentityService identityService;

 private  Logger logger;


@DeleteMapping(value = "{emp_id}")
public ExtAjaxResponse deleteEmploy(Integer emp_id){
    try {
        if (emp_id != null) {
            Employee employ = employServiceImpl.findById(emp_id).get();
            List<GroupRole> groupRole = employ.getGroupRoles();
            for (GroupRole groupRole2 : groupRole) {
                workflowService.deleteUser(employ.getUserName(), groupRole2.getGroupId());
            }
            employServiceImpl.deleteById(emp_id);
        }
        return new ExtAjaxResponse(true, "删除成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "删除失败");
    }
}


@RequestMapping(value = "exportExcel/{selectIds}", method = RequestMethod.GET)
public void exportExcelrows(Integer[] ids,HttpServletResponse response){
    List<EmployeeDTO> employeeDTOs = employServiceImpl.findByids(ids);
    HSSFWorkbook workbook = ExportExcel.exportExcel(employeeDTOs);
    // 设置要导出的文件的名字
    String fileName = "drn" + ".xls";
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
}


@PostMapping
public ExtAjaxResponse saveEmploy(EmployeeDTO employ){
    try {
        employServiceImpl.save(employ);
        logger.info("add empoly success");
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        // TODO: handle exception
        logger.info("add empoly false");
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@GetMapping
public Page<EmployeeDTO> getPage(EmployeeQueryDTO employQueryDTO,ExtjsPageRequest pageRequest,HttpSession httpSession){
    return employServiceImpl.findAll(EmployeeQueryDTO.getWhereClause(employQueryDTO), pageRequest.getPageable());
}


@GetMapping(value = "{emp_id}")
public Employee findone(Integer emp_id){
    return employServiceImpl.findById(emp_id).get();
}


@RequestMapping("test")
public Page<EmployeeDTO> getPage2(ExtjsPageRequest pageRequest){
    EmployeeQueryDTO employQueryDTO = new EmployeeQueryDTO();
    employQueryDTO.setDeptName("Admin管理部门");
    return employServiceImpl.findAll(EmployeeQueryDTO.getWhereClause(employQueryDTO), pageRequest.getPageable());
}


@PutMapping(value = "{emp_id}")
public ExtAjaxResponse updateEmploy(Integer emp_id,EmployeeDTO dto){
    try {
        Employee employ = employServiceImpl.findById(emp_id).get();
        // 部门
        Department department = iDeptService.findByDeptName(dto.getDeptName());
        // 获取角色
        List<GroupRole> groupRoless = employ.getGroupRoles();
        // 查询角色
        GroupRole groupRole = groupRoleService.findByGroupName(dto.getGroupName());
        if (null != dto.getUserName() && null == dto.getPassword()) {
            // 只更改用户名
            workflowService.deleteUser2(employ.getUserName());
            employ.setUserName(dto.getUserName());
            workflowService.addUser2(dto.getUserName(), employ.getPassword());
        }
        if (null != dto.getUserName() && null != dto.getPassword()) {
            // 更改用户名和密码
            workflowService.deleteUser2(employ.getUserName());
            employ.setUserName(dto.getUserName());
            workflowService.addUser2(dto.getUserName(), dto.getPassword());
        }
        if (null == dto.getUserName() && null != dto.getGroupName()) {
            // 只更改角色
            if (groupRoless.size() != 0) {
                for (GroupRole role : groupRoless) {
                    // 解除关系
                    identityService.deleteMembership(employ.getUserName(), role.getGroupId());
                    // 创建关系
                    identityService.createMembership(employ.getUserName(), groupRole.getGroupId());
                }
            } else {
                // 创建关系
                identityService.createMembership(employ.getUserName(), groupRole.getGroupId());
            }
        }
        if (null != dto.getUserName() && null != dto.getGroupName()) {
            // 更改角色和用户角色
            // 删除用户
            workflowService.deleteUser2(employ.getUserName());
            employ.setUserName(dto.getUserName());
            // 创建user
            workflowService.addUser2(dto.getUserName(), employ.getPassword());
            // 创建关系
            identityService.createMembership(dto.getUserName(), groupRole.getGroupId());
        }
        if (null != dto.getUserName() && null != dto.getGroupName() && null != dto.getPassword()) {
            // 
            // 删除用户
            workflowService.deleteUser2(employ.getUserName());
            // 创建user
            workflowService.addUser2(dto.getUserName(), dto.getPassword());
            // 创建关系
            identityService.createMembership(dto.getUserName(), groupRole.getGroupId());
        }
        BeanUtils.copyProperties(dto, employ);
        if (null != groupRole) {
            List<GroupRole> groupRoles = new ArrayList<>();
            groupRoles.add(groupRole);
            employ.setGroupRoles(groupRoles);
        }
        if (null != department) {
            employ.setDepartmentes(department);
        }
        employServiceImpl.save(employ);
        return new ExtAjaxResponse(true, "修改成功");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "修改失败");
    }
}


@RequestMapping(value = "/uploadExcel", method = { RequestMethod.GET, RequestMethod.POST })
public ExtAjaxResponse ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response){
    return employServiceImpl.ajaxUploadExcel(request, response);
}


@RequestMapping("/file")
public ExtAjaxResponse FileUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
    String userName = SessionUtil.getUserName(httpSession);
    if (null != userName) {
        if (file.isEmpty()) {
            return new ExtAjaxResponse(false, "文件为空");
        } else {
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            String empNo = StringUtils.substringBefore(filename, "+");
            Employee employee = employServiceImpl.findByEmpNo(empNo);
            if (null != employee) {
                String filetype = ".jpg" + ".jpeg" + ".png";
                if (filetype.indexOf(suffixName) != -1) {
                    String savePath = request.getServletContext().getRealPath("/resources/images/employee/");
                    // String realPath = "/resources/images/employee/"+filename;
                    // System.out.println(savePath);
                    // System.out.println(realPath);
                    File dest = new File(savePath + filename);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // String path2 = "C:/Users/Lenovo/git/HotelManageSystem/admin-dashboard/resources/images/employee/";
                    // File dest2 = new File(path2 + filename);
                    try {
                        employee.setEmpImage(filename);
                        file.transferTo(dest);
                        // file.transferTo(dest2);
                        employServiceImpl.save(employee);
                        return new ExtAjaxResponse(true, "上传成功");
                    } catch (IllegalStateException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return new ExtAjaxResponse(false, "上传失败");
                    }
                } else {
                    return new ExtAjaxResponse(false, "文件格式不正确");
                }
            } else {
                return new ExtAjaxResponse(false, "文件格式不正确");
            }
        }
    } else {
        return new ExtAjaxResponse(false, "未登入");
    }
}


@PostMapping("/deletes")
public ExtAjaxResponse deleterows(Integer[] ids){
    try {
        if (ids != null) {
            employServiceImpl.deleteAll(ids);
        }
        return new ExtAjaxResponse(true, "批量删除成功！");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "批量删除失败！");
    }
}


@RequestMapping("/lookselfmassage")
public ExtForm findselfmassage(HttpSession httpSession){
    String username = SessionUtil.getUserName(httpSession);
    Employee employee = null;
    EmployeeDTO employeeDTO = null;
    if (null != username) {
        employee = employServiceImpl.findByUserName(username);
        if (null != employee) {
            employeeDTO = new EmployeeDTO();
            EmployeeDTO.entityToDto(employee, employeeDTO);
            Department department = employee.getDepartmentes();
            List<GroupRole> groupRole = employee.getGroupRoles();
            String roles = "";
            if (null != groupRole) {
                for (GroupRole groupRole2 : groupRole) {
                    roles = roles + " " + groupRole2.getGroupName();
                }
            }
            if (null != roles) {
                employeeDTO.setGroupName(roles);
            }
            if (null != department) {
                employeeDTO.setDeptName(department.getDeptName());
            }
        }
        // Map data = null;
        // try {
        // data = org.apache.commons.beanutils.BeanUtils.describe(employeeDTO);
        // } catch (IllegalAccessException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (InvocationTargetException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (NoSuchMethodException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // data.remove("class");
        return new ExtForm(true, employeeDTO);
    } else {
        return null;
    }
}


}