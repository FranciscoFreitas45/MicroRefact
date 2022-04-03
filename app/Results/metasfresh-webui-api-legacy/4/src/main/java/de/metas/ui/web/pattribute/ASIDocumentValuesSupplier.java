package de.metas.ui.web.pattribute;
 import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.model.Document.DocumentValuesSupplier;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ASIDocumentValuesSupplier implements DocumentValuesSupplier{

 private  String VERSION;

 private  Supplier<DocumentId> documentIdSupplier;

 private  Map<String,Object> values;


@Override
public String getVersion(){
    return VERSION;
}


@Override
public DocumentId getDocumentId(){
    return documentIdSupplier.get();
}


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    final String fieldName = fieldDescriptor.getFieldName();
    if (values.containsKey(fieldName)) {
        return values.get(fieldName);
    } else {
        return NO_VALUE;
    }
}


}