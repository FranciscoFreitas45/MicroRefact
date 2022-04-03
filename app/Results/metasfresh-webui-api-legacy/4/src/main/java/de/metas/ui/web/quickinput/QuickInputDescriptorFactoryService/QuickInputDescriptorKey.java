package de.metas.ui.web.quickinput.QuickInputDescriptorFactoryService;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import de.metas.cache.CCache;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.NonNull;
@lombok.Builder
@lombok.Value
public class QuickInputDescriptorKey {

@NonNull
 private DocumentType documentType;

@NonNull
 private DocumentId documentTypeId;

@NonNull
 private Optional<String> includedTableName;

@NonNull
 private DetailId includedTabId;

@NonNull
 private Optional<SOTrx> soTrx;


}