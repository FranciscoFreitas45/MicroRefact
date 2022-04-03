package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import pl.szymanski.sharelibrary.requests.AddExchangeRequest;
import pl.szymanski.sharelibrary.requests.ExecuteExchangeRequest;
import pl.szymanski.sharelibrary.response.ExchangeResponse;
import pl.szymanski.sharelibrary.response.ExchangeWithDetailsResponse;
import pl.szymanski.sharelibrary.response.RequirementResponse;
import pl.szymanski.sharelibrary.services.ports.ExchangeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchanges")
public class ExchangeController {

 private  ExchangeService exchangeService;


@GetMapping("/withUser/{id}")
public ResponseEntity<List<ExchangeResponse>> getExchangesAtUser(Long userId){
    return new ResponseEntity<>(exchangeService.getExchangesByWithUserId(userId).stream().map(ExchangeResponse::of).collect(Collectors.toList()), OK);
}


@PostMapping
public ResponseEntity<ExchangeResponse> saveExchange(AddExchangeRequest addExchangeRequest){
    return new ResponseEntity<>(ExchangeResponse.of(exchangeService.saveExchange(addExchangeRequest)), CREATED);
}


@GetMapping("/{id}/requirements")
public ResponseEntity<List<RequirementResponse>> getRequirements(Long exchangeId){
    return new ResponseEntity<>(exchangeService.getRequirements(exchangeId).stream().map(RequirementResponse::of).collect(Collectors.toList()), OK);
}


@PostMapping("/execution")
public ResponseEntity<ExchangeResponse> executeExchange(ExecuteExchangeRequest executeExchangeRequest){
    return new ResponseEntity<>(ExchangeResponse.of(exchangeService.executeExchange(executeExchangeRequest)), OK);
}


@GetMapping("/filter")
public ResponseEntity<List<ExchangeResponse>> getWithFilters(Double latitude,Double longitude,Double radius,List<String> categories,String query,Integer languageId,List<Integer> condition){
    return new ResponseEntity<>(exchangeService.filter(latitude, longitude, radius, categories, query, languageId, condition), OK);
}


@GetMapping
public ResponseEntity<List<ExchangeResponse>> getUserExchanges(Long userId){
    return new ResponseEntity<>(exchangeService.getExchangesByUserId(userId).stream().map(ExchangeResponse::of).collect(Collectors.toList()), OK);
}


@GetMapping("/{id}")
public ResponseEntity<ExchangeResponse> getExchange(Long exchangeId){
    return new ResponseEntity<>(ExchangeResponse.of(exchangeService.getExchangeById(exchangeId)), OK);
}


@GetMapping("users/{userId}")
public ResponseEntity<List<ExchangeWithDetailsResponse>> getCurrentExchangesLinkedWithUser(Long userId){
    return new ResponseEntity<>(exchangeService.getExchangesLinkedByUser(userId).stream().map(ExchangeWithDetailsResponse::of).collect(Collectors.toList()), OK);
}


@PostMapping("/{id}/end")
public ResponseEntity<Void> finishExchange(Long exchangeId){
    exchangeService.finishExchange(exchangeId);
    return new ResponseEntity<>(NO_CONTENT);
}


}