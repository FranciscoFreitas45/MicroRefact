package org.live.live.service;
 import org.live.common.response.DataTableModel;
import java.util.Map;
public interface ReportService {


public void updateReport(String id)
;

public DataTableModel findReportByPage(Map<String,Object> params,boolean handleType)
;

}