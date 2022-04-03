package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;
public interface CreepersListCreditDao extends JpaSpecificationExecutor<TCreepersListCredit>, JpaRepository<TCreepersListCredit, Long>{


public List<TCreepersListCredit> findTop1ByUserCodeAndMessageCode(String userCode,String messageCode)
;

public List<TCreepersListCredit> findByUserCode(String userCode)
;

public long countByUserCode(String UserCode)
;

public TCreepersListCredit findTop1ByUserCode(String userCode)
;

}