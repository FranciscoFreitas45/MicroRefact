package com.poseidon.invoice.service;
 import com.poseidon.invoice.dao.InvoiceDAO;
import com.poseidon.invoice.domain.InvoiceVO;
import com.poseidon.transaction.domain.TransactionVO;
import com.poseidon.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.poseidon.Interface.TransactionService;
@Service
public class InvoiceService {

 private  Logger log;

 private  InvoiceDAO invoiceDAO;

 private  TransactionService transactionService;

public InvoiceService(final InvoiceDAO invoiceDAO, final TransactionService transactionService) {
    this.invoiceDAO = invoiceDAO;
    this.transactionService = transactionService;
}
public List<TransactionVO> getTransactionVOS(){
    return transactionService.listAllTransactions();
}


public void deleteInvoice(Long id){
    invoiceDAO.deleteInvoice(id);
}


public List<InvoiceVO> findInvoices(InvoiceVO searchInvoiceVo){
    return invoiceDAO.findInvoices(searchInvoiceVo);
}


public Optional<InvoiceVO> fetchInvoiceVOFromTagNo(String tagNo){
    return invoiceDAO.fetchInvoiceVOFromTagNo(tagNo);
}


public List<String> fetchTagNoFromListOfTransactionVOs(List<TransactionVO> transactionVOs){
    return transactionVOs.stream().map(TransactionVO::getTagNo).toList();
}


public List<InvoiceVO> fetchInvoiceForListOfTransactions(){
    List<InvoiceVO> invoiceVOs = null;
    var transactionVOs = getTransactionVOS();
    if (transactionVOs != null) {
        var tagNumbers = fetchTagNoFromListOfTransactionVOs(transactionVOs);
        log.info("tag numbers are : {}", tagNumbers);
        invoiceVOs = invoiceDAO.fetchInvoiceForListOfTransactions(tagNumbers);
        log.info("invoice vos count : {}", invoiceVOs.size());
    }
    return invoiceVOs;
}


public void addInvoice(InvoiceVO currentInvoiceVO){
    var transactionReportVO = transactionService.fetchTransactionFromTag(currentInvoiceVO.getTagNo());
    currentInvoiceVO.setCustomerId(transactionReportVO.getCustomerId());
    currentInvoiceVO.setCustomerName(transactionReportVO.getCustomerName());
    currentInvoiceVO.setSerialNo(transactionReportVO.getSerialNo());
    invoiceDAO.addInvoice(currentInvoiceVO);
}


public Optional<InvoiceVO> fetchInvoiceVOFromId(Long id){
    return invoiceDAO.fetchInvoiceVOFromId(id);
}


public void updateInvoice(InvoiceVO currentInvoiceVO){
    var transactionReportVo = transactionService.fetchTransactionFromTag(currentInvoiceVO.getTagNo());
    currentInvoiceVO.setCustomerId(transactionReportVo.getCustomerId());
    currentInvoiceVO.setCustomerName(transactionReportVo.getCustomerName());
    currentInvoiceVO.setSerialNo(transactionReportVo.getSerialNo());
    invoiceDAO.updateInvoice(currentInvoiceVO);
}


public List<String> allTagNumbers(){
    return transactionService.allTagNumbers();
}


}