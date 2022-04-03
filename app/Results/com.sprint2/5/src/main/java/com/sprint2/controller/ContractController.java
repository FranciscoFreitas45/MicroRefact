package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Contract;
import com.sprint2.service.*;
@Controller
@RequestMapping("/Contract")
public class ContractController implements IContractController{

 private Logger logger=LoggerFactory.getLogger(ContractController.class);

@Autowired
 private  ContractService ContractService;


@PostMapping("/")
@ResponseBody
public Contract addContract(Contract Contract){
    return ContractService.addContract(Contract);
}


@DeleteMapping("/{contractNumber}")
@ResponseBody
public boolean deleteContractbycontractNumber(Integer contractNumber){
    return ContractService.deleteContractbycontractNumber(contractNumber);
}


@GetMapping("/")
@ResponseBody
public List<Contract> getAllContract(){
    List<Contract> Contractlist = ContractService.getAllContracts();
    return Contractlist;
}


@GetMapping("/{contractNumber}")
@ResponseBody
public Contract getContractBycontractNumber(Integer contractNumber){
    logger.info("product service was instalized");
    return ContractService.getContractBycontractNumber(contractNumber);
}


@PutMapping("/{contractNumber}")
@ResponseBody
public Contract updateContract(Contract Contract){
    return ContractService.updateContract(Contract);
}


}