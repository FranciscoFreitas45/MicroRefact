package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.HealthCare;
import io.swagger.model.Medic;
import io.swagger.model.User;
@Transactional
public interface HealthCareRepository extends JpaRepository<HealthCare, Long>{


public List<HealthCare> findByMedic(Medic medic)
;

}