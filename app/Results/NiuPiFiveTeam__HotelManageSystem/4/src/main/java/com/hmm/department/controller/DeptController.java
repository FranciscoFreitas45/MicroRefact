package com.hmm.department.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.department.entity.Department;
import com.hmm.department.entity.DeptDTO;
import com.hmm.department.entity.DeptDTOCombox;
import com.hmm.department.entity.DeptQueryDTO;
import com.hmm.department.service.DeptService;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.Interface.EmployeeService;
import com.hmm.DTO.ExtjsPageRequest;
@RestController
@RequestMapping("dept")
public class DeptController {

@Autowired
 private  DeptService ideptService;

@Autowired
 private  EmployeeService employService;

 private  Logger logger;


@GetMapping
public Page<DeptDTO> getPage(DeptQueryDTO deptQueryDTO,ExtjsPageRequest pageRequest){
    return ideptService.findAll(DeptQueryDTO.getWhereClause(deptQueryDTO), pageRequest.getPageable());
}


@PutMapping(value = "{dept_id}")
public ExtAjaxResponse updateDept(Integer dept_id,DeptDTO deptdto){
    try {
        Department department = ideptService.findById(dept_id).get();
        BeanUtils.copyProperties(deptdto, department);
        Department parent = ideptService.findByDeptName(deptdto.getDeptParent());
        if (null != parent) {
            department.setDepartmentParent(parent);
        }
        Employee employ = employService.findByEmpNo(deptdto.getManagerNo());
        if (null != employ) {
            department.setManagerId(employ.getEmp_id());
            department.setManagerName(employ.getEmpName());
            department.setManagerNo(employ.getEmpNo());
        } else {
            department.setManagerId(null);
            department.setManagerName(null);
            department.setManagerNo(null);
        }
        // DeptDTO.entityToDto(department, deptdto);
        ideptService.save(department);
        return new ExtAjaxResponse(true, "修改成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "修改失败");
    }
}


@GetMapping("findCombox")
@ResponseBody
public List<DeptDTOCombox> findCombox(){
    List<DeptDTOCombox> comboxs = ideptService.findDeptCombox();
    return comboxs;
}


@DeleteMapping(value = "{dept_id}")
public ExtAjaxResponse delete(Integer dept_id){
    try {
        if (null != dept_id) {
            ideptService.deleteById(dept_id);
        }
        return new ExtAjaxResponse(true, "删除成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "删除失败");
    }
}


@PostMapping
public ExtAjaxResponse savedept(DeptDTO deptdto){
    try {
        ideptService.save(deptdto);
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "添加失败");
    }
}


}