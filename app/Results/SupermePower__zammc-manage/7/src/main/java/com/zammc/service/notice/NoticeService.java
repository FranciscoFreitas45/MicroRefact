package com.zammc.service.notice;
 import com.zammc.domain.notice.NoticeEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface NoticeService {


public Message addNotice(NoticeEntity noticeEntity,HttpServletRequest request)
;

public void deleteNotice(NoticeEntity noticeEntity)
;

public void startUsingNotice(NoticeEntity noticeEntity)
;

public void queryNoticePage(NoticeEntity noticeEntity,PageBean pageBean)
;

public void forbiddenNotice(NoticeEntity noticeEntity)
;

public NoticeEntity queryNoticeById(NoticeEntity noticeEntity)
;

public Message editNotice(NoticeEntity noticeEntity,HttpServletRequest request)
;

}