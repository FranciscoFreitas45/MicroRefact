package com.hmm.logistics.loseGoods.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.logistics.loseGoods.entity.LoseGoods;
import com.hmm.logistics.loseGoods.entity.LoseGoodsDTO;
import com.hmm.logistics.loseGoods.service.ILoseGoodsService;
import com.hmm.logistics.stock.dto.StockDTO;
import com.hmm.DTO.ExtjsPageRequest;
@RestController
@RequestMapping("LoseGoods")
public class LoseGoodsController {

@Autowired
 private  ILoseGoodsService loseGoodsService;


@PostMapping
public ExtAjaxResponse saveLoseGoods(LoseGoods loseGoods){
    try {
        loseGoodsService.save(loseGoods);
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@GetMapping
public Page<LoseGoods> getpage(LoseGoodsDTO loseGoodsDTO,ExtjsPageRequest pageRequest){
    return loseGoodsService.findAll(LoseGoodsDTO.getWhereClause(loseGoodsDTO), pageRequest.getPageable());
}


}