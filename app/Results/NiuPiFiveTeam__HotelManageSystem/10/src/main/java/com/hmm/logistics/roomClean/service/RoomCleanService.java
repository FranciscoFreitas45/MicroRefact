package com.hmm.logistics.roomClean.service;
 import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.service.EmployeeService;
import com.hmm.logistics.roomClean.entity.FloorVoRoomVoRoomClean;
import com.hmm.logistics.roomClean.entity.RoomClean;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
import com.hmm.logistics.roomClean.repository.FloorVoRoomVoRoomCleanRepository;
import com.hmm.logistics.roomClean.repository.RoomCleanRecordRepository;
import com.hmm.logistics.roomClean.repository.RoomCleanRepository;
import com.hmm.logistics.roomClean.util.RoomCleanState;
import com.hmm.logistics.stock.entity.OutDetailed;
import com.hmm.logistics.stock.entity.OutStorage;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.repository.OutDetailedRepository;
import com.hmm.logistics.stock.repository.OutStorageRepository;
import com.hmm.logistics.stock.repository.StockRepository;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.room.entity.Floor;
import com.hmm.room.entity.Room;
import com.hmm.room.repository.RoomRepository;
import com.hmm.room.util.RoomState;
import com.hmm.room.util.RoomType;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.OutStorageRepository;
import com.hmm.Interface.OutDetailedRepository;
import com.hmm.Interface.StockRepository;
import com.hmm.DTO.OutStorage;
@Service
@Transactional
public class RoomCleanService implements IRoomCleanService{

@Autowired
 private  RoomCleanRepository roomCleanRepository;

@Autowired
 private  RoomRepository roomRepository;

@Autowired
 private  FloorVoRoomVoRoomCleanRepository floorVoRoomVoRoomCleanDTORepository;

@Autowired
 private  IRoomCleanService roomCleanService;

@Autowired
 private  RoomCleanRecordRepository roomCleanRecordService;

@Autowired
 private  EmployeeService employeeService;

@Autowired
 private  OutStorageRepository OutStorageService;

@Autowired
 private  OutDetailedRepository OutDetailedService;

@Autowired
 private  StockRepository StockService;


@Override
public Page<FloorVoRoomVoRoomClean> findAllFloorVoRoomVoRoomCleanDTO(Specification<FloorVoRoomVoRoomClean> spec,ExtjsPageRequest pageRequest){
    // TODO Auto-generated method stub
    return floorVoRoomVoRoomCleanDTORepository.findAll(spec, pageRequest.getPageable());
}


@Override
public void dailyNecessary(String roomNo,String dailyTagData){
    // TODO Auto-generated method stub
    RoomClean roomClean = roomCleanService.findByRoomId(roomRepository.findRoomByRoomNo(roomNo).getRoomId());
    roomClean.setRoomCleanState(RoomCleanState.SERVICE);
    // ??????roomClean????????????????????????
    roomCleanService.save(roomClean);
    Room room = roomRepository.findRoomByRoomNo(roomNo);
    room.setState(RoomState.NEEDCLEAN);
    roomRepository.save(room);
    RoomCleanRecord roomCleanRecord = new RoomCleanRecord();
    // ???????????????
    roomCleanRecord.setRoom(roomRepository.findRoomByRoomNo(roomNo));
    roomCleanRecord.setRoomHandle("????????????");
    roomCleanRecord.setRoomOther("???");
    // b
    OutStorage outStorage = new OutStorage();
    outStorage.setReason("????????????");
    outStorage.setRoomCleanRecord(roomCleanRecord);
    outStorage.setRoomNo(roomNo);
    // OutStorageService.save(outStorage);
    roomCleanRecord.setOutStorage(outStorage);
    String rrr = "???:";
    JSONArray list = new JSONArray(dailyTagData);
    for (int i = 0; i < list.length(); i++) {
        // c
        JSONObject jsonObject = (JSONObject) list.get(i);
        OutDetailed outDetailed = new OutDetailed();
        outDetailed.setAmount((float) jsonObject.getDouble("number"));
        outDetailed.setGoodsName(jsonObject.getString("show"));
        outDetailed.setGoodsNo(jsonObject.getString("name"));
        outDetailed.setOutStorage(outStorage);
        outStorage.getOutDetailed().add(outDetailed);
        rrr = rrr + jsonObject.getString("show") + (int) jsonObject.getDouble("number") + " ";
    }
    FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = floorVoRoomVoRoomCleanDTORepository.findById(roomClean.getRoomCleanId()).get();
    floorVoRoomVoRoomClean.setRoomCleanState("????????????");
    floorVoRoomVoRoomClean.setRoomOther(rrr);
    floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
    // OutStorageService.save(outStorage);
    roomCleanRecordService.save(roomCleanRecord);
}


@Override
public void saveAllFloorVoRoomVoRoomCleanDTO(){
    // ?????????????????????
    List<FloorVoRoomVoRoomClean> floorVoRoomVoRoomCleanDTO = new ArrayList<FloorVoRoomVoRoomClean>();
    List<Room> rooms = (List<Room>) roomRepository.findAll();
    for (Room room : rooms) {
        // System.out.println(room.getFloorNode().getFloorName());
        FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = new FloorVoRoomVoRoomClean();
        // ??????????????????
        Floor floor = room.getFloorNode();
        // ??????????????????????????????
        RoomClean roomClean = roomCleanRepository.findByRoomId(room.getRoomId());
        floorVoRoomVoRoomClean.setFloorName(floor.getFloorName());
        floorVoRoomVoRoomClean.setRoomNo(room.getRoomNo());
        floorVoRoomVoRoomClean.setRoomOther(roomClean.getRoomOther());
        // start ???????????????
        floorVoRoomVoRoomClean.setId(roomClean.getRoomCleanId());
        // end
        if (room.getType() == RoomType.DOUBLEROOM) {
            floorVoRoomVoRoomClean.setType("?????????");
        } else if (room.getType() == RoomType.HOURROOM) {
            floorVoRoomVoRoomClean.setType("?????????");
        } else if (room.getType() == RoomType.SINGLEROOM) {
            floorVoRoomVoRoomClean.setType("?????????");
        } else if (room.getType() == RoomType.TRIPLEROOM) {
            floorVoRoomVoRoomClean.setType("?????????");
        }
        if (roomClean.getRoomCleanState() == RoomCleanState.CLEAN) {
            floorVoRoomVoRoomClean.setRoomCleanState("????????????");
            floorVoRoomVoRoomCleanDTO.add(floorVoRoomVoRoomClean);
        } else if (roomClean.getRoomCleanState() == RoomCleanState.CLEANING) {
            floorVoRoomVoRoomClean.setRoomCleanState("?????????");
            floorVoRoomVoRoomCleanDTO.add(floorVoRoomVoRoomClean);
        } else if (roomClean.getRoomCleanState() == RoomCleanState.SERVICE) {
            floorVoRoomVoRoomClean.setRoomCleanState("????????????");
            floorVoRoomVoRoomCleanDTO.add(floorVoRoomVoRoomClean);
        } else if (roomClean.getRoomCleanState() == RoomCleanState.SERVICING) {
            floorVoRoomVoRoomClean.setRoomCleanState("?????????");
            floorVoRoomVoRoomCleanDTO.add(floorVoRoomVoRoomClean);
        } else if (roomClean.getRoomCleanState() == RoomCleanState.WAITING1 || roomClean.getRoomCleanState() == RoomCleanState.WAITING2) {
            floorVoRoomVoRoomClean.setRoomCleanState("?????????");
            floorVoRoomVoRoomCleanDTO.add(floorVoRoomVoRoomClean);
        }
    }
    for (FloorVoRoomVoRoomClean floorVoRoomVoRoomClean : floorVoRoomVoRoomCleanDTO) {
        floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
    }
}


@Override
public void set(){
    List<Room> rooms = (List<Room>) roomRepository.findAll();
    for (Room room : rooms) {
        RoomClean rc = new RoomClean();
        rc.setRoom(room);
        rc.setRoomOther("???");
        rc.setRoomCleanState(RoomCleanState.WAITING1);
        save(rc);
    }
}


@Override
public void changeRoomState(String roomNo,String selectValue,String remark){
    // TODO Auto-generated method stub
    RoomCleanRecord roomCleanRecord = new RoomCleanRecord();
    if (selectValue.equals("roomserviceClean")) {
        // ????????????
        Room room = roomRepository.findRoomByRoomNo(roomNo);
        room.setState(RoomState.NEEDCLEAN);
        roomRepository.save(room);
        RoomClean roomClean = roomCleanService.findByRoomId(roomRepository.findRoomByRoomNo(roomNo).getRoomId());
        roomClean.setRoomCleanState(RoomCleanState.SERVICE);
        roomCleanService.save(roomClean);
        roomCleanRecord.setRoom(roomRepository.findRoomByRoomNo(roomNo));
        roomCleanRecord.setRoomHandle("????????????");
        roomCleanRecord.setRoomOther("???");
        FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = floorVoRoomVoRoomCleanDTORepository.findById(roomClean.getRoomCleanId()).get();
        floorVoRoomVoRoomClean.setRoomCleanState("????????????");
        floorVoRoomVoRoomClean.setRoomOther("????????????");
        ;
        floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
        roomCleanRecordService.save(roomCleanRecord);
    } else if (selectValue.equals("checkoutClean")) {
        // ????????????
        Room room = roomRepository.findRoomByRoomNo(roomNo);
        room.setState(RoomState.NEEDCLEAN);
        roomRepository.save(room);
        RoomClean roomClean = roomCleanService.findByRoomId(roomRepository.findRoomByRoomNo(roomNo).getRoomId());
        roomClean.setRoomCleanState(RoomCleanState.CLEAN);
        if (StringUtils.isNotBlank(remark)) {
            roomClean.setRoomOther(remark);
        } else {
            roomClean.setRoomOther("???");
        }
        roomCleanService.save(roomClean);
        // ???????????????
        roomCleanRecord.setRoom(roomRepository.findRoomByRoomNo(roomNo));
        roomCleanRecord.setRoomHandle("????????????");
        if (StringUtils.isNotBlank(remark)) {
            roomCleanRecord.setRoomOther(remark);
            FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = floorVoRoomVoRoomCleanDTORepository.findById(roomClean.getRoomCleanId()).get();
            floorVoRoomVoRoomClean.setRoomCleanState("????????????");
            floorVoRoomVoRoomClean.setRoomOther(remark);
            floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
        } else {
            roomCleanRecord.setRoomOther("???");
            FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = floorVoRoomVoRoomCleanDTORepository.findById(roomClean.getRoomCleanId()).get();
            floorVoRoomVoRoomClean.setRoomCleanState("????????????");
            floorVoRoomVoRoomClean.setRoomOther("???");
            floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
        }
        List<Stock> stocks = StockService.findByStockType(StockType.COMMODITY);
        OutStorage outStorage = new OutStorage();
        outStorage.setRoomNo(roomNo);
        outStorage.setReason("????????????");
        outStorage.setRoomCleanRecord(roomCleanRecord);
        roomCleanRecord.setOutStorage(outStorage);
        for (Stock stock : stocks) {
            OutDetailed outDetailed = new OutDetailed();
            outDetailed.setAmount(1);
            outDetailed.setGoodsName(stock.getGoodsName());
            outDetailed.setGoodsNo(stock.getGoodsNo());
            outDetailed.setOutStorage(outStorage);
            outStorage.getOutDetailed().add(outDetailed);
        }
        roomCleanRecordService.save(roomCleanRecord);
    }
}


@Override
@Transactional(readOnly = true)
public RoomClean findById(Long id){
    // TODO Auto-generated method stub
    return roomCleanRepository.findById(id).get();
}


@Override
public RoomClean save(RoomClean entity){
    return roomCleanRepository.save(entity);
}


@Override
@Transactional(readOnly = true)
public long count(){
    // TODO Auto-generated method stub
    return roomCleanRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    roomCleanRepository.deleteById(id);
}


@Override
public RoomClean findByRoomId(Long roomId){
    // TODO Auto-generated method stub
    return roomCleanRepository.findByRoomId(roomId);
}


@Override
@Transactional(readOnly = true)
public Page<RoomClean> findAll(Specification<RoomClean> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return roomCleanRepository.findAll(spec, pageable);
}


public void aloadRoomCleantoDTO(RoomClean roomClean){
    FloorVoRoomVoRoomClean floorVoRoomVoRoomClean = floorVoRoomVoRoomCleanDTORepository.findById(roomClean.getRoomCleanId()).get();
    // floorVoRoomVoRoomClean.setRoomCleanState(roomClean.getRoomCleanState());
    if (roomClean.getRoomCleanState() == RoomCleanState.CLEAN) {
        floorVoRoomVoRoomClean.setRoomCleanState("????????????");
    } else if (roomClean.getRoomCleanState() == RoomCleanState.CLEANING) {
        floorVoRoomVoRoomClean.setRoomCleanState("?????????");
    } else if (roomClean.getRoomCleanState() == RoomCleanState.SERVICE) {
        floorVoRoomVoRoomClean.setRoomCleanState("????????????");
    } else if (roomClean.getRoomCleanState() == RoomCleanState.SERVICING) {
        floorVoRoomVoRoomClean.setRoomCleanState("?????????");
    } else if (roomClean.getRoomCleanState() == RoomCleanState.WAITING1 || roomClean.getRoomCleanState() == RoomCleanState.WAITING2) {
        floorVoRoomVoRoomClean.setRoomOther("???");
        floorVoRoomVoRoomClean.setRoomCleanState("?????????");
    }
    floorVoRoomVoRoomCleanDTORepository.save(floorVoRoomVoRoomClean);
}


}