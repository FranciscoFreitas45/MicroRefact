import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.Receptionist;
import pt.iscte.hospital.repositories.user.ReceptionistRepository;
import java.util.List;
@Service
public class ReceptionistServiceImpl implements pt.iscte.hospital.services.user.ReceptionistService,ReceptionistService{

@Autowired
 private  ReceptionistRepository receptionistRepository;


@Override
public List<Receptionist> findAll(){
    return receptionistRepository.findAll();
}


}