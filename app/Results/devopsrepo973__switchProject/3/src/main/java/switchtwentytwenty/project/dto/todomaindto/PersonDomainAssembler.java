package switchtwentytwenty.project.dto.todomaindto;
 import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidVATException;
import java.util.List;
import java.util.UUID;
public class PersonDomainAssembler {

/**
 * Private empty constructor.
 */
private PersonDomainAssembler() {
}
public PersonVoDTO toDomain(PersonDTO personDTO,LedgerID ledgerID){
    PersonName personName = new PersonName(personDTO.getName());
    Email email = new Email(personDTO.getEmail());
    BirthDate birthDate = new BirthDate(personDTO.getBirthDate());
    VAT vat = new VAT(personDTO.getVat());
    String houseNumber = personDTO.getHouseNumber();
    String street = personDTO.getStreet();
    String zipCode = personDTO.getZipCode();
    String city = personDTO.getCity();
    String country = personDTO.getCountry();
    Address address = new Address(street, houseNumber, zipCode, city, country);
    TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
    List<String> telephoneNumbers = personDTO.getPhoneNumbers();
    if (telephoneNumbers != null) {
        for (String telephoneNumber : telephoneNumbers) {
            telephoneNumberList.add(new TelephoneNumber(telephoneNumber));
        }
    }
    FamilyID familyID = new FamilyID(UUID.fromString(personDTO.getFamilyID()));
    return new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
}


}