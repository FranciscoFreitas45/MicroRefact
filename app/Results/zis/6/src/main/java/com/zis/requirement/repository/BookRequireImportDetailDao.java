package com.zis.requirement.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.requirement.bean.BookRequireImportDetail;
import com.zis.requirement.bean.BookRequireImportDetailStatus;
public interface BookRequireImportDetailDao extends JpaSpecificationExecutor<BookRequireImportDetail>, PagingAndSortingRepository<BookRequireImportDetail, Integer>{


@Query("from BookRequireImportDetail where status = '" + BookRequireImportDetailStatus.BOOK_NOT_MATCHED + "' and  batchId = :batchId")
public List<BookRequireImportDetail> findByBatchId(Integer batchId)
;

}