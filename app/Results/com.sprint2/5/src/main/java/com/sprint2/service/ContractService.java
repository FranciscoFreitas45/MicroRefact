package com.sprint2.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.model.Contract;
import com.sprint2.repository.ContractRepository;
import com.sprint2.utility.ValidateContract;
@Service
public class ContractService {

@Autowired
 private  ContractRepository contractRepository;


public Contract addContract(Contract Contract){
    if (Contract != null && Contract.getDeliveryDate().matches(ValidateContract.dateregex)) {
        // saving a specific record by using the method save() of JpaRepository
        return contractRepository.save(Contract);
    } else {
        throw new InvalidOperation("Enter correct Contract details");
    }
}


public boolean deleteContractbycontractNumber(Integer contractNumber){
    try {
        System.out.println("Please enter a Integer value : ");
        contractRepository.deleteById(contractNumber);
        System.out.println("Contract details are successfully deleted");
        return true;
    } catch (IllegalArgumentException | EmptyResultDataAccessException ie) {
        throw new InvalidOperation("Contract not deleted \n enter the correct id");
    }
}


public Contract getContractBycontractNumber(Integer contractNumber){
    try {
        // getting a specific record by using the method findById() of JpaRepository
        return contractRepository.findById(contractNumber).get();
    } catch (NoSuchElementException | IllegalArgumentException ie) {
        throw new InvalidOperation("Enter the correct Contract Number");
    }
}


public Contract updateContract(Contract Contract){
    // validating the DeliveryDate by using the ValidateContract class
    if (Contract.getDeliveryDate().matches(ValidateContract.dateregex)) {
        System.out.println("Contract details are successfully updated");
        return contractRepository.save(Contract);
    } else
        throw new InvalidOperation("Contract is not found with given id");
}


public List<Contract> getAllContracts(){
    List<Contract> Contractlist = new ArrayList<Contract>();
    contractRepository.findAll().forEach(Contract -> Contractlist.add(Contract));
    return Contractlist;
}


}