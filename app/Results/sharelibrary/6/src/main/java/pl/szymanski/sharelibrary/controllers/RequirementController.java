package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import pl.szymanski.sharelibrary.requests.CreateRequirementRequest;
import pl.szymanski.sharelibrary.response.RequirementResponse;
import pl.szymanski.sharelibrary.services.ports.RequirementService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/api/v1/requirements")
@RequiredArgsConstructor
public class RequirementController {

 private  RequirementService requirementService;


@PostMapping
public ResponseEntity<RequirementResponse> createRequirement(CreateRequirementRequest request){
    return new ResponseEntity<>(RequirementResponse.of(requirementService.createRequirement(request)), CREATED);
}


@GetMapping("/{userId}")
public ResponseEntity<List<RequirementResponse>> getUserRequirements(Long userId){
    return new ResponseEntity<>(requirementService.getUserRequirements(userId).stream().map(RequirementResponse::of).collect(Collectors.toList()), OK);
}


}