package graph.entities;

import com.google.gson.annotations.Expose;

import java.util.List;

public class VariableOFMethodDTO {


    @Expose
    private String type;
    @Expose
    private String variable;
    @Expose
    private String qualifedType = "";


    public VariableOFMethodDTO(String type, String variable){
        this.type = type;
        this.variable = variable;
    }

    public VariableOFMethodDTO(String type, String variable,String qualifedType){
        this.type = type;
        this.variable = variable;
        this.qualifedType = qualifedType;
    }



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

    public String getQualifedType() {
        return qualifedType;
    }

    public void setQualifedType(String qualifedType) {
        this.qualifedType = qualifedType;
    }

    @Override
    public String toString() {
        return "VariableOFMethodDTO{" +
                "type='" + type + '\'' +
                ", variable='" + variable + '\'' +
                ", qualifedType='" + qualifedType + '\'' +
                '}';
    }
}
