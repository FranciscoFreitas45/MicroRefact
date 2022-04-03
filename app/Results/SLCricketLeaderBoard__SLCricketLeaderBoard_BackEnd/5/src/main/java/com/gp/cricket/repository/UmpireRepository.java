package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Umpire;
public interface UmpireRepository extends JpaRepository<Umpire, Integer>{


@Query("FROM Umpire p WHERE p.userId.status=1 ")
public List<Umpire> getAvailableUmpires()
;

@Query("FROM Umpire p WHERE p.userId.status=1 AND p.userId.userId =:umpireId")
public Umpire getAvailableUmpire(Integer umpireId)
;

}