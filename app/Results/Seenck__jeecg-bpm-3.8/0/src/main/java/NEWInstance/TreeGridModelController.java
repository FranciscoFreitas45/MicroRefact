package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TreeGridModelController {

 private TreeGridModel treegridmodel;

 private TreeGridModel treegridmodel;


@PutMapping
("/setIcon")
public void setIcon(@RequestParam(name = "icon") String icon){
treegridmodel.setIcon(icon);
}


@PutMapping
("/setTextField")
public void setTextField(@RequestParam(name = "textField") String textField){
treegridmodel.setTextField(textField);
}


@PutMapping
("/setParentText")
public void setParentText(@RequestParam(name = "parentText") String parentText){
treegridmodel.setParentText(parentText);
}


@PutMapping
("/setParentId")
public void setParentId(@RequestParam(name = "parentId") String parentId){
treegridmodel.setParentId(parentId);
}


@PutMapping
("/setSrc")
public void setSrc(@RequestParam(name = "src") String src){
treegridmodel.setSrc(src);
}


@PutMapping
("/setIdField")
public void setIdField(@RequestParam(name = "idField") String idField){
treegridmodel.setIdField(idField);
}


@PutMapping
("/setChildList")
public void setChildList(@RequestParam(name = "childList") String childList){
treegridmodel.setChildList(childList);
}


@PutMapping
("/setOrder")
public void setOrder(@RequestParam(name = "order") String order){
treegridmodel.setOrder(order);
}


@PutMapping
("/setIconStyle")
public void setIconStyle(@RequestParam(name = "iconStyle") String iconStyle){
treegridmodel.setIconStyle(iconStyle);
}


@PutMapping
("/setFunctionType")
public void setFunctionType(@RequestParam(name = "functionType") String functionType){
treegridmodel.setFunctionType(functionType);
}


}