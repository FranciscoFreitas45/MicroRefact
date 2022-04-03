package de.metas.ui.web.window.model;
 import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class OrderedDocumentsList {

 private  ArrayList<Document> documents;

 private  DocumentQueryOrderByList orderBys;


public ImmutableMap<DocumentId,Document> toImmutableMap(){
    return Maps.uniqueIndex(documents, Document::getDocumentId);
}


public void addDocuments(Collection<Document> documents){
    if (documents.isEmpty()) {
        return;
    }
    documents.forEach(this::addDocument);
}


public int size(){
    return documents.size();
}


public void addDocument(Document document){
    documents.add(document);
}


public OrderedDocumentsList of(Collection<Document> documents,DocumentQueryOrderByList orderBys){
    return new OrderedDocumentsList(documents, orderBys);
}


public Document get(int index){
    return documents.get(index);
}


public boolean isEmpty(){
    return documents.isEmpty();
}


public OrderedDocumentsList newEmpty(DocumentQueryOrderByList orderBys){
    return new OrderedDocumentsList(ImmutableList.of(), orderBys);
}


public ArrayList<Document> toList(){
    return documents;
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
}


}