package de.metas.ui.web.handlingunits;
 import java.util.function.Supplier;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder(toBuilder = true)
public class HUEditorRowAttributesSupplier implements Supplier<HUEditorRowAttributes>{

@NonNull
 private DocumentId viewRowId;

@NonNull
 private HuId huId;

@NonNull
 private HUEditorRowAttributesProvider provider;


@Override
public HUEditorRowAttributes get(){
    return provider.getAttributes(viewRowId, provider.createAttributeKey(huId));
}


public HUEditorRowAttributesSupplier changeRowId(DocumentId viewRowId){
    return toBuilder().viewRowId(viewRowId).build();
}


}