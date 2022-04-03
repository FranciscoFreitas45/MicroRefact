package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.logistics.stock.repository.InDetailedRepository;
import com.hmm.logistics.stock.entity.InDetailed;
@Service
public class InDetailedInStorageService {

@Autowired
 private InDetailedRepository indetailedrepository;


public List<InDetailed> getInDetaileds(String inStorageId){
return indetailedrepository.getInDetaileds(inStorageId);
}


public void setInDetaileds(String inStorageId,List<InDetailed> inDetaileds){
indetailedrepository.setInDetaileds(inStorageId,inDetaileds);
}


}