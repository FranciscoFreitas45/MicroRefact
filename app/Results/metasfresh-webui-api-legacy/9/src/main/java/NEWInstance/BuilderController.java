package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BuilderController {

 private Builder builder;

 private Builder builder;


@GetMapping
("/setType")
public Builder setType(@RequestParam(name = "type") IViewRowType type){
  return builder.setType(type);
}


@GetMapping
("/setParentRowId")
public Builder setParentRowId(@RequestParam(name = "parentRowId") DocumentId parentRowId){
  return builder.setParentRowId(parentRowId);
}


@GetMapping
("/setRowId")
public Builder setRowId(@RequestParam(name = "rowId") DocumentId rowId){
  return builder.setRowId(rowId);
}


@GetMapping
("/putFieldValue")
public Builder putFieldValue(@RequestParam(name = "fieldName") String fieldName,@RequestParam(name = "jsonValue") Object jsonValue){
  return builder.putFieldValue(fieldName,jsonValue);
}


@GetMapping
("/getRowId")
public DocumentId getRowId(){
  return builder.getRowId();
}


@GetMapping
("/isRootRow")
public boolean isRootRow(){
  return builder.isRootRow();
}


@GetMapping
("/addIncludedRow")
public Builder addIncludedRow(@RequestParam(name = "includedRow") IViewRow includedRow){
  return builder.addIncludedRow(includedRow);
}


@GetMapping
("/getFieldValueAsLookupValue")
public LookupValue getFieldValueAsLookupValue(@RequestParam(name = "fieldName") String fieldName){
  return builder.getFieldValueAsLookupValue(fieldName);
}


}