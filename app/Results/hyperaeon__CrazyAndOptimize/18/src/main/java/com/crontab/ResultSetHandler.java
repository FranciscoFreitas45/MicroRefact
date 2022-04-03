package com.crontab;
 import java.sql.ResultSet;
import java.sql.SQLException;
public interface ResultSetHandler {


public T handle(ResultSet rs)
;

}