package de.metas.ui.web.view.CreateViewRequest;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.process.view.ViewActionDescriptorsFactory;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.util.Check;
import de.metas.util.collections.CollectionUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@ToString
public class WrappedDocumentFilterList {

 public  WrappedDocumentFilterList EMPTY;

 private  ImmutableList<JSONDocumentFilter> jsonFilters;

 private  DocumentFilterList filters;


public WrappedDocumentFilterList ofFilters(DocumentFilterList filters){
    if (filters == null || filters.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONDocumentFilter> jsonFiltersEffective = null;
    final DocumentFilterList filtersEffective = filters;
    return new WrappedDocumentFilterList(jsonFiltersEffective, filtersEffective);
}


public WrappedDocumentFilterList ofJSONFilters(List<JSONDocumentFilter> jsonFilters){
    if (jsonFilters == null || jsonFilters.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONDocumentFilter> jsonFiltersEffective = ImmutableList.copyOf(jsonFilters);
    final DocumentFilterList filtersEffective = null;
    return new WrappedDocumentFilterList(jsonFiltersEffective, filtersEffective);
}


public DocumentFilterList unwrap(DocumentFilterDescriptorsProvider descriptors){
    if (filters != null) {
        return filters;
    }
    if (jsonFilters == null || jsonFilters.isEmpty()) {
        return DocumentFilterList.EMPTY;
    }
    return JSONDocumentFilter.unwrapList(jsonFilters, descriptors);
}


}