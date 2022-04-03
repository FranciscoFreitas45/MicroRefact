package ar.com.veterinaria.app.entities.dao;
 import ar.com.veterinaria.app.entities.Animal;
public interface AnimalDao extends BaseDao<Animal>{


public Animal findAnimalByName(String name)
;

}