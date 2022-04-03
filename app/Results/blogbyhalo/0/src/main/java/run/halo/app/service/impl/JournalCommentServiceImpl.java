package run.halo.app.service.impl;
 import cn.hutool.core.collection.CollectionUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.JournalDTO;
import run.halo.app.model.entity.Journal;
import run.halo.app.model.entity.JournalComment;
import run.halo.app.model.vo.JournalCommentWithJournalVO;
import run.halo.app.repository.JournalCommentRepository;
import run.halo.app.repository.JournalRepository;
import run.halo.app.service.JournalCommentService;
import run.halo.app.service.OptionService;
import run.halo.app.service.UserService;
import run.halo.app.utils.ServiceUtils;
@Service
public class JournalCommentServiceImpl extends BaseCommentServiceImpl<JournalComment>implements JournalCommentService{

 private  JournalRepository journalRepository;

public JournalCommentServiceImpl(JournalCommentRepository journalCommentRepository, OptionService optionService, UserService userService, ApplicationEventPublisher eventPublisher, JournalRepository journalRepository) {
    super(journalCommentRepository, optionService, userService, eventPublisher);
    this.journalRepository = journalRepository;
}
@Override
@NonNull
public Page<JournalCommentWithJournalVO> convertToWithJournalVo(Page<JournalComment> journalCommentPage){
    Assert.notNull(journalCommentPage, "Journal comment page must not be null");
    // Convert the list
    List<JournalCommentWithJournalVO> journalCmtWithJournalVos = convertToWithJournalVo(journalCommentPage.getContent());
    // Build and return
    return new PageImpl<>(journalCmtWithJournalVos, journalCommentPage.getPageable(), journalCommentPage.getTotalElements());
}


@Override
public void validateTarget(Integer journalId){
    if (!journalRepository.existsById(journalId)) {
        throw new NotFoundException("查询不到该日志信息").setErrorData(journalId);
    }
}


}