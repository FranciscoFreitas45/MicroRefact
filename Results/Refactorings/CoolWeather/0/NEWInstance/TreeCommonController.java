import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class TreeCommonController {

 private TreeCommon treecommon;


@GetMapping
("/TreeStructureUtil")
public List<QmsOrganizationInfoLeftDTO> TreeStructureUtil(@RequestParam(name = "qmsOrganizationInfoDTOList") List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOList,@RequestParam(name = "parentNodeList") List<QmsOrganizationInfoDTO> parentNodeList){
  return treecommon.TreeStructureUtil(qmsOrganizationInfoDTOList,parentNodeList);
}


}