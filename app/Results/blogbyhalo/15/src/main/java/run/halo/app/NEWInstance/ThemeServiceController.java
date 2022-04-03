package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ThemeServiceController {

 private ThemeService themeservice;


@GetMapping
("/templateExists")
public boolean templateExists(@RequestParam(name = "template") String template){
  return themeservice.templateExists(template);
}


@GetMapping
("/renderWithSuffix")
public String renderWithSuffix(@RequestParam(name = "pageName") String pageName){
  return themeservice.renderWithSuffix(pageName);
}


@GetMapping
("/render")
public String render(@RequestParam(name = "pageName") String pageName){
  return themeservice.render(pageName);
}


@GetMapping
("/getActivatedThemeId")
public String getActivatedThemeId(){
  return themeservice.getActivatedThemeId();
}


@GetMapping
("/fetchActivatedTheme")
public Optional<ThemeProperty> fetchActivatedTheme(){
  return themeservice.fetchActivatedTheme();
}


@GetMapping
("/ifPresent")
public Object ifPresent(@RequestParam(name = "Object") Object Object){
  return themeservice.ifPresent(Object);
}


@GetMapping
("/getBasePath")
public Path getBasePath(){
  return themeservice.getBasePath();
}


}