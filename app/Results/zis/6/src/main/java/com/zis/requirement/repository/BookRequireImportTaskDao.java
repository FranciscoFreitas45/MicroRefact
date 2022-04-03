package com.zis.requirement.repository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.zis.requirement.bean.BookRequireImportTask;
public interface BookRequireImportTaskDao extends JpaSpecificationExecutor<BookRequireImportTask>, PagingAndSortingRepository<BookRequireImportTask, Integer>{


}