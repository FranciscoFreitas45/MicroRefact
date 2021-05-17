import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.User;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


public User findByNif(Long nif)


public User findByPatientNumberAndAccount(Long patientNumber,String account)


public User findByDocumentNumberAndAccount(Long documentNumber,String account)


public User findByUsername(String username)


public User findByDocumentNumber(Long documentNumber)


public User findByPatientNumber(Long patientNumber)


public User findByUserId(Long userId)


public User findByEmail(String email)


public User findByNifAndAccount(Long nif,String account)


}