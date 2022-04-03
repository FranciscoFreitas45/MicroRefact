package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{


public Set<Visit> getVisits(Long id);

public void setVisits(Long id,Set<Visit> visits);

public Set<Visit> getVisits(Long id);

public OutdoorRegister setVisits(Long id,Set<Visit> visits);

}