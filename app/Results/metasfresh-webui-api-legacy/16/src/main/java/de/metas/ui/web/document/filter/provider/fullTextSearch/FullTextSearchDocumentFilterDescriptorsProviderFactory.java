package de.metas.ui.web.document.filter.provider.fullTextSearch;
 import java.util.Collection;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdTabId;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Component;
import de.metas.elasticsearch.indexer.IESModelIndexer;
import de.metas.elasticsearch.indexer.IESModelIndexersRegistry;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterInlineRenderMode;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsConstants;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProviderFactory;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class FullTextSearchDocumentFilterDescriptorsProviderFactory implements DocumentFilterDescriptorsProviderFactory{

 private  IMsgBL msgBL;

 private  IESModelIndexersRegistry esModelIndexersRegistry;

 private  Client elasticsearchClient;

 private  AdMessageKey MSG_FULL_TEXT_SEARCH_CAPTION;


@Override
public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId_NOTUSED,String tableName,Collection<DocumentFieldDescriptor> fields_NOTUSED){
    if (tableName == null) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    final IESModelIndexer modelIndexer = esModelIndexersRegistry.getFullTextSearchModelIndexer(tableName).orElse(null);
    if (modelIndexer == null) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    final ITranslatableString caption = msgBL.getTranslatableMsgText(MSG_FULL_TEXT_SEARCH_CAPTION);
    final FullTextSearchFilterContext context = createFullTextSearchFilterContext(modelIndexer);
    final DocumentFilterDescriptor filterDescriptor = DocumentFilterDescriptor.builder().setFilterId(FullTextSearchSqlDocumentFilterConverter.FILTER_ID).setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_FULL_TEXT_SEARCH).setDisplayName(caption).setFrequentUsed(true).setInlineRenderMode(DocumentFilterInlineRenderMode.INLINE_PARAMETERS).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(FullTextSearchSqlDocumentFilterConverter.PARAM_SearchText).setDisplayName(caption).setWidgetType(DocumentFieldWidgetType.Text)).addInternalParameter(FullTextSearchSqlDocumentFilterConverter.PARAM_Context, context).build();
    return ImmutableDocumentFilterDescriptorsProvider.of(filterDescriptor);
}


public FullTextSearchFilterContext createFullTextSearchFilterContext(IESModelIndexer modelIndexer){
    return FullTextSearchFilterContext.builder().elasticsearchClient(elasticsearchClient).modelTableName(modelIndexer.getModelTableName()).esIndexName(modelIndexer.getIndexName()).esSearchFieldNames(modelIndexer.getFullTextSearchFieldNames()).build();
}


}