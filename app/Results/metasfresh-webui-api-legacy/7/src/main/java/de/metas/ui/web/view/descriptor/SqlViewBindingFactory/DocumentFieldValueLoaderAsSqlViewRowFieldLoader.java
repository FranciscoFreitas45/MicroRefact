package de.metas.ui.web.view.descriptor.SqlViewBindingFactory;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.cache.CCache;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.view.DefaultViewInvalidationAdvisor;
import de.metas.ui.web.view.IViewInvalidationAdvisor;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewRowCustomizer;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding.SqlViewRowFieldLoader;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.sql.DocumentFieldValueLoader;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class DocumentFieldValueLoaderAsSqlViewRowFieldLoader implements SqlViewRowFieldLoader{

@NonNull
 private  DocumentFieldValueLoader fieldValueLoader;

 private  boolean isDisplayColumnAvailable;


@Override
public Object retrieveValue(ResultSet rs,String adLanguage){
    return fieldValueLoader.retrieveFieldValue(rs, isDisplayColumnAvailable, adLanguage, (LookupDescriptor) null);
}


}