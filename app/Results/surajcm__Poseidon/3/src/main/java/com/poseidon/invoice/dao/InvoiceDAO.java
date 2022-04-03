package com.poseidon.invoice.dao;
 import com.poseidon.invoice.dao.entities.Invoice;
import com.poseidon.invoice.dao.repo.InvoiceRepository;
import com.poseidon.invoice.domain.InvoiceVO;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneak;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
@SuppressWarnings("unused")
@Service
public class InvoiceDAO {

 private  String TAG_NO;

 private  String SERIAL_NO;

 private  String DESCRIPTION;

 private  String AMOUNT;

 private  String ID;

 private  InvoiceRepository invoiceRepository;

@PersistenceContext
 private  EntityManager em;

 private  Logger log;

public InvoiceDAO(final InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
}
public void deleteInvoice(Long id){
    var consumer = sneaked(invoiceRepository::deleteById);
    consumer.accept(id);
}


public Optional<InvoiceVO> fetchInvoiceVOFromTagNo(String tagNo){
    var optionalInvoice = sneak(() -> invoiceRepository.findByTagNumber(tagNo));
    return optionalInvoice.map(this::getInvoiceVoFromInvoice);
}


public String pattern(boolean includes,boolean startsWith,String element){
    String patternString;
    if (Boolean.TRUE.equals(includes)) {
        patternString = "%" + element + "%";
    } else if (Boolean.TRUE.equals(startsWith)) {
        patternString = element + "%";
    } else {
        patternString = element;
    }
    return patternString;
}


public InvoiceVO getInvoiceVoFromInvoice(Invoice invoice){
    var invoiceVO = new InvoiceVO();
    invoiceVO.setId(invoice.getId());
    invoiceVO.setTransactionId(invoice.getTransactionId());
    invoiceVO.setTagNo(invoice.getTagNumber());
    invoiceVO.setCustomerId(invoice.getCustomerId());
    invoiceVO.setCustomerName(invoice.getCustomerName());
    invoiceVO.setDescription(invoice.getDescription());
    invoiceVO.setSerialNo(invoice.getSerialNumber());
    if (invoice.getQuantity() != null) {
        invoiceVO.setQuantity(invoice.getQuantity());
    }
    if (invoice.getRate() != null) {
        invoiceVO.setRate(Double.valueOf(invoice.getRate()));
    }
    if (invoice.getAmount() != null) {
        invoiceVO.setAmount(Double.valueOf(invoice.getAmount()));
    }
    return invoiceVO;
}


public List<InvoiceVO> searchInvoice(InvoiceVO searchInvoiceVO){
    var builder = em.unwrap(Session.class).getCriteriaBuilder();
    var criteria = builder.createQuery(Invoice.class);
    var invoiceRoot = criteria.from(Invoice.class);
    criteria.select(invoiceRoot);
    var includes = searchInvoiceVO.getIncludes();
    var startsWith = searchInvoiceVO.getStartsWith();
    if (StringUtils.hasText(searchInvoiceVO.getTagNo())) {
        var tag = pattern(includes, startsWith, searchInvoiceVO.getTagNo());
        criteria.where(builder.like(invoiceRoot.get(TAG_NO), tag));
    }
    if (StringUtils.hasText(searchInvoiceVO.getSerialNo())) {
        var serial = pattern(includes, startsWith, searchInvoiceVO.getSerialNo());
        criteria.where(builder.like(invoiceRoot.get(SERIAL_NO), serial));
    }
    if (StringUtils.hasText(searchInvoiceVO.getDescription())) {
        var desc = pattern(includes, startsWith, searchInvoiceVO.getDescription());
        criteria.where(builder.like(invoiceRoot.get(DESCRIPTION), desc));
    }
    if (searchInvoiceVO.getId() != null && searchInvoiceVO.getId() > 0) {
        criteria.where(builder.equal(invoiceRoot.get(ID), searchInvoiceVO.getId()));
    }
    if (searchInvoiceVO.getAmount() != null && searchInvoiceVO.getAmount() > 0) {
        if (Boolean.TRUE.equals(searchInvoiceVO.getGreater()) && Boolean.FALSE.equals(searchInvoiceVO.getLesser())) {
            criteria.where(builder.greaterThanOrEqualTo(invoiceRoot.get(AMOUNT), searchInvoiceVO.getAmount()));
        } else if (Boolean.TRUE.equals(searchInvoiceVO.getLesser()) && Boolean.FALSE.equals(searchInvoiceVO.getGreater())) {
            criteria.where(builder.lessThanOrEqualTo(invoiceRoot.get(AMOUNT), searchInvoiceVO.getAmount()));
        } else if (Boolean.FALSE.equals(searchInvoiceVO.getLesser()) && Boolean.FALSE.equals(searchInvoiceVO.getGreater())) {
            criteria.where(builder.equal(invoiceRoot.get(AMOUNT), searchInvoiceVO.getAmount()));
        }
    }
    List<Invoice> resultList = em.unwrap(Session.class).createQuery(criteria).getResultList();
    return resultList.stream().map(this::convertInvoiceToInvoiceVO).toList();
}


public List<InvoiceVO> fetchInvoiceForListOfTransactions(List<String> tagNumbers){
    var invoices = sneak(() -> invoiceRepository.fetchTodaysInvoices(tagNumbers));
    return invoices.stream().map(this::getInvoiceVoFromInvoice).toList();
}


public Optional<InvoiceVO> fetchInvoiceVOFromId(Long id){
    var optionalInvoice = sneak(() -> invoiceRepository.findById(id));
    return optionalInvoice.map(this::getInvoiceVoFromInvoice);
}


public void updateInvoice(InvoiceVO currentInvoiceVO){
    var optionalInvoice = sneak(() -> invoiceRepository.findById(currentInvoiceVO.getId()));
    if (optionalInvoice.isPresent()) {
        var invoice = getInvoice(currentInvoiceVO, optionalInvoice.get());
        sneak(() -> invoiceRepository.save(invoice));
    }
}


public InvoiceVO convertInvoiceToInvoiceVO(Invoice invoice){
    var invoiceVO = new InvoiceVO();
    invoiceVO.setId(invoice.getId());
    invoiceVO.setCustomerName(invoice.getCustomerName());
    invoiceVO.setTagNo(invoice.getTagNumber());
    invoiceVO.setDescription(invoice.getDescription());
    invoiceVO.setSerialNo(invoice.getSerialNumber());
    invoiceVO.setAmount(Double.valueOf(invoice.getAmount()));
    return invoiceVO;
}


public List<InvoiceVO> findInvoices(InvoiceVO searchInvoiceVO){
    return sneak(() -> searchInvoice(searchInvoiceVO));
}


public Invoice getInvoice(InvoiceVO currentInvoiceVO,Invoice invoice){
    invoice.setTagNumber(currentInvoiceVO.getTagNo());
    invoice.setDescription(currentInvoiceVO.getDescription());
    invoice.setSerialNumber(currentInvoiceVO.getSerialNo());
    if (currentInvoiceVO.getAmount() != null) {
        invoice.setAmount(currentInvoiceVO.getAmount().longValue());
    }
    invoice.setQuantity(currentInvoiceVO.getQuantity());
    if (currentInvoiceVO.getRate() != null) {
        invoice.setRate(currentInvoiceVO.getRate().longValue());
    }
    invoice.setCustomerName(currentInvoiceVO.getCustomerName());
    invoice.setCustomerId(currentInvoiceVO.getCustomerId());
    invoice.setTransactionId(currentInvoiceVO.getTransactionId());
    invoice.setModifiedBy(currentInvoiceVO.getModifiedBy());
    return invoice;
}


public void addInvoice(InvoiceVO currentInvoiceVO){
    sneak(() -> invoiceRepository.save(convertInvoiceVOToInvoice(currentInvoiceVO)));
}


public Invoice convertInvoiceVOToInvoice(InvoiceVO currentInvoiceVO){
    var invoice = getInvoice(currentInvoiceVO, new Invoice());
    invoice.setCreatedBy(currentInvoiceVO.getCreatedBy());
    return invoice;
}


}