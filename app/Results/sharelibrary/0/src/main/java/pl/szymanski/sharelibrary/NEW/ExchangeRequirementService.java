package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.ExchangeJPARepository;
import pl.szymanski.sharelibrary.entity.Exchange;
@Service
public class ExchangeRequirementService {

@Autowired
 private ExchangeJPARepository exchangejparepository;


}