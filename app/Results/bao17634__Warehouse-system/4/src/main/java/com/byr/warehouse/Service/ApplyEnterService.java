package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.ApplyEnterRepository;
import com.byr.warehouse.pojo.ApplyEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ApplyEnterService {

@Autowired
 private  ApplyEnterRepository applyEnterRepository;


public int getNumberOfTodayApplyEnter(){
    List<ApplyEnter> todayEnsure = applyEnterRepository.getTodayEnsure();
    int total = 0;
    for (ApplyEnter applyEnter : todayEnsure) {
        total = applyEnter.getNumber() + total;
    }
    return total;
}


}