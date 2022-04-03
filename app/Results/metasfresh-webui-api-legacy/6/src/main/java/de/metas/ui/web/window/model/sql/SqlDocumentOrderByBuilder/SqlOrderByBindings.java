package de.metas.ui.web.window.model.sql.SqlDocumentOrderByBuilder;
 import java.util.Optional;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
@FunctionalInterface
public interface SqlOrderByBindings {


public SqlOrderByValue getFieldOrderBy(String fieldName)
;

}