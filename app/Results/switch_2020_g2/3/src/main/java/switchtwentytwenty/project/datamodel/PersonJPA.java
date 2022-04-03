package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Person")
@NoArgsConstructor
public class PersonJPA {

@Id
@Getter
 private  String mainEmail;

@Getter
 private  String name;

@Getter
 private  String vat;

@Getter
 private  String birthDate;

@Getter
@OneToMany(mappedBy = "personJPA", cascade = CascadeType.ALL)
 private  List<TelephoneNumberJPA> telephoneNumbers;

@Getter
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "address", referencedColumnName = "id")
 private  AddressJPA address;

@Getter
 private  String familyID;

@Getter
@Setter
@OneToMany(mappedBy = "personJPA", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<EmailJPA> otherEmails;

@Getter
@OneToMany(mappedBy = "personJPA", cascade = CascadeType.ALL)
 private  List<AccountIDJPA> accountIDList;

@Getter
 private  String ledgerID;

// Constructor methods
/**
 * All arguments constructor.
 *
 * @param mainEmail - person identifier
 * @param name - person's name
 * @param vat - person's vat
 * @param birthDate - person's birth date
 * @param familyID - person's family by identifier
 * @param ledgerID - person's ledger by identifier
 */
public PersonJPA(String mainEmail, String name, String vat, String birthDate, String familyID, String ledgerID) {
    this.mainEmail = mainEmail;
    this.name = name;
    this.vat = vat;
    this.birthDate = birthDate;
    this.familyID = familyID;
    this.ledgerID = ledgerID;
    this.telephoneNumbers = new ArrayList<>();
    this.accountIDList = new ArrayList<>();
    this.otherEmails = new ArrayList<>();
}
public void addTelephoneNumber(TelephoneNumberJPA telephoneNumberJPA){
    this.telephoneNumbers.add(telephoneNumberJPA);
}


public void setAddress(AddressJPA addressJPA){
    this.address = addressJPA;
}


public void deleteEmails(){
    this.otherEmails = new ArrayList<>();
}


public void addAccount(AccountIDJPA accountIDJPA){
    this.accountIDList.add(accountIDJPA);
}


public void addEmail(EmailJPA emailJPA){
    this.otherEmails.add(emailJPA);
}


}