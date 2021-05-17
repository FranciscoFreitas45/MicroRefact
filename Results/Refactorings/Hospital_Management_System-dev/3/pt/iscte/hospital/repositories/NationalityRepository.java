import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Nationality;
@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {


public Nationality findByName(String name)


}