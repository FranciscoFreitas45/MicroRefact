package Interface;
public interface MaterialCockpitFilters {

   public DocumentFilterDescriptorsProvider getFilterDescriptors();
   public DocumentFilterList extractDocumentFilters(CreateViewRequest request);
   public DocumentFilterList createAutoFilters();
   public LocalDate getFilterByDate(DocumentFilterList filters);
   public IQuery<I_MD_Cockpit> createQuery(DocumentFilterList filters);
   public Object list(Object Object);
}