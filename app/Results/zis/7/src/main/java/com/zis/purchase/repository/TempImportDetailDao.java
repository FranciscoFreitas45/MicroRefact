package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.zis.purchase.bean.TempImportDetail;
public interface TempImportDetailDao extends JpaSpecificationExecutor<TempImportDetail>, PagingAndSortingRepository<TempImportDetail, Integer>{


public List<TempImportDetail> findByTaskIdAndStatus(Integer taskId,String status)
;

}