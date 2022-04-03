package de.metas.ui.web.window.model.sql.SqlDocumentsRepository;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.persistence.TableModelLoader;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.exceptions.DBMoreThanOneRecordsFoundException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.PlainContextAware;
import org.adempiere.service.ClientId;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import de.metas.cache.model.POCacheSourceModel;
import de.metas.logging.LogManager;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.DocumentFieldValueLoader;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.DocumentValuesSupplier;
import de.metas.ui.web.window.model.DocumentQuery;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.ui.web.window.model.OrderedDocumentsList;
import de.metas.ui.web.window.model.lookup.LabelsLookup;
import de.metas.util.Services;
import lombok.NonNull;
public class ResultSetDocumentValuesSupplier implements DocumentValuesSupplier{

 private  AtomicInteger _nextMissingId;

 private  DocumentEntityDescriptor entityDescriptor;

 private  String adLanguage;

 private  ResultSet rs;

 private  boolean idAquired;

 private  DocumentId id;

 private  String version;


@Override
public String getVersion(){
    if (version != null) {
        return version;
    }
    final DocumentFieldDescriptor versionField = entityDescriptor.getFieldOrNull(SqlDocumentEntityDataBindingDescriptor.FIELDNAME_Version);
    if (versionField == null) {
        version = VERSION_DEFAULT;
        return version;
    } else {
        final Instant versionDate = TimeUtil.asInstant(getValue(versionField));
        version = versionDate == null ? VERSION_DEFAULT : String.valueOf(versionDate.toEpochMilli());
        return version;
    }
}


@Override
public DocumentId getDocumentId(){
    if (idAquired) {
        return id;
    } else {
        id = retrieveDocumentId();
        idAquired = true;
        return id;
    }
}


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    final SqlDocumentFieldDataBindingDescriptor fieldDataBinding = SqlDocumentFieldDataBindingDescriptor.castOrNull(fieldDescriptor.getDataBinding());
    // If there is no SQL databinding, we cannot provide a value
    if (fieldDataBinding == null) {
        return NO_VALUE;
    }
    final DocumentFieldValueLoader fieldValueLoader = fieldDataBinding.getDocumentFieldValueLoader();
    final boolean isDisplayColumnAvailable = true;
    final LookupDescriptor lookupDescriptor = fieldDescriptor.getLookupDescriptor().orElse(null);
    try {
        return fieldValueLoader.retrieveFieldValue(rs, isDisplayColumnAvailable, adLanguage, lookupDescriptor);
    } catch (final SQLException e) {
        throw new DBException("Failed retrieving the value for " + fieldDescriptor + " using " + fieldValueLoader, e);
    }
}


public DocumentId retrieveDocumentId(){
    final List<DocumentFieldDescriptor> idFields = entityDescriptor.getIdFields();
    if (idFields.isEmpty()) {
        // FIXME: workaround to bypass the missing ID field for views
        final int idInt = _nextMissingId.decrementAndGet();
        return DocumentId.of(idInt);
    } else if (idFields.size() == 1) {
        final Object idObj = getValue(idFields.get(0));
        return DocumentId.ofObject(idObj);
    } else {
        final List<Object> idParts = idFields.stream().map(this::getValue).collect(Collectors.toList());
        return DocumentId.ofComposedKeyParts(idParts);
    }
}


}