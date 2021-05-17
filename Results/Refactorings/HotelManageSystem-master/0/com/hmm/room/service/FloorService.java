import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmm.room.entity.Floor;
import com.hmm.room.entity.Room;
import com.hmm.room.repository.FloorRepository;
import com.hmm.room.util.RoomState;
import com.hmm.room.util.TreeNode;
@Service
public class FloorService implements IFloorService,com.hmm.room.service.IFloorService{

@Autowired
 private  FloorRepository floorRepository;


@Override
public List<TreeNode> findNodes(Long parentId,String type){
    // 节点list
    List<TreeNode> nodeList = new ArrayList<TreeNode>();
    List<Floor> lists;
    if (parentId == null) {
        // 如果 父id为null，说明是 root 节点,加载出所有楼层子菜单
        lists = floorRepository.findFloorNodes();
    } else {
        lists = floorRepository.findChildNodes(parentId);
    }
    for (Floor f : lists) {
        TreeNode node = new TreeNode();
        node.setId(f.getFloorId());
        node.setText(f.getFloorName());
        if (f.getChildNodes() != null) {
            if (f.getChildNodes().size() > 0) {
                // 置设为父节点
                node.setLeaf(false);
                node.setIconCls("fa-server");
                // 设置二级节点
                // 节点roomlist
                List<TreeNode> roomlist = new ArrayList<TreeNode>();
                for (Room room : f.getChildNodes()) {
                    TreeNode roomnode = new TreeNode();
                    if (type.equals("empty")) {
                        if (room.getState() == RoomState.EMPTY) {
                            roomnode.setText(room.getRoomNo());
                            roomnode.setId(room.getRoomId());
                            roomnode.setLeaf(true);
                            roomnode.setIconCls("fa-bed");
                        } else {
                            continue;
                        }
                    } else if (type.equals("checkIn")) {
                        if (room.getState() != RoomState.EMPTY) {
                            roomnode.setText(room.getRoomNo());
                            roomnode.setId(room.getRoomId());
                            roomnode.setLeaf(true);
                            roomnode.setIconCls("fa-bed");
                        } else {
                            continue;
                        }
                    }
                    roomlist.add(roomnode);
                }
                // 设置好二级节点
                node.setChildren(roomlist);
            } else {
                // 置设为子节点
                node.setLeaf(true);
            }
        }
        // 设置好一级节点
        nodeList.add(node);
    }
    return nodeList;
}


}