package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.ExlImptplDetailes;
import com.ipe.module.core.service.ExlImptplDetailesService;
import com.ipe.module.core.web.controller.AbstractController;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
@RequestMapping("/exlImptplDetailes")
public class ExlImptplDetailesController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  ExlImptplDetailesService exlImptplDetailesService;


@RequestMapping(value = { "/getByTplId" })
@ResponseBody
public BodyWrapper getByTplId(String tplId){
    try {
        List<ExlImptplDetailes> detaileses = exlImptplDetailesService.findAll(new Specification<ExlImptplDetailes>() {

            @Override
            public Predicate toPredicate(Root<ExlImptplDetailes> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get("exlImptpl").get("id").as(String.class), tplId);
            }
        });
        return success(detaileses);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(ExlImptplDetailes exlImptplDetailes,RestRequest rest){
    try {
        exlImptplDetailesService.save(exlImptplDetailes);
        return success(exlImptplDetailes);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(ExlImptplDetailes exlImptplDetailes,RestRequest rest){
    try {
        exlImptplDetailesService.save(exlImptplDetailes);
        return success(exlImptplDetailes);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        exlImptplDetailesService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@Override
public Predicate toPredicate(Root<ExlImptplDetailes> root,CriteriaQuery<?> cq,CriteriaBuilder cb){
    return cb.equal(root.get("exlImptpl").get("id").as(String.class), tplId);
}


}