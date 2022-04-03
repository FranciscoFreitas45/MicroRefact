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
import ar.com.veterinaria.app.entities.exception.notFound.BreedNotFoundException;
import ar.com.veterinaria.app.entities.service.UserService;
import ar.com.veterinaria.app.entities.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(tags = "User", description = "Endpoint for User management")
public class UserController {

 private  Logger logger;

@Autowired
 private UserService userService;

@Autowired
public UserController(UserService userService) {
    this.userService = userService;
}
@GetMapping(value = "/get-list-User")
@ApiOperation(value = "Returns List of User", notes = "Returns a complete list of User details.", response = User.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of User List", response = User.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<List<Map<String,Object>>> getAll(){
    try {
        List<Map<String, Object>> result = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@GetMapping(value = "/{id}")
@ApiOperation(value = "Search a Address by ID", response = User.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<User> getById(int id){
    try {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PostMapping(value = "/new-User")
@ApiOperation(value = "Create a new User", notes = "Create a new User.", response = User.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of User List", response = User.class), @ApiResponse(code = 500, message = "Internal server error") })
public ResponseEntity<User> create(User user){
    try {
        userService.isValidInputData(user);
        userService.exist(user);
        userService.add(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@PutMapping(value = "/{id}")
@ApiOperation(value = "Update a User by ID", response = User.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<User> update(int id,User user){
    try {
        userService.isValidInputData(user);
        userService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


@DeleteMapping(value = "/{id}")
@ApiOperation(value = "Delete a User by ID", response = User.class)
@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "Operation is forbidden"), @ApiResponse(code = 500, message = "Server error") })
public ResponseEntity<Void> delete(int id){
    try {
        userService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


}