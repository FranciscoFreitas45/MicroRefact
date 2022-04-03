package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUBarcodeSqlDocumentFilterConverterController {

 private HUBarcodeSqlDocumentFilterConverter hubarcodesqldocumentfilterconverter;


@GetMapping
("/createDocumentFilterDescriptor")
public DocumentFilterDescriptor createDocumentFilterDescriptor(){
  return hubarcodesqldocumentfilterconverter.createDocumentFilterDescriptor();
}


}