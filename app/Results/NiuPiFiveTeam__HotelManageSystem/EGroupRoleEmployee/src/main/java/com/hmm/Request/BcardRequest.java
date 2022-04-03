package com.hmm.Request;
import com.hmm.DTO.Bcard;
public interface BcardRequest {

   public Set<Bcard> getBcards(Integer emp_id);
   public void setBcards(Set<Bcard> bcards,Integer emp_id);
}