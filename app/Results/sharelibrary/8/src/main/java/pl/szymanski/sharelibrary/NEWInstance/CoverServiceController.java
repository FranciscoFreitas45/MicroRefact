package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CoverServiceController {

 private CoverService coverservice;


@GetMapping
("/getCoverByBookId")
public Cover getCoverByBookId(@RequestParam(name = "id") Long id){
  return coverservice.getCoverByBookId(id);
}


}