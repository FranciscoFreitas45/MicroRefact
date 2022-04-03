package de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
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
public class FILTER_DocumentFieldChange_ByFieldNamesSet implements Predicate<DocumentFieldChange>{

 private  Set<String> fieldNamesSet;

 private  Predicate<DocumentFieldChange> parentFilter;


@Override
public boolean test(DocumentFieldChange field){
    if (!fieldNamesSet.contains(field.getFieldName())) {
        return false;
    }
    return parentFilter.test(field);
}


@Override
public String toString(){
    return "field name in " + fieldNamesSet + " and " + parentFilter;
}


}