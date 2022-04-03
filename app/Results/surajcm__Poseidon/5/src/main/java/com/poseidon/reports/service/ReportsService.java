package com.poseidon.reports.service;
 import com.poseidon.company.domain.CompanyTermsVO;
import com.poseidon.company.service.CompanyTermsService;
import com.poseidon.invoice.domain.InvoiceReportVO;
import com.poseidon.invoice.domain.InvoiceVO;
import com.poseidon.invoice.service.InvoiceService;
import com.poseidon.make.domain.MakeAndModelVO;
import com.poseidon.make.service.MakeService;
import com.poseidon.reports.dao.ReportsDAO;
import com.poseidon.reports.domain.ReportsVO;
import com.poseidon.transaction.domain.TransactionReportVO;
import com.poseidon.transaction.domain.TransactionVO;
import com.poseidon.transaction.service.TransactionService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.poseidon.Interface.CompanyTermsService;
import com.poseidon.Interface.InvoiceService;
@Service
public class ReportsService {

 private  Logger LOG;

 private  ReportsDAO reportsDAO;

 private  MakeService makeService;

 private  TransactionService transactionService;

 private  CompanyTermsService companyTermsService;

 private  InvoiceService invoiceService;

public ReportsService(final ReportsDAO reportsDAO, final MakeService makeService, final TransactionService transactionService, final CompanyTermsService companyTermsService, final InvoiceService invoiceService) {
    this.reportsDAO = reportsDAO;
    this.makeService = makeService;
    this.transactionService = transactionService;
    this.companyTermsService = companyTermsService;
    this.invoiceService = invoiceService;
}
public List<TransactionVO> getTransactionVOS(TransactionVO searchTransaction){
    return transactionService.searchTransactions(searchTransaction);
}


public JasperPrint getModelListReport(JasperReport jasperReport,ReportsVO currentReport,MakeAndModelVO searchMakeAndModelVO){
    currentReport.setMakeAndModelVOs(makeService.searchMakeVOs(searchMakeAndModelVO));
    var jasperPrint = new JasperPrint();
    try {
        jasperPrint = reportsDAO.getModelListReport(jasperReport, currentReport);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


public void updateTransactionInfo(InvoiceReportVO invoiceReportVO,TransactionReportVO transactionVO){
    invoiceReportVO.setTagNo(transactionVO.getTagNo());
    invoiceReportVO.setCustomerId(transactionVO.getCustomerId());
    invoiceReportVO.setCustomerName(transactionVO.getCustomerName());
    invoiceReportVO.setCustomerAddress(transactionVO.getAddress());
}


public TransactionReportVO getTransactionReportVO(String tagNo){
    return transactionService.fetchTransactionFromTag(tagNo);
}


public JasperPrint getInvoiceReport(JasperReport jasperReport,ReportsVO currentReport){
    var transaction = getTransactionReportVO(currentReport.getTagNo());
    var invoiceVO = getInvoiceVOFromTag(transaction);
    if (invoiceVO != null) {
        currentReport.setInvoiceReportVO(getInvoiceReportVO(transaction, invoiceVO));
    }
    var jasperPrint = new JasperPrint();
    try {
        jasperPrint = reportsDAO.getInvoiceReport(jasperReport, currentReport);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


public InvoiceVO getInvoiceVOFromTag(TransactionReportVO transaction){
    InvoiceVO invoiceVO = null;
    if (transaction != null) {
        invoiceVO = getInvoiceVOs(transaction.getTagNo());
    }
    return invoiceVO;
}


public CompanyTermsVO getCompanyTerms(){
    var companyTermsVO = companyTermsService.listCompanyTerms();
    CompanyTermsVO companyTerms = null;
    if (companyTermsVO.isPresent()) {
        companyTerms = companyTermsVO.get();
    }
    return companyTerms;
}


public JasperPrint getCallReport(JasperReport jasperReport,ReportsVO currentReport){
    var transactionVO = getTransactionReportVO(currentReport.getTagNo());
    currentReport.setTransactionReportVO(transactionVO);
    var jasperPrint = new JasperPrint();
    var companyTerms = getCompanyTerms();
    try {
        jasperPrint = reportsDAO.getCallReport(jasperReport, currentReport, companyTerms);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


public JasperPrint getMakeDetailsChart(JasperReport jasperReport,ReportsVO currentReport){
    currentReport.setMakeVOList(makeService.fetchMakes());
    var jasperPrint = new JasperPrint();
    try {
        jasperPrint = reportsDAO.getMakeDetailsChart(jasperReport, currentReport);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


public InvoiceReportVO getInvoiceReportVO(TransactionReportVO transactionVO,InvoiceVO invoiceVO){
    var invoiceReportVO = new InvoiceReportVO();
    updateInvoiceInfo(invoiceReportVO, invoiceVO);
    updateTransactionInfo(invoiceReportVO, transactionVO);
    updateCompanyInfo(invoiceReportVO);
    return invoiceReportVO;
}


public InvoiceVO getInvoiceVOs(String tagNo){
    Optional<InvoiceVO> firstInvoice = invoiceService.fetchInvoiceVOFromTagNo(tagNo);
    // todo:this is not the right way
    return firstInvoice.orElse(null);
}


public JasperPrint getTransactionsListReport(JasperReport jasperReport,ReportsVO currentReport,TransactionVO searchTransaction){
    currentReport.setTransactionsList(getTransactionVOS(searchTransaction));
    var jasperPrint = new JasperPrint();
    try {
        jasperPrint = reportsDAO.getTransactionsListReport(jasperReport, currentReport);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


public List<ReportsVO> generateDailyReport(){
    return reportsDAO.generateDailyReport();
}


public void updateInvoiceInfo(InvoiceReportVO invoiceReportVO,InvoiceVO invoiceVO){
    invoiceReportVO.setInvoiceId(invoiceVO.getId());
    invoiceReportVO.setDescription(invoiceVO.getDescription());
    invoiceReportVO.setSerialNo(invoiceVO.getSerialNo());
    invoiceReportVO.setQuantity(invoiceVO.getQuantity());
    invoiceReportVO.setRate(invoiceVO.getRate());
    invoiceReportVO.setAmount(invoiceVO.getAmount());
    invoiceReportVO.setTotalAmount(invoiceVO.getAmount());
}


public void updateCompanyInfo(InvoiceReportVO invoiceReportVO){
    var companyTermsVO = companyTermsService.listCompanyTerms();
    if (companyTermsVO.isPresent()) {
        invoiceReportVO.setCompanyName(companyTermsVO.get().getCompanyName());
        invoiceReportVO.setCompanyAddress(companyTermsVO.get().getCompanyAddress());
        invoiceReportVO.setCompanyPhoneNumber(companyTermsVO.get().getCompanyPhoneNumber());
        invoiceReportVO.setCompanyWebsite(companyTermsVO.get().getCompanyWebsite());
        invoiceReportVO.setCompanyEmail(companyTermsVO.get().getCompanyEmail());
        invoiceReportVO.setCompanyTerms(companyTermsVO.get().getCompanyTerms());
        invoiceReportVO.setCompanyVatTin(companyTermsVO.get().getCompanyVatTin());
        invoiceReportVO.setCompanyCstTin(companyTermsVO.get().getCompanyCstTin());
    }
}


public JasperPrint getErrorReport(JasperReport jasperReport){
    var jasperPrint = new JasperPrint();
    try {
        jasperPrint = reportsDAO.getErrorReport(jasperReport);
    } catch (JRException ex) {
        LOG.error(ex.getLocalizedMessage());
    }
    return jasperPrint;
}


}