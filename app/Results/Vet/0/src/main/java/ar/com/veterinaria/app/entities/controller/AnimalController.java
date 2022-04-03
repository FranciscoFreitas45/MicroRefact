package ar.com.veterinaria.app.entities.controller;
 import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@RequestMapping(value = "/animal", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "Animal", description = "Endpoint for Animal management")
public class AnimalController {

 private  Logger logger;

@Autowired
 private AnimalService animalService;

@Autowired
public AnimalController(AnimalService animalService) {
    this.animalService = animalService;
}
@GetMapping(value = "/get-list-animal")
@ApiOperation(value = "Returns List of Animal", notes = "Returns a complete list of Animal details.", response = Animal.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Animal List", response = Animal.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<List<Map<String,Object>>> getAll(){
    try {
        List<Map<String, Object>> result = animalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@GetMapping(value = "search-by-id/{id}")
@ApiOperation(value = "Search a Animal by ID", response = Animal.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Animal> getById(int id){
    try {
        Animal animal = animalService.findById(id);
        if (animal != null) {
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@GetMapping(value = "/search-by-name/{animal}")
@ApiOperation(value = "Search a Animal by Name", response = Animal.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Animal> getByName(String animal){
    try {
        Animal aAnimal = animalService.findAnimalByName(animal);
        return ResponseEntity.status(HttpStatus.OK).body(aAnimal);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PostMapping(value = "/new-animal")
@ApiOperation(value = "Create a new Animal", notes = "Create a new Breed.", response = Animal.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Animal List", response = Animal.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<Animal> create(Animal animal){
    // longitud
    // tipo de dato - letras May y Min, WhiteSpace
    // null
    // empty -> white space
    try {
        animalService.isValidInputData(animal);
        animalService.exist(animal);
        animalService.add(animal);
        return new ResponseEntity<Animal>(animal, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PutMapping(value = "/{id}")
@ApiOperation(value = "Update a Animal by ID", response = Animal.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Animal> update(int id,Animal animal){
    try {
        animalService.isValidInputData(animal);
        animalService.update(id, animal);
        return ResponseEntity.status(HttpStatus.OK).body(animal);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@DeleteMapping(value = "/{id}")
@ApiOperation(value = "Delete a Animal by ID", response = Animal.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Void> delete(int id){
    try {
        animalService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


}