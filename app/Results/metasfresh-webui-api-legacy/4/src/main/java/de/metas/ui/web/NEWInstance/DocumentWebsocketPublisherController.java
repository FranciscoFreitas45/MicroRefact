package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentWebsocketPublisherController {

 private DocumentWebsocketPublisher documentwebsocketpublisher;


@PutMapping
("/convertAndPublish")
public void convertAndPublish(@RequestParam(name = "jsonDocumentEvents") List<JSONDocument> jsonDocumentEvents){
documentwebsocketpublisher.convertAndPublish(jsonDocumentEvents);
}


}