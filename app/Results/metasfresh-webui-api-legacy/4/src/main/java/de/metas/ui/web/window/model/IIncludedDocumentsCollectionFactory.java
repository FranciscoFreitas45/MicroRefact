package de.metas.ui.web.window.model;
 import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
@FunctionalInterface
public interface IIncludedDocumentsCollectionFactory {


public IIncludedDocumentsCollection createIncludedDocumentsCollection(Document parentDocument,DocumentEntityDescriptor entityDescriptor)
;

}