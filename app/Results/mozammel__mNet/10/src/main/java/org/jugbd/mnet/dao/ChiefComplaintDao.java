package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.ChiefComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChiefComplaintDao extends JpaRepository<ChiefComplaint, Long>{


public ChiefComplaint getChiefComplaint(Long id);

public void setChiefComplaint(Long id,ChiefComplaint chiefComplaint);

public OutdoorRegister setChiefComplaint(Long id,ChiefComplaint chiefComplaint);

public ChiefComplaint getChiefComplaint(Long id);

}