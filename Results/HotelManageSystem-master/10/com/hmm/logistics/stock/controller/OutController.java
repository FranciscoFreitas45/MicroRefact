import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.logistics.stock.dto.OutDTO;
import com.hmm.logistics.stock.dto.StockDTO;
import com.hmm.logistics.stock.entity.OutStorage;
import com.hmm.logistics.stock.service.IInDetailedService;
import com.hmm.logistics.stock.service.IOutDetailedService;
import com.hmm.logistics.stock.service.IOutStorageService;
import com.hmm.logistics.stock.service.IStockService;
@RestController
@RequestMapping("Out")
public class OutController {

@Autowired
 private  IInDetailedService inDetailedService;

@Autowired
 private  IOutDetailedService outDetailedService;

@Autowired
 private  IOutStorageService outStorageService;

@Autowired
 private  IStockService stockService;


@GetMapping
public Page<OutDTO> getOutPage(OutDTO outDTO,ExtjsPageRequest pageRequest){
    Page<OutStorage> a = outStorageService.findAll(null, pageRequest.getPageable());
    ExtjsPageRequest pageRequests = pageRequest;
    pageRequests.setLimit(100);
    int looo = outStorageService.findAll(null, pageRequests.getPageable()).getContent().size();
    List<OutStorage> OutStorages = a.getContent();
    List<OutDTO> outDTOs = new ArrayList<OutDTO>();
    for (OutStorage outStorage : OutStorages) {
        OutDTO loutDTO = new OutDTO();
        loutDTO.setId(outStorage.getId());
        loutDTO.setOutDate(outStorage.getOutDate());
        System.out.println(outStorage.getOutDate());
        loutDTO.setReason(outStorage.getReason());
        loutDTO.setRoomNo(outStorage.getRoomNo());
        if (outStorage.getWorker() != null) {
            loutDTO.setWorker(outStorage.getWorker().getUserName());
        } else {
            loutDTO.setWorker("");
        }
        outDTOs.add(loutDTO);
    }
    System.out.println(looo);
    return new PageImpl<OutDTO>(outDTOs, pageRequest.getPageable(), 50);
}


}