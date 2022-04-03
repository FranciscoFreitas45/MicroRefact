package de.metas.ui.web.handlingunits.trace;
 import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.document.DocTypeId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU_Trace;
import de.metas.handlingunits.trace.HUTraceEventQuery;
import de.metas.handlingunits.trace.HUTraceEventQuery.EventTimeOperator;
import de.metas.handlingunits.trace.HUTraceEventQuery.RecursionMode;
import de.metas.handlingunits.trace.HUTraceType;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Check;
import de.metas.util.StringUtils;
import lombok.NonNull;
public class HuTraceQueryCreator {

 private  Map<String,BiFunction<HUTraceEventQuery,DocumentFilterParam,HUTraceEventQuery>> FIELD_NAME_2_UPDATE_METHOD;


public HUTraceEventQuery updateTypeFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotNull("Type", query.getType(), query);
    return query.withType(HUTraceType.valueOf(extractString(parameter)));
}


public HUTraceEventQuery updatePpCostCollectorIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueGreaterThanZero("PpCostCollectorId", query.getPpCostCollectorId(), query);
    return query.withPpCostCollectorId(extractInt(parameter));
}


public void errorIfQueryValueGreaterThanZero(String field,int value,HUTraceEventQuery query){
    if (value > 0) {
        final String message = StringUtils.formatMessage("The given HUTraceEventQuery already has {}={}", field, value);
        throw new AdempiereException(message).setParameter("HUTraceEventQuery", query);
    }
}


public HuId extractHuId(DocumentFilterParam parameter){
    return HuId.ofRepoIdOrNull(extractInt(parameter));
}


public HUTraceEventQuery updateShipmentScheduleIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotNull("ShipmentScheduleId", query.getShipmentScheduleId(), query);
    return query.withShipmentScheduleId(ShipmentScheduleId.ofRepoIdOrNull(extractInt(parameter)));
}


public void errorIfQueryValueNotEmpty(String field,Collection<?> value,HUTraceEventQuery query){
    if (!Check.isEmpty(value)) {
        final String message = StringUtils.formatMessage("The given HUTraceEventQuery already has {}={}", field, value);
        throw new AdempiereException(message).setParameter("HUTraceEventQuery", query);
    }
}


public HUTraceEventQuery updateTopLevelHuIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotEmpty("TopLevelHuId", query.getTopLevelHuIds(), query);
    return query.withTopLevelHuIds(extractHuIds(parameter));
}


public HUTraceEventQuery updateDocTypeIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    if (query.getDocTypeId().isPresent()) {
        errorIfQueryValueNotNull("DocTypeId", query.getDocTypeId().orElse(null), query);
    }
    return query.withDocTypeId(DocTypeId.optionalOfRepoId(extractInt(parameter)));
}


public void errorfIfNotEqualsOperator(DocumentFilterParam parameter){
    if (!Operator.EQUAL.equals(parameter.getOperator())) {
        final String message = StringUtils.formatMessage("The given DocumentFilterParam needs to have an EQUAL operator, but has {}", parameter.getOperator());
        throw new AdempiereException(message).setParameter("DocumentFilterParam", parameter);
    }
}


public BiFunction<HUTraceEventQuery,DocumentFilterParam,HUTraceEventQuery> getUpdateMethodForParameterOrThrowException(DocumentFilterParam parameter){
    final String paramName = parameter.getFieldName();
    final // 
    BiFunction<HUTraceEventQuery, DocumentFilterParam, HUTraceEventQuery> queryUpdateFunction = FIELD_NAME_2_UPDATE_METHOD.get(paramName);
    if (queryUpdateFunction == null) {
        final String message = StringUtils.formatMessage("The given filterparam has an unexpected fieldName={}", paramName);
        throw new AdempiereException(message).setParameter("documentFilterParam", parameter);
    }
    return queryUpdateFunction;
}


public HUTraceEventQuery updateHuTrxLineIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueGreaterThanZero("TopLevelHuId", query.getHuTrxLineId(), query);
    return query.withHuTrxLineId(extractInt(parameter));
}


public HUTraceEventQuery createTraceQueryFromDocumentFilter(DocumentFilter documentFilter){
    try {
        return createTraceQueryFromDocumentFilter0(documentFilter);
    } catch (final AdempiereException e) {
        throw e.appendParametersToMessage().setParameter("documentFilter", documentFilter);
    }
}


public void errorIfQueryValueNotNull(String field,Object value,HUTraceEventQuery query){
    if (value != null) {
        final String message = StringUtils.formatMessage("The given HUTraceEventQuery already has {}={}", field, value);
        throw new AdempiereException(message).setParameter("HUTraceEventQuery", query);
    }
}


public HUTraceEventQuery updateMovementIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueGreaterThanZero("MovementId", query.getMovementId(), query);
    return query.withMovementId(extractInt(parameter));
}


public int extractInt(DocumentFilterParam parameter){
    final Object value = Check.assumeNotNull(parameter.getValue(), "Given paramter may not have a null value; parameter={}", parameter);
    if (value instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) value;
        return lookupValue.getIdAsInt();
    } else if (value instanceof Integer) {
        return (Integer) value;
    } else {
        throw new AdempiereException("Unable to extract an integer ID from parameter=" + parameter);
    }
}


public HUTraceEventQuery updateVhuSourceIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotNull("VhuSourceId", query.getVhuSourceId(), query);
    return query.withVhuSourceId(extractHuId(parameter));
}


public HUTraceEventQuery updateQueryFromParams(HUTraceEventQuery query,DocumentFilterParam parameter){
    final BiFunction<HUTraceEventQuery, DocumentFilterParam, HUTraceEventQuery> queryUpdateFunction = getUpdateMethodForParameterOrThrowException(parameter);
    return queryUpdateFunction.apply(query, parameter);
}


public HUTraceEventQuery updateHuTraceIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    if (query.getHuTraceEventId().isPresent()) {
        errorIfQueryValueGreaterThanZero("HuTraceEventId", query.getHuTraceEventId().getAsInt(), query);
    }
    return query.withHuTraceEventId(OptionalInt.of(extractInt(parameter)));
}


public HUTraceEventQuery updatePpOrderIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueGreaterThanZero("PpOrderId", query.getPpOrderId(), query);
    return query.withPpOrderId(extractInt(parameter));
}


public HUTraceEventQuery updateProductIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorfIfNotEqualsOperator(parameter);
    errorIfQueryValueNotNull("ProductId", query.getProductId(), query);
    return query.withProductId(ProductId.ofRepoIdOrNull(extractInt(parameter)));
}


public HUTraceEventQuery updateOrgIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotNull("OrgId", query.getOrgId(), query);
    return query.withOrgId(OrgId.ofRepoIdOrNull(extractInt(parameter)));
}


public ImmutableSet<HuId> extractHuIds(DocumentFilterParam parameter){
    final HuId huId = extractHuId(parameter);
    return huId != null ? ImmutableSet.of(huId) : ImmutableSet.of();
}


public String extractString(DocumentFilterParam parameter){
    final Object value = Check.assumeNotNull(parameter.getValue(), "Given paramter may not have a null value; parameter={}", parameter);
    if (value instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) value;
        return lookupValue.getIdAsString();
    } else if (value instanceof String) {
        return (String) value;
    }
    Check.fail("Unable to extract a String from parameter={}", parameter);
    // not reached
    return null;
}


public HUTraceEventQuery updateInOutIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueGreaterThanZero("InOutId", query.getInOutId(), query);
    return query.withInOutId(extractInt(parameter));
}


public HUTraceEventQuery updateVhuIdFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotEmpty("VhuId", query.getVhuIds(), query);
    return query.withVhuIds(extractHuIds(parameter));
}


public HUTraceEventQuery updateDocStatusFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotEmpty("DocStatus", query.getDocStatus(), query);
    return query.withDocStatus(extractString(parameter));
}


public HUTraceEventQuery updateVhuStatusFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotEmpty("VhuStatus", query.getVhuStatus(), query);
    return query.withVhuStatus(extractString(parameter));
}


public HUTraceEventQuery createTraceQueryFromDocumentFilter0(DocumentFilter documentFilter){
    HUTraceEventQuery query = HUTraceEventQuery.builder().recursionMode(RecursionMode.BOTH).build();
    for (final DocumentFilterParam filterParam : documentFilter.getParameters()) {
        query = updateQueryFromParams(query, filterParam);
    }
    return query;
}


public HUTraceEventQuery updateEventTimeFromParameter(HUTraceEventQuery query,DocumentFilterParam parameter){
    errorIfQueryValueNotNull("EventTime", query.getEventTime(), query);
    switch(parameter.getOperator()) {
        case EQUAL:
            final Instant value = parameter.getValueAsInstant();
            return query.withEventTimeOperator(EventTimeOperator.EQUAL).withEventTime(value);
        case BETWEEN:
            final Instant valueFrom = parameter.getValueAsInstant();
            final Instant valueTo = parameter.getValueToAsInstant();
            return query.withEventTimeOperator(EventTimeOperator.BETWEEN).withEventTime(valueFrom).withEventTimeTo(valueTo);
        default:
            throw new AdempiereException("Unexpected operator=" + parameter.getOperator() + " in parameter").appendParametersToMessage().setParameter("HUTraceEventQuery", query).setParameter("DocumentFilterParam", parameter);
    }
}


}