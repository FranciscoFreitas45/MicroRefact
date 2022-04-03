package com.hmm.Request;
import com.hmm.DTO.Leave;
public interface LeaveRequest {

   public void setLeaves(Set<Leave> leaves,Integer emp_id);
   public Set<Leave> getLeaves(Integer emp_id);
}