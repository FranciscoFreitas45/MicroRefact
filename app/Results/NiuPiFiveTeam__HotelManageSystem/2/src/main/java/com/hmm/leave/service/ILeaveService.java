package com.hmm.leave.service;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.entity.LeaveDTO;
import com.hmm.leave.entity.LeaveEmpDTO;
public interface ILeaveService {


public void save(Leave leave)
;

public Leave findOne(Long id)
;

public void deleteAll(Long[] ids)
;

public List<Map<Object,Object>> findByyearAndOntudytimeleave(Integer year,String userName)
;

public Page<LeaveDTO> findTodoTasks(String userId,Pageable pageable)
;

public void delete(Long id)
;

public Page<LeaveEmpDTO> findAll(Specification<Leave> whereClause,Pageable pageable)
;

public List<Map<Object,Object>> findleave(Integer year)
;

public Page<Leave> findLeave(String userId,Pageable pageable)
;

public void startWorkflow(String userId,Long leaveId,Map<String,Object> variables)
;

public float findTotalLeaveTimes(String userName)
;

public void claim(String taskId,String userId)
;

public void complete(String taskId,Map<String,Object> variables)
;

public int findTatalPersonLeave()
;

}