package sn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sn.repositories.MessageRepository;
import sn.model.Message;
@Service
public class MessagePersonService {

@Autowired
 private MessageRepository messagerepository;


}