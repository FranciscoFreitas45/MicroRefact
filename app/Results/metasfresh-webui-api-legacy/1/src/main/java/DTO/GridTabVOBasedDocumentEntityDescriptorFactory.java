package DTO;
 import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import org.compiere.Adempiere;
import org.compiere.model.GridFieldDefaultFilterDescriptor;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTabVO;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Tab;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.X_AD_UI_ElementField;
import org.compiere.util.DisplayType;
import org.compiere.util.Evaluatees;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import de.metas.adempiere.service.IColumnBL;
import de.metas.elasticsearch.indexer.IESModelIndexer;
import de.metas.elasticsearch.indexer.IESModelIndexersRegistry;
import de.metas.i18n.IModelTranslationMap;
import de.metas.logging.LogManager;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.session.WebRestApiContextProvider;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor.ButtonFieldActionType;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDefaultFilterDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Builder;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.FullTextSearchLookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentFieldValueProvider;
import de.metas.ui.web.window.model.lookup.LabelsLookup;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.ui.web.window.model.lookup.TimeZoneLookupDescriptor;
import de.metas.ui.web.window.model.sql.SqlDocumentsRepository;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class GridTabVOBasedDocumentEntityDescriptorFactory {

 private  Logger logger;

 private  IColumnBL adColumnBL;

 private  DocumentsRepository documentsRepository;

 private  Map<Integer,String> _adFieldId2columnName;

 private  DefaultValueExpressionsFactory defaultValueExpressionsFactory;

 private  SpecialDocumentFieldsCollector _specialFieldsCollector;

 private  DocumentEntityDescriptor.Builder _documentEntityBuilder;


public Map<Characteristic,DocumentFieldDescriptor.Builder> getSpecialField_DocSatusAndDocAction(){
    return _specialFieldsCollector == null ? null : _specialFieldsCollector.getDocStatusAndDocAction();
}


public DocumentFieldDescriptor.Builder getSpecialField_DocumentSummary(){
    return _specialFieldsCollector == null ? null : _specialFieldsCollector.getDocumentSummary();
}


public String getLabelsFieldName(I_AD_UI_Element uiElement){
    return "Labels_" + uiElement.getAD_UI_Element_ID();
}


public ILogicExpression getTabDisplayLogic(){
    return documentEntity().getDisplayLogic();
}


}