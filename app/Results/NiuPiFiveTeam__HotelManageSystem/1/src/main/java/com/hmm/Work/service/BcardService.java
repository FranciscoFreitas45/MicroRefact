package com.hmm.Work.service;
 import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.entity.BcardDTO;
import com.hmm.Work.entity.BcardEmpDTO;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.entity.LeaveDTO;
import com.hmm.leave.entity.LeaveEmpDTO;
public interface BcardService {


public void save(Bcard leave)
;

public Bcard findOne(Long id)
;

public void deleteAll(Long[] ids)
;

public void claim(String taskId,String userId)
;

public Page<BcardDTO> findTodoTasks(String userId,Pageable pageable)
;

public void complete(String taskId,Map<String,Object> variables)
;

public void delete(Long id)
;

public Page<BcardEmpDTO> findAll(Specification<Bcard> whereClause,Pageable pageable)
;

public void startWorkflow(String userId,Long leaveId,Map<String,Object> variables)
;

}