package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Long>{


public MedicalHistory getMedicalHistory(Long id);

public void setMedicalHistory(Long id,MedicalHistory medicalHistory);

}