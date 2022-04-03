package de.metas.ui.web.window.datatypes.json;
 import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import de.metas.printing.esb.base.util.Check;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.model.DocumentFieldChange;
import de.metas.ui.web.window.model.IDocumentFieldView;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class JSONDocumentOptions {

@Getter
 private  JSONOptions jsonOpts;

 private  String showOnlyFieldsListStr;

 private  Predicate<IDocumentFieldView> _documentFieldFilter;

 private  Predicate<DocumentFieldChange> _documentFieldChangeFilter;

@Getter
 private  boolean doNotFetchIncludedTabs;

 private  boolean showAdvancedFields;

 private  Supplier<JSONDocumentPermissions> documentPermissionsSupplier;

 private  Splitter FIELDS_LIST_SPLITTER;

 private  Predicate<IDocumentFieldView> FILTER_DocumentFieldView_BASIC_PUBLIC_FIELDS;

 private  Predicate<DocumentFieldChange> FILTER_DocumentFieldChange_BASIC_PUBLIC_FIELDS;

 private  Predicate<IDocumentFieldView> FILTER_DocumentFieldView_ALL_PUBLIC_FIELDS;

 private  Predicate<DocumentFieldChange> FILTER_DocumentFieldChange_ALL_PUBLIC_FIELDS;

 private  Set<String> fieldNamesSet;

 private  Predicate<IDocumentFieldView> parentFilter;

 private  Set<String> fieldNamesSet;

 private  Predicate<DocumentFieldChange> parentFilter;


public Predicate<DocumentFieldChange> documentFieldChangeFilter(){
    if (_documentFieldChangeFilter == null) {
        _documentFieldChangeFilter = createDocumentFieldChangeFilter();
    }
    return _documentFieldChangeFilter;
}


public Predicate<IDocumentFieldView> documentFieldFilter(){
    if (_documentFieldFilter == null) {
        _documentFieldFilter = createDocumentFieldFilter();
    }
    return _documentFieldFilter;
}


public Predicate<DocumentFieldChange> createDocumentFieldChangeFilter(){
    final Predicate<DocumentFieldChange> filter = showAdvancedFields ? FILTER_DocumentFieldChange_ALL_PUBLIC_FIELDS : FILTER_DocumentFieldChange_BASIC_PUBLIC_FIELDS;
    final Set<String> dataFieldNamesSet = Check.isEmpty(showOnlyFieldsListStr, true) ? ImmutableSet.of() : ImmutableSet.copyOf(FIELDS_LIST_SPLITTER.splitToList(showOnlyFieldsListStr));
    if (dataFieldNamesSet.isEmpty() || dataFieldNamesSet.contains("*")) {
        return filter;
    }
    return new FILTER_DocumentFieldChange_ByFieldNamesSet(dataFieldNamesSet, filter);
}


public Supplier<JSONDocumentPermissions> createPermissionsSupplier(UserSession userSession){
    if (userSession == null) {
        return () -> null;
    }
    return ExtendedMemorizingSupplier.of(() -> {
        final IUserRolePermissions userRolePermissions = userSession.getUserRolePermissions();
        return new JSONDocumentPermissions(userRolePermissions);
    });
}


@Override
public boolean test(DocumentFieldChange field){
    if (!fieldNamesSet.contains(field.getFieldName())) {
        return false;
    }
    return parentFilter.test(field);
}


public JSONDocumentOptions of(UserSession userSession){
    return builder().userSession(userSession).build();
}


@Override
public String toString(){
    return "field name in " + fieldNamesSet + " and " + parentFilter;
}


public Predicate<IDocumentFieldView> createDocumentFieldFilter(){
    final Predicate<IDocumentFieldView> filter = showAdvancedFields ? FILTER_DocumentFieldView_ALL_PUBLIC_FIELDS : FILTER_DocumentFieldView_BASIC_PUBLIC_FIELDS;
    final Set<String> dataFieldNamesSet = Check.isEmpty(showOnlyFieldsListStr, true) ? ImmutableSet.of() : ImmutableSet.copyOf(FIELDS_LIST_SPLITTER.splitToList(showOnlyFieldsListStr));
    if (dataFieldNamesSet.isEmpty() || dataFieldNamesSet.contains("*")) {
        return filter;
    }
    return new FILTER_DocumentFieldView_ByFieldNamesSet(dataFieldNamesSet, filter);
}


public JSONDocumentPermissions getDocumentPermissions(){
    return documentPermissionsSupplier.get();
}


public String getAdLanguage(){
    return getJsonOpts().getAdLanguage();
}


}