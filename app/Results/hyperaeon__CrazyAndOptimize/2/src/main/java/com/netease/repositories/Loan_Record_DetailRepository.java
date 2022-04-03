package com.netease.repositories;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.Loan_Record_Detail;
public interface Loan_Record_DetailRepository extends CrudRepository<Loan_Record_Detail, Integer>{


public List<Loan_Record_Detail> findByReportIdAndAccountTypeAndDetailType(Integer reportId,Integer accountType,Integer detailType)
;

}