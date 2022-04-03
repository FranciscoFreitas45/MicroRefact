package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.ClinicalHistory;
public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Integer>{


@SuppressWarnings("unchecked")
public ClinicalHistory save(ClinicalHistory clinicalHistory)
;

public ClinicalHistory findClinicalHistoryById(Integer id)
;

public List<ClinicalHistory> findAll()
;

public void delete(ClinicalHistory clinicalHistory)
;

public void setClinicalHistory(Integer id,ClinicalHistory clinicalHistory);

public ClinicalHistory getClinicalHistory(Integer id);

}