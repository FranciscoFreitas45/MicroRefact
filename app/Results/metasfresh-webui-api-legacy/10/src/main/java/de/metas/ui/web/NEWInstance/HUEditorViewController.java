package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorViewController {

 private HUEditorView hueditorview;


@GetMapping
("/matchesAnyRowRecursive")
public boolean matchesAnyRowRecursive(@RequestParam(name = "filter") HUEditorRowFilter filter){
  return hueditorview.matchesAnyRowRecursive(filter);
}


@GetMapping
("/streamAllRecursive")
public Stream<HUEditorRow> streamAllRecursive(@RequestParam(name = "filter") HUEditorRowFilter filter){
  return hueditorview.streamAllRecursive(filter);
}


@GetMapping
("/streamByIds")
public Stream<HUEditorRow> streamByIds(@RequestParam(name = "filter") HUEditorRowFilter filter){
  return hueditorview.streamByIds(filter);
}


}