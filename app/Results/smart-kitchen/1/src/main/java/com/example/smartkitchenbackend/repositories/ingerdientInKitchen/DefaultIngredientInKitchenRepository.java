package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;
 import com.example.smartkitchenbackend.entities.IngredientInKitchen;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class DefaultIngredientInKitchenRepository implements IngredientInKitchenRepository{

 private  IngredientInKitchenJPARepository ingredientInKitchenJPARepository;


@Override
public Boolean existsByIngredientIdAndKitchenId(long ingredientId,long kitchenId){
    return ingredientInKitchenJPARepository.existsByIngredientIdAndKitchenId(ingredientId, kitchenId);
}


@Override
public IngredientInKitchen findByIngredientIdAndKitchenId(long ingredientId,long kitchenId){
    return ingredientInKitchenJPARepository.findByIngredientIdAndKitchenId(ingredientId, kitchenId);
}


@Override
public IngredientInKitchen findById(long id){
    return ingredientInKitchenJPARepository.findById(id).orElseThrow(() -> new NotFoundException("No ingredient in kitchen exists with id:" + id));
}


@Override
public IngredientInKitchen save(IngredientInKitchen ingredientInKitchen){
    return ingredientInKitchenJPARepository.save(ingredientInKitchen);
}


@Override
public void deleteById(long ingredientInKitchenId){
    ingredientInKitchenJPARepository.deleteById(ingredientInKitchenId);
}


}