package com.sobey.cmop.mvc.service.department;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.dao.DepartmentDao;
import com.sobey.cmop.mvc.entity.Department;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.cmop.mvc.DTO.SearchFilter;
@Service
@Transactional(readOnly = true)
public class DepartmentService extends BaseSevcie{

@Resource
 private  DepartmentDao departmentDao;


public List<Department> getDepartmentList(){
    return (List<Department>) departmentDao.findAll();
}


public Department findDepartmentByName(String name){
    return departmentDao.findByName(name);
}


public Department getDepartment(Integer id){
    return departmentDao.findOne(id);
}


@Transactional(readOnly = false)
public Department saveOrUpdateDepartment(Department department){
    return departmentDao.save(department);
}


@Transactional(readOnly = false)
public void deleteDepartment(Integer id){
    departmentDao.delete(id);
}


public Page<Department> getDepartmentPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<Department> spec = DynamicSpecifications.bySearchFilter(filters.values(), Department.class);
    return departmentDao.findAll(spec, pageRequest);
}


}