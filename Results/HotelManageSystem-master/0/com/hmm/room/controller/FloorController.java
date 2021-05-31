import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hmm.room.service.IFloorService;
import com.hmm.room.util.TreeNode;
@Controller
@RequestMapping("/floor")
public class FloorController {

@Autowired
 private  IFloorService floorService;


@RequestMapping("/findNodes")
@ResponseBody
public List<TreeNode> findNodesByParentId(String node,String type){
    if (node.equals("root")) {
        return floorService.findNodes(null, type);
    } else {
        return floorService.findNodes(Long.parseLong(node), type);
    }
}


}