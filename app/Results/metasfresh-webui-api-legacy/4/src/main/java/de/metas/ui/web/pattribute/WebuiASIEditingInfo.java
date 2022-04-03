package de.metas.ui.web.pattribute;
 import org.adempiere.mm.attributes.AttributeId;
import org.adempiere.mm.attributes.AttributeSetId;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.util.ASIEditingInfo;
import org.adempiere.mm.attributes.util.ASIEditingInfo.WindowType;
import org.compiere.model.I_M_Attribute;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.lang.SOTrx;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class WebuiASIEditingInfo {

@NonNull
 private WindowType contextWindowType;

 private DocumentPath contextDocumentPath;

@NonNull
 private AttributeSetId attributeSetId;

 private String attributeSetName;

 private String attributeSetDescription;

@NonNull
 private AttributeSetInstanceId attributeSetInstanceId;

 private ProductId productId;

@NonNull
 private SOTrx soTrx;

 private String callerTableName;

 private int callerAdColumnId;

@NonNull
@Singular
 private ImmutableList<I_M_Attribute> attributes;


public WebuiASIEditingInfo readonlyASI(AttributeSetInstanceId attributeSetInstanceId){
    final ASIEditingInfo info = ASIEditingInfo.builder().type(WindowType.StrictASIAttributes).soTrx(SOTrx.SALES).attributeSetInstanceId(attributeSetInstanceId).build();
    return builder(info).build();
}


public WebuiASIEditingInfo processParameterASI(AttributeSetInstanceId attributeSetInstanceId,DocumentPath documentPath){
    final ASIEditingInfo info = ASIEditingInfo.builder().type(WindowType.ProcessParameter).soTrx(SOTrx.SALES).attributeSetInstanceId(attributeSetInstanceId).build();
    return builder(info).contextDocumentPath(documentPath).build();
}


public WebuiASIEditingInfoBuilder builder(ASIEditingInfo info){
    return new WebuiASIEditingInfoBuilder().contextWindowType(info.getWindowType()).attributeSetId(info.getAttributeSetId()).attributeSetName(info.getM_AttributeSet_Name()).attributeSetDescription(info.getM_AttributeSet_Description()).attributeSetInstanceId(info.getAttributeSetInstanceId() != null ? info.getAttributeSetInstanceId() : AttributeSetInstanceId.NONE).productId(info.getProductId()).soTrx(info.getSOTrx()).callerTableName(info.getCallerTableName()).callerAdColumnId(info.getCallerColumnId()).attributes(info.getAvailableAttributes());
}


public ImmutableSet<AttributeId> getAttributeIds(){
    return attributes.stream().map(a -> AttributeId.ofRepoId(a.getM_Attribute_ID())).collect(ImmutableSet.toImmutableSet());
}


}