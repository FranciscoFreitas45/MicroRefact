package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.LedgerJPA;
import switchtwentytwenty.project.datamodel.PaymentJPA;
import switchtwentytwenty.project.datamodel.TransferJPA;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata;
import switchtwentytwenty.project.dto.todomaindto.LedgerJpaToDomainDTO;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
@Service
public class LedgerDomainDataAssembler {

@Autowired
 private TransferDomainDataAssembler transferDomainDataAssembler;

@Autowired
 private PaymentDomainDataAssembler paymentDomainDataAssembler;


public LedgerJPA toData(Ledger ledger){
    return new LedgerJPA(ledger.getID().toString());
}


public LedgerJpaToDomainDTO toDomain(LedgerJPA ledgerJPA){
    LedgerID ledgerID = new LedgerID(UUID.fromString(ledgerJPA.getId()));
    LedgerJpaToDomainDTO ledgerJpaToDomainDTO = new LedgerJpaToDomainDTO(ledgerID);
    List<PaymentJPA> paymentJPAList = ledgerJPA.getPaymentJPAList();
    List<TransferJPA> transferJPAList = ledgerJPA.getTransferJPAList();
    // Repopulate ledger with transfers
    for (TransferJPA transferJPA : transferJPAList) {
        Transaction transfer = transferDomainDataAssembler.toDomain(transferJPA);
        ledgerJpaToDomainDTO.getTransactionList().add(transfer);
    }
    // Repopulate ledger with payments
    for (PaymentJPA paymentJPA : paymentJPAList) {
        Transaction payment = paymentDomainDataAssembler.toDomain(paymentJPA);
        ledgerJpaToDomainDTO.getTransactionList().add(payment);
    }
    return ledgerJpaToDomainDTO;
}


}