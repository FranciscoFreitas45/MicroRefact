package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AjaxJsonController {

 private AjaxJson ajaxjson;

 private AjaxJson ajaxjson;


@PutMapping
("/setMsg")
public void setMsg(@RequestParam(name = "msg") String msg){
ajaxjson.setMsg(msg);
}


@PutMapping
("/setSuccess")
public void setSuccess(@RequestParam(name = "success") boolean success){
ajaxjson.setSuccess(success);
}


@PutMapping
("/setAttributes")
public void setAttributes(@RequestParam(name = "attributes") Map<String,Object> attributes){
ajaxjson.setAttributes(attributes);
}


@PutMapping
("/setObj")
public void setObj(@RequestParam(name = "obj") Object obj){
ajaxjson.setObj(obj);
}


}