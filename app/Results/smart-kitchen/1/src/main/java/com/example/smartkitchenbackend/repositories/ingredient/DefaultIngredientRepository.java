package com.example.smartkitchenbackend.repositories.ingredient;
 import com.example.smartkitchenbackend.entities.Ingredient;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class DefaultIngredientRepository implements IngredientRepository{

 private  IngredientJPARepository ingredientJPARepository;


@Override
public Boolean existsByName(String name){
    return ingredientJPARepository.existsByName(name);
}


@Override
public Ingredient findById(long id){
    return ingredientJPARepository.findById(id).orElseThrow(() -> new NotFoundException("No ingredient exists with id:" + id));
}


@Override
public Ingredient save(Ingredient ingredient){
    return ingredientJPARepository.save(ingredient);
}


@Override
public Ingredient findByName(String name){
    return ingredientJPARepository.findByName(name);
}


@Override
public void delete(Ingredient ingredient){
    ingredientJPARepository.delete(ingredient);
}


}