package com.example.smartkitchenbackend.controllers;
 import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDetailDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
public class KitchenController {

 private  KitchenService kitchenService;


@GetMapping("/kitchenDetails/{kitchenId}")
public KitchenDetailDTO getKitchenById(long kitchenId){
    return kitchenService.getKitchenById(kitchenId);
}


@PostMapping("/create")
public void createKitchen(NewKitchenDTO kitchenDTO){
    kitchenService.create(kitchenDTO);
}


@GetMapping("/list")
public List<KitchenDTO> getKitchens(){
    return kitchenService.getKitchens();
}


}