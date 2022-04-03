package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StorageCheckSnap;
public interface StorageCheckSnapDao extends JpaRepository<StorageCheckSnap, Integer>{


public List<StorageCheckSnap> findByCheckId(Integer checkId)
;

@Query(value = "from StorageCheckSnap where checkId = :checkId and checkTime = :checkTime and isdelete = false")
public List<StorageCheckSnap> findByCheckIdAndCheckTimeNotIsDelete(Integer checkId,String checkTime)
;

}