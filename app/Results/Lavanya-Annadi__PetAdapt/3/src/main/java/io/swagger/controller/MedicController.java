package io.swagger.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;
import io.swagger.model.Pet;
import io.swagger.model.HealthCare;
import io.swagger.model.Medic;
import io.swagger.repository.HealthCareRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.MedicRepository;
import io.swagger.repository.PetRepository;
import io.swagger.Interface.SessionRepository;
import io.swagger.DTO.Session;
@RestController
@Api(value = "medic", description = " ", tags = { "medic" })
@Transactional
public class MedicController {

@Autowired
 private MedicRepository medicRepository;

@Autowired
 private PetRepository petRepository;

@Autowired
 private HealthCareRepository healthCareRepository;

@Autowired
 private SessionRepository sessionRepository;


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "medic not found"), @ApiResponse(code = 405, message = "Validation exception") })
@RequestMapping(value = "/medic", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.PUT)
public ResponseEntity<Medic> updateMedic(Medic body){
    return new ResponseEntity<Medic>(medicRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "medic not found") })
@RequestMapping(value = "/medic/{id}/healthcare/{petId}", produces = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<HealthCare> heathCare(Long id,Long petId,HealthCare body){
    Medic medic = medicRepository.findById(id).get();
    Pet pet = petRepository.findById(petId).get();
    body.setPet(pet);
    body.setMedic(medic);
    return new ResponseEntity<HealthCare>(healthCareRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = String.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "medic not found") })
@RequestMapping(value = "medic/logout", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<Map> logout(HttpSession session){
    Map<String, String> map = new HashMap<>();
    map.put("message", "Successfull");
    map.put("status_code", "200");
    io.swagger.model.Session session_logout = sessionRepository.findBySessionId(session.getId());
    if (session_logout != null && session_logout.getStatus() == true) {
        session_logout.setStatus(false);
        sessionRepository.save(session_logout);
        session.invalidate();
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    return new ResponseEntity<Map>(map, HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Medic.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "medic not found") })
@RequestMapping(value = "/medic/{id}", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<Optional<Medic>> getmedicById(Long id){
    return new ResponseEntity<Optional<Medic>>(medicRepository.findById(id), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Medic not found") })
@RequestMapping(value = "/medic/{medicId}", produces = { "application/json", "application/xml" }, method = RequestMethod.DELETE)
public ResponseEntity<Void> deleteMedic(Long id){
    medicRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
@RequestMapping(value = "/medic", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Medic> addMedic(Medic body){
    return new ResponseEntity<Medic>(medicRepository.save(body), HttpStatus.CREATED);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "medic not found") })
@RequestMapping(value = "/medic/{id}/healthcare", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<List<HealthCare>> makeOrder(Long id){
    return new ResponseEntity<List<HealthCare>>(healthCareRepository.findByMedic(medicRepository.findById(id).get()), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HttpSession.class), @ApiResponse(code = 400, message = "Invalid email supplied"), @ApiResponse(code = 404, message = "medic not found") })
@RequestMapping(value = "/medic/login", produces = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<io.swagger.model.Session> login(String email,String password,HttpSession session,HttpServletRequest request){
    Medic medic = medicRepository.findByEmail(email);
    if (password.equals(medic.getPassword())) {
        session.putValue("login", true);
        io.swagger.model.Session session_model = new io.swagger.model.Session();
        session_model.setMedic(medic);
        session_model.setSessionId(session.getId());
        session_model.setIpAddress(request.getRemoteAddr());
        session_model.setStatus(true);
        sessionRepository.save(session_model);
        return new ResponseEntity<io.swagger.model.Session>(session_model, HttpStatus.OK);
    } else
        return null;
}


}