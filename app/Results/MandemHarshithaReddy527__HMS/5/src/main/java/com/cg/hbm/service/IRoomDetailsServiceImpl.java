package com.cg.hbm.service;
 import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IRoomDetailsRepository;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
@Service
@Transactional
public class IRoomDetailsServiceImpl implements IRoomDetailsService{

@Autowired
 private IRoomDetailsRepository rDao;


@Override
public RoomDetails updateRoomDetails(int roomId,RoomDetails roomDetails){
    // TODO Auto-generated method stub
    Optional<RoomDetails> r = rDao.findById(roomId);
    if (r.isEmpty()) {
        throw new RoomDetailsNotFoundException("RoomDetails not found");
    } else
        rDao.save(roomDetails);
    return roomDetails;
}


@Override
public RoomDetails addRoomDetails(RoomDetails roomDetails){
    Optional<RoomDetails> r = rDao.findById(roomDetails.getRoom_id());
    if (r.isEmpty()) {
        return rDao.saveAndFlush(roomDetails);
    } else {
        throw new RoomDetailsNotFoundException("RoomDetails already exists");
    }
}


@Override
public RoomDetails removeRoomDetails(int room_id){
    Optional<RoomDetails> op = rDao.findById(room_id);
    if (op.isPresent()) {
        rDao.deleteById(room_id);
        return op.get();
    } else
        throw new RoomDetailsNotFoundException("RoomDetails with given Id doesn't exist.");
}


@Override
public List<RoomDetails> showAllRoomDetails(){
    List<RoomDetails> r = rDao.findAll();
    if (r.isEmpty()) {
        throw new RoomDetailsNotFoundException("RoomDetails not found");
    }
    return r;
}


@Override
public RoomDetails showRoomDetails(int room_id){
    Optional<RoomDetails> r = rDao.findById(room_id);
    if (r.isEmpty()) {
        throw new RoomDetailsNotFoundException("Given RoomId does not exist");
    }
    return r.get();
}


}