package app.qienuren.controller;
 import app.qienuren.model.TijdelijkeTrainee;
import app.qienuren.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TijdelijkeTraineeRepository extends JpaRepository<TijdelijkeTrainee, Long>{


public void koppelTrainee(long id,Trainee trainee);

public List<Trainee> getTrainees(long id);

public void setTrainees(long id,List<Trainee> trainees);

}