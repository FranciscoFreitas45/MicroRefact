package com.crontab;
 import java.util.List;
public class StoredProcedure extends AbstractCallableStatement{


@Override
public void compileInternal(){
    List<SqlParameter> parameters = getDeclaredParameters();
    int parameterCount = 0;
    callString = "{call " + getSql() + "(";
    for (int i = 0; i < parameters.size(); i++) {
        if (parameterCount > 0) {
            callString += ", ";
        }
        if (parameterCount >= 0) {
            callString += "?";
        }
        parameterCount++;
    }
    callString += ")}";
}


}