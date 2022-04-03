package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.MessageJPARepository;
import kielce.tu.weaii.telelearn.models.Message;
@Service
public class MessageUserService {

@Autowired
 private MessageJPARepository messagejparepository;


}