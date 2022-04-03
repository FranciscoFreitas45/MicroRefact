package com.zis.requirement.repository;
 import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.zis.requirement.bean.SysVar;
public interface SysVarDao extends PagingAndSortingRepository<SysVar, Integer>{


public List<SysVar> findByDepKey(String depKey)
;

}