package graph.entities;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Optional;

public class MyMethodDTO {

    @Expose
    private String name;
    @Expose
    private int lineBegin;
    @Expose
    private int lineEnd;

    @Expose
    private List<String> annotations;
    @Expose
    private List<String> returnDataType;
    @Expose
    private List<String> identifier;
    @Expose
    private List<VariableOFMethodDTO> parametersDataType;
    @Expose
    private List<VariableOFMethodDTO> variables;
    @Expose
    private String  body;
    @Expose
    private List<MethodInvocationDTO> methodInvocations;
    @Expose
    private List<String> exception;




    public MyMethodDTO(String name, int lineBegin, int lineEnd,List<String> returnDataType,List<String> identifier,List<VariableOFMethodDTO> parameterDataType,List<VariableOFMethodDTO> variables ,List<MethodInvocationDTO> methodInvocations,List<String> annotations,String body,List<String> exception) {
        this.name = name;
        this.lineBegin = lineBegin;
        this.lineEnd = lineEnd;
        this.returnDataType = returnDataType;
        this.identifier = identifier;
        this.parametersDataType = parameterDataType;
        this.variables = variables;
        this.methodInvocations =methodInvocations;
        this.body = body;
        this.annotations = annotations;
        this.exception = exception;
    }
    public MyMethodDTO(String name, int lineBegin, int lineEnd,List<String> returnDataType,List<String> identifier,List<VariableOFMethodDTO> parameterDataType,List<VariableOFMethodDTO> variables ,List<MethodInvocationDTO> methodInvocations,List<String> annotations, List<String> exception) {
        this.name = name;
        this.lineBegin = lineBegin;
        this.lineEnd = lineEnd;
        this.returnDataType = returnDataType;
        this.identifier=identifier;
        this.parametersDataType = parameterDataType;
        this.variables = variables;
        this.methodInvocations =methodInvocations;
        this.body = "";
        this.annotations = annotations;
        this.exception = exception;
    }




    public int getLineBegin() {
        return lineBegin;
    }

    public void setLineBegin(int lineBegin) {
        this.lineBegin = lineBegin;
    }

    public int getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(int lineEnd) {
        this.lineEnd = lineEnd;
    }

    public List<VariableOFMethodDTO> getVariables() {
        return variables;
    }

    public void setVariables(List<VariableOFMethodDTO> variables) {
        this.variables = variables;
    }
}