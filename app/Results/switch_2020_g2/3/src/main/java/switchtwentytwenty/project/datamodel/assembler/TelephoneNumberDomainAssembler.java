package switchtwentytwenty.project.datamodel.assembler;
 import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.TelephoneNumberJPA;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumber;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelephoneNumberDomainAssembler {


public TelephoneNumberJPA toData(TelephoneNumber telephoneNumber,PersonJPA personJPA){
    return new TelephoneNumberJPA(telephoneNumber.toString(), personJPA);
}


public TelephoneNumber toDomain(TelephoneNumberJPA telephoneNumberJPA){
    return new TelephoneNumber(telephoneNumberJPA.getNumber());
}


}