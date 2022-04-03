package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.finance.logisticst.repository.InStorageRepository;
import com.hmm.finance.logisticst.domain.InStorage;
@Service
public class InStorageDoSendService {

@Autowired
 private InStorageRepository instoragerepository;


public void setInAll(String inStorageIdEAHF,InStorage inAll){
instoragerepository.setInAll(inStorageIdEAHF,inAll);
}


public InStorage getInAll(String inStorageIdEAHF){
return instoragerepository.getInAll(inStorageIdEAHF);
}


}