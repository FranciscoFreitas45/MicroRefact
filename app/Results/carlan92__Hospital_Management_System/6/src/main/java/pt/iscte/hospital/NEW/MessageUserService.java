package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.MessageRepository;
import pt.iscte.hospital.entities.Message;
@Service
public class MessageUserService {

@Autowired
 private MessageRepository messagerepository;


}