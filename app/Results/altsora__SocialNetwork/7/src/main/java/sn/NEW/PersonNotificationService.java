package sn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sn.repositories.PersonRepository;
import sn.model.Person;
@Service
public class PersonNotificationService {

@Autowired
 private PersonRepository personrepository;


}