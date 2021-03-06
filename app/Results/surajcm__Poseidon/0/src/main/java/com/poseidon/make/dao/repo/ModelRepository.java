package com.poseidon.make.dao.repo;
 import com.poseidon.make.dao.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{


public List<Model> findByModelName(String modelName)
;

@Query("Select m from Model m where m.modelName like :modelName")
public List<Model> findByModelNameWildCard(String modelName)
;

}