package switchtwentytwenty.project.applicationservice.irepository;
 import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import java.io.IOException;
import java.util.List;
public interface IFamilyRepository {


public boolean existsByID(FamilyID id)
;

public Family findByID(FamilyID id)
;

public FamilyJPA save(Family entity)
;

public void deleteAll()
;

public List<Family> findAll()
;

}