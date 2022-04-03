package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StoragePosition;
public interface StoragePositionDao extends JpaSpecificationExecutor<StoragePosition>, PagingAndSortingRepository<StoragePosition, Integer>{


@Query("from StoragePosition where repoId=:repoId and posStatus!='delete'")
public List<StoragePosition> findByRepoId(Integer repoId)
;

public StoragePosition findByPosIdAndRepoId(Integer posId,Integer repoId)
;

@Query("from StoragePosition where label=:label and repoId=:repoId and posStatus!='delete'")
public StoragePosition findByLabelAndRepoId(String label,Integer repoId)
;

}