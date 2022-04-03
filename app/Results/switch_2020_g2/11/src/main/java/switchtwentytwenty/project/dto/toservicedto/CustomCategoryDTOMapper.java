package switchtwentytwenty.project.dto.toservicedto;
 import switchtwentytwenty.project.dto.indto.CustomCategoryInDTO;
public class CustomCategoryDTOMapper {

/**
 * Sole constructor.
 */
private CustomCategoryDTOMapper() {
}
public CustomCategoryDTO toDTO(CustomCategoryInDTO inDTO,String familyID){
    return new CustomCategoryDTO(inDTO.getDesignation(), inDTO.getParentID(), familyID);
}


}