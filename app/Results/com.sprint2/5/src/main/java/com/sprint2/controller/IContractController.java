package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Contract;
public interface IContractController {


@ResponseBody
public Contract addContract(Contract Contract)
;

@ResponseBody
public boolean deleteContractbycontractNumber(Integer contractNumber)
;

@ResponseBody
public List<Contract> getAllContract()
;

@ResponseBody
public Contract getContractBycontractNumber(Integer contractNumber)
;

@ResponseBody
public Contract updateContract(Contract Contract)
;

}