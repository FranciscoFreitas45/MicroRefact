package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentIdsSelectionController {

 private DocumentIdsSelection documentidsselection;


@GetMapping
("/ofCommaSeparatedString")
public DocumentIdsSelection ofCommaSeparatedString(@RequestParam(name = "string") String string){
  return documentidsselection.ofCommaSeparatedString(string);
}


@GetMapping
("/fromNullable")
public DocumentIdsSelection fromNullable(@RequestParam(name = "documentId") DocumentId documentId){
  return documentidsselection.fromNullable(documentId);
}


@GetMapping
("/isSingleDocumentId")
public boolean isSingleDocumentId(){
  return documentidsselection.isSingleDocumentId();
}


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return documentidsselection.isEmpty();
}


@GetMapping
("/toCommaSeparatedString")
public String toCommaSeparatedString(){
  return documentidsselection.toCommaSeparatedString();
}


@GetMapping
("/getSingleDocumentId")
public DocumentId getSingleDocumentId(){
  return documentidsselection.getSingleDocumentId();
}


@GetMapping
("/of")
public DocumentIdsSelection of(@RequestParam(name = "documentIds") Collection<DocumentId> documentIds){
  return documentidsselection.of(documentIds);
}


@GetMapping
("/toSet")
public ImmutableSet<T> toSet(@RequestParam(name = "mapper") Function<DocumentId,T> mapper){
  return documentidsselection.toSet(mapper);
}


@GetMapping
("/toIntSet")
public Set<Integer> toIntSet(){
  return documentidsselection.toIntSet();
}


@PutMapping
("/forEach")
public void forEach(@RequestParam(name = "action") Consumer<DocumentId> action){
documentidsselection.forEach(action);
}


@GetMapping
("/isAll")
public boolean isAll(){
  return documentidsselection.isAll();
}


@GetMapping
("/ofStringSet")
public DocumentIdsSelection ofStringSet(@RequestParam(name = "stringDocumentIds") Collection<String> stringDocumentIds){
  return documentidsselection.ofStringSet(stringDocumentIds);
}


@GetMapping
("/ofIntSet")
public DocumentIdsSelection ofIntSet(@RequestParam(name = "intDocumentIds") Collection<Integer> intDocumentIds){
  return documentidsselection.ofIntSet(intDocumentIds);
}


@GetMapping
("/toDocumentIdsSelection")
public Collector<DocumentId,?,DocumentIdsSelection> toDocumentIdsSelection(){
  return documentidsselection.toDocumentIdsSelection();
}


@GetMapping
("/contains")
public boolean contains(@RequestParam(name = "documentId") DocumentId documentId){
  return documentidsselection.contains(documentId);
}


@GetMapping
("/stream")
public Stream<DocumentId> stream(){
  return documentidsselection.stream();
}


@GetMapping
("/isMoreThanOneDocumentId")
public boolean isMoreThanOneDocumentId(){
  return documentidsselection.isMoreThanOneDocumentId();
}


@GetMapping
("/size")
public int size(){
  return documentidsselection.size();
}


}