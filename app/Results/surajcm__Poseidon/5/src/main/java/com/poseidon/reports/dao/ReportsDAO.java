package com.poseidon.reports.dao;
 import com.poseidon.company.domain.CompanyTermsVO;
import com.poseidon.reports.domain.ReportsVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ReportsDAO {


public JasperPrint getMakeDetailsChart(JasperReport jasperReport,ReportsVO currentReport){
    Map<String, Object> params = new HashMap<>();
    return JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(currentReport.getMakeVOList()));
}


public JasperPrint getModelListReport(JasperReport jasperReport,ReportsVO currentReport){
    Map<String, Object> params = new HashMap<>();
    return JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(currentReport.getMakeAndModelVOs()));
}


public JasperPrint getTransactionsListReport(JasperReport jasperReport,ReportsVO currentReport){
    Map<String, Object> params = new HashMap<>();
    return JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(currentReport.getTransactionsList()));
}


public List<ReportsVO> generateDailyReport(){
    return Collections.emptyList();
}


public JasperPrint getInvoiceReport(JasperReport jasperReport,ReportsVO currentReport){
    Map<String, Object> params = new HashMap<>();
    var invoiceReportVOs = Collections.singletonList(currentReport.getInvoiceReportVO());
    return JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(invoiceReportVOs));
}


public JasperPrint getCallReport(JasperReport jasperReport,ReportsVO currentReport,CompanyTermsVO companyTermsVO){
    if (companyTermsVO != null) {
        currentReport.getTransactionReportVO().setCompanyName(companyTermsVO.getCompanyName());
        currentReport.getTransactionReportVO().setCompanyAddress(companyTermsVO.getCompanyAddress());
        currentReport.getTransactionReportVO().setCompanyPhoneNumber(companyTermsVO.getCompanyPhoneNumber());
        currentReport.getTransactionReportVO().setCompanyWebsite(companyTermsVO.getCompanyWebsite());
        currentReport.getTransactionReportVO().setCompanyEmail(companyTermsVO.getCompanyEmail());
        // todo : fix it
        currentReport.getTransactionReportVO().setDateReported(companyTermsVO.getCreatedDate());
    }
    var reportVOs = Collections.singletonList(currentReport.getTransactionReportVO());
    return JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRBeanCollectionDataSource(reportVOs));
}


public JasperPrint getErrorReport(JasperReport jasperReport){
    Map<String, Object> params = new HashMap<>();
    return JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(new ArrayList<>()));
}


}