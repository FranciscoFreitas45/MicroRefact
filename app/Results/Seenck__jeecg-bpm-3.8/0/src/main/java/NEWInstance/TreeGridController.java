package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TreeGridController {

 private TreeGrid treegrid;

 private TreeGrid treegrid;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") String id){
treegrid.setId(id);
}


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
treegrid.setText(text);
}


@PutMapping
("/setCode")
public void setCode(@RequestParam(name = "code") String code){
treegrid.setCode(code);
}


@PutMapping
("/setState")
public void setState(@RequestParam(name = "state") String state){
treegrid.setState(state);
}


@PutMapping
("/setOrder")
public void setOrder(@RequestParam(name = "order") String order){
treegrid.setOrder(order);
}


@PutMapping
("/setSrc")
public void setSrc(@RequestParam(name = "src") String src){
treegrid.setSrc(src);
}


@PutMapping
("/setParentId")
public void setParentId(@RequestParam(name = "parentId") String parentId){
treegrid.setParentId(parentId);
}


@PutMapping
("/setParentText")
public void setParentText(@RequestParam(name = "parentText") String parentText){
treegrid.setParentText(parentText);
}


@PutMapping
("/setOperations")
public void setOperations(@RequestParam(name = "operations") String operations){
treegrid.setOperations(operations);
}


@PutMapping
("/setFieldMap")
public void setFieldMap(@RequestParam(name = "fieldMap") Map<String,Object> fieldMap){
treegrid.setFieldMap(fieldMap);
}


@PutMapping
("/setFunctionType")
public void setFunctionType(@RequestParam(name = "functionType") String functionType){
treegrid.setFunctionType(functionType);
}


@PutMapping
("/setIconStyle")
public void setIconStyle(@RequestParam(name = "iconStyle") String iconStyle){
treegrid.setIconStyle(iconStyle);
}


}