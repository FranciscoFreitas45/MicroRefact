package com.ukefu.webim.web.handler.api.rest;
 import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.handler.api.request.SearchData;
import com.ukefu.webim.web.model.Contacts;
import com.ukefu.webim.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/api/contacts")
@Api(value = "联系人服务", description = "联系人管理功能")
public class ApiContactsController extends Handler{

@Autowired
 private  ContactsRepository contactsRepository;


@RequestMapping(method = RequestMethod.GET)
@Menu(type = "apps", subtype = "contacts", access = true)
@ApiOperation("返回联系人列表，支持分页，分页参数为 p=1&ps=50，默认分页尺寸为 20条每页")
public ResponseEntity<RestResult> list(HttpServletRequest request,String creater,String q){
    Page<Contacts> contactsList = null;
    if (!StringUtils.isBlank(creater)) {
        User user = super.getUser(request);
        contactsList = contactsRepository.findByCreaterAndSharesAndOrgi(user.getId(), user.getId(), super.getOrgi(request), false, q, new PageRequest(super.getP(request), super.getPs(request)));
    } else {
        contactsList = contactsRepository.findByOrgi(super.getOrgi(request), false, q, new PageRequest(super.getP(request), super.getPs(request)));
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK, new SearchData<Contacts>(contactsList)), HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.DELETE)
@Menu(type = "apps", subtype = "contacts", access = true)
@ApiOperation("删除联系人，联系人删除是逻辑删除，将 datastatus字段标记为 true，即已删除")
public ResponseEntity<RestResult> delete(HttpServletRequest request,String id){
    RestResult result = new RestResult(RestResultType.OK);
    if (!StringUtils.isBlank(id)) {
        Contacts contacts = contactsRepository.findOne(id);
        if (contacts != null) {
            // 系统管理员， 不允许 使用 接口删除
            contacts.setDatastatus(true);
            contactsRepository.save(contacts);
        }
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.PUT)
@Menu(type = "apps", subtype = "contacts", access = true)
@ApiOperation("新增或修改联系人，联系人部分字段是字典选项，请从字典接口获取数据")
public ResponseEntity<RestResult> put(HttpServletRequest request,Contacts contacts){
    if (contacts != null && !StringUtils.isBlank(contacts.getName())) {
        contacts.setOrgi(super.getOrgi(request));
        contacts.setCreater(super.getUser(request).getId());
        contacts.setUsername(super.getUser(request).getUsername());
        contacts.setOrgan(super.getUser(request).getOrgan());
        contacts.setCreatetime(new Date());
        contacts.setUpdatetime(new Date());
        contactsRepository.save(contacts);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
}


}