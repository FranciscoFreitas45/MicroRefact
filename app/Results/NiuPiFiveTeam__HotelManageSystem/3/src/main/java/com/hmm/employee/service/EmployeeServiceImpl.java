package com.hmm.employee.service;
 import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.department.entity.Department;
import com.hmm.department.service.DeptService;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeDTO;
import com.hmm.employee.entity.EmployeeQueryDTO;
import com.hmm.employee.util.ExcelUtils;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.userRole.service.GroupRoleService;
import com.hmm.Interface.EmployeeDao;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.GroupRoleService;
import com.hmm.Interface.IWorkflowService;
import com.hmm.DTO.Employee;
import com.hmm.DTO.GroupRole;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private  EmployeeDao employdao;

@Autowired
 private  DeptService iDeptService;

@Autowired
 private  GroupRoleService groupRoleService;

@Autowired
 private  IWorkflowService workflowService;

@Autowired
 private  IdentityService identityService;


@Override
public void updatePassword(String password,String userName){
    // TODO Auto-generated method stub
    employdao.updatePassword(password, userName);
}


@Override
public List<EmployeeDTO> findByids(Integer[] ids){
    // TODO Auto-generated method stub
    List<Integer> idlists = new ArrayList<Integer>(Arrays.asList(ids));
    List<Employee> employs = (List<Employee>) employdao.findAllById(idlists);
    List<EmployeeDTO> employeeDTOs = null;
    if (null != employs) {
        employeeDTOs = new ArrayList<>();
        for (Employee employee : employs) {
            EmployeeDTO dto = new EmployeeDTO();
            EmployeeDTO.entityToDto(employee, dto);
            Department department = employee.getDepartmentes();
            List<GroupRole> groupRole = employee.getGroupRoles();
            String roles = "";
            if (null != groupRole) {
                for (GroupRole groupRole2 : groupRole) {
                    roles = roles + " " + groupRole2.getGroupName();
                }
            }
            dto.setGroupName(roles);
            if (null != department) {
                dto.setDeptName(department.getDeptName());
            }
            employeeDTOs.add(dto);
        }
        return employeeDTOs;
    } else {
        return null;
    }
}


@Override
public Employee findByUserName(String userName){
    // TODO Auto-generated method stub
    return employdao.findByUserName(userName);
}


@Override
public Employee findByEmpNameAndEmpNo(String empName,String empNo){
    // TODO Auto-generated method stub
    return employdao.findByEmpNameAndEmpNo(empName, empNo);
}


@Override
public void save(Employee entity){
    // TODO Auto-generated method stub
    employdao.save(entity);
}


@Override
public long count(Specification<Employee> spec){
    // TODO Auto-generated method stub
    return employdao.count(spec);
}


@Override
public void deleteAll(Integer[] ids){
    // TODO Auto-generated method stub
    List<Integer> idlists = new ArrayList<Integer>(Arrays.asList(ids));
    List<Employee> employs = (List<Employee>) employdao.findAllById(idlists);
    if (employs != null) {
        employdao.deleteAll(employs);
    }
}


@Override
public Employee findByEmpNo(String empNo){
    // TODO Auto-generated method stub
    return employdao.findByEmpNo(empNo);
}


@Override
public EmployeeDTO findDTOByID(Integer id){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Page<EmployeeDTO> findAll(Specification<Employee> spec,Pageable pageable){
    // TODO Auto-generated method stub
    List<Employee> employs = employdao.findAll(spec);
    // System.out.println(employs);
    List<EmployeeDTO> dtos = null;
    if (null != employs) {
        dtos = new ArrayList<>();
        for (Employee employ : employs) {
            EmployeeDTO dto = new EmployeeDTO();
            EmployeeDTO.entityToDto(employ, dto);
            Department department = employ.getDepartmentes();
            List<GroupRole> groupRole = employ.getGroupRoles();
            String roles = "";
            if (null != groupRole) {
                for (GroupRole groupRole2 : groupRole) {
                    roles = roles + " " + groupRole2.getGroupName();
                }
            }
            dto.setGroupName(roles);
            if (null != department) {
                dto.setDeptName(department.getDeptName());
            }
            dtos.add(dto);
        }
    }
    return new PageImpl<EmployeeDTO>(dtos, pageable, null != employs ? employs.size() : 0);
}


@Override
public ExtAjaxResponse ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response){
    // TODO Auto-generated method stub
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    MultipartFile file = multipartRequest.getFile("upfile");
    if (file.isEmpty()) {
        try {
            throw new Exception("文件不存在！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    InputStream in = null;
    try {
        in = file.getInputStream();
    } catch (IOException e) {
        e.printStackTrace();
    }
    List<List<Object>> listob = null;
    try {
        listob = new ExcelUtils().getBankListByExcel(in, file.getOriginalFilename());
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
    for (int i = 0; i < listob.size(); i++) {
        List<Object> lo = listob.get(i);
        Employee vo = new Employee();
        Employee j = null;
        Department department = null;
        GroupRole groupRole = null;
        try {
            // Integer.valueOf(String.valueOf(lo.get(0)))
            j = employdao.findByEmpNo(lo.get(0).toString());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("没有新增");
        }
        // { "员工编号" , "员工姓名","性别","职称" ,"类别" ,"联系方式" ,"籍贯","身份证","入职日期","部门","简介"};
        if (// 新增用户
        j == null) {
            vo.setEmpNo(lo.get(0).toString());
            vo.setEmpName(lo.get(1).toString());
            vo.setUserName(lo.get(0).toString());
            vo.setEmpSex(lo.get(2).toString());
            vo.setJobtype(lo.get(4).toString());
            vo.setTel(lo.get(5).toString());
            vo.setAddress(lo.get(6).toString());
            vo.setIdcard(lo.get(7).toString());
            String entry = lo.get(8).toString();
            SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = timeFormater.parse(entry);
                vo.setEntryDate(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            vo.setIntroduce(lo.get(10).toString());
            if (lo.get(8).toString() == " ") {
                vo.setIdcard(null);
            }
            if (null != lo.get(9).toString()) {
                department = iDeptService.findByDeptName(lo.get(9).toString());
            }
            if (null != lo.get(3).toString()) {
                groupRole = groupRoleService.findByGroupName(lo.get(3).toString());
            }
            if (null != groupRole) {
                List<GroupRole> groupRoles = new ArrayList<>();
                groupRoles.add(groupRole);
                vo.setGroupRoles(groupRoles);
                workflowService.addUser(lo.get(0).toString(), "888888", groupRole.getGroupId());
            } else {
                workflowService.addUser2(lo.get(0).toString(), "888888");
            }
            if (null != department) {
                vo.setDepartmentes(department);
            }
            employdao.save(vo);
        } else // { "员工编号" , "员工姓名","性别","职称" ,"类别" ,"联系方式" ,"籍贯","身份证","入职日期","部门","简介"};
        // 修改
        {
            // vo.setEmpNo(lo.get(0).toString());
            j.setEmpName(lo.get(1).toString());
            // vo.setUserName(lo.get(0).toString());
            j.setEmpSex(lo.get(2).toString());
            j.setJobtype(lo.get(4).toString());
            j.setTel(lo.get(5).toString());
            j.setAddress(lo.get(6).toString());
            j.setIdcard(lo.get(7).toString());
            String entry = lo.get(8).toString();
            SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                if (entry != null) {
                    date = timeFormater.parse(entry);
                    j.setEntryDate(date);
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            vo.setIntroduce(lo.get(10).toString());
            if (lo.get(7).toString() == " ") {
                j.setIdcard(null);
            }
            if (null != lo.get(9).toString()) {
                department = iDeptService.findByDeptName(lo.get(9).toString());
            }
            if (null != lo.get(3).toString()) {
                groupRole = groupRoleService.findByGroupName(lo.get(3).toString());
                if (null != groupRole) {
                    List<GroupRole> list = j.getGroupRoles();
                    if (list.size() != 0) {
                        for (GroupRole role : list) {
                            // 解除关系
                            workflowService.deleteUser(j.getUserName(), role.getGroupId());
                        }
                        // 创建关系
                        identityService.createMembership(j.getUserName(), groupRole.getGroupId());
                    } else {
                        // 创建关系
                        identityService.createMembership(j.getUserName(), groupRole.getGroupId());
                    }
                    // j.setGroupRoles(null);
                    List<GroupRole> groupRoles = new ArrayList<>();
                    groupRoles.add(groupRole);
                    j.setGroupRoles(groupRoles);
                }
            }
            if (null != department) {
                j.setDepartmentes(department);
            }
            employdao.save(j);
        }
    }
    return new ExtAjaxResponse(true, "文件导入成功！");
}


@Override
public boolean existsById(Integer id){
    // TODO Auto-generated method stub
    return employdao.existsById(id);
}


@Override
public Employee findByEmpName(String empName){
    // TODO Auto-generated method stub
    return employdao.findByEmpName(empName);
}


@Override
public Optional<Employee> findById(Integer id){
    // TODO Auto-generated method stub
    return employdao.findById(id);
}


@Override
public void deleteById(Integer id){
    // TODO Auto-generated method stub
    employdao.deleteById(id);
}


}