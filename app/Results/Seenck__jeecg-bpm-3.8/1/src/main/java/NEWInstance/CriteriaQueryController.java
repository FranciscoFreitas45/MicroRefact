package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CriteriaQueryController {

 private CriteriaQuery criteriaquery;

 private CriteriaQuery criteriaquery;


@PutMapping
("/add")
public void add(){
criteriaquery.add();
}


@PutMapping
("/in")
public void in(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object[] keyvalue){
criteriaquery.in(keyname,keyvalue);
}


@PutMapping
("/isNull")
public void isNull(@RequestParam(name = "keyname") String keyname){
criteriaquery.isNull(keyname);
}


@PutMapping
("/notEq")
public void notEq(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object keyvalue){
criteriaquery.notEq(keyname,keyvalue);
}


@PutMapping
("/setProjection")
public void setProjection(@RequestParam(name = "property") Property property){
criteriaquery.setProjection(property);
}


@PutMapping
("/addOrder")
public void addOrder(@RequestParam(name = "ordername") String ordername,@RequestParam(name = "ordervalue") SortDirection ordervalue){
criteriaquery.addOrder(ordername,ordervalue);
}


@PutMapping
("/createAlias")
public void createAlias(@RequestParam(name = "name") String name,@RequestParam(name = "value") String value){
criteriaquery.createAlias(name,value);
}


@PutMapping
("/like")
public void like(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object keyvalue){
criteriaquery.like(keyname,keyvalue);
}


@PutMapping
("/setPageSize")
public void setPageSize(@RequestParam(name = "pageSize") int pageSize){
criteriaquery.setPageSize(pageSize);
}


@PutMapping
("/eq")
public void eq(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object keyvalue){
criteriaquery.eq(keyname,keyvalue);
}


@PutMapping
("/ge")
public void ge(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object keyvalue){
criteriaquery.ge(keyname,keyvalue);
}


@PutMapping
("/le")
public void le(@RequestParam(name = "keyname") String keyname,@RequestParam(name = "keyvalue") Object keyvalue){
criteriaquery.le(keyname,keyvalue);
}


}