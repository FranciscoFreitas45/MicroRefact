package ar.com.veterinaria.app.entities.dao;
 import ar.com.veterinaria.app.entities.ClinicalHistory;
public interface ClinicalHistoryDao extends BaseDao<ClinicalHistory>{


public ClinicalHistory findClinicalHistoryByName(String breed)
;

}