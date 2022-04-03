package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlForFetchingLookupByIdController {

 private SqlForFetchingLookupById sqlforfetchinglookupbyid;


@GetMapping
("/toStringExpression")
public IStringExpression toStringExpression(@RequestParam(name = "joinOnColumnNameFQ") String joinOnColumnNameFQ){
  return sqlforfetchinglookupbyid.toStringExpression(joinOnColumnNameFQ);
}


}