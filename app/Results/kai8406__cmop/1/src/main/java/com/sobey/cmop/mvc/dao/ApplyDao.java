package com.sobey.cmop.mvc.dao;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Apply;
public interface ApplyDao extends JpaSpecificationExecutor<Apply>, PagingAndSortingRepository<Apply, Integer>{


public List<Apply> findByUserIdAndServiceTypeAndStatusInOrderByIdDesc(Integer userId,Integer serviceType,Collection<Integer> status)
;

}