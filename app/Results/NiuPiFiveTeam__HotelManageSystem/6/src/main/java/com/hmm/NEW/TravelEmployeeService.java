package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.travel.dao.TravelDao;
import com.hmm.travel.entity.Travel;
@Service
public class TravelEmployeeService {

@Autowired
 private TravelDao traveldao;


public Set<Travel> getTravels(Integer emp_id){
return traveldao.getTravels(emp_id);
}


public void setTravels(Integer emp_id,Set<Travel> travels){
traveldao.setTravels(emp_id,travels);
}


}