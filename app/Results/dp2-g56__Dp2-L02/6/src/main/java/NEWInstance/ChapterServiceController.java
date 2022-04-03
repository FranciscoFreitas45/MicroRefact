package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChapterServiceController {

 private ChapterService chapterservice;


@GetMapping
("/listFreeAreas")
public List<Area> listFreeAreas(){
  return chapterservice.listFreeAreas();
}


@GetMapping
("/createChapter")
public Chapter createChapter(){
  return chapterservice.createChapter();
}


@GetMapping
("/reconstruct")
public Chapter reconstruct(@RequestParam(name = "formObjectChapter") FormObjectChapter formObjectChapter,@RequestParam(name = "binding") BindingResult binding){
  return chapterservice.reconstruct(formObjectChapter,binding);
}


@GetMapping
("/saveCreate")
public Chapter saveCreate(@RequestParam(name = "chapter") Chapter chapter){
  return chapterservice.saveCreate(chapter);
}


@PutMapping
("/deleteAccountChapter")
public void deleteAccountChapter(){
chapterservice.deleteAccountChapter();
}


@GetMapping
("/loggedChapter")
public Chapter loggedChapter(){
  return chapterservice.loggedChapter();
}


@GetMapping
("/reconstructPersonalData")
public Chapter reconstructPersonalData(@RequestParam(name = "chapter") Chapter chapter,@RequestParam(name = "binding") BindingResult binding){
  return chapterservice.reconstructPersonalData(chapter,binding);
}


@GetMapping
("/update")
public Chapter update(@RequestParam(name = "chapter") Chapter chapter){
  return chapterservice.update(chapter);
}


}