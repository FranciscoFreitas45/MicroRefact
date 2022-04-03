package com.hmm.department.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.department.dao.DeptDao;
import com.hmm.department.entity.Department;
import com.hmm.department.entity.DeptDTO;
import com.hmm.department.entity.DeptDTOCombox;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.Interface.EmployeeService;
@Service
@Transactional
public class IDeptService implements DeptService{

@Autowired
 private  DeptDao deptDao;

@Autowired
 private EmployeeService employServiceImpl;


public Department findByDeptNoAndDeptName(String deptNo,String deptName){
    // TODO Auto-generated method stub
    return deptDao.findByDeptNoAndDeptName(deptNo, deptName);
}


@Override
public boolean existsById(Integer id){
    // TODO Auto-generated method stub
    return deptDao.existsById(id);
}


@Override
public Page<DeptDTO> findAllByDTO(DeptDTO deptDTO,Pageable pageable){
    // TODO Auto-generated method stub
    List<Department> departments = (List<Department>) deptDao.findAll();
    List<DeptDTO> deptDTOs = null;
    if (null != departments) {
        deptDTOs = new ArrayList<>();
        for (Department department : departments) {
            DeptDTO deptDTO1 = new DeptDTO();
            DeptDTO.entityToDto(department, deptDTO1);
            Department department2 = department.getDepartmentParent();
            deptDTO1.setParentId(department2.getDept_id());
            deptDTO1.setDeptParent(department2.getDeptName());
            deptDTOs.add(deptDTO1);
        }
    }
    return new PageImpl<DeptDTO>(deptDTOs, pageable, null != departments ? departments.size() : 0);
}


@Override
public Optional<Department> findById(Integer id){
    // TODO Auto-generated method stub
    return deptDao.findById(id);
}


@Override
public void save(Department department){
    // TODO Auto-generated method stub
    deptDao.save(department);
}


@Override
public void deleteById(Integer id){
    // TODO Auto-generated method stub
    deptDao.deleteById(id);
}


@Override
public long count(Specification<Department> spec){
    // TODO Auto-generated method stub
    return deptDao.count(spec);
}


@Override
public void deleteAll(Integer[] ids){
    // TODO Auto-generated method stub
    List<Integer> integers = new ArrayList<>(Arrays.asList(ids));
    List<Department> departments = (List<Department>) deptDao.findAllById(integers);
    deptDao.deleteAll(departments);
}


@Override
public Department findByDeptNo(String deptNo){
    // TODO Auto-generated method stub
    return deptDao.findByDeptNo(deptNo);
}


@Override
public Department findByDeptName(String deptName){
    // TODO Auto-generated method stub
    return deptDao.findByDeptName(deptName);
}


@Override
public List<DeptDTOCombox> findDeptCombox(){
    // TODO Auto-generated method stub
    List<Department> departments = (List<Department>) deptDao.findAll();
    List<DeptDTOCombox> comboxs = null;
    if (null != departments) {
        comboxs = new ArrayList<>();
        for (Department department : departments) {
            DeptDTOCombox combox = new DeptDTOCombox();
            combox.setDeptParent(department.getDeptName());
            comboxs.add(combox);
        }
    }
    return comboxs;
}


@Override
public Page<DeptDTO> findAll(Specification<Department> spec,Pageable pageable){
    // TODO Auto-generated method stub
    List<Department> departments = (List<Department>) deptDao.findAll(spec);
    List<DeptDTO> deptDTOs = null;
    if (null != departments) {
        deptDTOs = new ArrayList<>();
        for (Department department : departments) {
            DeptDTO deptDTO1 = new DeptDTO();
            DeptDTO.entityToDto(department, deptDTO1);
            Department department2 = department.getDepartmentParent();
            if (null != department2) {
                deptDTO1.setParentId(department2.getDept_id());
                deptDTO1.setDeptParent(department2.getDeptName());
            }
            deptDTOs.add(deptDTO1);
        }
    }
    return new PageImpl<DeptDTO>(deptDTOs, pageable, null != departments ? departments.size() : 0);
}


}