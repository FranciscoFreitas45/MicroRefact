package com.netease.repositories;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.Loan_Record;
public interface Loan_RecordRepository extends CrudRepository<Loan_Record, Integer>{


public List<Loan_Record> findByReportId(Integer reportId)
;

}