package de.metas.ui.web.window.descriptor.sql;
 import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Set;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.exceptions.ExpressionCompileException;
import org.adempiere.ad.expression.exceptions.ExpressionEvaluationException;
import org.adempiere.ad.expression.json.JsonStringExpressionSerializer;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.DBException;
import org.compiere.util.CtxName;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Evaluatee;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.util.Check;
import lombok.NonNull;
@JsonSerialize(using = JsonStringExpressionSerializer.class)
public class SqlDefaultValueExpression implements IExpression<V>{

 private  Logger logger;

 private  IStringExpression stringExpression;

 private  Class<V> valueClass;

 private  V noResultValue;

 private  ValueLoader<V> valueRetriever;


@Override
public Class<V> getValueClass(){
    return valueClass;
}


public Instant retrieveInstant(ResultSet rs){
    return TimeUtil.asInstant(rs.getTimestamp(1));
}


public LocalTime retrieveLocalTime(ResultSet rs){
    return TimeUtil.asLocalTime(rs.getTimestamp(1));
}


public LocalDate retrieveLocalDate(ResultSet rs){
    return TimeUtil.asLocalDate(rs.getTimestamp(1));
}


public ZonedDateTime retrieveZonedDateTime(ResultSet rs){
    return TimeUtil.asZonedDateTime(rs.getTimestamp(1));
}


@Override
public String getExpressionString(){
    return stringExpression.getExpressionString();
}


@Override
public String getFormatedExpressionString(){
    return stringExpression.getFormatedExpressionString();
}


public SqlDefaultValueExpression<?> of(IStringExpression stringExpression,Class<V> valueClass){
    if (Integer.class.equals(valueClass) || IntegerLookupValue.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, Integer.class, (rs) -> rs.getInt(1));
    } else if (BigDecimal.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, BigDecimal.class, (rs) -> rs.getBigDecimal(1));
    } else if (Boolean.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, Boolean.class, (rs) -> {
            final String valueStr = rs.getString(1);
            return DisplayType.toBoolean(valueStr, null);
        });
    } else // 
    if (java.util.Date.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, java.util.Date.class, rs -> rs.getTimestamp(1));
    } else if (Timestamp.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, Timestamp.class, rs -> rs.getTimestamp(1));
    } else if (ZonedDateTime.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, ZonedDateTime.class, rs -> retrieveZonedDateTime(rs));
    } else if (Instant.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, Instant.class, rs -> retrieveInstant(rs));
    } else if (LocalDate.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, LocalDate.class, rs -> retrieveLocalDate(rs));
    } else if (LocalTime.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, LocalTime.class, rs -> retrieveLocalTime(rs));
    } else // 
    if (String.class.equals(valueClass) || StringLookupValue.class.equals(valueClass)) {
        return new SqlDefaultValueExpression<>(stringExpression, String.class, (rs) -> rs.getString(1));
    }
    throw new ExpressionCompileException("Value type " + valueClass + " is not supported by " + SqlDefaultValueExpression.class);
}


public V get(ResultSet rs)


@Override
public boolean isNoResult(Object result){
    return result == null || result == noResultValue;
}


@Override
public Set<CtxName> getParameters(){
    return stringExpression.getParameters();
}


@Override
public Set<String> getParameterNames(){
    return stringExpression.getParameterNames();
}


@Override
public String toString(){
    return stringExpression.toString();
}


@Override
public boolean isNullExpression(){
    return false;
}


@Override
public V evaluate(Evaluatee ctx,OnVariableNotFound onVariableNotFound){
    final String sql = stringExpression.evaluate(ctx, onVariableNotFound);
    if (Check.isEmpty(sql, true)) {
        logger.warn("Expression " + stringExpression + " was evaluated to empty string. Returning no result: {}", noResultValue);
        return noResultValue;
    }
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, ITrx.TRXNAME_None);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            final V value = valueRetriever.get(rs);
            return value;
        } else {
            if (onVariableNotFound == OnVariableNotFound.Fail) {
                throw new ExpressionEvaluationException("Got no result for " + this + " (SQL: " + sql + ")");
            }
            logger.warn("Got no result for {} (SQL: {})", this, sql);
            return noResultValue;
        }
    } catch (final SQLException e) {
        throw new DBException(e, sql);
    } finally {
        DB.close(rs, pstmt);
    }
}


}