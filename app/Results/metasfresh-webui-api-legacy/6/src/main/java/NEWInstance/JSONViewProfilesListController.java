package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONViewProfilesListController {

 private JSONViewProfilesList jsonviewprofileslist;

 private JSONViewProfilesList jsonviewprofileslist;


@GetMapping
("/of")
public JSONViewProfilesList of(@RequestParam(name = "viewProfiles") List<ViewProfile> viewProfiles,@RequestParam(name = "adLanguage") String adLanguage){
  return jsonviewprofileslist.of(viewProfiles,adLanguage);
}


}