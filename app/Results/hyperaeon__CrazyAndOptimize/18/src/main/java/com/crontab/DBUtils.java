package com.crontab;
 import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
public class DBUtils {

 private  Logger LOG;

/**
 * Default constructor.
 */
private DBUtils() {
}
public Connection getConnection(DataSource dataSource){
    assert dataSource != null;
    return dataSource.getConnection();
}


public void setNull(PreparedStatement ps,int paramIndex,int sqlType){
    ps.setNull(paramIndex, sqlType);
}


public void setValue(PreparedStatement ps,int paramIndex,int sqlType,Object inValue){
    if (sqlType == Types.CLOB) {
        ps.setClob(paramIndex, (Clob) inValue);
    } else if (sqlType == Types.VARCHAR || sqlType == Types.LONGVARCHAR) {
        ps.setString(paramIndex, inValue.toString());
    } else if (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) {
        if (inValue instanceof BigDecimal) {
            ps.setBigDecimal(paramIndex, (BigDecimal) inValue);
        } else {
            ps.setObject(paramIndex, inValue, sqlType);
        }
    } else if (sqlType == Types.DATE) {
        if (inValue instanceof java.util.Date) {
            if (inValue instanceof java.sql.Date) {
                ps.setDate(paramIndex, (java.sql.Date) inValue);
            } else {
                ps.setDate(paramIndex, new java.sql.Date(((java.util.Date) inValue).getTime()));
            }
        } else if (inValue instanceof Calendar) {
            Calendar cal = (Calendar) inValue;
            ps.setDate(paramIndex, new java.sql.Date(cal.getTime().getTime()), cal);
        } else {
            ps.setObject(paramIndex, inValue, Types.DATE);
        }
    } else if (sqlType == Types.TIME) {
        if (inValue instanceof java.util.Date) {
            if (inValue instanceof java.sql.Time) {
                ps.setTime(paramIndex, (java.sql.Time) inValue);
            } else {
                ps.setTime(paramIndex, new java.sql.Time(((java.util.Date) inValue).getTime()));
            }
        } else if (inValue instanceof Calendar) {
            Calendar cal = (Calendar) inValue;
            ps.setTime(paramIndex, new java.sql.Time(cal.getTime().getTime()), cal);
        } else {
            ps.setObject(paramIndex, inValue, Types.TIME);
        }
    } else if (sqlType == Types.TIMESTAMP) {
        if (inValue instanceof java.util.Date) {
            if (inValue instanceof java.sql.Timestamp) {
                ps.setTimestamp(paramIndex, (java.sql.Timestamp) inValue);
            } else {
                ps.setTimestamp(paramIndex, new java.sql.Timestamp(((java.util.Date) inValue).getTime()));
            }
        } else if (inValue instanceof Calendar) {
            Calendar cal = (Calendar) inValue;
            ps.setTimestamp(paramIndex, new java.sql.Timestamp(cal.getTime().getTime()), cal);
        } else {
            ps.setObject(paramIndex, inValue, Types.TIMESTAMP);
        }
    } else {
        // Fall back to generic setObject call with SQL type specified.
        ps.setObject(paramIndex, inValue, sqlType);
    }
}


public void closeQuietly(Statement stmt){
    try {
        close(stmt);
    } catch (SQLException e) {
        LOG.info("Close the connection with exception: ", e);
        LOG.warn("Close the connection with exception:" + e.getMessage());
    }
}


public void close(Statement stmt){
    if (stmt != null) {
        stmt.close();
    }
}


public void setParameterValue(PreparedStatement ps,int paramIndex,int sqlType,Object inValue){
    if (inValue == null) {
        setNull(ps, paramIndex, sqlType);
    } else {
        setValue(ps, paramIndex, sqlType, inValue);
    }
}


}