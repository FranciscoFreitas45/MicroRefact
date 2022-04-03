package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OptionServiceController {

 private OptionService optionservice;


@GetMapping
("/getCommentPageSize")
public int getCommentPageSize(){
  return optionservice.getCommentPageSize();
}


@GetMapping
("/getByPropertyOrDefault")
public T getByPropertyOrDefault(@RequestParam(name = "property") PropertyEnum property,@RequestParam(name = "propertyType") Class<T> propertyType){
  return optionservice.getByPropertyOrDefault(property,propertyType);
}


@GetMapping
("/isEnabledAbsolutePath")
public Boolean isEnabledAbsolutePath(){
  return optionservice.isEnabledAbsolutePath();
}


@GetMapping
("/getJournalsPrefix")
public String getJournalsPrefix(){
  return optionservice.getJournalsPrefix();
}


@GetMapping
("/getBlogBaseUrl")
public String getBlogBaseUrl(){
  return optionservice.getBlogBaseUrl();
}


@GetMapping
("/getBlogTitle")
public String getBlogTitle(){
  return optionservice.getBlogTitle();
}


@GetMapping
("/getSeoKeywords")
public String getSeoKeywords(){
  return optionservice.getSeoKeywords();
}


@GetMapping
("/getSeoDescription")
public String getSeoDescription(){
  return optionservice.getSeoDescription();
}


@PutMapping
("/saveProperties")
public void saveProperties(@RequestParam(name = "properties") Map<? extends PropertyEnum,String> properties){
optionservice.saveProperties(properties);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return optionservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return optionservice.createInBatch(Object);
}


@GetMapping
("/flush")
public Object flush(@RequestParam(name = "Object") Object Object){
  return optionservice.flush(Object);
}


@GetMapping
("/listOptions")
public Map<String,Object> listOptions(@RequestParam(name = "keys") Collection<String> keys){
  return optionservice.listOptions(keys);
}


@GetMapping
("/getLinksPrefix")
public String getLinksPrefix(){
  return optionservice.getLinksPrefix();
}


@GetMapping
("/getPhotosPrefix")
public String getPhotosPrefix(){
  return optionservice.getPhotosPrefix();
}


@GetMapping
("/getArchivesPrefix")
public String getArchivesPrefix(){
  return optionservice.getArchivesPrefix();
}


@GetMapping
("/getCategoriesPrefix")
public String getCategoriesPrefix(){
  return optionservice.getCategoriesPrefix();
}


@GetMapping
("/getTagsPrefix")
public String getTagsPrefix(){
  return optionservice.getTagsPrefix();
}


@GetMapping
("/getByPropertyOfNonNull")
public Object getByPropertyOfNonNull(@RequestParam(name = "property") PropertyEnum property){
  return optionservice.getByPropertyOfNonNull(property);
}


@GetMapping
("/toString")
public Object toString(@RequestParam(name = "Object") Object Object){
  return optionservice.toString(Object);
}


@GetMapping
("/getPostPermalinkType")
public PostPermalinkType getPostPermalinkType(){
  return optionservice.getPostPermalinkType();
}


@GetMapping
("/getPathSuffix")
public String getPathSuffix(){
  return optionservice.getPathSuffix();
}


@GetMapping
("/getArchivesPageSize")
public int getArchivesPageSize(){
  return optionservice.getArchivesPageSize();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return optionservice.equals(Object);
}


@GetMapping
("/getSheetPermalinkType")
public SheetPermalinkType getSheetPermalinkType(){
  return optionservice.getSheetPermalinkType();
}


@GetMapping
("/getSheetPrefix")
public String getSheetPrefix(){
  return optionservice.getSheetPrefix();
}


@GetMapping
("/getRssPageSize")
public int getRssPageSize(){
  return optionservice.getRssPageSize();
}


@GetMapping
("/getPostPageSize")
public int getPostPageSize(){
  return optionservice.getPostPageSize();
}


@GetMapping
("/getEnumByPropertyOrDefault")
public T getEnumByPropertyOrDefault(@RequestParam(name = "property") PropertyEnum property,@RequestParam(name = "valueType") Class<T> valueType,@RequestParam(name = "defaultValue") T defaultValue){
  return optionservice.getEnumByPropertyOrDefault(property,valueType,defaultValue);
}


@GetMapping
("/getByProperty")
public Optional<T> getByProperty(@RequestParam(name = "property") PropertyEnum property,@RequestParam(name = "propertyType") Class<T> propertyType){
  return optionservice.getByProperty(property,propertyType);
}


@GetMapping
("/orElse")
public Object orElse(@RequestParam(name = "Object") Object Object){
  return optionservice.orElse(Object);
}


@GetMapping
("/getBirthday")
public long getBirthday(){
  return optionservice.getBirthday();
}


}