package com.example.smartkitchenbackend.repositories.kitchen;
 import com.example.smartkitchenbackend.entities.Kitchen;
import java.util.List;
public interface KitchenRepository {


public int numberOfKitchensWithSimilarNames(String name)
;

public Kitchen findById(long id)
;

public Kitchen save(Kitchen kitchen)
;

public void delete(Kitchen kitchen)
;

public List<Kitchen> findAll()
;

}