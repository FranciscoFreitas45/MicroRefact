package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Work.dao.WorkDao;
import com.hmm.Work.entity.Work;
@Service
public class WorkEmployeeService {

@Autowired
 private WorkDao workdao;


public Set<Work> getWorks(Integer emp_id){
return workdao.getWorks(emp_id);
}


public void setWorks(Integer emp_id,Set<Work> works){
workdao.setWorks(emp_id,works);
}


}