package Interface;
public interface IViewInvalidationAdvisor {

   public Set<DocumentId> findAffectedRowIds(TableRecordReferenceSet recordRefs,IView view);
}