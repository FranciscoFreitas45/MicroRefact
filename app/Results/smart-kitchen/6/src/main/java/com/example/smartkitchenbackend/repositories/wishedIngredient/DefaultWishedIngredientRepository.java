package com.example.smartkitchenbackend.repositories.wishedIngredient;
 import com.example.smartkitchenbackend.entities.WishedIngredient;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class DefaultWishedIngredientRepository implements WishedIngredientRepository{

 private  WishedIngredientJPARepository wishedIngredientJPARepository;


@Override
public WishedIngredient findByIngredientIdAndWishListId(long id,long wishListId){
    return wishedIngredientJPARepository.findByIngredientIdAndWishListId(id, wishListId);
}


@Override
public WishedIngredient findById(long id){
    return wishedIngredientJPARepository.findById(id).orElseThrow(() -> new NotFoundException("No wished ingredient exists with id:" + id));
}


@Override
public WishedIngredient save(WishedIngredient wishedIngredient){
    return wishedIngredientJPARepository.save(wishedIngredient);
}


@Override
public Boolean existsByIngredientIdAndWishListId(long id,long wishListId){
    return wishedIngredientJPARepository.existsByIngredientIdAndWishListId(id, wishListId);
}


@Override
public void deleteById(long wishedIngredientId){
    wishedIngredientJPARepository.deleteById(wishedIngredientId);
}


@Override
public void delete(WishedIngredient wishedIngredient){
    wishedIngredientJPARepository.delete(wishedIngredient);
}


}