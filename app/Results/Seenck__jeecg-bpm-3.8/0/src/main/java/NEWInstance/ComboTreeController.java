package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComboTreeController {

 private ComboTree combotree;

 private ComboTree combotree;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") String id){
combotree.setId(id);
}


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
combotree.setText(text);
}


@PutMapping
("/setChecked")
public void setChecked(@RequestParam(name = "checked") Boolean checked){
combotree.setChecked(checked);
}


@PutMapping
("/setChildren")
public void setChildren(@RequestParam(name = "children") List<ComboTree> children){
combotree.setChildren(children);
}


@PutMapping
("/setState")
public void setState(@RequestParam(name = "state") String state){
combotree.setState(state);
}


@PutMapping
("/setAttributes")
public void setAttributes(@RequestParam(name = "attributes") Map<String,Object> attributes){
combotree.setAttributes(attributes);
}


}