package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.OperationalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OperationalDetailDao extends JpaRepository<OperationalDetail, Long>{


public Set<OperationalDetail> getOperationalDetails(Long id);

public void setOperationalDetails(Long id,Set<OperationalDetail> operationalDetails);

}