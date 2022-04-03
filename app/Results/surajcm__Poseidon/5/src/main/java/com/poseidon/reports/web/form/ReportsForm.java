package com.poseidon.reports.web.form;
 import com.poseidon.make.domain.MakeAndModelVO;
import com.poseidon.make.domain.MakeVO;
import com.poseidon.reports.domain.ReportsVO;
import com.poseidon.transaction.domain.TransactionVO;
import java.util.List;
import java.util.StringJoiner;
public class ReportsForm {

 private  ReportsVO currentReport;

 private  ReportsVO searchReports;

 private  List<ReportsVO> reportsVOs;

 private  String loggedInUser;

 private  String loggedInRole;

 private  List<String> exportList;

 private  TransactionVO txnReportTransactionVO;

 private  TransactionVO invoiceListReportTransactionVO;

 private  List<MakeVO> makeVOs;

 private  List<String> statusList;

 private  MakeAndModelVO modelReportMakeAndModelVO;


public List<String> getStatusList(){
    return statusList;
}


public void setModelReportMakeAndModelVO(MakeAndModelVO modelReportMakeAndModelVO){
    this.modelReportMakeAndModelVO = modelReportMakeAndModelVO;
}


public TransactionVO getInvoiceListReportTransactionVO(){
    return invoiceListReportTransactionVO;
}


public void setLoggedInRole(String loggedInRole){
    this.loggedInRole = loggedInRole;
}


public void setCurrentReport(ReportsVO currentReport){
    this.currentReport = currentReport;
}


public MakeAndModelVO getModelReportMakeAndModelVO(){
    return modelReportMakeAndModelVO;
}


public void setReportsVOs(List<ReportsVO> reportsVOs){
    this.reportsVOs = reportsVOs;
}


public void setExportList(List<String> exportList){
    this.exportList = exportList;
}


public void setLoggedInUser(String loggedInUser){
    this.loggedInUser = loggedInUser;
}


public TransactionVO getTxnReportTransactionVO(){
    return txnReportTransactionVO;
}


public void setInvoiceListReportTransactionVO(TransactionVO invoiceListReportTransactionVO){
    this.invoiceListReportTransactionVO = invoiceListReportTransactionVO;
}


public void setMakeVOs(List<MakeVO> makeVOs){
    this.makeVOs = makeVOs;
}


public ReportsVO getSearchReports(){
    return searchReports;
}


public void setSearchReports(ReportsVO searchReports){
    this.searchReports = searchReports;
}


public String getLoggedInUser(){
    return loggedInUser;
}


@Override
public String toString(){
    return new StringJoiner(", ", ReportsForm.class.getSimpleName() + "[", "]").add("currentReport=" + currentReport).add("searchReports=" + searchReports).add("reportsVOs=" + reportsVOs).add("loggedInUser='" + loggedInUser + "'").add("loggedInRole='" + loggedInRole + "'").add("exportList=" + exportList).add("txnReportTransactionVO=" + txnReportTransactionVO).add("invoiceListReportTransactionVO=" + invoiceListReportTransactionVO).add("makeVOs=" + makeVOs).add("statusList=" + statusList).add("modelReportMakeAndModelVO=" + modelReportMakeAndModelVO).toString();
}


public ReportsVO getCurrentReport(){
    return currentReport;
}


public List<MakeVO> getMakeVOs(){
    return makeVOs;
}


public void setStatusList(List<String> statusList){
    this.statusList = statusList;
}


public String getLoggedInRole(){
    return loggedInRole;
}


public List<String> getExportList(){
    return exportList;
}


public List<ReportsVO> getReportsVOs(){
    return reportsVOs;
}


public void setTxnReportTransactionVO(TransactionVO txnReportTransactionVO){
    this.txnReportTransactionVO = txnReportTransactionVO;
}


}