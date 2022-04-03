package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.Statistics;
@Transactional
public interface StatisticsRepository extends CrudRepository<Statistics, Long>, JpaRepository<Statistics, Long>{


public Statistics findByServicename(String servicename)
;

}