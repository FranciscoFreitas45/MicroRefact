package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Investigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InvestigationDao extends JpaRepository<Investigation, Long>{


public Set<Investigation> getInvestigation(Long id);

public void setInvestigation(Long id,Set<Investigation> investigation);

}