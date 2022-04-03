package com.gp.cricket.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.gp.cricket.entity.BallerType;
public interface BallerTypeRepository extends JpaRepository<BallerType, Integer>{


public BallerType getBallerTypeId(Integer ballerTypeIdv2);

public void setBallerTypeId(Integer ballerTypeIdv2,BallerType ballerTypeId);

}