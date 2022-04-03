package Interface;
public interface Document {

   public IDocumentEvaluatee asEvaluatee();
   public IDocumentFieldView getFieldView(String fieldName);
}