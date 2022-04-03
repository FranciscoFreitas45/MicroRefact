package com.cg.sprint.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.City;
public interface CityDao extends JpaRepository<City, Integer>{


@Query("select c from City c")
public List<City> getCityNames()
;

@Query("select c from City c")
public List<City> getCityList()
;

}