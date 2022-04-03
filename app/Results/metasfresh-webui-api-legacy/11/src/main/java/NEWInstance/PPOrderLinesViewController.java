package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PPOrderLinesViewController {

 private PPOrderLinesView pporderlinesview;

 private PPOrderLinesView pporderlinesview;


@PutMapping
("/invalidateAll")
public void invalidateAll(){
pporderlinesview.invalidateAll();
}


}