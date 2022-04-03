package io.delivery.controller;
 import io.delivery.entity.Document;
import io.delivery.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import java.util.List;
@Controller
@RequestMapping("/document")
public class DocumentController {

 final  DocumentService documentService;

@Autowired
public DocumentController(DocumentService documentService) {
    this.documentService = documentService;
}
@RequestMapping(value = "/all", method = RequestMethod.GET)
@ResponseBody
public List<Document> getDocumentList(){
    return documentService.getDocumentList();
}


@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@ResponseBody
public Document addDocument(Document document){
    documentService.create(document);
    return document;
}


@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
@ResponseBody
public Document deleteDocument(String inputId){
    return documentService.deleteDocument(Long.parseLong(inputId));
}


@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
@ResponseBody
public Document getDocumentById(String id){
    return documentService.findById(Long.parseLong(id));
}


@RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
@ResponseBody
public List<Document> getDocumentByName(String name){
    return documentService.findByName(name);
}


@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
@ResponseBody
public Document updateDocument(Document document){
    documentService.updateDocument(document);
    return document;
}


}