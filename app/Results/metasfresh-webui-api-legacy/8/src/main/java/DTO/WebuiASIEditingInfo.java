package DTO;
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
public class WebuiASIEditingInfo {

 private WindowType contextWindowType;

 private DocumentPath contextDocumentPath;

 private AttributeSetId attributeSetId;

 private String attributeSetName;

 private String attributeSetDescription;

 private AttributeSetInstanceId attributeSetInstanceId;

 private ProductId productId;

 private SOTrx soTrx;

 private String callerTableName;

 private int callerAdColumnId;

 private ImmutableList<I_M_Attribute> attributes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public ImmutableSet<AttributeId> getAttributeIds(){
    return attributes.stream().map(a -> AttributeId.ofRepoId(a.getM_Attribute_ID())).collect(ImmutableSet.toImmutableSet());
}


public WebuiASIEditingInfoBuilder builder(ASIEditingInfo info){
    return new WebuiASIEditingInfoBuilder().contextWindowType(info.getWindowType()).attributeSetId(info.getAttributeSetId()).attributeSetName(info.getM_AttributeSet_Name()).attributeSetDescription(info.getM_AttributeSet_Description()).attributeSetInstanceId(info.getAttributeSetInstanceId() != null ? info.getAttributeSetInstanceId() : AttributeSetInstanceId.NONE).productId(info.getProductId()).soTrx(info.getSOTrx()).callerTableName(info.getCallerTableName()).callerAdColumnId(info.getCallerColumnId()).attributes(info.getAvailableAttributes());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

.queryParam("info",info);
WebuiASIEditingInfoBuilder aux = restTemplate.getForObject(builder.toUriString(),WebuiASIEditingInfoBuilder.class);
return aux;
}


}