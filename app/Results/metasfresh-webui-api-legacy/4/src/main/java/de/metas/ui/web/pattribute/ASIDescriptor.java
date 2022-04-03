package de.metas.ui.web.pattribute;
 import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class ASIDescriptor {

 private  AttributeSetId attributeSetId;

 private  DocumentEntityDescriptor entityDescriptor;

 private  ASILayout layout;

 private  DocumentPath contextDocumentPath;


}