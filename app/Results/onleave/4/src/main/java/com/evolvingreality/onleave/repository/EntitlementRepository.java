package com.evolvingreality.onleave.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
public interface EntitlementRepository extends JpaRepository<Entitlement, Long>{


public Optional<Entitlement> findByUserAndYear(User user,Integer year)
;

}