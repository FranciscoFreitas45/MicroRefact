package DTO;
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

 private  JSONOptions jsonOpts;

 private  String showOnlyFieldsListStr;

 private  Predicate<IDocumentFieldView> _documentFieldFilter;

 private  Predicate<DocumentFieldChange> _documentFieldChangeFilter;

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


public JSONDocumentPermissions getDocumentPermissions(){
    return documentPermissionsSupplier.get();
}


public String getAdLanguage(){
    return getJsonOpts().getAdLanguage();
}


}