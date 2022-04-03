package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zis.storage.entity.StorageCheck;
public interface StorageCheckDao extends JpaRepository<StorageCheck, Integer>{


public List<StorageCheck> findByPlanId(Integer checkPlanId)
;

}