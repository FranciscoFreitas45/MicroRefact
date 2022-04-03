package de.metas.ui.web.window.datatypes;
 import java.util.concurrent.atomic.AtomicInteger;
import lombok.ToString;
@ToString
public class DocumentIdIntSequence {

 private  AtomicInteger nextInt;


public DocumentIdIntSequence newInstance(){
    return new DocumentIdIntSequence();
}


public DocumentId nextDocumentId(){
    return DocumentId.of(nextInt.getAndIncrement());
}


}