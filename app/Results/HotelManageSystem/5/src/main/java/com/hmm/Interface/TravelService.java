package com.hmm.Interface;
public interface TravelService {

   public float findTotalTravelAllowance(String userName);
   public Integer findTatalPersonTravel();
   public List<Map<Object,Object>> findByyearAndOntudytimetravel(Integer year,String userName);
   public List<Map<Object,Object>> findtravel(Integer year);
}