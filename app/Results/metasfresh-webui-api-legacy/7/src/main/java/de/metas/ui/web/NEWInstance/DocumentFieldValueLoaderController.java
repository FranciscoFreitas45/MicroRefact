package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFieldValueLoaderController {

 private DocumentFieldValueLoader documentfieldvalueloader;


@GetMapping
("/retrieveFieldValue")
public Object retrieveFieldValue(@RequestParam(name = "rs") ResultSet rs,@RequestParam(name = "isDisplayColumnAvailable") boolean isDisplayColumnAvailable,@RequestParam(name = "adLanguage") String adLanguage,@RequestParam(name = "lookupDescriptor") LookupDescriptor lookupDescriptor){
  return documentfieldvalueloader.retrieveFieldValue(rs,isDisplayColumnAvailable,adLanguage,lookupDescriptor);
}


}