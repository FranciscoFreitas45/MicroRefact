package switchtwentytwenty.project.domain.aggregate.family;
 import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyJpaToDomainDTO;
public class FamilyFactory {

/**
 * Private constructor.
 */
private FamilyFactory() {
}
public Family create(FamilyJpaToDomainDTO dto){
    Family family = new Family(dto.getFamilyID());
    family.setAdministratorID(dto.getAdministratorID());
    family.setLedgerID(dto.getLedgerID());
    family.setRegistrationDate(dto.getRegistrationDate());
    family.addAccountID(dto.getAccountID());
    family.setFamilyID(dto.getFamilyID());
    family.setFamilyRelationList(dto.getFamilyRelation());
    family.setName(dto.getName());
    return family;
}


}