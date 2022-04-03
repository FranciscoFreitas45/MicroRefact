package com.hmm.Request;
import com.hmm.DTO.Travel;
public interface TravelRequest {

   public Set<Travel> getTravels(Integer emp_id);
   public void setTravels(Set<Travel> travels,Integer emp_id);
}