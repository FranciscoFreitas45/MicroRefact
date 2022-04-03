package com.ipe.module.bpm.respository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.bpm.entity.TaskProxy;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskProxyRepository extends CustomerRepository<TaskProxy, String>{


}