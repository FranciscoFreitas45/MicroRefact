package com.kingen.service.contract;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.kingen.bean.ClientContact;
import com.kingen.bean.Contract;
import com.kingen.repository.client.ClientContactDao;
import com.kingen.repository.contract.ContractDao;
import com.kingen.service.CommonService;
import com.kingen.util.Page;
import com.kingen.util.Parameter;
@Component
@Transactional
public class ContractService extends CommonService<Contract, String>{

@Autowired
 private  ContractDao dao;

@Autowired
 private  ClientContactDao ccdao;

 private  Logger logger;


public List<ClientContact> findClientContacts(String clientId){
    return ccdao.findClientContacts(clientId);
}


public Page<Contract> findContractVo(Page<Contract> page,Parameter p){
    // TODO Auto-generated method stub
    return dao.findContract(page, p);
}


}