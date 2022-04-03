package com.ukefu.util.metadata;
 import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.ukefu.util.TabelSql;
import com.ukefu.webim.web.model.Database;
import DTO.UKDatabaseMetadata;
public class DatabaseMetaDataHandler {


public TabelSql getSQL(Database database,String tablename,int startindex,int len){
    TabelSql tableSQL = null;
    if (!StringUtils.isBlank(database.getDatabasetype())) {
        if ("oracle".equalsIgnoreCase(database.getDatabasetype())) {
            StringBuffer strb = new StringBuffer();
            strb.append("select * from ( select row_.*, rownum rownum_ from ( ").append(tablename);
            {
                strb.append(")");
                strb.append(" row_ where rownum <= ");
                strb.append(startindex + len);
                strb.append(") where rownum_ > ").append(startindex);
                tableSQL = new TabelSql(strb.toString(), false, 1, null, null);
            }
        } else if ("mysql".equalsIgnoreCase(database.getDatabasetype())) {
            StringBuffer strb = new StringBuffer();
            strb.append("select * from ").append(tablename);
            strb.append(" limit ").append(startindex).append(",").append(len);
            tableSQL = new TabelSql(strb.toString(), false, 1, null, null);
        } else if ("postgresql".equalsIgnoreCase(database.getDatabasetype())) {
            StringBuffer strb = new StringBuffer();
            strb.append("select * from ( ").append(tablename).append(") ");
            strb.append("limit ").append(len).append(" offset ").append(startindex);
            tableSQL = new TabelSql(strb.toString(), false, 1, null, null);
        }
    }
    if (tableSQL == null) {
        StringBuffer strb = new StringBuffer();
        strb.append("select * ").append(" from ").append(tablename);
        String sql = strb.toString();
        tableSQL = new TabelSql(sql, false, 1, null, null);
    }
    return tableSQL;
}


public List<UKTableMetaData> getTables(Connection conn,String tabltableNamePattern){
    List<UKTableMetaData> tables = null;
    {
        UKDatabaseMetadata rivuDatabase = null;
        try {
            rivuDatabase = new UKDatabaseMetadata(conn);
            tables = rivuDatabase.loadTables(tabltableNamePattern, null, null, true);
        } finally {
            if (conn != null)
                conn.close();
        }
    }
    return tables;
}


public UKTableMetaData getTable(Connection conn,String tablename){
    UKTableMetaData rivuTableMetaData = null;
    {
        UKDatabaseMetadata rivuDatabase = null;
        rivuDatabase = new UKDatabaseMetadata(conn);
        rivuTableMetaData = rivuDatabase.loadTable(tablename, null, null, true);
    }
    return rivuTableMetaData;
}


}