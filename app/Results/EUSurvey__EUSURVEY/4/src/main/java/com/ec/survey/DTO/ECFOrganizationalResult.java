package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFOrganizationalResult {

 private  List<ECFOrganizationalCompetencyResult> competencyResults;

 private  List<TypeUUIDAndName> competenciesTypes;


public List<ECFOrganizationalCompetencyResult> getCompetencyResults(){
    return competencyResults;
}


public List<TypeUUIDAndName> getCompetenciesTypes(){
    return competenciesTypes;
}


}