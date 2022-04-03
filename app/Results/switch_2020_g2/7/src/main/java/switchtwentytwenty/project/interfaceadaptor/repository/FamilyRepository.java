package switchtwentytwenty.project.interfaceadaptor.repository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationJPA;
import switchtwentytwenty.project.datamodel.assembler.FamilyDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.FamilyRelationDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.FamilyJpaToDomainDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.FamilyJPARepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class FamilyRepository implements IFamilyRepository{

@Autowired
 private FamilyJPARepository repository;

@Autowired
 private FamilyDomainDataAssembler familyAssembler;

@Autowired
 private FamilyRelationDomainDataAssembler relationAssembler;


@Override
public boolean existsByID(FamilyID familyID){
    return this.repository.existsById(familyID.toString());
}


@Transactional
@Override
public Family findByID(FamilyID familyID){
    Optional<FamilyJPA> familyJPA = this.repository.findById(familyID.toString());
    if (!familyJPA.isPresent()) {
        throw new ElementNotFoundException(Constants.FAMILY_NOT_FOUND);
    }
    FamilyJpaToDomainDTO dto = familyAssembler.toDomain(familyJPA.get());
    return FamilyFactory.create(dto);
}


@Override
public FamilyJPA save(Family family){
    FamilyJPA familyJPA = familyAssembler.toData(family);
    List<FamilyRelation> relationList = family.getFamilyRelationList();
    for (FamilyRelation familyRelation : relationList) {
        FamilyRelationJPA familyRelationJPA = relationAssembler.toData(familyRelation, familyJPA);
        familyJPA.addFamilyRelation(familyRelationJPA);
    }
    return this.repository.save(familyJPA);
}


@Override
public void deleteAll(){
    this.repository.deleteAll();
}


@Transactional
@Override
public List<Family> findAll(){
    Iterable<FamilyJPA> familyJPA = this.repository.findAll();
    List<Family> familyList = new ArrayList<>();
    for (FamilyJPA familyJPA1 : familyJPA) {
        FamilyJpaToDomainDTO dto = familyAssembler.toDomain(familyJPA1);
        Family family = FamilyFactory.create(dto);
        familyList.add(family);
    }
    return familyList;
}


}