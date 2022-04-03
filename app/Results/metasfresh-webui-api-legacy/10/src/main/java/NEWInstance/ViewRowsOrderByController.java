package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewRowsOrderByController {

 private ViewRowsOrderBy viewrowsorderby;

 private ViewRowsOrderBy viewrowsorderby;


@GetMapping
("/withOrderBys")
public ViewRowsOrderBy withOrderBys(@RequestParam(name = "orderBys") DocumentQueryOrderByList orderBys){
  return viewrowsorderby.withOrderBys(orderBys);
}


@GetMapping
("/toComparator")
public Comparator<T> toComparator(){
  return viewrowsorderby.toComparator();
}


@GetMapping
("/toDocumentQueryOrderByList")
public DocumentQueryOrderByList toDocumentQueryOrderByList(){
  return viewrowsorderby.toDocumentQueryOrderByList();
}


}