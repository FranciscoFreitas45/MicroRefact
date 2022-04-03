package io.swagger.controller;
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
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Address;
import io.swagger.repository.AddressRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;
@RestController
@Api(value = "address", description = " ", tags = { "address" })
@Transactional
public class AddressController {

@Autowired
 private AddressRepository addressRepository;

@Autowired
 private OrderRepository orderRepository;

@Autowired
 private SessionRepository sessionRepository;


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "address not found"), @ApiResponse(code = 405, message = "Validation exception") })
@RequestMapping(value = "/address", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.PUT)
public ResponseEntity<Address> updateAddress(Address body){
    return new ResponseEntity<Address>(addressRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
@RequestMapping(value = "/address", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Address> addAddress(Address body){
    return new ResponseEntity<Address>(addressRepository.save(body), HttpStatus.CREATED);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Address not found") })
@RequestMapping(value = "/address/{addressId}", produces = { "application/json", "application/xml" }, method = RequestMethod.DELETE)
public ResponseEntity<Void> deleteAddress(Long id,String apiKey){
    addressRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Address.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "address not found") })
@RequestMapping(value = "/address/{id}", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<Optional<Address>> getaddressById(Long id){
    return new ResponseEntity<Optional<Address>>(addressRepository.findById(id), HttpStatus.OK);
}


}