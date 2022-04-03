package io.swagger.controller;
 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import io.swagger.model.Order;
import io.swagger.model.Pet;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PetRepository;
import io.swagger.Interface.OrderRepository;
@RestController
@Api(value = "pet", description = " ", tags = { "pet" })
@Transactional
public class PetController {

 private  String UPLOADED_FOLDER;

@Autowired
 private PetRepository petRepository;

@Autowired
 private OrderRepository orderRepository;


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = PetController.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
@RequestMapping(value = "/pets", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<List<Pet>> getAllPets(){
    return new ResponseEntity<List<Pet>>(petRepository.findAll(), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found"), @ApiResponse(code = 405, message = "Validation exception") })
@RequestMapping(value = "/pet", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.PUT)
public ResponseEntity<Pet> updatePet(Pet body){
    return new ResponseEntity<Pet>(petRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Pet.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
@RequestMapping(value = "/pet/{petId}", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<Optional<Pet>> getPetById(Long petId){
    return new ResponseEntity<Optional<Pet>>(petRepository.findById(petId), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
@RequestMapping(value = "/pet/makeOrder", produces = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Order> makeOrder(Order body){
    return new ResponseEntity<Order>(orderRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
@RequestMapping(value = "/pet/{petId}", produces = { "application/json", "application/xml" }, method = RequestMethod.DELETE)
public ResponseEntity<Void> deletePet(Long petId,String apiKey){
    petRepository.deleteById(petId);
    return new ResponseEntity<>(HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
@RequestMapping(value = "/pet", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Pet> addPet(Pet body){
    return new ResponseEntity<Pet>(petRepository.save(body), HttpStatus.CREATED);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = PetController.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
@RequestMapping(value = "/pet/{id}/uploadImage", produces = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Pet> singleFileUpload(MultipartFile file,RedirectAttributes redirectAttributes,Long id){
    if (file.isEmpty()) {
        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    }
    try {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
    } catch (IOException e) {
        e.printStackTrace();
    }
    Optional<Pet> pet_optional = petRepository.findById(id);
    Pet pet = pet_optional.get();
    pet.getImages().add(UPLOADED_FOLDER + file.getOriginalFilename());
    return new ResponseEntity<Pet>(petRepository.save(pet), HttpStatus.OK);
}


}