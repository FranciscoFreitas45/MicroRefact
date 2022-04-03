package com.cg.sprint.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Theatre;
public interface TheatreDao extends JpaRepository<Theatre, Integer>{


@Query("select t from Theatre t where city_name=?1")
public List<Theatre> theatreNames(String name)
;

@Query("select t from Theatre t")
public List<Theatre> getTheatreList()
;

}