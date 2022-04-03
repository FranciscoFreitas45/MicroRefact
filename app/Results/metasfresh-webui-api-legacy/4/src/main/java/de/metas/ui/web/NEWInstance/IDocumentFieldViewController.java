package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IDocumentFieldViewController {

 private IDocumentFieldView idocumentfieldview;


@GetMapping
("/getFieldName")
public String getFieldName(){
  return idocumentfieldview.getFieldName();
}


}