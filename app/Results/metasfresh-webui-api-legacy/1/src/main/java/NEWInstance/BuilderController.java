package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BuilderController {

 private Builder builder;

 private Builder builder;


@GetMapping
("/setProcessId")
public Builder setProcessId(@RequestParam(name = "processId") ProcessId processId){
  return builder.setProcessId(processId);
}


@GetMapping
("/setLayoutType")
public Builder setLayoutType(@RequestParam(name = "layoutType") PanelLayoutType layoutType){
  return builder.setLayoutType(layoutType);
}


@GetMapping
("/addElement")
public Builder addElement(@RequestParam(name = "elementBuilder") DocumentLayoutElementDescriptor.Builder elementBuilder){
  return builder.addElement(elementBuilder);
}


@GetMapping
("/build")
public ASILayout build(){
  return builder.build();
}


@GetMapping
("/addElementsFromViewRowClassAndFieldNames")
public Builder addElementsFromViewRowClassAndFieldNames(@RequestParam(name = "viewRowClass") Class<T> viewRowClass,@RequestParam(name = "viewDataType") JSONViewDataType viewDataType,@RequestParam(name = "columns") ClassViewColumnOverrides columns){
  return builder.addElementsFromViewRowClassAndFieldNames(viewRowClass,viewDataType,columns);
}


}