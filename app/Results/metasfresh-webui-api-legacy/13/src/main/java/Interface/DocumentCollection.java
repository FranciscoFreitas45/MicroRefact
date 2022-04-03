package Interface;
public interface DocumentCollection {

   public R forDocumentWritable(DocumentPath documentPath,IDocumentChangesCollector changesCollector,Function<Document,R> documentProcessor);
}