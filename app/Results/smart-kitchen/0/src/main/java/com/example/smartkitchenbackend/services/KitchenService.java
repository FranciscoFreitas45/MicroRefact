package com.example.smartkitchenbackend.services;
 import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDetailDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.expection.BadRequestException;
import com.example.smartkitchenbackend.repositories.kitchen.KitchenRepository;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.services.Converters.KitchenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class KitchenService {

 private  KitchenRepository kitchenRepository;

 private  UserRepository userRepository;

 private  WishListService wishListService;


public KitchenDetailDTO getKitchenById(long kitchenId){
    return KitchenConverter.toKitchenDetailDTO(kitchenRepository.findById(kitchenId));
}


public Kitchen findById(long kitchenId){
    return kitchenRepository.findById(kitchenId);
}


public void create(NewKitchenDTO kitchenDTO){
    Kitchen kitchen = new Kitchen();
    if (kitchenDTO.getName() == null)
        throw new BadRequestException("Please fill out the name field");
    int uniqueNumberForName = kitchenRepository.numberOfKitchensWithSimilarNames(kitchenDTO.getName()) + 1001;
    kitchen.setName(kitchenDTO.getName() + "#" + uniqueNumberForName);
    kitchen.addUser(userRepository.getReference(kitchenDTO.getUserId()));
    kitchenRepository.save(kitchen);
    kitchen.setWishList(wishListService.createWishListInKitchen(kitchen));
    kitchenRepository.save(kitchen);
}


public List<KitchenDTO> getKitchens(){
    return kitchenRepository.findAll().stream().map(KitchenConverter::toKitchenDTO).collect(Collectors.toList());
}


public KitchenDTO addUserToKitchen(long kitchenId,long userId){
    Kitchen kitchen = kitchenRepository.findById(kitchenId);
    kitchen.addUser(userRepository.getReference(userId));
    return KitchenConverter.toKitchenDTO(kitchenRepository.save(kitchen));
}


}