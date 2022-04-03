package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterListController {

 private DocumentFilterList documentfilterlist;


@GetMapping
("/toDocumentFilterList")
public Collector<DocumentFilter,?,DocumentFilterList> toDocumentFilterList(){
  return documentfilterlist.toDocumentFilterList();
}


@GetMapping
("/stream")
public Stream<DocumentFilter> stream(){
  return documentfilterlist.stream();
}


@GetMapping
("/filter")
public Object filter(@RequestParam(name = "Object") Object Object){
  return documentfilterlist.filter(Object);
}


@GetMapping
("/mergeWith")
public DocumentFilterList mergeWith(@RequestParam(name = "filter") DocumentFilter filter){
  return documentfilterlist.mergeWith(filter);
}


@GetMapping
("/ofList")
public DocumentFilterList ofList(@RequestParam(name = "list") Collection<DocumentFilter> list){
  return documentfilterlist.ofList(list);
}


@GetMapping
("/toList")
public ImmutableList<DocumentFilter> toList(){
  return documentfilterlist.toList();
}


}