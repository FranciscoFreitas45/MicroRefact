package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrotherhoodController {

 private Brotherhood brotherhood;


@PutMapping
("/setParades")
public void setParades(@RequestParam(name = "parades") List<Parade> parades){
brotherhood.setParades(parades);
}


@PutMapping
("/setFloats")
public void setFloats(@RequestParam(name = "floats") List<Float> floats){
brotherhood.setFloats(floats);
}


@PutMapping
("/setHistory")
public void setHistory(@RequestParam(name = "history") History history){
brotherhood.setHistory(history);
}


}