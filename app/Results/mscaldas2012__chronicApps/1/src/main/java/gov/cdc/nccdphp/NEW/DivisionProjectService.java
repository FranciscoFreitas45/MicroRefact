package gov.cdc.nccdphp.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import gov.cdc.nccdphp.domain.Division;
@Service
public class DivisionProjectService {

@Autowired
 private DivisionRepository divisionrepository;


}