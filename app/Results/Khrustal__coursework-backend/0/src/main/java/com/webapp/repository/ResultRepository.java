package com.webapp.repository;
 import com.webapp.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{


public List<Result> findBySessionId(Long id)
;

public List<Result> getResults(Long id);

public void setResults(Long id,List<Result> results);

}