import org.springframework.data.jpa.repository.JpaRepository;
import pt.iscte.hospital.entities.Receptionist;
import java.util.List;
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {


public List<Receptionist> findAll()


}