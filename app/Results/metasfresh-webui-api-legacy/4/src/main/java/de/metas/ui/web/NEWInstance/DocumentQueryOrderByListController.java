package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentQueryOrderByListController {

 private DocumentQueryOrderByList documentqueryorderbylist;


@GetMapping
("/parse")
public DocumentQueryOrderByList parse(@RequestParam(name = "orderBysListStr") String orderBysListStr){
  return documentqueryorderbylist.parse(orderBysListStr);
}


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return documentqueryorderbylist.isEmpty();
}


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "list1") DocumentQueryOrderByList list1,@RequestParam(name = "list2") DocumentQueryOrderByList list2){
  return documentqueryorderbylist.equals(list1,list2);
}


@GetMapping
("/toComparator")
public Comparator<T> toComparator(@RequestParam(name = "fieldValueExtractor") FieldValueExtractor<T> fieldValueExtractor,@RequestParam(name = "jsonOpts") JSONOptions jsonOpts){
  return documentqueryorderbylist.toComparator(fieldValueExtractor,jsonOpts);
}


@GetMapping
("/ofList")
public DocumentQueryOrderByList ofList(@RequestParam(name = "list") List<DocumentQueryOrderBy> list){
  return documentqueryorderbylist.ofList(list);
}


@GetMapping
("/toDocumentQueryOrderByList")
public Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> toDocumentQueryOrderByList(){
  return documentqueryorderbylist.toDocumentQueryOrderByList();
}


@GetMapping
("/stream")
public Stream<DocumentQueryOrderBy> stream(){
  return documentqueryorderbylist.stream();
}


}