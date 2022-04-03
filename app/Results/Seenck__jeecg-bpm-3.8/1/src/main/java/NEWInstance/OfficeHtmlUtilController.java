package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OfficeHtmlUtilController {

 private OfficeHtmlUtil officehtmlutil;

 private OfficeHtmlUtil officehtmlutil;


@GetMapping
("/doHtml")
public String doHtml(@RequestParam(name = "htmlStr") String htmlStr){
  return officehtmlutil.doHtml(htmlStr);
}


@PutMapping
("/stringToFile")
public void stringToFile(@RequestParam(name = "str") String str,@RequestParam(name = "filename") String filename){
officehtmlutil.stringToFile(str,filename);
}


}