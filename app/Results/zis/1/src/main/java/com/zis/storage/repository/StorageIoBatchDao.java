package com.zis.storage.repository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StorageIoBatch;
public interface StorageIoBatchDao extends JpaSpecificationExecutor<StorageIoBatch>, CrudRepository<StorageIoBatch, Integer>, PagingAndSortingRepository<StorageIoBatch, Integer>{


@Query(value = "select * from storage_io_batch where date_format(gmt_create, '%y-%M-%d')=date_format(now(), '%y-%M-%d') and biz_type=:bizType and repo_id=:repoId order by gmt_create limit 1", nativeQuery = true)
public StorageIoBatch findDailyBatchByBizTypeAndRepoId(String bizType,Integer repoId)
;

public void setAmount(Integer id,Integer amount);

}