package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FloatServiceController {

 private FloatService floatservice;


@PutMapping
("/deleteAllFloatsBrotherhood")
public void deleteAllFloatsBrotherhood(){
floatservice.deleteAllFloatsBrotherhood();
}


@GetMapping
("/findOne")
public domain.Float findOne(@RequestParam(name = "id") int id){
  return floatservice.findOne(id);
}


@GetMapping
("/floatsInParadeInFinalMode")
public List<domain.Float> floatsInParadeInFinalMode(){
  return floatservice.floatsInParadeInFinalMode();
}


@GetMapping
("/create")
public domain.Float create(){
  return floatservice.create();
}


@GetMapping
("/reconstruct")
public domain.Float reconstruct(@RequestParam(name = "floatt") domain.Float floatt,@RequestParam(name = "binding") BindingResult binding){
  return floatservice.reconstruct(floatt,binding);
}


@GetMapping
("/save")
public domain.Float save(@RequestParam(name = "floatt") domain.Float floatt){
  return floatservice.save(floatt);
}


@GetMapping
("/isUrl")
public Boolean isUrl(@RequestParam(name = "url") String url){
  return floatservice.isUrl(url);
}


@GetMapping
("/addPicture")
public domain.Float addPicture(@RequestParam(name = "picture") String picture,@RequestParam(name = "floatt") domain.Float floatt){
  return floatservice.addPicture(picture,floatt);
}


@GetMapping
("/showBrotherhoodFloats")
public List<domain.Float> showBrotherhoodFloats(){
  return floatservice.showBrotherhoodFloats();
}


@PutMapping
("/remove")
public void remove(@RequestParam(name = "floatt") domain.Float floatt){
floatservice.remove(floatt);
}


}