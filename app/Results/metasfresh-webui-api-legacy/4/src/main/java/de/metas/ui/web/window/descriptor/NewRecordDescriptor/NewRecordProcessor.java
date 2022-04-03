package de.metas.ui.web.window.descriptor.NewRecordDescriptor;
 import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.model.Document;
public interface NewRecordProcessor {


public int processNewRecordDocument(Document document)
;

}