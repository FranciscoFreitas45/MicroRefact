import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PageUtilController {

 private PageUtil pageutil;


@GetMapping
("/listToPage")
public Page<T> listToPage(@RequestParam(name = "list") List<T> list,@RequestParam(name = "pageable") Pageable pageable){
  return pageutil.listToPage(list,pageable);
}


@GetMapping
("/listToPage")
public Page<T> listToPage(@RequestParam(name = "list") List<T> list,@RequestParam(name = "pageable") Pageable pageable){
  return pageutil.listToPage(list,pageable);
}


@GetMapping
("/listToPage")
public Page<T> listToPage(@RequestParam(name = "list") List<T> list,@RequestParam(name = "pageable") Pageable pageable){
  return pageutil.listToPage(list,pageable);
}


@GetMapping
("/listToPage")
public Page<T> listToPage(@RequestParam(name = "list") List<T> list,@RequestParam(name = "pageable") Pageable pageable){
  return pageutil.listToPage(list,pageable);
}


}