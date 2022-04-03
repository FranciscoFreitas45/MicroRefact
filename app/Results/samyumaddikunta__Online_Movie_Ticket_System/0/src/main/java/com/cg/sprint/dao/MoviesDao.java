package com.cg.sprint.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Movies;
public interface MoviesDao extends JpaRepository<Movies, Integer>{


@Query("select m from Movies m where theatres=?1")
public List<Movies> movieNames(String name)
;

@Query("select m from Movies m")
public List<Movies> getMovieList()
;

}