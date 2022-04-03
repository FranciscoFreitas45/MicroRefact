package com.byr.warehouse.dao;
 import com.byr.warehouse.pojo.FreightSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FreightSpaceRespository extends JpaRepository<FreightSpace, Long>{


public FreightSpace findFreightSpaceByid(int id)
;

}