package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.EntrepotStatusRepository;
import com.byr.warehouse.pojo.EntrepotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class EntrepotStatusService {

@Autowired
 private  EntrepotStatusRepository entrepotStatusRepository;


public int getAllEntrepotCount(){
    List<EntrepotStatus> all = entrepotStatusRepository.findAll();
    int total = 0;
    for (EntrepotStatus entrepotStatus : all) {
        total = total + entrepotStatus.getTotalSize();
    }
    return total;
}


public List<EntrepotStatus> finEntrpotByEnterCodeAndMaterialId(String enterCode,String materialCode){
    List<EntrepotStatus> results = new ArrayList<EntrepotStatus>();
    List<EntrepotStatus> byEnterCode = entrepotStatusRepository.findEntrepotStatusByEnterCode(enterCode);
    System.err.println("从库存查找" + byEnterCode + "    ");
    if (byEnterCode.size() == 0) {
        throw new Exception("Don't find the goods!");
    }
    for (EntrepotStatus entrepotStatus : byEnterCode) {
        System.out.println("从结果中筛选:" + entrepotStatus.getMaterialCode() + "  " + materialCode);
        if (materialCode.equals(entrepotStatus.getMaterialCode())) {
            results.add(entrepotStatus);
        }
    }
    return results;
}


}