package com.example.smartkitchenbackend.repositories.ingredient;
 import com.example.smartkitchenbackend.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IngredientJPARepository extends JpaRepository<Ingredient, Long>{


public Boolean existsByName(String name)
;

public Ingredient findByName(String name)
;

}