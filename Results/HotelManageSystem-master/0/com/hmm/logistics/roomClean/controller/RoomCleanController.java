import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.logistics.roomClean.dto.FloorVoRoomVoRoomCleanDTO;
import com.hmm.logistics.roomClean.entity.FloorVoRoomVoRoomClean;
import com.hmm.logistics.roomClean.entity.RoomClean;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
import com.hmm.logistics.roomClean.repository.RoomCleanRecordRepository;
import com.hmm.logistics.roomClean.service.IRoomCleanService;
import com.hmm.logistics.roomClean.util.RoomCleanState;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.OutDetailed;
import com.hmm.logistics.stock.entity.OutStorage;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.repository.OutDetailedRepository;
import com.hmm.logistics.stock.repository.OutStorageRepository;
import com.hmm.logistics.stock.service.IStockService;
import com.hmm.logistics.stock.util.YesOrNoSend;
import com.hmm.room.entity.Room;
import com.hmm.room.repository.RoomRepository;
import com.hmm.room.util.RoomState;
@RestController
@RequestMapping("roomClean")
public class RoomCleanController {

@Autowired
 private  IRoomCleanService roomCleanService;

@Autowired
 private  RoomRepository roomRepository;

@Autowired
 private  RoomCleanRecordRepository roomCleanRecordService;

@Autowired
 private  EmployeeService employeeService;

@Autowired
 private  OutStorageRepository OutStorageService;

@Autowired
 private  OutDetailedRepository OutDetailedService;

@Autowired
 private  IStockService stockService;


@RequestMapping("/changeRoomState")
@ResponseBody
public String changeRoomState(String roomNo,String selectValue,String remark){
    roomCleanService.changeRoomState(roomNo, selectValue, remark);
    System.out.println(roomNo + " " + selectValue);
    if (remark != null) {
        System.out.println(remark);
    }
    return RoomState.NEEDCLEAN.toString();
}


@RequestMapping("/dailyNecessarySupplement")
@ResponseBody
public String dailyNecessarySupplement(String roomNo,String dailyTagData){
    System.out.println(roomNo + " " + dailyTagData);
    roomCleanService.dailyNecessary(roomNo, dailyTagData);
    return RoomState.NEED_DAILY_NECESSITIES.toString();
}


@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
public ExtAjaxResponse update(Long myId,RoomClean dto,HttpSession session){
    try {
        System.out.println(myId);
        RoomClean entity = roomCleanService.findById(myId);
        if (entity != null) {
            BeanUtils.copyProperties(dto, entity);
            roomCleanService.aloadRoomCleantoDTO(entity);
            // 更改状态
            roomCleanService.save(entity);
        }
        ;
        if (dto.getRoomCleanState() == RoomCleanState.WAITING1 || dto.getRoomCleanState() == RoomCleanState.WAITING2) {
            if (dto.getRoomCleanState() == RoomCleanState.WAITING1) {
                Room room = entity.getRoom();
                room.setState(RoomState.EMPTY);
                roomRepository.save(room);
            }
            if (dto.getRoomCleanState() == RoomCleanState.WAITING2) {
                Room room = entity.getRoom();
                room.setState(RoomState.CHECKIN);
                roomRepository.save(room);
            }
            List<RoomCleanRecord> roomCleanRecords = roomCleanRecordService.findByRoomId(entity.getRoom().getRoomId());
            for (RoomCleanRecord roomCleanRecord : roomCleanRecords) {
                if (roomCleanRecord.getRoomWorker() == null) {
                    if (roomCleanRecord.getOutStorage() != null) {
                        List<OutDetailed> outDetaileds = roomCleanRecord.getOutStorage().getOutDetailed();
                        for (OutDetailed outDetailed : outDetaileds) {
                            Stock stock = stockService.findByGoodsNo(outDetailed.getGoodsNo());
                            stock.setAmount(stock.getAmount() - outDetailed.getAmount());
                            if ((stock.getAmount() - outDetailed.getAmount() < 10)) {
                                stock.setYesOrNoSend(YesOrNoSend.NO);
                            }
                            stockService.save(stock);
                        }
                    }
                    roomCleanRecord.setRoomDate(new Date());
                    // roomCleanRecord.setRoomWorker(employeeService.findByUserName(SessionUtil.getUserName(session)));
                    Employee employee = employeeService.findByUserName(SessionUtil.getUserName(session));
                    // System.out.println(employee);
                    if (employee != null) {
                        if (roomCleanRecord.getOutStorage() != null) {
                            roomCleanRecord.getOutStorage().setWorker(employee);
                        }
                    }
                    roomCleanRecordService.save(roomCleanRecord);
                }
            }
        }
        return new ExtAjaxResponse(true, "更新成功！");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(true, "更新失败！");
    }
}


@GetMapping
public Page<FloorVoRoomVoRoomClean> getPages(FloorVoRoomVoRoomCleanDTO floorVoRoomVoRoomCleanDTO,ExtjsPageRequest pageRequest){
    // roomCleanService.saveAllFloorVoRoomVoRoomCleanDTO();//更新数据
    // roomCleanService.aloadRoomCleantoDTO();
    return roomCleanService.findAllFloorVoRoomVoRoomCleanDTO(FloorVoRoomVoRoomCleanDTO.getWhereClause(floorVoRoomVoRoomCleanDTO), pageRequest);
}


}