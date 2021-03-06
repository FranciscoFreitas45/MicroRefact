import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.guest.dto.GuestDto;
import com.hmm.guest.dto.GuestInfoDto;
import com.hmm.guest.entity.Guest;
import com.hmm.guest.service.IGuestService;
import com.hmm.guest.util.Gender;
import com.hmm.guest.util.GuestState;
import com.hmm.logistics.roomClean.entity.RoomClean;
import com.hmm.room.entity.Room;
@RestController
@RequestMapping("/guest")
public class GuestController {

@Autowired
 private  IGuestService guestService;


@RequestMapping("/findCheckInGuest")
public Page<GuestDto> getCheckInPages(ExtjsPageRequest pageable){
    pageable.setSort("idCard");
    Page<GuestDto> guestPage = guestService.findAllCheckInGuest(pageable.getPageable());
    return guestPage;
}


@RequestMapping("/findGuestByIdCard")
@ResponseBody
public GuestInfoDto findGuestByIdCard(String cardNumber){
    System.out.println("132456");
    Guest guest = guestService.findGuestByIdCard(cardNumber);
    if (guest == null) {
        return null;
    } else {
        GuestInfoDto guestInfoDto = new GuestInfoDto();
        Room room = guest.getRoom();
        System.out.println(room);
        BeanUtils.copyProperties(guest, guestInfoDto);
        guestInfoDto.setGuestState(guest.getState());
        if (room != null) {
            BeanUtils.copyProperties(room, guestInfoDto);
            guestInfoDto.setRoomState(room.getState());
        }
        System.out.println(guestInfoDto);
        return guestInfoDto;
    }
}


@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
@ResponseBody
public ExtAjaxResponse uploadPhoto(MultipartFile file,HttpServletRequest request){
    String webappPath = request.getSession().getServletContext().getRealPath("/");
    // ????????????
    String savePath = webappPath + "resources\\images\\upload";
    File targetfile = new File(savePath);
    // ?????????????????????????????????????????????
    if (!targetfile.exists() && !targetfile.isDirectory()) {
        System.out.println(savePath + "??????????????????????????????");
        // ????????????
        targetfile.mkdir();
    }
    // ????????????????????????
    String fileName = file.getOriginalFilename();
    /* ????????????????????????????????????????????????UUID */
    String uuid = UUID.randomUUID().toString().replace("-", "").substring(2, 10);
    String saveFileName = uuid + fileName;
    String showPath = "resources\\images\\upload\\" + uuid + fileName;
    System.out.println(showPath);
    try {
        // ????????????????????????????????????
        InputStream fileInputStream = file.getInputStream();
        // ???????????????????????????
        FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
        // ?????????????????????
        byte[] buffer = new byte[1024];
        // ??????????????????????????????????????????????????????
        int len = 0;
        // ?????????????????????????????????????????????(len=in.read(buffer))>0?????????in??????????????????
        while ((len = fileInputStream.read(buffer)) > 0) {
            // ??????FileOutputStream??????????????????????????????????????????????????????(savePath + "\\" + filename)??????
            out.write(buffer, 0, len);
        }
        // ???????????????
        fileInputStream.close();
        // ???????????????
        out.close();
        Map<String, String> map = new HashMap<>();
        map.put("src", showPath);
        map.put("msg", "????????????");
        return new ExtAjaxResponse(true, map);
    } catch (Exception e) {
        return new ExtAjaxResponse(false, "????????????!");
    }
}


@RequestMapping("/findAll")
public Page<GuestDto> getPages(ExtjsPageRequest pageable){
    pageable.setSort("idCard");
    Page<GuestDto> guestPage = guestService.findAll(pageable.getPageable());
    return guestPage;
}


@RequestMapping("/findAllVipGuest")
public Page<GuestDto> getVipPages(ExtjsPageRequest pageable){
    pageable.setSort("idCard");
    Page<GuestDto> guestPage = guestService.findAllVipGuest(pageable.getPageable());
    return guestPage;
}


@RequestMapping("/saveGuest")
public String saveGuest(String[] guestList){
    GuestInfoDto guestInfoDto = new GuestInfoDto();
    for (String string : guestList) {
        System.out.println(string);
    }
    int index = 0;
    System.out.println(guestList.length);
    while (index < guestList.length) {
        String idcard = null;
        if (index != 0) {
            idcard = guestList[index].substring(11).replace("\"", "");
        } else {
            idcard = guestList[index].substring(12).replace("\"", "");
        }
        System.out.println(idcard);
        Guest guest = guestService.findGuestByIdCard(idcard);
        if (guest == null) {
            // ?????????????????????????????????
            Guest newGuest = new Guest();
            newGuest.setGuestId("Guest_" + idcard);
            newGuest.setIdCard(idcard);
            newGuest.setRealName(guestList[index + 1].substring(12).replace("\"", ""));
            newGuest.setAddress(guestList[index + 2].substring(11).replace("\"", ""));
            newGuest.setPhone(guestList[index + 3].substring(9).replace("\"", ""));
            if (guestList[index + 4].substring(9).replace("\"", "").equals("??????")) {
                newGuest.setGender(Gender.MALE);
            } else {
                newGuest.setGender(Gender.FEMALE);
            }
            // ????????????
            newGuest.setState(GuestState.CASUAL);
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date da = simple.parse(guestList[index + 6].substring(15).replace("\"", ""));
                newGuest.setRegisterTime(da);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newGuest.setPhotoUrl(guestList[index + 7].substring(13).replace("\"}]", ""));
            System.out.println(newGuest);
            guestService.save(newGuest);
        } else {
            guest.setPhotoUrl(guestList[7].substring(12).replace("\"}]", ""));
            System.out.println(guest);
            guestService.save(guest);
        }
        // ????????????Url
        index += guestList.length;
    }
    return "success";
}


@RequestMapping("/findGuestByRoomNo")
public List<GuestInfoDto> findGuestByRoomNo(String roomNo){
    // System.out.println(roomNo);
    List<Guest> guests = guestService.findGuestByRoomNo(roomNo);
    List<GuestInfoDto> guestDtos = new ArrayList<>();
    for (Guest guest : guests) {
        GuestInfoDto guestInfoDto = new GuestInfoDto();
        Room room = guest.getRoom();
        System.out.println(room);
        BeanUtils.copyProperties(guest, guestInfoDto);
        guestInfoDto.setGuestState(guest.getState());
        if (room != null) {
            BeanUtils.copyProperties(room, guestInfoDto);
            guestInfoDto.setRoomState(room.getState());
        }
        System.out.println(guestInfoDto);
        guestDtos.add(guestInfoDto);
    }
    System.out.println(guestDtos.size());
    System.out.println(guestDtos);
    return guestDtos;
}


}