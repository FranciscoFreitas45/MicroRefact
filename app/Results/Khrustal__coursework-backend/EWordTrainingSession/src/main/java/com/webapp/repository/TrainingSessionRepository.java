package com.webapp.repository;
 import com.webapp.models.TrainingSession;
import com.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long>{


public boolean existsByNameAndUserId(String name,Long userId)
;

public List<TrainingSession> findByUser(User user)
;

public void setSession(Long idOXI5,TrainingSession session);

}