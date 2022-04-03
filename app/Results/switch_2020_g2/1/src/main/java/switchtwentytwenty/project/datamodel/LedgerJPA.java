package switchtwentytwenty.project.datamodel;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Ledger")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerJPA {

@Id
@Getter
 private  String id;

@Getter
@OneToMany(mappedBy = "ledgerJPA", cascade = CascadeType.ALL)
 private  List<PaymentJPA> paymentJPAList;

@Getter
@OneToMany(mappedBy = "ledgerJPA", cascade = CascadeType.ALL)
 private  List<TransferJPA> transferJPAList;

/**
 * Sole Constructor
 * @param id - ledger id
 */
public LedgerJPA(String id) {
    this.id = id;
    this.paymentJPAList = new ArrayList<>();
    this.transferJPAList = new ArrayList<>();
}
public void addTransfer(TransferJPA transferJPA){
    this.transferJPAList.add(transferJPA);
}


public void addPayment(PaymentJPA paymentJPA){
    this.paymentJPAList.add(paymentJPA);
}


}