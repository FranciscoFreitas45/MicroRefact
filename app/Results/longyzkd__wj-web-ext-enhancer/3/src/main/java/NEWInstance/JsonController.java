package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JsonController {

 private Json json;

 private Json json;


@PutMapping
("/setSuccess")
public void setSuccess(@RequestParam(name = "success") boolean success){
json.setSuccess(success);
}


@PutMapping
("/setMsg")
public void setMsg(@RequestParam(name = "msg") String msg){
json.setMsg(msg);
}


@PutMapping
("/setChildren")
public void setChildren(@RequestParam(name = "children") List children){
json.setChildren(children);
}


}