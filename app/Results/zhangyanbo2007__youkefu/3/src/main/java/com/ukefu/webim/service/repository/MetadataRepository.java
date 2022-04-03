package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
public interface MetadataRepository extends JpaRepository<MetadataTable, String>{


public MetadataTable findByTablename(String tablename)
;

public List<MetadataTable> findByPid(String pid)
;

public List<MetadataTable> findByOrgi(String orgi)
;

public MetadataTable findById(String id)
;

public int countByTablename(String tableName)
;

public List<MetadataTable> findAll(Specification<MetadataTable> spec)
;

public List<MetadataTable> findByTablenameIn(List<String> tablename)
;

}