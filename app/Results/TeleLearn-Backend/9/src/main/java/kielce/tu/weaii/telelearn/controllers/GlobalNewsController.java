package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.GlobalNewsRequest;
import kielce.tu.weaii.telelearn.services.ports.GlobalNewsService;
import kielce.tu.weaii.telelearn.views.GlobalNewsView;
import kielce.tu.weaii.telelearn.views.PageView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class GlobalNewsController {

 private  GlobalNewsService globalNewsService;


@PostMapping
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Object> add(GlobalNewsRequest request){
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/get/{id}").buildAndExpand(globalNewsService.add(request).getId()).toUri();
    return ResponseEntity.created(location).build();
}


@GetMapping(path = "/get")
public ResponseEntity<PageView<GlobalNewsView>> getBriefPage(Integer pageNo,Integer pageSize){
    return new ResponseEntity<>(PageView.of(globalNewsService.getPage(pageSize, pageNo), GlobalNewsView::from), HttpStatus.OK);
}


@GetMapping(path = "/get/{id}")
public ResponseEntity<GlobalNewsView> getById(Long id){
    return new ResponseEntity<>(GlobalNewsView.from(globalNewsService.getById(id)), HttpStatus.OK);
}


@PreAuthorize("hasRole('ADMIN')")
@PutMapping(path = "/{id}")
public ResponseEntity<Object> edit(Long id,GlobalNewsRequest request){
    globalNewsService.edit(id, request);
    return ResponseEntity.noContent().build();
}


@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping(path = "/{id}")
public ResponseEntity<Object> delete(Long id){
    globalNewsService.delete(id);
    return ResponseEntity.noContent().build();
}


}