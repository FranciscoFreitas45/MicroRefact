package com.netease.repositories;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.Check_Record;
public interface Check_RecordRepository extends CrudRepository<Check_Record, Integer>{


public List<Check_Record> findByReportIdAndQueryReasonStartingWith(Integer reportId,String queryReason)
;

public List<Check_Record> findByReportIdAndQueryReason(Integer reportId,String queryReason)
;

}