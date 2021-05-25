package graph.entities;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Optional;

public class InstanceVariableDTO {


    @Expose
    private List<String> annotations;
    @Expose
    private String modifier;
    @Expose
    private List<String> identifier;
    @Expose
    private String type;
    @Expose
    private String variable;
    @Expose
    private int lineBegin;
    @Expose
    private int lineEnd;


    public InstanceVariableDTO(List<String> annotations,String modifier,List<String> identifier,String type, String variable, int lineBegin, int lineEnd){
        this.annotations = annotations;
        this.modifier = modifier;
        this.identifier = identifier;
        this.type = type;
        this.variable = variable;
        this.lineBegin = lineBegin;
        this.lineEnd = lineEnd;
    }





    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }
/*
    public List<String> getModifier() {
        return modifier;
    }

    public void setModifier(List<String> modifier) {
        this.modifier = modifier;
    }
*/
    public String getVariable() {
        return this.variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
