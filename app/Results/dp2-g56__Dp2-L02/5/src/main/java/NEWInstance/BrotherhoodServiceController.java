package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrotherhoodServiceController {

 private BrotherhoodService brotherhoodservice;


@PutMapping
("/loggedAsBrotherhood")
public void loggedAsBrotherhood(){
brotherhoodservice.loggedAsBrotherhood();
}


@GetMapping
("/loggedBrotherhood")
public Brotherhood loggedBrotherhood(){
  return brotherhoodservice.loggedBrotherhood();
}


@GetMapping
("/save")
public Brotherhood save(@RequestParam(name = "h") Brotherhood h){
  return brotherhoodservice.save(h);
}


@GetMapping
("/securityAndBrotherhood")
public Brotherhood securityAndBrotherhood(){
  return brotherhoodservice.securityAndBrotherhood();
}


@GetMapping
("/findOne")
public Brotherhood findOne(@RequestParam(name = "id") int id){
  return brotherhoodservice.findOne(id);
}


@GetMapping
("/findAll")
public List<Brotherhood> findAll(){
  return brotherhoodservice.findAll();
}


@GetMapping
("/getParadesByBrotherhoodFinal")
public List<Parade> getParadesByBrotherhoodFinal(@RequestParam(name = "b") Brotherhood b){
  return brotherhoodservice.getParadesByBrotherhoodFinal(b);
}


@GetMapping
("/getMembersOfBrotherhood")
public List<Member> getMembersOfBrotherhood(@RequestParam(name = "bro") Brotherhood bro){
  return brotherhoodservice.getMembersOfBrotherhood(bro);
}


@GetMapping
("/getFloatsByBrotherhood")
public List<Float> getFloatsByBrotherhood(@RequestParam(name = "b") Brotherhood b){
  return brotherhoodservice.getFloatsByBrotherhood(b);
}


@GetMapping
("/getBrotherhoodIdByLegalRecord")
public int getBrotherhoodIdByLegalRecord(@RequestParam(name = "legalRecordId") int legalRecordId){
  return brotherhoodservice.getBrotherhoodIdByLegalRecord(legalRecordId);
}


@GetMapping
("/getBrotherhoodIdByInceptionRecord")
public int getBrotherhoodIdByInceptionRecord(@RequestParam(name = "inceptionRecordId") int inceptionRecordId){
  return brotherhoodservice.getBrotherhoodIdByInceptionRecord(inceptionRecordId);
}


@GetMapping
("/getBrotherhoodIdByPeriodRecord")
public int getBrotherhoodIdByPeriodRecord(@RequestParam(name = "periodRecordId") int periodRecordId){
  return brotherhoodservice.getBrotherhoodIdByPeriodRecord(periodRecordId);
}


@GetMapping
("/getBrotherhoodsByArea")
public List<Brotherhood> getBrotherhoodsByArea(@RequestParam(name = "areaId") Integer areaId){
  return brotherhoodservice.getBrotherhoodsByArea(areaId);
}


@GetMapping
("/reconstructBrotherhood")
public Brotherhood reconstructBrotherhood(@RequestParam(name = "brotherhood") Brotherhood brotherhood,@RequestParam(name = "binding") BindingResult binding){
  return brotherhoodservice.reconstructBrotherhood(brotherhood,binding);
}


@GetMapping
("/addPicture")
public Brotherhood addPicture(@RequestParam(name = "picture") String picture,@RequestParam(name = "brotherhood") Brotherhood brotherhood){
  return brotherhoodservice.addPicture(picture,brotherhood);
}


@PutMapping
("/deleteBrotherhood")
public void deleteBrotherhood(){
brotherhoodservice.deleteBrotherhood();
}


@GetMapping
("/reconstructArea")
public Brotherhood reconstructArea(@RequestParam(name = "brotherhood") Brotherhood brotherhood,@RequestParam(name = "binding") BindingResult binding){
  return brotherhoodservice.reconstructArea(brotherhood,binding);
}


@GetMapping
("/updateBrotherhood")
public Brotherhood updateBrotherhood(@RequestParam(name = "brotherhood") Brotherhood brotherhood){
  return brotherhoodservice.updateBrotherhood(brotherhood);
}


}