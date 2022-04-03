package Interface;
public interface DocumentFilterDescriptorsProvidersService {

   public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId,String tableName,Collection<DocumentFieldDescriptor> fields);
}