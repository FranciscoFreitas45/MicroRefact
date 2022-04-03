package switchtwentytwenty.project.dto.todomaindto;
 import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidVATException;
public class FamilyAndAdminDomainAssembler {


public FamilyAndAdminVODTO toDomain(FamilyAndAdminDTO familyAndAdminDTO){
    FamilyAndAdminVODTO vo = new FamilyAndAdminVODTO();
    vo.setBirthDate(new BirthDate(familyAndAdminDTO.getBirthDate()));
    vo.setAddress(new Address(familyAndAdminDTO.getStreet(), familyAndAdminDTO.getHouseNumber(), familyAndAdminDTO.getZipCode(), familyAndAdminDTO.getCity(), familyAndAdminDTO.getCountry()));
    vo.setEmail(new Email(familyAndAdminDTO.getEmail()));
    vo.setVat(new VAT(familyAndAdminDTO.getVat()));
    vo.setPersonName(new PersonName(familyAndAdminDTO.getPersonName()));
    vo.setFamilyName(new FamilyName(familyAndAdminDTO.getFamilyName()));
    vo.setPhoneNumbers(new TelephoneNumberList(familyAndAdminDTO.getPhoneNumbers()));
    return vo;
}


}