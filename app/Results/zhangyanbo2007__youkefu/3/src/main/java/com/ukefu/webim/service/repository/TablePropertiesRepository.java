package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.TableProperties;
public interface TablePropertiesRepository extends JpaRepository<TableProperties, String>{


public List<TableProperties> findByTablename(String tablename)
;

public List<TableProperties> findByFieldnameAndTablename(String fieldname,String tablename)
;

public TableProperties findById(String id)
;

public List<TableProperties> findByDbtableid(String dbtableid)
;

public TableProperties findByTablenameAndFieldname(String tablename,String fieldname)
;

public List<TableProperties> findByName(String name)
;

public List<TableProperties> findByFieldnameAndDbtableid(String fieldname,String dbtableid)
;

public List<TableProperties> findBySecfield(boolean secfield)
;

}