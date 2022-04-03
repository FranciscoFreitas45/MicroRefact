package DTO;
 import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.hibernate.annotations.common.util.StringHelper;
public class UKDatabaseMetadata {

 private  Connection connection;

 private  List<UKTableMetaData> tables;

 private  DatabaseMetaData meta;

 public  Properties properties;

 private  String[] TYPES;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public UKDatabaseMetadata(Connection connection) throws SQLException {
    this.connection = connection;
    meta = connection.getMetaData();
}
public List<UKTableMetaData> getTables(){
    return this.tables;
}


public List<UKTableMetaData> loadTables(String name,String schema,String catalog,boolean isQuoted){
    boolean upcase = false;
    try {
        if (properties != null && properties.get("schema") != null && schema == null) {
            schema = properties.get("upcase") != null ? ((String) properties.get("schema")).toUpperCase() : (String) properties.get("schema");
        }
        if (properties != null && properties.get("upcase") != null) {
            upcase = properties.get("upcase") != null && properties.get("upcase").toString().toLowerCase().equals("true");
        }
        UKTableMetaData table = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            if ((isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
                rs = meta.getTables(catalog, schema, name, TYPES);
            } else if ((isQuoted && meta.storesUpperCaseIdentifiers() && meta.storesUpperCaseQuotedIdentifiers()) || (!isQuoted && meta.storesUpperCaseIdentifiers())) {
                rs = meta.getTables(StringHelper.toUpperCase(catalog), StringHelper.toUpperCase(schema), StringHelper.toUpperCase(name), TYPES);
            } else if ((isQuoted && meta.storesLowerCaseQuotedIdentifiers()) || (!isQuoted && meta.storesLowerCaseIdentifiers())) {
                rs = meta.getTables(StringHelper.toLowerCase(catalog), StringHelper.toLowerCase(schema), StringHelper.toLowerCase(name), TYPES);
            } else if (schema != null && schema.equals("hive")) {
                statement = this.connection.createStatement();
                if (properties.get("database") != null) {
                    statement.execute("USE " + properties.get("database"));
                }
                rs = statement.executeQuery("SHOW TABLES");
            } else {
                rs = meta.getTables(catalog, schema, name, TYPES);
            }
            while (rs.next()) {
                String tableName = null;
                if (schema != null && schema.equals("hive")) {
                    tableName = rs.getString("tab_name");
                } else {
                    tableName = rs.getString("TABLE_NAME");
                }
                if (tableName.matches("[\\da-zA-Z_-\u4e00-\u9fa5]+")) {
                    table = new UKTableMetaData(rs, meta, true, upcase, false, schema);
                    tables.add(table);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    } catch (SQLException sqle) {
        throw sqle;
    }
    return tables;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadTables"))

.queryParam("name",name)
.queryParam("schema",schema)
.queryParam("catalog",catalog)
.queryParam("isQuoted",isQuoted)
;
List<UKTableMetaData> aux = restTemplate.getForObject(builder.toUriString(),List<UKTableMetaData>.class);
return aux;
}


public UKTableMetaData loadTable(String name,String schema,String catalog,boolean isQuoted){
    UKTableMetaData table = null;
    boolean upcase = false;
    try {
        if (properties != null && properties.get("schema") != null && schema == null) {
            schema = (String) properties.get("schema");
        }
        if (properties != null && properties.get("upcase") != null) {
            upcase = properties.get("upcase") != null && properties.get("upcase").toString().toLowerCase().equals("true");
        }
        ResultSet rs = null;
        try {
            if ((isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
                rs = meta.getTables(catalog, schema, name, TYPES);
            } else if ((isQuoted && meta.storesUpperCaseQuotedIdentifiers()) || (!isQuoted && meta.storesUpperCaseIdentifiers())) {
                rs = meta.getTables(StringHelper.toUpperCase(catalog), StringHelper.toUpperCase(schema), StringHelper.toUpperCase(name), TYPES);
            } else if ((isQuoted && meta.storesLowerCaseQuotedIdentifiers()) || (!isQuoted && meta.storesLowerCaseIdentifiers())) {
                rs = meta.getTables(StringHelper.toLowerCase(catalog), StringHelper.toLowerCase(schema), StringHelper.toLowerCase(name), TYPES);
            } else {
                rs = meta.getTables(catalog, schema, name, TYPES);
            }
            while (rs.next()) {
                table = new UKTableMetaData(rs, meta, true, upcase, true, schema);
                break;
            }
        } finally {
            if (rs != null)
                rs.close();
        }
    } catch (SQLException sqle) {
        sqle.printStackTrace();
        throw sqle;
    }
    return table;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadTable"))

.queryParam("name",name)
.queryParam("schema",schema)
.queryParam("catalog",catalog)
.queryParam("isQuoted",isQuoted)
;
UKTableMetaData aux = restTemplate.getForObject(builder.toUriString(),UKTableMetaData.class);
return aux;
}


}