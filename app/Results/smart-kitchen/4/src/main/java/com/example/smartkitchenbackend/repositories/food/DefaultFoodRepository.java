package com.example.smartkitchenbackend.repositories.food;
 import com.example.smartkitchenbackend.entities.Food;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class DefaultFoodRepository implements FoodRepository{

 private  FoodJPARepository foodJPARepository;


@Override
public Food findReference(long id){
    return foodJPARepository.getOne(id);
}


@Override
public Food findById(long id){
    return foodJPARepository.findById(id).orElseThrow(() -> new NotFoundException("No food exists with id:" + id));
}


@Override
public Food save(Food food){
    return foodJPARepository.save(food);
}


@Override
public void delete(Food food){
    foodJPARepository.delete(food);
}


}