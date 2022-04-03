package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Vlan;
public interface VlanDao extends JpaSpecificationExecutor<Vlan>, PagingAndSortingRepository<Vlan, Integer>{


public List<Vlan> findByLocationId(Integer locationId)
;

public Vlan findByName(String name)
;

}