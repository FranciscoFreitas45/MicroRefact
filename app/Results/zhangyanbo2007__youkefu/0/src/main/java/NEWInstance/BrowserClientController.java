package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrowserClientController {

 private BrowserClient browserclient;

 private BrowserClient browserclient;


@PutMapping
("/setOs")
public void setOs(@RequestParam(name = "os") String os){
browserclient.setOs(os);
}


@PutMapping
("/setBrowser")
public void setBrowser(@RequestParam(name = "browser") String browser){
browserclient.setBrowser(browser);
}


@PutMapping
("/setVersion")
public void setVersion(@RequestParam(name = "version") String version){
browserclient.setVersion(version);
}


}