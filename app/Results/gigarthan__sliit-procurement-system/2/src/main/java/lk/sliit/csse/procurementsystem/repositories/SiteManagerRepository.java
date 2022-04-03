package lk.sliit.csse.procurementsystem.repositories;
 import java.util.List;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SiteManagerRepository extends EmployeeBaseRepository<SiteManager>{


@Query("select s from SiteManager s where s.firstName like %?1% or s.lastName like %?2%")
public List<SiteManager> findByFirstnameContainingOrLastnameContaining(String firstname,String lastname)
;

public SiteManager findByFirstName(String firstName)
;

@Query("select s from SiteManager s where s.firstName like %?1")
public List<SiteManager> findByFirstnameEndsWith(String firstname)
;

public SiteManager findByFirstNameAndLastName(String firstName,String lastName)
;

}