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
import io.swagger.model.Provider;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.ProviderRepository;
@RestController
@Api(value = "provider", description = " ", tags = { "provider" })
@Transactional
public class ProviderController {

@Autowired
 private ProviderRepository providerRepository;

@Autowired
 private OrderRepository orderRepository;

@Autowired
 private SessionRepository sessionRepository;


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = String.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "provider not found") })
@RequestMapping(value = "provider/logout", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
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


@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
@RequestMapping(value = "/provider", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<Provider> addProvider(Provider body){
    return new ResponseEntity<Provider>(providerRepository.save(body), HttpStatus.CREATED);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "provider not found") })
@RequestMapping(value = "/provider/{id}/orders", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<List<Order>> makeOrder(Long id){
    return new ResponseEntity<List<Order>>(orderRepository.findByProvider(providerRepository.findById(id).get()), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Provider not found") })
@RequestMapping(value = "/provider/{providerId}", produces = { "application/json", "application/xml" }, method = RequestMethod.DELETE)
public ResponseEntity<Void> deleteProvider(Long id,String apiKey){
    providerRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "provider not found"), @ApiResponse(code = 405, message = "Validation exception") })
@RequestMapping(value = "/provider", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, method = RequestMethod.PUT)
public ResponseEntity<Provider> updateProvider(Provider body){
    return new ResponseEntity<Provider>(providerRepository.save(body), HttpStatus.OK);
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HttpSession.class), @ApiResponse(code = 400, message = "Invalid email supplied"), @ApiResponse(code = 404, message = "provider not found") })
@RequestMapping(value = "/provider/login", produces = { "application/json", "application/xml" }, method = RequestMethod.POST)
public ResponseEntity<io.swagger.model.Session> login(String email,String password,HttpSession session,HttpServletRequest request){
    Provider provider = providerRepository.findByEmail(email);
    if (password.equals(provider.getPassword())) {
        session.putValue("login", true);
        io.swagger.model.Session session_model = new io.swagger.model.Session();
        session_model.setProvider(provider);
        session_model.setSessionId(session.getId());
        session_model.setIpAddress(request.getRemoteAddr());
        session_model.setStatus(true);
        sessionRepository.save(session_model);
        return new ResponseEntity<io.swagger.model.Session>(session_model, HttpStatus.OK);
    } else
        return null;
}


@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Provider.class), @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "provider not found") })
@RequestMapping(value = "/provider/{id}", produces = { "application/json", "application/xml" }, method = RequestMethod.GET)
public ResponseEntity<Optional<Provider>> getproviderById(Long id){
    return new ResponseEntity<Optional<Provider>>(providerRepository.findById(id), HttpStatus.OK);
}


}