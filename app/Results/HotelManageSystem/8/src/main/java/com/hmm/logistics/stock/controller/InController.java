package com.hmm.logistics.stock.controller;
 import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.logistics.stock.dto.InDTO;
import com.hmm.logistics.stock.dto.InSendDTO;
import com.hmm.logistics.stock.dto.StockDTO;
import com.hmm.logistics.stock.entity.DoSend;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.service.IDoSendService;
import com.hmm.logistics.stock.service.IInDetailedService;
import com.hmm.logistics.stock.service.IOutDetailedService;
import com.hmm.logistics.stock.service.IOutStorageService;
import com.hmm.logistics.stock.service.IStockService;
import com.hmm.logistics.stock.util.InIn;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.logistics.stock.util.YesOrNoSend;
@RestController
@RequestMapping("In")
public class InController {

@Autowired
 private  IInDetailedService inDetailedService;

@Autowired
 private  IOutDetailedService outDetailedService;

@Autowired
 private  IOutStorageService outStorageService;

@Autowired
 private  IStockService stockService;

@Autowired
 private  IDoSendService iDoSendService;


@PostMapping("/synchro")
public ExtAjaxResponse synchro(){
    try {
        List<DoSend> find = iDoSendService.findAll();
        for (int i = 0; i < find.size(); i++) {
            if (find.get(i).getInAll().getProcessStatus().equals(ProcessStatus.COMPLETE) && find.get(i).getInin().equals(InIn.NO)) {
                // System.out.println(find.get(i).getInAll().getProcessStatus());
                find.get(i).setInin(InIn.YES);
                iDoSendService.save(find.get(i));
                // 获取详细表
                Iterator<InDetailed> getd = find.get(i).getInAll().getInDetaileds().iterator();
                List<InDetailed> listd = new ArrayList<InDetailed>();
                while (getd.hasNext()) listd.add(getd.next());
                for (int j = 0; j < listd.size(); j++) {
                    Stock stock = stockService.findByGoodsNo(listd.get(j).getGoodsNo());
                    System.out.println(stock.getGoodsName());
                    stock.setAmount(stock.getAmount() + listd.get(j).getAmount());
                    stock.setYesOrNoSend(YesOrNoSend.ENOUGH);
                    stockService.save(stock);
                }
            }
        // if(find.get(i).getInAll().getProcessStatus().equals(ProcessStatus.CANCEL)&&find.get(i).getInin().equals(InIn.NO)) {
        // //System.out.println(find.get(i).getInAll().getProcessStatus());
        // find.get(i).setInin(InIn.YES);
        // 
        // iDoSendService.save(find.get(i));
        // }
        }
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        System.out.println(e);
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@SuppressWarnings("deprecation")
@GetMapping
public Page<InDTO> getInPage(InDTO inDTO,ExtjsPageRequest pageRequest){
    Page<DoSend> pageDoSend = iDoSendService.findAll(InDTO.getWhereClause(inDTO), pageRequest.getPageable());
    List<DoSend> listDoSend = pageDoSend.getContent();
    List<InDTO> listInDTO = new ArrayList<InDTO>();
    for (DoSend doSend : listDoSend) {
        System.out.println(doSend.getInAll().getInStorageDate());
        InDTO inDTOs = new InDTO();
        inDTOs.setDoDate(doSend.getDoDate());
        inDTOs.setInStorageId(doSend.getInAll().getInStorageId());
        inDTOs.setSendWorker(doSend.getSendWorker().getUserName());
        if (doSend.getInAll().getAmount() != null) {
            inDTOs.setAmount(doSend.getInAll().getAmount());
        } else {
            inDTOs.setAmount(0);
        }
        if (doSend.getInAll().getInStorageDate() != null) {
            inDTOs.setInStorageDate(doSend.getInAll().getInStorageDate());
        }
        if (doSend.getInAll().getVender() != null) {
            inDTOs.setVender(doSend.getInAll().getVender());
        } else {
            inDTOs.setVender("");
        }
        if (doSend.getInAll().getEmployee() != null) {
            inDTOs.setWorker(doSend.getInAll().getEmployee().getUserName());
        } else {
            inDTOs.setWorker("");
        }
        listInDTO.add(inDTOs);
    }
    return new PageImpl<InDTO>(listInDTO, pageRequest.getPageable(), null != listInDTO ? listInDTO.size() : 0);
}


}