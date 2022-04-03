package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.sharelibrary.response.CategoryResponse;
import pl.szymanski.sharelibrary.services.ports.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

 private  CategoryService categoryService;


@GetMapping
public ResponseEntity<List<CategoryResponse>> getCategories(){
    return new ResponseEntity<>(categoryService.getAll().stream().map(CategoryResponse::of).collect(Collectors.toList()), OK);
}


}