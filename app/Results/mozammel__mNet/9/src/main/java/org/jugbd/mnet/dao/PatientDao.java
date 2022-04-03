package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface PatientDao extends JpaSpecificationExecutor, JpaRepository<Patient, Long>{


public void setPatient(Long id,Patient patient);

public Patient getPatient(Long id);

public OutdoorRegister setPatient(Long id,Patient patient);

public Patient getPatient(Long id);

public Vital setPatient(Long id,Patient patient);

public Patient getPatient(Long id);

}