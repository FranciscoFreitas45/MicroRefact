package de.metas.ui.web.dataentry.window.descriptor.factory;
 import java.util.Date;
import java.util.Optional;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.springframework.stereotype.Service;
import de.metas.dataentry.DataEntryFieldId;
import de.metas.dataentry.DataEntryListValueId;
import de.metas.dataentry.FieldType;
import de.metas.dataentry.data.DataEntryCreatedUpdatedInfo;
import de.metas.dataentry.data.DataEntryRecord;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.user.User;
import de.metas.user.UserRepository;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class DataEntryWebuiTools {

 private  AdMessageKey MSG_CREATED_UPDATED_INFO_6P;

 private  UserRepository userRepository;


public Object extractFieldValueForDataEntry(IDocumentFieldView fieldView){
    final Object value = fieldView.getValue();
    final DocumentFieldDescriptor descriptor = fieldView.getDescriptor();
    final DataEntryFieldBindingDescriptor dataBinding = descriptor.getDataBindingNotNull(DataEntryFieldBindingDescriptor.class);
    final FieldType fieldType = dataBinding.getFieldType();
    if (value == null) {
        return null;
    }
    final Object result;
    switch(fieldType) {
        case DATE:
            result = fieldType.getClazz().cast(value);
            break;
        case LIST:
            final LookupDescriptor lookupDescriptor = descriptor.getLookupDescriptor().get();
            final DataEntryListValueDataSourceFetcher fetcher = (DataEntryListValueDataSourceFetcher) lookupDescriptor.getLookupDataSourceFetcher();
            result = fetcher.getListValueIdForLookup((IntegerLookupValue) value);
            break;
        case NUMBER:
            result = fieldType.getClazz().cast(value);
            break;
        case TEXT:
            result = fieldType.getClazz().cast(value);
            break;
        case LONG_TEXT:
            result = fieldType.getClazz().cast(value);
            break;
        case YESNO:
            result = fieldType.getClazz().cast(value);
            break;
        case PARENT_LINK_ID:
            result = fieldType.getClazz().cast(value);
            break;
        case SUB_TAB_ID:
            result = fieldType.getClazz().cast(value);
            break;
        default:
            // this includes CREATED_UPDATED_INFO, PARENT_LINK_ID and SUB_TAB_ID; we don't expect the document repo to try and extract these
            throw new AdempiereException("Unexpected fieldType=" + fieldType);
    }
    return result;
}


public DataEntryFieldId computeDataEntryFieldId(IDocumentFieldView field){
    final String fieldName = field.getFieldName();
    final DataEntryFieldId dataEntryFieldId = DataEntryFieldId.ofRepoId(Integer.parseInt(fieldName));
    return dataEntryFieldId;
}


public ITranslatableString extractCreatedUpdatedInfo(DataEntryCreatedUpdatedInfo createdUpdatedInfo){
    final User creator = userRepository.getByIdInTrx(createdUpdatedInfo.getCreatedBy());
    final User updater = userRepository.getByIdInTrx(createdUpdatedInfo.getCreatedBy());
    final ITranslatableString createdUpdatedInfoString = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_CREATED_UPDATED_INFO_6P, creator.getName(), Date.from(createdUpdatedInfo.getCreated().toInstant()), Date.from(createdUpdatedInfo.getCreated().toInstant()), updater.getName(), Date.from(createdUpdatedInfo.getUpdated().toInstant()), Date.from(createdUpdatedInfo.getUpdated().toInstant()));
    return createdUpdatedInfoString;
}


public Object extractDataEntryValueForField(DataEntryRecord dataEntryRecord,DocumentFieldDescriptor fieldDescriptor){
    final DataEntryFieldBindingDescriptor dataBinding = fieldDescriptor.getDataBindingNotNull(DataEntryFieldBindingDescriptor.class);
    final DataEntryFieldId dataEntryFieldId = dataBinding.getDataEntryFieldId();
    switch(dataBinding.getFieldType()) {
        case CREATED_UPDATED_INFO:
            final ITranslatableString trlString = extractCreatedUpdatedInfo(dataEntryRecord, dataEntryFieldId);
            return trlString.translate(Env.getAD_Language());
        case PARENT_LINK_ID:
            return dataEntryRecord.getMainRecord().getRecord_ID();
        case SUB_TAB_ID:
            return dataEntryRecord.getDataEntrySubTabId().getRepoId();
        case LIST:
            final DataEntryListValueId dataEntryListValueId = (DataEntryListValueId) dataEntryRecord.getFieldValue(dataEntryFieldId).orElse(null);
            final DataEntryListValueDataSourceFetcher fetcher = (DataEntryListValueDataSourceFetcher) fieldDescriptor.getLookupDescriptor().get().getLookupDataSourceFetcher();
            return fetcher.getLookupForForListValueId(dataEntryListValueId);
        default:
            return dataEntryRecord.getFieldValue(dataEntryFieldId).orElse(null);
    }
}


public String computeFieldName(DataEntryFieldId dataEntryFieldId){
    return Integer.toString(dataEntryFieldId.getRepoId());
}


}