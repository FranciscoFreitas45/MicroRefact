package com.gp.cricket.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.gp.cricket.entity.BatmanType;
public interface BatmanTypeRepository extends JpaRepository<BatmanType, Integer>{


public BatmanType getBatmanTypeId(Integer batmanTypeIdv2);

public void setBatmanTypeId(Integer batmanTypeIdv2,BatmanType batmanTypeId);

}