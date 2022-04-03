package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Configuration;
@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer>{


@Query("select c from Configuration c")
public Configuration configuration()
;

@Query("select c.badWords from Configuration c")
public String badWords()
;

@Query("select s from Configuration c join c.spamWords s")
public List<String> spamWords()
;

@Query("select c.goodWords from Configuration c")
public String goodWords()
;

}