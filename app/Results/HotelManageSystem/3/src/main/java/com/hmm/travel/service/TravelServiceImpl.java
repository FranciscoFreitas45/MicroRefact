package com.hmm.travel.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.domain.WorkflowDTO;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.travel.dao.TravelDao;
import com.hmm.travel.entity.Travel;
import com.hmm.travel.entity.TravelDTO;
import com.hmm.travel.entity.TravelEmpDTO;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.EmployeeService;
import com.hmm.DTO.Employee;
@Service
@Transactional
public class TravelServiceImpl implements TravelService{

@Autowired
 private  TravelDao travelDao;

@Autowired
 private  IWorkflowService workflowService;

@Autowired
 private  EmployeeService employServiceImpl;


@Override
public Page<Travel> findTravel(Specification<Travel> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return travelDao.findAll(spec, pageable);
}


@Override
public List<Map<Object,Object>> findByyearAndOntudytimetravel(Integer year,String userName){
    // TODO Auto-generated method stub
    return travelDao.findByyearAndOntudytimetravel(year, userName);
}


@Override
public Integer findTatalPersonTravel(){
    // TODO Auto-generated method stub
    Integer travel = travelDao.findTatalPersonTravel();
    if (null != travel) {
        return travel;
    } else {
        return 0;
    }
}


@Override
public Travel save(Travel entity){
    // TODO Auto-generated method stub
    return travelDao.save(entity);
}


@Override
public long count(Specification<Travel> spec){
    // TODO Auto-generated method stub
    return travelDao.count(spec);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> iList = new ArrayList<>(Arrays.asList(ids));
    List<Travel> lists = (List<Travel>) travelDao.findAllById(iList);
    if (lists != null) {
        travelDao.deleteAll(lists);
    }
}


@Override
public Page<TravelDTO> findTodoTasks(String userId,Pageable pageable){
    // TODO Auto-generated method stub
    List<TravelDTO> results = null;
    List<WorkflowDTO> workflowDTOs = workflowService.findTodoTasks(userId);
    if (null != workflowDTOs) {
        results = new ArrayList<>();
        for (WorkflowDTO workflowDTO : workflowDTOs) {
            Long businessKey = new Long(workflowDTO.getBusinessKey());
            if (workflowDTO.getBusinessKey() == null) {
                continue;
            }
            Travel travel = travelDao.findById(businessKey).get();
            if (null != travel) {
                TravelDTO travelDTO = new TravelDTO();
                BeanUtils.copyProperties(travel, travelDTO);
                BeanUtils.copyProperties(workflowDTO, travelDTO);
                if (null != workflowDTO.getAssignee()) {
                    travel.setProcessStatus(ProcessStatus.APPROVAL);
                }
                Employee employ = travel.getEmploy();
                travelDTO.setEmpName(employ.getEmpName());
                travelDTO.setEmpNo(employ.getEmpNo());
                travelDTO.setDeptName(employ.getDepartmentes().getDeptName());
                results.add(travelDTO);
            }
        }
    }
    return new PageImpl<TravelDTO>(results, pageable, null != results ? results.size() : 0);
}


@Override
public Page<TravelEmpDTO> findAll(String userId,String groupName,Pageable pageable){
    // TODO Auto-generated method stub
    List<TravelEmpDTO> results = null;
    if (null != userId) {
        results = new ArrayList<>();
        Employee employ = employServiceImpl.findByUserName(userId);
        Department department = employ.getDepartmentes();
        if (groupName.indexOf("Manager") != -1) {
            Set<Employee> employs = employ.getDepartmentes().getEmployee();
            for (Employee employ2 : employs) {
                Set<Travel> travels = employ2.getTravels();
                for (Travel travel : travels) {
                    TravelEmpDTO empDTO = new TravelEmpDTO();
                    TravelEmpDTO.entityToDto(travel, empDTO);
                    empDTO.setDeptName(employ.getDepartmentes().getDeptName());
                    empDTO.setEmpName(employ2.getEmpName());
                    empDTO.setEmpNo(employ2.getEmpNo());
                    results.add(empDTO);
                }
            }
            return new PageImpl<TravelEmpDTO>(results, pageable, null != results ? results.size() : 0);
        } else {
            Set<Travel> travels = employ.getTravels();
            for (Travel travel : travels) {
                TravelEmpDTO empDTO = new TravelEmpDTO();
                TravelEmpDTO.entityToDto(travel, empDTO);
                empDTO.setDeptName(department.getDeptName());
                empDTO.setEmpName(employ.getEmpName());
                empDTO.setEmpNo(employ.getEmpNo());
                results.add(empDTO);
            }
            return new PageImpl<TravelEmpDTO>(results, pageable, null != results ? results.size() : 0);
        }
    } else {
        return null;
    }
}


@Override
public void startWorkflow(String userId,Long travelId,Map<String,Object> variables){
    // TODO Auto-generated method stub
    ProcessInstance processInstance = null;
    Travel travel = travelDao.findById(travelId).get();
    if (travel != null) {
        try {
            processInstance = workflowService.startWorkflow(userId, "Travel", travel.getTravelId().toString(), variables);
            travel.setProcessInstanceId(processInstance.getId());
            travel.setProcessStatus(ProcessStatus.APPROVAL);
            travel.setApplyTime(new Date());
            travelDao.save(travel);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}


@Override
public Page<TravelEmpDTO> findAllQueryDTO(Specification<Travel> whereClause,Pageable pageable){
    // TODO Auto-generated method stub
    Page<Travel> travels = travelDao.findAll(whereClause, pageable);
    List<TravelEmpDTO> empDTOs = null;
    if (null != travels) {
        empDTOs = new ArrayList<>();
        for (Travel leave : travels) {
            TravelEmpDTO empDTO = new TravelEmpDTO();
            TravelEmpDTO.entityToDto(leave, empDTO);
            Employee employ = leave.getEmploy();
            empDTO.setEmpName(employ.getEmpName());
            empDTO.setEmpNo(employ.getEmpNo());
            empDTO.setDeptName(employ.getDepartmentes().getDeptName());
            empDTOs.add(empDTO);
        }
    }
    return new PageImpl<TravelEmpDTO>(empDTOs, pageable, null != travels ? travels.getTotalElements() : 0);
}


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return travelDao.existsById(id);
}


@Override
public Optional<Travel> findById(Long id){
    // TODO Auto-generated method stub
    return travelDao.findById(id);
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    Travel travel = travelDao.findById(id).get();
    if (null != travel) {
        travelDao.deleteById(id);
    }
}


@Override
public Page<TravelEmpDTO> findByUserId(Specification<Travel> whereClause,String userId,Pageable pageable){
    // TODO Auto-generated method stub
    return null;
}


@Override
public List<Map<Object,Object>> findtravel(Integer year){
    // TODO Auto-generated method stub
    return travelDao.findtravel(year);
}


@Override
public void claim(String taskId,String userId){
    // TODO Auto-generated method stub
    workflowService.claim(taskId, userId);
}


@Override
public void complete(String taskId,Map<String,Object> variables){
    // TODO Auto-generated method stub
    workflowService.complete(taskId, variables);
}


@Override
public float findTotalTravelAllowance(String userName){
    // TODO Auto-generated method stubif()
    if (null != userName) {
        Float allence = null;
        allence = travelDao.findTotalTravelAllowance(userName);
        if (null != allence) {
            return allence;
        } else {
            return (float) 0.0;
        }
    } else {
        return (float) 0.0;
    }
}


}