import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.Nationality;
import pt.iscte.hospital.repositories.NationalityRepository;
import java.util.List;
@Service
public class NationalityServiceImpl implements pt.iscte.hospital.services.NationalityService,NationalityService{

@Autowired
 private  NationalityRepository nationalityRepository;


@Override
public List<Nationality> findAll(){
    return nationalityRepository.findAll();
}


}