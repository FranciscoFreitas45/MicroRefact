package com.zis.purchase.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskStatus;
public interface TempImportTaskDao extends PagingAndSortingRepository<TempImportTask, Integer>{


@Query(value = "select b from TempImportTask b where status <> 9 order by gmtCreate desc")
public Page<TempImportTask> findAllTempImportTask(Pageable page)
;

}