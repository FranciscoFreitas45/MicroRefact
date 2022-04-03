package com.example.smartkitchenbackend.services;
 import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities;
import com.example.smartkitchenbackend.expection.BadRequestException;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.repositories.ingerdientInKitchen.IngredientInKitchenRepository;
import com.example.smartkitchenbackend.repositories.ingredient.IngredientRepository;
import com.example.smartkitchenbackend.repositories.neededIngredient.NeededIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishedIngredient.WishedIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import com.example.smartkitchenbackend.services.Converters.IngredientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientService {

 private  WishListRepository wishListRepository;

 private  KitchenService kitchenService;

 private  FoodRepository foodRepository;

 private  IngredientRepository ingredientRepository;

 private  IngredientInKitchenRepository ingredientInKitchenRepository;

 private  NeededIngredientRepository neededIngredientRepository;

 private  WishedIngredientRepository wishedIngredientRepository;


public IngredientDTO createInWishList(NewIngredientDTO newIngredientDTO,long wishListId){
    checkInput(newIngredientDTO);
    if (ingredientRepository.existsByName(newIngredientDTO.getName())) {
        Ingredient ingredient = ingredientRepository.findByName(newIngredientDTO.getName());
        return createWishedIngredient(newIngredientDTO.getWeightOrCount(), wishListId, ingredient);
    } else {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(newIngredientDTO.getName());
        ingredient.setType(newIngredientDTO.getType());
        ingredientRepository.save(ingredient);
        return createWishedIngredient(newIngredientDTO.getWeightOrCount(), wishListId, ingredient);
    }
}


@Transactional
public void emptyWishList(long wishListId){
    wishListRepository.findReference(wishListId).getIngredients().forEach(wishedIngredient -> removeFromWishList(wishedIngredient.getId()));
}


public void checkInput(NewIngredientDTO newIngredientDTO){
    if (newIngredientDTO.getName() == null || newIngredientDTO.getWeightOrCount() == 0.0)
        throw new BadRequestException("Invalid input, name and weight must be filled");
}


public NeededIngredient createIngredientInFood(double weightOrCount,long foodId,Ingredient ingredient){
    if (neededIngredientRepository.existsByIngredientIdAndFoodId(ingredient.getId(), foodId)) {
        NeededIngredient neededIngredient = neededIngredientRepository.findByIngredientIdAndFoodId(ingredient.getId(), foodId);
        double weight = neededIngredient.getWeightOrCount() + weightOrCount;
        neededIngredient.setWeightOrCount(weight);
        neededIngredientRepository.save(neededIngredient);
        return IngredientConverter.toNeededIngredientEntity(weight, foodRepository.findReference(foodId), ingredient);
    } else {
        NeededIngredient neededIngredient = new NeededIngredient();
        neededIngredient.setIngredient(ingredient);
        neededIngredient.setWeightOrCount(weightOrCount);
        neededIngredient.setFood(foodRepository.findReference(foodId));
        neededIngredientRepository.save(neededIngredient);
        return IngredientConverter.toNeededIngredientEntity(weightOrCount, foodRepository.findReference(foodId), ingredient);
    }
}


public IngredientDTO createInKitchen(NewIngredientDTO newIngredientDTO,long kitchenId){
    checkInput(newIngredientDTO);
    if (ingredientRepository.existsByName(newIngredientDTO.getName())) {
        Ingredient ingredient = ingredientRepository.findByName(newIngredientDTO.getName());
        return createIngredientInKitchen(newIngredientDTO.getWeightOrCount(), kitchenId, ingredient);
    } else {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(newIngredientDTO.getName());
        ingredient.setType(newIngredientDTO.getType());
        ingredientRepository.save(ingredient);
        return createIngredientInKitchen(newIngredientDTO.getWeightOrCount(), kitchenId, ingredient);
    }
}


public IngredientDTO createIngredientInKitchen(double weightOrCount,long kitchenId,Ingredient ingredient){
    if (ingredientInKitchenRepository.existsByIngredientIdAndKitchenId(ingredient.getId(), kitchenId)) {
        IngredientInKitchen ingredientInKitchen = ingredientInKitchenRepository.findByIngredientIdAndKitchenId(ingredient.getId(), kitchenId);
        double weight = weightOrCount + ingredientInKitchen.getWeightOrCount();
        ingredientInKitchen.setWeightOrCount(weight);
        ingredientInKitchenRepository.save(ingredientInKitchen);
        return IngredientConverter.toIngredientDTO(weight, ingredient.getName(), ingredient.getType(), ingredientInKitchen.getId());
    } else {
        IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
        ingredientInKitchen.setIngredient(ingredient);
        ingredientInKitchen.setWeightOrCount(weightOrCount);
        ingredientInKitchen.setKitchen(kitchenService.findById(kitchenId));
        ingredientInKitchenRepository.save(ingredientInKitchen);
        return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), ingredient.getType(), ingredientInKitchen.getId());
    }
}


public void createMultipleInWishList(List<NewIngredientDTO> newIngredientDTOs,long foodId){
    long wishListId = foodRepository.findReference(foodId).getKitchen().getWishList().getId();
    newIngredientDTOs.forEach(newIngredientDTO -> createInWishList(newIngredientDTO, wishListId));
}


public void removeFromKitchen(long ingredientInKitchenId){
    ingredientInKitchenRepository.deleteById(ingredientInKitchenId);
}


public IngredientDTO createWishedIngredient(double weightOrCount,long wishListId,Ingredient ingredient){
    if (wishedIngredientRepository.existsByIngredientIdAndWishListId(ingredient.getId(), wishListId)) {
        WishedIngredient wishedIngredient = wishedIngredientRepository.findByIngredientIdAndWishListId(ingredient.getId(), wishListId);
        wishedIngredient.setWeightOrCount(wishedIngredient.getWeightOrCount() + weightOrCount);
        wishedIngredientRepository.save(wishedIngredient);
        return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), ingredient.getType(), wishedIngredient.getId());
    } else {
        WishedIngredient wishedIngredient = new WishedIngredient();
        wishedIngredient.setIngredient(ingredient);
        wishedIngredient.setWeightOrCount(weightOrCount);
        wishedIngredient.setWishList(wishListRepository.findReference(wishListId));
        wishedIngredientRepository.save(wishedIngredient);
        return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), ingredient.getType(), wishedIngredient.getId());
    }
}


public NeededIngredient createInFood(NewIngredientDTO newIngredientDTO,long foodId){
    checkInput(newIngredientDTO);
    if (ingredientRepository.existsByName(newIngredientDTO.getName())) {
        Ingredient ingredient = ingredientRepository.findByName(newIngredientDTO.getName());
        return createIngredientInFood(newIngredientDTO.getWeightOrCount(), foodId, ingredient);
    } else {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(newIngredientDTO.getName());
        ingredient.setType(newIngredientDTO.getType());
        ingredientRepository.save(ingredient);
        return createIngredientInFood(newIngredientDTO.getWeightOrCount(), foodId, ingredient);
    }
}


public void createMultipleInKitchen(List<NewIngredientDTO> newIngredientDTOs,long wishListId){
    long kitchenId = wishListRepository.findReference(wishListId).getKitchen().getId();
    newIngredientDTOs.forEach(newIngredientDTO -> createInKitchen(newIngredientDTO, kitchenId));
}


public void removeFromWishList(long wishedIngredientId){
    wishedIngredientRepository.deleteById(wishedIngredientId);
}


}