package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gp.cricket.entity.Referee;
public interface RefereeRepository extends JpaRepository<Referee, Integer>{


@Query("FROM Referee m WHERE  m.userId.status = 1")
public List<Referee> getAvailableReferees()
;

}