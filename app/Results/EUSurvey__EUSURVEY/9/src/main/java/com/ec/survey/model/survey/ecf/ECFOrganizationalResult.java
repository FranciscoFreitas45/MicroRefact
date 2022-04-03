package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFOrganizationalResult {

@JsonProperty("competencyResults")
 private  List<ECFOrganizationalCompetencyResult> competencyResults;

@JsonProperty("competenciesTypes")
 private  List<TypeUUIDAndName> competenciesTypes;


public void addCompetencyResult(ECFOrganizationalCompetencyResult competencyResult){
    this.competencyResults.add(competencyResult);
}


public void setCompetencyResults(List<ECFOrganizationalCompetencyResult> competencyResults){
    this.competencyResults = competencyResults;
}


public List<ECFOrganizationalCompetencyResult> getCompetencyResults(){
    return competencyResults;
}


public List<TypeUUIDAndName> getCompetenciesTypes(){
    return competenciesTypes;
}


public void setCompetenciesTypes(List<TypeUUIDAndName> competenciesTypes){
    this.competenciesTypes = competenciesTypes;
}


@Override
public String toString(){
    return "ECFOrganizationalResult [competenciesTypes=" + competenciesTypes + ", competencyResults=" + competencyResults + "]";
}


public void addCompetenciesType(TypeUUIDAndName competenciesTypesName){
    this.competenciesTypes.add(competenciesTypesName);
}


}