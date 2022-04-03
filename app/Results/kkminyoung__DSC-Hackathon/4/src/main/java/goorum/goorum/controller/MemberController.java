package goorum.goorum.controller;
 import goorum.goorum.domain.Member;
import goorum.goorum.service.MemberService;
import goorum.goorum.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import goorum.goorum.util.Constants;
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

@Autowired
 private  MemberService memberService;


@GetMapping("/logout")
public void logout(HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    HttpSession session = request.getSession();
    Member member = (Member) session.getAttribute("loginMember");
    if (Objects.isNull(member)) {
        log.warn("!! Invalid approach !!");
        res.put(RESULT, INVALID_APPROACH);
    } else {
        log.info("** [" + member.getId() + "] has logged out **");
        session.invalidate();
        res.put(RESULT, SUCCESS);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@GetMapping("/get-profile")
public void getProfilePhoto(String middlePath,String imageFileName,HttpServletRequest request,HttpServletResponse response){
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
}


@GetMapping("/check-duplicate")
public void checkDuplicate(String id,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    if (memberService.isDuplicate(id)) {
        res.put(RESULT, FAIL);
        log.info("Duplicate id : " + id);
    } else {
        res.put(RESULT, SUCCESS);
        log.info("Available id : " + id);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/join")
public void join(String id,String pwd,String email,String nick,HttpServletResponse response){
    JSONObject res = new JSONObject();
    try {
        Member newMember = memberService.join(id, pwd, email, nick);
        log.info("New Member[ " + newMember.getMemberId() + " : " + id + " ] has just signed up.");
        res.put(RESULT, SUCCESS);
    } catch (Exception e) {
        log.warn(e.toString());
        res.put(RESULT, FAIL);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/login")
public void login(String id,String pwd,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member loginMember = memberService.login(id, pwd);
    if (Objects.isNull(loginMember)) {
        res.put(RESULT, FAIL);
        log.info("** [" + id + "] Failed to log in **");
        return;
    } else {
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        if (loginMember.getMemberId() == 0) {
            res.put(RESULT, ADMIN_ID);
            log.info("** ADMIN has logged in **");
            return;
        } else {
            res.put(RESULT, SUCCESS);
            log.info("** [" + id + "] has logged in **");
        }
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@RequestMapping("/members")
public List getMembers(){
    List<Member> list = memberService.getList();
    return list;
}


}