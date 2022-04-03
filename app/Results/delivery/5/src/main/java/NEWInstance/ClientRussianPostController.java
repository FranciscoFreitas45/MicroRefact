package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientRussianPostController {

 private ClientRussianPost clientrussianpost;


@GetMapping
("/result")
public String result(@RequestParam(name = "barcodestring") String barcodestring){
  return clientrussianpost.result(barcodestring);
}


}