package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BuilderController {

 private Builder builder;

 private Builder builder;


@GetMapping
("/build")
public DocumentFilter build(){
  return builder.build();
}


@GetMapping
("/setFilters")
public Builder setFilters(@RequestParam(name = "filters") DocumentFilterList filters){
  return builder.setFilters(filters);
}


@GetMapping
("/addFiltersIfAbsent")
public Builder addFiltersIfAbsent(@RequestParam(name = "filters") Collection<DocumentFilter> filters){
  return builder.addFiltersIfAbsent(filters);
}


@GetMapping
("/addStickyFilter")
public Builder addStickyFilter(@RequestParam(name = "stickyFilter") DocumentFilter stickyFilter){
  return builder.addStickyFilter(stickyFilter);
}


@GetMapping
("/setParameters")
public Builder setParameters(@RequestParam(name = "parameters") Map<String,Object> parameters){
  return builder.setParameters(parameters);
}


@GetMapping
("/setParameter")
public Builder setParameter(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return builder.setParameter(name,value);
}


}