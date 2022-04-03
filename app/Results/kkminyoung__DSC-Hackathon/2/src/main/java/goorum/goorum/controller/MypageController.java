package goorum.goorum.controller;
 import goorum.goorum.domain;
import goorum.goorum.service;
import goorum.goorum.util.Conversion;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import goorum.goorum.util.Constants;
import java.util.Objects.isNull;
import goorum.goorum.Interface.BoardService;
import goorum.goorum.Interface.MemberService;
import goorum.goorum.Interface.ReplyService;
import goorum.goorum.Interface.MemberLikeBoardService;
@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {

 private  String DEFAULT_PAGE;

 private  int DEFAULT_LIST_SIZE;

 private  String DEFAULT_TYPE;

@Autowired
 private  BoardService boardService;

@Autowired
 private  MemberService memberService;

@Autowired
 private  ReplyService replyService;

@Autowired
 private  MypageService mypageService;

@Autowired
 private  MemberLikeBoardService memberLikeBoardService;


@GetMapping("/set-default-profile")
public void setDefaultProfileImage(int id,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(loginMember) || loginMember.getMemberId() != id) {
        res.put("result", INVALID_APPROACH);
    } else {
        if (memberService.setProfilePhoto(loginMember.getMemberId(), null)) {
            res.put("result", SUCCESS);
        } else {
            res.put("result", FAIL);
        }
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
}


@GetMapping
public void showMypage(String type,Integer page,Long memberId,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    ModelAndView mav = new ModelAndView();
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(memberId)) {
        if (isNull(loginMember)) {
            mav.setViewName("redirect:/board");
            res.put("result", -2);
            return;
        }
        memberId = loginMember.getMemberId();
    }
    Mypage mypage = mypageService.getMypageInfo(memberId);
    if (isNull(mypage)) {
        res.put("result", FAIL);
    }
    int startPage = Conversion.calcStartPage(page);
    mav.addObject("mypage", mypage);
    mav.addObject("type", type);
    mav.addObject("curPage", page);
    mav.addObject("startPage", startPage);
    mav.setViewName("my_page");
    String nickname = mypage.getNickname();
    Integer boardNum = mypage.getBoardNum();
    Integer replyNum = mypage.getReplyNum();
    res.put("nick", nickname);
    // 게시글 수
    res.put("boardNum", boardNum);
    // 답변 수
    res.put("replyNum", replyNum);
    // 내가쓴 게시글 불러오기
    List<Boardlist> boards = boardService.getListByMemberId(memberId, page - 1, DEFAULT_LIST_SIZE, mav);
    JSONArray boardArray = new JSONArray();
    for (int i = 0; i < boards.size(); i++) {
        JSONObject data = new JSONObject();
        data.put("boardId", boards.get(i).getBoardId());
        data.put("category", boards.get(i).getCategory());
        data.put("sector", boards.get(i).getSector());
        data.put("company", boards.get(i).getCompany());
        data.put("title", boards.get(i).getTitle());
        data.put("content", boards.get(i).getContent());
        data.put("writerId", boards.get(i).getWriterId());
        data.put("writerNickname", boards.get(i).getWriterNickname());
        data.put("date", boards.get(i).getDate());
        data.put("likes", boards.get(i).getLikes());
        data.put("replies", boards.get(i).getReplies());
        boardArray.add(i, data);
    }
    res.put("board", boardArray);
    // 내가 좋아요한 글 불러오기
    List<MemberLikeBoard> memberLikeBoard = memberLikeBoardService.likeBoard(loginMember.getMemberId());
    JSONArray likeBoardArray = new JSONArray();
    for (int i = 0; i < memberLikeBoard.size(); i++) {
        JSONObject likedata = new JSONObject();
        long bid = memberLikeBoard.get(i).getBoardId();
        likedata.put("boardId", boardService.getBoardById(bid).getBoardId());
        likedata.put("category", boardService.getBoardById(bid).getCategory());
        likedata.put("sector", boardService.getBoardById(bid).getSector());
        likedata.put("company", boardService.getBoardById(bid).getCompany());
        likedata.put("title", boardService.getBoardById(bid).getTitle());
        likedata.put("content", boardService.getBoardById(bid).getContent());
        likedata.put("writerId", boardService.getBoardListById(bid).getWriterId());
        likedata.put("writerNickname", boardService.getBoardListById(bid).getWriterNickname());
        likedata.put("date", boardService.getBoardById(bid).getDate());
        likedata.put("likes", boardService.getBoardListById(bid).getLikes());
        likedata.put("replies", boardService.getBoardListById(bid).getReplies());
        likeBoardArray.add(i, likedata);
    }
    res.put("likeboard", likeBoardArray);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@GetMapping("/show-profile")
public ModelAndView showProfileImage(Long memberId){
    ModelAndView mav = new ModelAndView();
    Mypage mypage = mypageService.getMypageInfo(memberId);
    mav.addObject("mypage", mypage);
    mav.setViewName("show_profile");
    return mav;
}


}