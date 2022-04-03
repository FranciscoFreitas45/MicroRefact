package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Vital;
import org.jugbd.mnet.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VitalDao extends JpaRepository<Vital, Long>, JpaSpecificationExecutor<Vital>{


public List<Vital> findByStatusAndRegister_Id(Status status,Long id)
;

public Set<Vital> getVitals(Long id);

public void setVitals(Long id,Set<Vital> vitals);

public OutdoorRegister setVitals(Long id,Set<Vital> vitals);

public Set<Vital> getVitals(Long id);

}