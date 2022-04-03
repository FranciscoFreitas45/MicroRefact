package switchtwentytwenty.project.dto.outdto;
 import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import java.util.ArrayList;
import java.util.List;
public class StandardCategoryOutDTOMapper {

/**
 * Sole Constructor.
 */
private StandardCategoryOutDTOMapper() {
}
public CategoryOutDTO toOutCategoryDTO(StandardCategoryOutDTO standardCategoryDTO){
    return new CategoryOutDTO(standardCategoryDTO.getId(), standardCategoryDTO.getDesignation(), standardCategoryDTO.getParentID());
}


public StandardCategoryOutDTO toDTO(Category standard){
    String designation = standard.getDesignation().toString();
    String id = standard.getID().toString();
    if (standard.getParentID() != null) {
        String parentID = standard.getParentID().toString();
        return new StandardCategoryOutDTO(designation, id, parentID);
    }
    return new StandardCategoryOutDTO(designation, id, null);
}


public List<CategoryOutDTO> toOutCategoryDTOList(List<StandardCategoryOutDTO> standardCategoryDTOList){
    List<CategoryOutDTO> dtoList = new ArrayList<>();
    for (StandardCategoryOutDTO standardCategoryOutDTO : standardCategoryDTOList) {
        dtoList.add(toOutCategoryDTO(standardCategoryOutDTO));
    }
    return dtoList;
}


public List<StandardCategoryOutDTO> toOutStandardCategoryDTOList(List<StandardCategoryDTO> standardList){
    List<StandardCategoryOutDTO> dtoList = new ArrayList<>();
    for (StandardCategoryDTO standardCategoryDTO : standardList) {
        StandardCategoryOutDTO standardCategoryOutDTO = new StandardCategoryOutDTO(standardCategoryDTO.getDesignation(), standardCategoryDTO.getId(), standardCategoryDTO.getParentID());
        dtoList.add(standardCategoryOutDTO);
    }
    return dtoList;
}


}