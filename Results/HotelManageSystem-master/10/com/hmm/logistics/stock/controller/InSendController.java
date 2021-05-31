import java.util.Date;
import javax.servlet.http.HttpSession;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.common.SessionUtil;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.service.EmployeeService;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.service.IInStorageService;
import com.hmm.logistics.stock.dto.InSendDTO;
import com.hmm.logistics.stock.entity.DoSend;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.service.IDoSendService;
import com.hmm.logistics.stock.service.IInDetailedService;
import com.hmm.logistics.stock.service.IOutDetailedService;
import com.hmm.logistics.stock.service.IOutStorageService;
import com.hmm.logistics.stock.service.IStockService;
import com.hmm.logistics.stock.util.InIn;
import com.hmm.logistics.stock.util.YesOrNoSend;
@RestController
@RequestMapping("InSend")
public class InSendController {

@Autowired
 private  IInDetailedService inDetailedService;

@Autowired
 private  IOutDetailedService outDetailedService;

@Autowired
 private  IOutStorageService outStorageService;

@Autowired
 private  IStockService stockService;

@Autowired
 private  IInStorageService inStorageService;

@Autowired
 private  EmployeeService employeeService;

@Autowired
 private  IDoSendService iDoSendService;


@PostMapping
public ExtAjaxResponse saveSend(String inStorageId,String listString,HttpSession session){
    try {
        // a
        DoSend doSend = new DoSend();
        doSend.setDoDate(new Date());
        doSend.setSendWorker(employeeService.findByUserName(SessionUtil.getUserName(session)));
        doSend.setInin(InIn.NO);
        // b
        InStorage inStorage = new InStorage();
        inStorage.setInStorageId(inStorageId);
        inStorage.setProcessStatus(ProcessStatus.NEW);
        inStorage.setEmployee(employeeService.findByUserName(SessionUtil.getUserName(session)));
        inStorageService.save(inStorage);
        doSend.setInAll(inStorage);
        iDoSendService.save(doSend);
        JSONArray list = new JSONArray(listString);
        for (int i = 0; i < list.length(); i++) {
            // c
            JSONObject jsonObject = (JSONObject) list.get(i);
            InDetailed inDetailed = new InDetailed();
            inDetailed.setAmount((float) jsonObject.getDouble("amount"));
            inDetailed.setGoodsName(jsonObject.getString("goodsName"));
            inDetailed.setUnit(jsonObject.getString("unit"));
            inDetailed.setGoodsNo(jsonObject.getString("goodsNo"));
            inDetailed.setInAll(inStorage);
            inStorage.getInDetaileds().add(inDetailed);
            Stock stock = stockService.findById(jsonObject.getLong("id"));
            stock.setYesOrNoSend(YesOrNoSend.YES);
            stockService.save(stock);
        }
        inStorageService.save(inStorage);
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        System.out.println(e);
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@GetMapping
public Page<InSendDTO> getInSendPage(InSendDTO inSendDTO,ExtjsPageRequest pageRequest){
    return null;
}


}