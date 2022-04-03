package de.metas.ui.web.document.filter.provider;
 import java.util.Collection;
import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
public interface DocumentFilterDescriptorsProvider {


public Collection<DocumentFilterDescriptor> getAll()
;

public DocumentFilterDescriptor getByFilterId(String filterId){
    final DocumentFilterDescriptor filterDescriptor = getByFilterIdOrNull(filterId);
    if (filterDescriptor == null) {
        throw new AdempiereException("Filter '" + filterId + "' was not found in " + this);
    }
    return filterDescriptor;
}
;

public DocumentFilterDescriptor getByFilterIdOrNull(String filterId)
;

}