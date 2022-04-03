package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import goorum.goorum.domain.Reply;
import goorum.goorum.domain.Replylist;
import goorum.goorum.repository.ReplyRepository;
import goorum.goorum.repository.ReplylistRepository;
import goorum.goorum.util.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import goorum.goorum.util.Constants.ADMIN_ID;
@Service
public class ReplyServiceImpl implements ReplyService{

 private  String DATE_FORMAT;

 private  int JUST_ONE_REPLY;

@Autowired
 private  ReplylistRepository replylistRepository;

@Autowired
 private  ReplyRepository replyRepository;


@Override
public int getListByMemberId(long memberId,int page,int size,ModelAndView mav){
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Replylist> replylistPage = replylistRepository.findAllByMemberId(memberId, pageRequest);
    List<Replylist> replies = replylistPage.getContent();
    replies.forEach(Conversion::convertDateFormatForArticleList);
    Conversion.convertContentLength(replies);
    int totalPages = replylistPage.getTotalPages();
    mav.addObject("replies", replies);
    mav.addObject("totalPages", totalPages);
    return replies.size();
}


@Override
public boolean deleteReply(long replyId,long parent,Member member){
    if (Objects.isNull(member)) {
        return false;
    }
    Optional<Reply> resReply = replyRepository.findByReplyIdAndParent(replyId, parent);
    if (!resReply.isPresent()) {
        return false;
    }
    if (resReply.get().getWriter() != member.getMemberId() && member.getMemberId() != ADMIN_ID) {
        return false;
    }
    List<Reply> replies = replyRepository.findAllByParent(parent);
    if ((replies.size() > JUST_ONE_REPLY) && (parent == replyId)) {
        Reply reply = resReply.get();
        reply.setContent("NULL");
        replyRepository.save(reply);
        return true;
    }
    replyRepository.deleteById(replyId);
    return !replyRepository.findById(replyId).isPresent();
}


@Override
public boolean writeReply(long boardId,long parent,String content,Member member){
    if (Objects.isNull(member)) {
        return false;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    String date = sdf.format(new Date());
    Reply reply = Reply.builder().parent(parent).board(boardId).writer(member.getMemberId()).content(content).date(date).build();
    reply = replyRepository.save(reply);
    if (parent == 0) {
        reply.setParent(reply.getReplyId());
        reply = replyRepository.save(reply);
    }
    return true;
}


@Override
public List<Replylist> getRepliesByBoardId(long boardId){
    List<Replylist> replies = replylistRepository.findAllByBoardId(boardId);
    replies.stream().peek(Conversion::convertContent).forEach(Conversion::convertDateFormatForArticle);
    return replies;
}


}