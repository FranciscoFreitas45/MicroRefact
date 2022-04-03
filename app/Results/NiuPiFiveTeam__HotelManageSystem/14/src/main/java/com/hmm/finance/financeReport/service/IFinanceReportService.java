package com.hmm.finance.financeReport.service;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import com.hmm.finance.financeReport.domain.FinanceReport;
public interface IFinanceReportService {


public List<FinanceReport> findFinanceReportDailyByYearGroupByMonth(Integer year)
;

public List<Map<String,Integer>> findAllYearInFinanceReport()
;

}