package switchtwentytwenty.project.interfaceadaptor.repository.jpa;
 import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.PersonJPA;
import java.util.List;
public interface PersonJPARepository extends CrudRepository<PersonJPA, String>{


public List<PersonJPA> findAllByFamilyID(String familyID)
;

public boolean existsByFamilyIDAndVat(String familyID,String vat)
;

}