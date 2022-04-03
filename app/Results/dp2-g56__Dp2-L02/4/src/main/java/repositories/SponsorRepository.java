package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Sponsor;
@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer>{


@Query("select a from Sponsor a join a.userAccount b where b.username = ?1")
public Sponsor getSponsorByUsername(String a)
;

}