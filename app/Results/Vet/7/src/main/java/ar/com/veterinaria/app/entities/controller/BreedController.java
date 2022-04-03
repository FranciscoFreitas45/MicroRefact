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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.exception.notFound.BreedNotFoundException;
import ar.com.veterinaria.app.entities.service.BreedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@RequestMapping(value = "/breed", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "Breed", description = "Endpoint for Breed management")
public class BreedController {

 private  Logger logger;

@Autowired
 private BreedService breedService;

@Autowired
public BreedController(BreedService breedService) {
    this.breedService = breedService;
}
@GetMapping
@RequestMapping(value = "/get-list-breed", method = RequestMethod.GET)
@ApiOperation(value = "Returns List of Breed", notes = "Returns a complete list of Breed details.", response = Breed.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Breed List", response = Breed.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<List<Map<String,Object>>> getAll(){
    try {
        List<Map<String, Object>> result = breedService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ApiOperation(value = "Search a Breed by ID", response = Breed.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Breed> getById(int id){
    try {
        Breed breed = breedService.findById(id);
        if (breed != null) {
            return ResponseEntity.status(HttpStatus.OK).body(breed);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@GetMapping
@RequestMapping(value = "/new-breed", method = RequestMethod.POST)
@ApiOperation(value = "Create a new Breed", notes = "Create a new Breed.", response = Breed.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Breed List", response = Breed.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<Breed> create(Breed breed){
    try {
        breedService.isValidInputData(breed);
        breedService.exist(breed);
        breedService.add(breed);
        return new ResponseEntity<Breed>(breed, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
@ApiOperation(value = "Update a Breed by ID", response = Breed.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Breed> update(int id,Breed breed){
    try {
        breedService.isValidInputData(breed);
        breedService.update(id, breed);
        return ResponseEntity.status(HttpStatus.OK).body(breed);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ApiOperation(value = "Delete a Breed by ID", response = Breed.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Void> delete(int id){
    try {
        breedService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


}