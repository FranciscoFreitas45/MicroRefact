package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.util.server.message.ChatMessage;
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String>{


public Page<ChatMessage> findByContextidAndUseridAndOrgi(String contextid,String userid,String orgi,Pageable page)
;

public Page<ChatMessage> findByAiidIsNotNullAndOrgi(String orgi,Pageable page)
;

public List<ChatMessage> findByOrgiAndAgentserviceidAndChatype(String orgi,String agentserviceid,String chatype)
;

public ChatMessage findById(String id)
;

public Page<ChatMessage> findByContextidAndOrgi(String contextid,String orgi,Pageable page)
;

public Page<ChatMessage> findByChatypeAndOrgi(String chatype,String orgi,Pageable page)
;

public Page<ChatMessage> findByUsessionAndOrgi(String usession,String orgi,Pageable page)
;

public Page<ChatMessage> findByUseridAndOrgi(String userid,String orgi,Pageable page)
;

public Page<ChatMessage> findByAgentserviceidAndOrgi(String agentserviceid,String orgi,Pageable page)
;

}