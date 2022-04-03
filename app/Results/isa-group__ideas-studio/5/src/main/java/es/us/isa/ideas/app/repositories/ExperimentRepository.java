package es.us.isa.ideas.app.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.us.isa.ideas.app.entities.Experiment;
@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Integer>{


}