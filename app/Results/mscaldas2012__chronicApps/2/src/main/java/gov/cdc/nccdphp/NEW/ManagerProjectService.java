package gov.cdc.nccdphp.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import gov.cdc.nccdphp.domain.Manager;
@Service
public class ManagerProjectService {

@Autowired
 private ManagerRepository managerrepository;


}