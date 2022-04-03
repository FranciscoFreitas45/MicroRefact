package io.delivery.service;
 import io.delivery.entity.Document;
import java.util.List;
public interface DocumentService {


public List<Document> getDocumentList()
;

public Document findById(long id)
;

public Document deleteDocument(long id)
;

public List<Document> findByName(String name)
;

public Document create(Document document)
;

public Document updateDocument(Document document)
;

}