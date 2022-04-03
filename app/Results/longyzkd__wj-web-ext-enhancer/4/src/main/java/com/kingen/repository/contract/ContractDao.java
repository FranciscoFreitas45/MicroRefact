package com.kingen.repository.contract;
 import java.util.Map;
import org.springframework.stereotype.Component;
import com.kingen.bean.Contract;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
import com.kingen.util.Parameter;
@Component
public class ContractDao extends CommonDao<Contract, String>{


public Page<Contract> findContract(Page<Contract> page,Parameter p){
    // String sql = "select c.* ,cl.unit_Name as unitName from t_contract c,t_client cl where c.client_Id = cl.id and c.client_Id=:p1";
    // return findPageHiberEntityBySql(page, sql, p,Contract.class);
    String sql = "select c.id as id ,   c.contract_name as contractName ,c.client_id as clientId ,c.client_contact_id as clientContactId ,c.contract_no as contractNo ,c.sevice_lv as seviceLv ,c.contract_type as contractType , c.contract_amt as contractAmt ,c.service_begin_time as serviceBeginTime ,c.service_end_time as serviceEndTime ,c.`desc` as `desc`, c.proc_def_name as procDefName ,   " + "cl.unit_Name as unitName from t_contract c,t_client cl where c.client_Id = cl.id and c.client_Id=:p1";
    return findPageBySql(page, sql, p, Contract.class);
}


}