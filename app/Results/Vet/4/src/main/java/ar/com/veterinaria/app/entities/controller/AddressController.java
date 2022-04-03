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
import ar.com.veterinaria.app.entities.Address;
import ar.com.veterinaria.app.entities.exception.notFound.BreedNotFoundException;
import ar.com.veterinaria.app.entities.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "Address", description = "Endpoint for Address management")
public class AddressController {

 private  Logger logger;

@Autowired
 private AddressService addressService;

@Autowired
public AddressController(AddressService addressService) {
    this.addressService = addressService;
}
@GetMapping(value = "/get-list-Address")
@ApiOperation(value = "Returns List of Address", notes = "Returns a complete list of Address details.", response = Address.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Breed List", response = Address.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<List<Map<String,Object>>> getAll(){
    try {
        List<Map<String, Object>> result = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@GetMapping(value = "/{id}")
@ApiOperation(value = "Search a Address by ID", response = Address.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Address> getById(int id){
    try {
        Address address = addressService.findById(id);
        if (address != null) {
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PostMapping(value = "/new-Address")
@ApiOperation(value = "Create a new Address", notes = "Create a new Address.", response = Address.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Address List", response = Address.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<Address> create(Address address){
    try {
        addressService.isValidInputData(address);
        addressService.exist(address);
        addressService.add(address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PutMapping(value = "/{id}")
@ApiOperation(value = "Update a Address by ID", response = Address.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Address> update(int id,Address address){
    try {
        addressService.isValidInputData(address);
        addressService.update(id, address);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@DeleteMapping(value = "/{id}")
@ApiOperation(value = "Delete a Address by ID", response = Address.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Void> delete(int id){
    try {
        addressService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


}