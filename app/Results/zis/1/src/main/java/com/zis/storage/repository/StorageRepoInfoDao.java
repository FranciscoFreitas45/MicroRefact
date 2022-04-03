package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zis.storage.entity.StorageRepoInfo;
public interface StorageRepoInfoDao extends JpaRepository<StorageRepoInfo, Integer>{


public List<StorageRepoInfo> findByOwnerIdOrderByGmtCreateAsc(Integer ownerId)
;

public List<StorageRepoInfo> findByOwnerIdAndRepoIdOrderByGmtCreateAsc(Integer ownerId,Integer repoId)
;

}