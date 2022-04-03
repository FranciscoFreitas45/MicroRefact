package com.hmm.logistics.stock.controller;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.logistics.stock.dto.ShowInDetailedWinGrilDTO;
import com.hmm.logistics.stock.dto.StockDTO;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.service.IInDetailedService;
import com.hmm.logistics.stock.service.IOutDetailedService;
import com.hmm.logistics.stock.service.IOutStorageService;
import com.hmm.logistics.stock.service.IStockService;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.logistics.stock.util.YesOrNoSend;
@RestController
@RequestMapping("Stock")
public class StockController {

@Autowired
 private  IInDetailedService inDetailedService;

@Autowired
 private  IOutDetailedService outDetailedService;

@Autowired
 private  IOutStorageService outStorageService;

@Autowired
 private  IStockService stockService;


@GetMapping("/ShowInDetailedWinGril")
public Page<ShowInDetailedWinGrilDTO> getInDetailedPage(ShowInDetailedWinGrilDTO showInDetailedWinGrilDTO,ExtjsPageRequest pageRequest){
    Page<InDetailed> pageInDetailed = inDetailedService.findAll(ShowInDetailedWinGrilDTO.getWhereClause(showInDetailedWinGrilDTO), pageRequest.getPageable());
    List<InDetailed> listInDetailed = pageInDetailed.getContent();
    // System.out.println("listInDetailed:"+showInDetailedWinGrilDTO.getInStorageId());
    List<ShowInDetailedWinGrilDTO> listShowInDetailedWinGrilDTO = new ArrayList<ShowInDetailedWinGrilDTO>();
    for (InDetailed inDetailed : listInDetailed) {
        ShowInDetailedWinGrilDTO showInDetailedWinGrilDTOs = new ShowInDetailedWinGrilDTO();
        showInDetailedWinGrilDTOs.setAmount(inDetailed.getAmount());
        showInDetailedWinGrilDTOs.setGoodsName(inDetailed.getGoodsName());
        showInDetailedWinGrilDTOs.setUnit(inDetailed.getUnit());
        showInDetailedWinGrilDTOs.setGoodsNo(inDetailed.getGoodsNo());
        showInDetailedWinGrilDTOs.setInStorageId(inDetailed.getInAll().getInStorageId());
        if (stockService.findByGoodsNo(inDetailed.getGoodsNo()).getStockType() == StockType.COMMODITY) {
            showInDetailedWinGrilDTOs.setStockType("日用品");
        } else if (stockService.findByGoodsNo(inDetailed.getGoodsNo()).getStockType() == StockType.DURABLE) {
            showInDetailedWinGrilDTOs.setStockType("耐久品");
        }
        listShowInDetailedWinGrilDTO.add(showInDetailedWinGrilDTOs);
    }
    return new PageImpl<ShowInDetailedWinGrilDTO>(listShowInDetailedWinGrilDTO, pageRequest.getPageable(), inDetailedService.findAll(ShowInDetailedWinGrilDTO.getWhereClause(showInDetailedWinGrilDTO)).size());
}


@PostMapping
public ExtAjaxResponse saveEmploy(StockDTO stockDTO){
    try {
        Stock stock = new Stock();
        stock.setAmount(stockDTO.getAmount());
        stock.setGoodsName(stockDTO.getGoodsName());
        stock.setYesOrNoSend(YesOrNoSend.NO);
        if (stockDTO.getStockType().equals("COMMODITY")) {
            stock.setStockType(StockType.COMMODITY);
        } else if (stockDTO.getStockType().equals("DURABLE")) {
            System.out.println(stockDTO.getStockType());
            stock.setStockType(StockType.DURABLE);
        }
        if (stockDTO.getStockType().equals("COMMODITY")) {
            stock.setStockType(StockType.COMMODITY);
        }
        Double random = Math.random();
        String ss = random.toString().substring(2, 11);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        stock.setGoodsNo(sdf.format(date) + String.valueOf(ss));
        stock.setUnit(stockDTO.getUnit());
        stockService.save(stock);
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@GetMapping
public Page<StockDTO> getOutPage(StockDTO stockDTO,ExtjsPageRequest pageRequest){
    Page<Stock> pageStock = stockService.findAll(StockDTO.getWhereClause(stockDTO), pageRequest.getPageable());
    List<Stock> listStock = pageStock.getContent();
    List<StockDTO> listStockDTO = new ArrayList<StockDTO>();
    for (Stock stock : listStock) {
        StockDTO stockDTOs = new StockDTO();
        stockDTOs.setId(stock.getId());
        stockDTOs.setAmount(stock.getAmount());
        stockDTOs.setGoodsName(stock.getGoodsName());
        stockDTOs.setUnit(stock.getUnit());
        stockDTOs.setGoodsNo(stock.getGoodsNo());
        if (stock.getStockType() == StockType.COMMODITY) {
            stockDTOs.setStockType("日用品");
        } else if (stock.getStockType() == StockType.DURABLE) {
            stockDTOs.setStockType("耐久品");
        }
        if (stock.getYesOrNoSend() == YesOrNoSend.YES) {
            stockDTOs.setYesOrNoSend("已申请");
        } else if (stock.getYesOrNoSend() == YesOrNoSend.NO) {
            stockDTOs.setYesOrNoSend("未申请");
        } else if (stock.getYesOrNoSend() == YesOrNoSend.ENOUGH) {
            stockDTOs.setYesOrNoSend("库存足够");
        }
        listStockDTO.add(stockDTOs);
    }
    return new PageImpl<StockDTO>(listStockDTO, pageRequest.getPageable(), null != listStockDTO ? listStockDTO.size() : 0);
}


}