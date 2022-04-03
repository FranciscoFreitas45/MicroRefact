package Interface;
public interface DocumentCollection {

   public DocumentEntityDescriptor getDocumentEntityDescriptor(WindowId windowId);
   public R forRootDocumentReadonly(DocumentPath documentPath,Function<Document,R> rootDocumentProcessor);
   public R forDocumentReadonly(DocumentPath documentPath,Function<Document,R> documentProcessor);
   public R forRootDocumentWritable(DocumentPath documentPathOrNew,IDocumentChangesCollector changesCollector,Function<Document,R> rootDocumentProcessor);
}