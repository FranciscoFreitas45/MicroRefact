package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.VoiceTranscription;
public interface VoiceTranscriptionRepository extends JpaRepository<VoiceTranscription, String>{


public List<VoiceTranscription> findByCallidAndOrgi(String callid,String orgi)
;

public VoiceTranscription findByIdAndOrgi(String paramString,String orgi)
;

}