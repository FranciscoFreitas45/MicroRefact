package io.delivery.service.impl;
 import io.delivery.service.PreparedSQL;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class PreparedSQLImpl implements PreparedSQL{

 private  JdbcTemplate jdbcTemplate;


@Override
public String execute(){
    String query = "UPDATE users " + "SET company_name = ? WHERE company_name<=?";
    jdbcTemplate.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
        ps.setInt(1, 10);
        ps.setInt(2, 20);
        return ps.execute();
    });
    return "Prepared statement is OK";
}


}