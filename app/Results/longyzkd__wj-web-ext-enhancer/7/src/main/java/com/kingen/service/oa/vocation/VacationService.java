package com.kingen.service.oa.vocation;
 import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.kingen.bean.workflow.Vacation;
import com.kingen.service.CommonService;
@Service
@Transactional
public class VacationService extends CommonService<Vacation, Integer>{


public Serializable doAdd(Vacation vacation){
    return add(vacation);
}


public void doDelete(Vacation vacation){
    delete(vacation);
}


public List<Vacation> findByStatus(Serializable userId,String status){
    List<Vacation> list = findByPage("Vacation", new String[] { "userId", "status" }, new String[] { userId.toString(), status });
    return list;
}


public Vacation findById(Integer id){
    return getUnique("Vacation", new String[] { "id" }, new String[] { id.toString() });
}


public void doUpdate(Vacation vacation){
    update(vacation);
}


public List<Vacation> toList(Integer userId){
    List<Vacation> list = findByPage("Vacation", new String[] { "userId" }, new String[] { userId.toString() });
    return list;
}


}