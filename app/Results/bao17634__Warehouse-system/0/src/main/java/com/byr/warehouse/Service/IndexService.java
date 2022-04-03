package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.ApplyEnterRepository;
import com.byr.warehouse.dao.ApplyOutPutRepository;
import com.byr.warehouse.dao.EntrepotStatusRepository;
import com.byr.warehouse.pojo.ApplyEnter;
import com.byr.warehouse.pojo.ApplyOutPut;
import com.byr.warehouse.pojo.EntrepotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.byr.warehouse.Interface.ApplyEnterRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
@Service
public class IndexService {

@Autowired
 private  ApplyEnterRepository applyEnterRepository;

@Autowired
 private  ApplyOutPutRepository applyOutPutRepository;

@Autowired
 private  EntrepotStatusRepository entrepotStatusRepository;


public int getYestdayApplyEnterCount(){
    int size = 0;
    List<ApplyEnter> yestdayApplys = applyEnterRepository.getYestdayApplys();
    if (null != yestdayApplys) {
        size = yestdayApplys.size();
    }
    return size;
}


public int getYestdayApplyOutCount(){
    int size = 0;
    List<ApplyOutPut> yestdayApplys = applyOutPutRepository.getYestdayApplys();
    if (null != yestdayApplys) {
        size = yestdayApplys.size();
    }
    return size;
}


public int getEntrpotSize(){
    int size = 0;
    List<EntrepotStatus> totalSize = entrepotStatusRepository.getTotalSize();
    if (null != totalSize) {
        size = totalSize.size();
    }
    return size;
}


}