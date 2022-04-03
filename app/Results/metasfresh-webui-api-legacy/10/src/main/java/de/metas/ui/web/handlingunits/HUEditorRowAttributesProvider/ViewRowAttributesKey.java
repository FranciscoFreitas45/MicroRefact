package de.metas.ui.web.handlingunits.HUEditorRowAttributesProvider;
 import java.util.concurrent.ConcurrentHashMap;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactoryService;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.product.ProductId;
import de.metas.ui.web.view.IViewRowAttributesProvider;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.util.Services;
import lombok.Builder;
import lombok.Value;
@Value
public class ViewRowAttributesKey {

 private  DocumentId huEditorRowId;

 private  DocumentId huId;


}