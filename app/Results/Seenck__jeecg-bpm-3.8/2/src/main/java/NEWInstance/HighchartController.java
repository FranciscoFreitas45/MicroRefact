package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HighchartController {

 private Highchart highchart;

 private Highchart highchart;


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
highchart.setType(type);
}


@PutMapping
("/setData")
public void setData(@RequestParam(name = "data") List data){
highchart.setData(data);
}


}