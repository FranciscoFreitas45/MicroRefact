package com.zammc.service.goods.impl;
 import com.zammc.common.FileUtil;
import com.zammc.domain.goods.GoodsCateEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.GoodsCateRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.goods.GoodsCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.zammc.Interface.IdWorker;
import com.zammc.DTO.PageBean;
@Service
public class GoodsCateServiceImpl implements GoodsCateService{

@Autowired
 private  IdWorker idWorker;

@Autowired
 private  GoodsCateRepository goodsCateRepository;


public GoodsCateEntity queryGoodsCateById(GoodsCateEntity goodsCateEntity){
    return goodsCateRepository.findOne(goodsCateEntity.getCateId());
}


@Override
public void forbiddenCate(GoodsCateEntity goodsCateEntity){
    GoodsCateEntity one = goodsCateRepository.findOne(goodsCateEntity.getCateId());
    if (null != one && one.getDataStatus() == 0 && one.getStatus() == 1) {
        one.setStatus((byte) 0);
        goodsCateRepository.saveAndFlush(one);
    }
}


public void queryGoodsCatePage(GoodsCateEntity goodsCateEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<GoodsCateEntity> goodsCatePage = goodsCateRepository.findAll(GoodsCateSpecification.where(goodsCateEntity), pageable);
    pageBean.setPageSize(goodsCatePage.getSize());
    pageBean.setPageNum(goodsCatePage.getNumber() + 1);
    pageBean.setTotalPage(goodsCatePage.getTotalPages());
    pageBean.setTotalRecorder(goodsCatePage.getTotalElements());
    pageBean.setContent(goodsCatePage.getContent());
}


public Message addGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request){
    if (null == goodsCateEntity.getCateName() || "".equals(goodsCateEntity.getCateName())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "分类名称不能为空");
    }
    if (null == goodsCateEntity.getSort()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "分类显示顺序不能为空");
    }
    MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
    MultipartFile image = mreq.getFile("image");
    if (image == null) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "上传图片不能为空");
    } else {
        String img = "";
        try {
            img = FileUtil.uploadFile(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        goodsCateEntity.setCateIcon(img);
    }
    goodsCateEntity.setCateId(idWorker.nextId());
    goodsCateEntity.setDataStatus((byte) 1);
    goodsCateRepository.saveAndFlush(goodsCateEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "新增成功");
}


public List<GoodsCateEntity> queryCateList(){
    return goodsCateRepository.queryCateList();
}


@Override
public void startUsingCate(GoodsCateEntity goodsCateEntity){
    GoodsCateEntity one = goodsCateRepository.findOne(goodsCateEntity.getCateId());
    if (null != one && one.getDataStatus() == 0 && one.getStatus() == 0) {
        one.setStatus((byte) 1);
        goodsCateRepository.saveAndFlush(one);
    }
}


@Override
public void deleteGoodsCate(GoodsCateEntity goodsCateEntity){
    GoodsCateEntity one = goodsCateRepository.findOne(goodsCateEntity.getCateId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        goodsCateRepository.saveAndFlush(one);
    }
}


public Message editGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request){
    if (null == goodsCateEntity.getCateName() || "".equals(goodsCateEntity.getCateName())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "分类名称不能为空");
    }
    if (null == goodsCateEntity.getSort()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "分类显示顺序不能为空");
    }
    GoodsCateEntity one = goodsCateRepository.findOne(goodsCateEntity.getCateId());
    if (null == goodsCateEntity.getCateIcon() && goodsCateEntity.getCateIcon() != one.getCateIcon()) {
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
        MultipartFile image = mreq.getFile("image");
        if (image == null) {
            return new Message(MessageStatus.FAIL, MessageTitle.失败, "上传图片不能为空");
        } else {
            String img = "";
            try {
                img = FileUtil.uploadFile(image.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            goodsCateEntity.setCateIcon(img);
        }
    }
    goodsCateRepository.saveAndFlush(goodsCateEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "修改成功");
}


}