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
    // 保存路径
    String savePath = webappPath + "resources\\images\\upload";
    File targetfile = new File(savePath);
    // 判断上传文件的保存目录是否存在
    if (!targetfile.exists() && !targetfile.isDirectory()) {
        System.out.println(savePath + "目录不存在，需要创建");
        // 创建目录
        targetfile.mkdir();
    }
    // 获取上传的文件名
    String fileName = file.getOriginalFilename();
    /* 为了避免文件名重复，在文件名前加UUID */
    String uuid = UUID.randomUUID().toString().replace("-", "").substring(2, 10);
    String saveFileName = uuid + fileName;
    String showPath = "resources\\images\\upload\\" + uuid + fileName;
    System.out.println(showPath);
    try {
        // 得到输入流（字节流）对象
        InputStream fileInputStream = file.getInputStream();
        // 创建一个文件输出流
        FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
        // 创建一个缓冲区
        byte[] buffer = new byte[1024];
        // 判断输入流中的数据是否已经读完的标识
        int len = 0;
        // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while ((len = fileInputStream.read(buffer)) > 0) {
            // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
            out.write(buffer, 0, len);
        }
        // 关闭输入流
        fileInputStream.close();
        // 关闭输出流
        out.close();
        Map<String, String> map = new HashMap<>();
        map.put("src", showPath);
        map.put("msg", "部署成功");
        return new ExtAjaxResponse(true, map);
    } catch (Exception e) {
        return new ExtAjaxResponse(false, "部署失败!");
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
            // 说明这个身份证是新用户
            Guest newGuest = new Guest();
            newGuest.setGuestId("Guest_" + idcard);
            newGuest.setIdCard(idcard);
            newGuest.setRealName(guestList[index + 1].substring(12).replace("\"", ""));
            newGuest.setAddress(guestList[index + 2].substring(11).replace("\"", ""));
            newGuest.setPhone(guestList[index + 3].substring(9).replace("\"", ""));
            if (guestList[index + 4].substring(9).replace("\"", "").equals("男性")) {
                newGuest.setGender(Gender.MALE);
            } else {
                newGuest.setGender(Gender.FEMALE);
            }
            // 临时客人
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
        // 设置头像Url
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