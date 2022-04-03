package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersMedical;
public interface CreepersMedicalDao extends JpaRepository<TCreepersMedical, Long>, JpaSpecificationExecutor<TCreepersMedical>{


public TCreepersMedical findTop1ByKeyAndType(String key,String type)
;

public List<TCreepersMedical> findByKey(String key)
;

public TCreepersMedical findTop1ByKey(String key)
;

}