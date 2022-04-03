package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExaminationDao extends JpaRepository<Examination, Long>{


public Examination getExamination(Long id);

public void setExamination(Long id,Examination examination);

public Examination getExamination(Long id);

public OutdoorRegister setExamination(Long id,Examination examination);

}