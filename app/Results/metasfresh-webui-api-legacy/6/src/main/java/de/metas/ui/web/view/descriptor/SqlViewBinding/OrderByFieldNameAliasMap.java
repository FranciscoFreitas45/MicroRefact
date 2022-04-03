package de.metas.ui.web.view.descriptor.SqlViewBinding;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.fullTextSearch.FullTextSearchSqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConvertersList;
import de.metas.ui.web.document.geo_location.GeoLocationFilterConverter;
import de.metas.ui.web.view.DefaultViewInvalidationAdvisor;
import de.metas.ui.web.view.IViewInvalidationAdvisor;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewRowCustomizer;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding.SqlViewRowFieldLoader;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
@lombok.Value
public class OrderByFieldNameAliasMap {

 private  ImmutableMap<String,ImmutableList<String>> orderByAliasFieldNames;


public Stream<DocumentQueryOrderBy> flatMapEffectiveFieldNames(DocumentQueryOrderBy orderBy){
    final List<String> aliasFieldNames = orderByAliasFieldNames.get(orderBy.getFieldName());
    if (aliasFieldNames == null || aliasFieldNames.isEmpty()) {
        return Stream.of(orderBy);
    }
    return aliasFieldNames.stream().map(orderBy::copyOverridingFieldName);
}


}