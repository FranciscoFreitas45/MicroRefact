package com.meli.weather.infrastructure.repository;
 import com.meli.weather.infrastructure.model.Planet;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class PlanetJpaRepository implements CrudRepository<Planet, String>{

 private  EntityManager entityManager;

public PlanetJpaRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
}
@TransactionalAdvice
public List<Planet> firstThreePlanets(){
    return entityManager.createQuery("FROM Planet", Planet.class).setMaxResults(3).getResultList();
}


}