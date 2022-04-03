package com.sprint2.service;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.model.Contract;
public interface IContractService {


public Contract addContract(Contract Contract)
;

public List<Contract> getAllContract()
;

public Contract getContractBycontractNumber(Integer contractNumber)
;

public Contract updateContract(Contract Contract)
;

public boolean deleteContractbyContractNumber(Integer contractNumber)
;

}