package com.cg.sprint.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Shows;
public interface ShowsDao extends JpaRepository<Shows, Integer>{


@Query("select a from Shows a ")
public List<Shows> getShows()
;

@Query("select a from Shows a")
public List<Shows> getShowList()
;

}