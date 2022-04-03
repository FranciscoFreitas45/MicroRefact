package de.metas.ui.web.websocket.WebsocketSender;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager.TrxEventTiming;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import de.metas.logging.LogManager;
import de.metas.util.Services;
import lombok.NonNull;
@lombok.Value
@lombok.Builder
public class WebsocketEvent {

 private  String destination;

 private  Object payload;

 private  boolean converted;


}