package de.metas.ui.web.board.BoardDescriptorRepository;
 import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.validationRule.IValidationRule;
import org.adempiere.ad.validationRule.IValidationRuleFactory;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.exceptions.DBUniqueConstraintException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.RecordZoomWindowFinder;
import org.compiere.model.I_AD_User;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Evaluatees;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import de.metas.currency.CurrencyRepository;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.money.CurrencyId;
import de.metas.ui.web.base.model.I_WEBUI_Board;
import de.metas.ui.web.base.model.I_WEBUI_Board_CardField;
import de.metas.ui.web.base.model.I_WEBUI_Board_Lane;
import de.metas.ui.web.base.model.I_WEBUI_Board_RecordAssignment;
import de.metas.ui.web.board.BoardCardFieldDescriptor.BoardFieldLoader;
import de.metas.ui.web.board.BoardDescriptor.BoardDescriptorBuilder;
import de.metas.ui.web.board.json.events.JSONBoardChangedEventsList;
import de.metas.ui.web.board.json.events.JSONBoardChangedEventsList.JSONBoardChangedEventsListBuilder;
import de.metas.ui.web.board.json.events.JSONBoardLaneChangedEvent;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.sql.DocumentFieldValueLoader;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class LaneCardsSequence {

 private  int laneId;

 private  List<Integer> cardIds;


public int getLaneId(){
    return laneId;
}


public List<Integer> getCardIds(){
    return cardIds;
}


public void addCardIdAtPosition(int cardId,int position){
    Preconditions.checkArgument(cardId > 0, "cardId > 0");
    if (position < 0) {
        cardIds.remove((Object) cardId);
        cardIds.add(cardId);
    } else if (position == Integer.MAX_VALUE || position > cardIds.size() - 1) {
        cardIds.remove((Object) cardId);
        cardIds.add(cardId);
    } else {
        cardIds.remove((Object) cardId);
        cardIds.add(position, cardId);
    }
}


public void removeCardId(int cardId){
    Preconditions.checkArgument(cardId > 0, "cardId > 0");
    cardIds.remove((Object) cardId);
}


public LaneCardsSequence copy(){
    return new LaneCardsSequence(laneId, cardIds);
}


}