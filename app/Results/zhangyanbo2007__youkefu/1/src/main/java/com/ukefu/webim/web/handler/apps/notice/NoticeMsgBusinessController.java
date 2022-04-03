package com.ukefu.webim.web.handler.apps.notice;
 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.NoticeMsgRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.NoticeMsg;
import com.ukefu.webim.web.model.User;
@Controller
@RequestMapping("/notice/msg/business")
public class NoticeMsgBusinessController extends Handler{

@Autowired
 private  NoticeMsgRepository noticeMsgRes;


@RequestMapping("/index")
@Menu(type = "notice", subtype = "noticemsgbus")
public ModelAndView index(ModelMap map,HttpServletRequest request,HttpServletResponse response,String msg){
    final String orgi = super.getOrgi(request);
    final User user = super.getUser(request);
    Page<NoticeMsg> noticeMsgList = noticeMsgRes.findAll(new Specification<NoticeMsg>() {

        @Override
        public Predicate toPredicate(Root<NoticeMsg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<Predicate>();
            list.add(cb.equal(root.get("orgi").as(String.class), orgi));
            list.add(cb.equal(root.get("target").as(String.class), user.getId()));
            list.add(cb.equal(root.get("type").as(String.class), UKDataContext.NoticeType.BUSINESS.toString()));
            list.add(cb.equal(root.get("datastatus").as(boolean.class), false));
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }
    }, new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
    map.addAttribute("noticeMsgList", noticeMsgList);
    map.addAttribute("msg", msg);
    map.addAttribute("type", UKDataContext.NoticeType.BUSINESS.toString());
    return request(super.createAppsTempletResponse("/apps/notice/msg/index"));
}


@RequestMapping("/detail")
@Menu(type = "notice", subtype = "noticemsg")
public ModelAndView detail(ModelMap map,HttpServletRequest request,String id,String msg){
    if (!StringUtils.isBlank(id)) {
        NoticeMsg noticemsg = noticeMsgRes.findByIdAndOrgi(id, super.getOrgi(request));
        if (noticemsg != null) {
            noticemsg.setStatus(true);
            noticeMsgRes.save(noticemsg);
            map.addAttribute("notice", noticemsg);
        }
    }
    map.addAttribute("msg", msg);
    return request(super.createAppsTempletResponse("/apps/notice/msg/detail"));
}


@Override
public Predicate toPredicate(Root<NoticeMsg> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> list = new ArrayList<Predicate>();
    list.add(cb.equal(root.get("orgi").as(String.class), orgi));
    list.add(cb.equal(root.get("target").as(String.class), user.getId()));
    list.add(cb.equal(root.get("type").as(String.class), UKDataContext.NoticeType.BUSINESS.toString()));
    list.add(cb.equal(root.get("datastatus").as(boolean.class), false));
    Predicate[] p = new Predicate[list.size()];
    return cb.and(list.toArray(p));
}


}