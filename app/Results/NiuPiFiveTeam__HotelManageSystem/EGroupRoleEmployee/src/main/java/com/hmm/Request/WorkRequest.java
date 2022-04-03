package com.hmm.Request;
import com.hmm.DTO.Work;
public interface WorkRequest {

   public Set<Work> getWorks(Integer emp_id);
   public void setWorks(Set<Work> works,Integer emp_id);
}