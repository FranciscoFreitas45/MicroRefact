package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.RequirementJPARepository;
import pl.szymanski.sharelibrary.entity.Requirement;
@Service
public class RequirementExchangeService {

@Autowired
 private RequirementJPARepository requirementjparepository;


}