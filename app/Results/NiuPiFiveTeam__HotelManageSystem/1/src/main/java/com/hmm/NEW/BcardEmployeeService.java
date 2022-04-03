package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Work.dao.BcardDao;
import com.hmm.Work.entity.Bcard;
@Service
public class BcardEmployeeService {

@Autowired
 private BcardDao bcarddao;


public Set<Bcard> getBcards(Integer emp_id){
return bcarddao.getBcards(emp_id);
}


public void setBcards(Integer emp_id,Set<Bcard> bcards){
bcarddao.setBcards(emp_id,bcards);
}


}