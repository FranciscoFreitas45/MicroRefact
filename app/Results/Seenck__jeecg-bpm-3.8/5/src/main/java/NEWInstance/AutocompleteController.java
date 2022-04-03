package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AutocompleteController {

 private Autocomplete autocomplete;

 private Autocomplete autocomplete;


@PutMapping
("/setTrem")
public void setTrem(@RequestParam(name = "trem") String trem){
autocomplete.setTrem(trem);
}


}