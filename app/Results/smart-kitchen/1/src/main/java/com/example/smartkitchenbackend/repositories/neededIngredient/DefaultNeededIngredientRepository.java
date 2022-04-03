package com.example.smartkitchenbackend.repositories.neededIngredient;
 import com.example.smartkitchenbackend.entities.NeededIngredient;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class DefaultNeededIngredientRepository implements NeededIngredientRepository{

 private  NeededIngredientJPARepository neededIngredientJPARepository;


@Override
public Boolean existsByIngredientIdAndFoodId(long id,long foodId){
    return neededIngredientJPARepository.existsByIngredientIdAndFoodId(id, foodId);
}


@Override
public NeededIngredient findById(long id){
    return neededIngredientJPARepository.findById(id).orElseThrow(() -> new NotFoundException("No needed ingredient for food exists with id:" + id));
}


@Override
public NeededIngredient save(NeededIngredient neededIngredient){
    return neededIngredientJPARepository.save(neededIngredient);
}


@Override
public NeededIngredient findByIngredientIdAndFoodId(long id,long foodId){
    return neededIngredientJPARepository.findByIngredientIdAndFoodId(id, foodId);
}


@Override
public void delete(NeededIngredient neededIngredient){
    neededIngredientJPARepository.delete(neededIngredient);
}


}