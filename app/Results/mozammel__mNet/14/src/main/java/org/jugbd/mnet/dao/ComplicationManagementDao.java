package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.ComplicationManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ComplicationManagementDao extends JpaRepository<ComplicationManagement, Long>{


public ComplicationManagement getComplicationManagement(Long id);

public void setComplicationManagement(Long id,ComplicationManagement complicationManagement);

}