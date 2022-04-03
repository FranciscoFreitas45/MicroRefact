package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.entity.ResourceType;
import java.util.ArrayList;
import java.util.List;
public class DtoUtilMapper {


public ResourceTypeDTO resTypesToResTypesDTO(ResourceType resType){
    return new ResourceTypeDTO(resType.getId(), resType.getTypeName(), resType.getTableName());
}


}