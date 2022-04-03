package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.finance.logisticst.domain.InStorage;
@RestController
@CrossOrigin
public class InStorageDoSendController {

@Autowired
 private InStorageDoSendService instoragedosendservice;


@PutMapping
("/DoSend/{id}/InStorage/setInAll")
public void setInAll(@PathVariable(name="id") String inStorageIdEAHF,@RequestBody InStorage inAll){
instoragedosendservice.setInAll(inStorageIdEAHF,inAll);
}


@GetMapping
("/DoSend/{id}/InStorage/getInAll")
public InStorage getInAll(@PathVariable(name="id") String inStorageIdEAHF){
return instoragedosendservice.getInAll(inStorageIdEAHF);
}


}