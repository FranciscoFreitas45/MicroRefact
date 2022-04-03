package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.SocialProfile;
@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer>{


}