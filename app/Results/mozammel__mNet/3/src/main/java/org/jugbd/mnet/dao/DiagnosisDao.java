package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DiagnosisDao extends JpaRepository<Diagnosis, Long>{


}