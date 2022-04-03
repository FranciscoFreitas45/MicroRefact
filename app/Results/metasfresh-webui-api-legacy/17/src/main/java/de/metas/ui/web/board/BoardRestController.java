package de.metas.ui.web.board;
 import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.comparator.FixedOrderByKeyComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import de.metas.ui.web.board.BoardCardChangeRequest.BoardCardChangeRequestBuilder;
import de.metas.ui.web.board.json.JSONBoard;
import de.metas.ui.web.board.json.JSONBoard.JSONBoardBuilder;
import de.metas.ui.web.board.json.JSONBoardCard;
import de.metas.ui.web.board.json.JSONBoardCardAddRequest;
import de.metas.ui.web.board.json.JSONBoardCardOrderBy;
import de.metas.ui.web.board.json.JSONBoardLane;
import de.metas.ui.web.board.json.JSONNewCardsViewLayout;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowOverridesHelper;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.view.json.JSONViewResult;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping(BoardRestController.ENDPOINT)
public class BoardRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  BoardDescriptorRepository boardsRepo;

@Autowired
 private  IViewsRepository viewsRepo;

 private  ConcurrentHashMap<Integer,Set<IView>> boardId2newCardsViewId;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


@GetMapping("/{boardId}/newCardsView/{viewId}/filter/{filterId}/field/{parameterName}/typeahead")
public JSONLookupValuesList getFilterParameterTypeahead(int boardId,String viewIdStr,String filterId,String parameterName,String query){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    return viewsRepo.getView(viewId).getFilterParameterTypeahead(filterId, parameterName, query, userSession.toEvaluatee()).transform(this::toJSONLookupValuesList);
}


@GetMapping("/{boardId}/newCardsView/{viewId}/filter/{filterId}/field/{parameterName}/dropdown")
public JSONLookupValuesList getFilterParameterDropdown(int boardId,String viewIdStr,String filterId,String parameterName){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    return viewsRepo.getView(viewId).getFilterParameterDropdown(filterId, parameterName, userSession.toEvaluatee()).transform(this::toJSONLookupValuesList);
}


public JSONViewResult toJSONCardsViewResult(int boardId,IView view,JSONOptions jsonOpts){
    final ViewResult viewResult = ViewResult.ofView(view);
    final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
    return JSONViewResult.of(viewResult, rowOverrides, jsonOpts);
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


public BoardCardChangeRequest createBoardCardChangeRequest(List<JSONDocumentChangedEvent> changes){
    if (changes.isEmpty()) {
        throw new AdempiereException("no changes requested");
    }
    final BoardCardChangeRequestBuilder request = BoardCardChangeRequest.builder();
    for (final JSONDocumentChangedEvent change : changes) {
        if (!change.isReplace()) {
            continue;
        }
        if ("laneId".equals(change.getPath())) {
            request.newLaneId(change.getValueAsInteger(-1));
        }
        if ("position".equals(change.getPath())) {
            request.newPosition(change.getValueAsInteger(-1));
        }
    }
    return request.build();
}


@GetMapping("/{boardId}")
public JSONBoard getBoard(int boardId){
    userSession.assertLoggedIn();
    final String adLanguage = userSession.getAD_Language();
    final BoardDescriptor boardDescriptor = boardsRepo.getBoardDescriptor(boardId);
    final Multimap<Integer, JSONBoardCard> cardsByLaneId = boardsRepo.getCards(boardId).stream().map(card -> JSONBoardCard.of(card, adLanguage)).collect(GuavaCollectors.toImmutableListMultimap(JSONBoardCard::getLaneId));
    final JSONBoardBuilder jsonBoard = JSONBoard.builder().boardId(boardId).caption(boardDescriptor.getCaption().translate(adLanguage)).websocketEndpoint(boardDescriptor.getWebsocketEndpoint());
    boardDescriptor.getLanes().values().stream().map(lane -> JSONBoardLane.builder().laneId(lane.getLaneId()).caption(lane.getCaption().translate(adLanguage)).cards(cardsByLaneId.get(lane.getLaneId())).build()).forEach(jsonBoard::lane);
    return jsonBoard.build();
}


@PatchMapping("/{boardId}/card/{cardId}")
public JSONBoardCard patchCard(int boardId,int cardId,List<JSONDocumentChangedEvent> changes){
    userSession.assertLoggedIn();
    final BoardCard card = boardsRepo.changeCard(boardId, cardId, createBoardCardChangeRequest(changes));
    return JSONBoardCard.of(card, userSession.getAD_Language());
}


@DeleteMapping("/{boardId}/card/{cardId}")
public void removeCard(int boardId,int cardId){
    userSession.assertLoggedIn();
    boardsRepo.removeCard(boardId, cardId);
    invalidateAllNewCardsViews(boardId);
}


@GetMapping("/{boardId}/newCardsView/layout")
public JSONNewCardsViewLayout getNewCardsViewLayout(int boardId){
    userSession.assertLoggedIn();
    final BoardDescriptor boardDescriptor = boardsRepo.getBoardDescriptor(boardId);
    final ViewLayout documentsViewLayout = viewsRepo.getViewLayout(boardDescriptor.getDocumentWindowId(), JSONViewDataType.list, ViewProfileId.NULL);
    final JSONDocumentLayoutOptions options = newJSONLayoutOptions();
    final String adLanguage = options.getAdLanguage();
    return JSONNewCardsViewLayout.builder().caption(documentsViewLayout.getCaption(adLanguage)).description(documentsViewLayout.getDescription(adLanguage)).emptyResultHint(documentsViewLayout.getEmptyResultHint(adLanguage)).emptyResultText(documentsViewLayout.getEmptyResultText(adLanguage)).filters(JSONDocumentFilterDescriptor.ofCollection(documentsViewLayout.getFilters(), options)).orderBys(boardDescriptor.getCardFields().stream().map(cardField -> JSONBoardCardOrderBy.builder().fieldName(cardField.getFieldName()).caption(cardField.getCaption().translate(adLanguage)).build()).collect(ImmutableList.toImmutableList())).build();
}


public void invalidateAllNewCardsViews(int boardId){
    final Set<IView> boardActiveViews = boardId2newCardsViewId.get(boardId);
    if (boardActiveViews == null) {
        return;
    }
    synchronized (boardActiveViews) {
        // NOTE: because we are actually not deleting from view but just filtering out the board cardIds,
        // we don't have to actually invalidate the view but just fire an "refresh request" to frontend
        boardActiveViews.forEach(view -> ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(view));
    }
}


@PostMapping("/{boardId}/card")
public JSONBoardCard addCard(int boardId,JSONBoardCardAddRequest request){
    userSession.assertLoggedIn();
    final int position = request.getPosition() != null ? request.getPosition() : Integer.MAX_VALUE;
    final BoardCard card = boardsRepo.addCardForDocumentId(boardId, request.getLaneId(), request.getDocumentId(), position);
    invalidateAllNewCardsViews(boardId);
    return JSONBoardCard.of(card, userSession.getAD_Language());
}


@GetMapping("/{boardId}/card")
@ApiOperation("gets cards indexed by cardId")
public Map<Integer,JSONBoardCard> getCards(int boardId,String cardIdsListStr){
    userSession.assertLoggedIn();
    final Set<Integer> cardIds = DocumentIdsSelection.ofCommaSeparatedString(cardIdsListStr).toIntSet();
    final List<BoardCard> cards = boardsRepo.getCards(boardId, cardIds);
    final String adLanguage = userSession.getAD_Language();
    return cards.stream().map(card -> JSONBoardCard.of(card, adLanguage)).collect(GuavaCollectors.toImmutableMapByKey(JSONBoardCard::getCardId));
}


public void addActiveNewCardsView(int boardId,IView view){
    final Set<IView> boardActiveViews = boardId2newCardsViewId.computeIfAbsent(boardId, id -> Collections.newSetFromMap(new WeakHashMap<>()));
    synchronized (boardActiveViews) {
        boardActiveViews.add(view);
    }
}


@PostMapping("/{boardId}/newCardsView")
public JSONViewResult createNewCardsView(int boardId){
    userSession.assertLoggedIn();
    final BoardDescriptor boardDescriptor = boardsRepo.getBoardDescriptor(boardId);
    final CreateViewRequest request = CreateViewRequest.builder(boardDescriptor.getDocumentWindowId(), JSONViewDataType.list).setStickyFilters(boardDescriptor.getDocumentFilters()).build();
    final IView view = viewsRepo.createView(request);
    addActiveNewCardsView(boardId, view);
    final JSONOptions jsonOpts = newJSONOptions();
    return toJSONCardsViewResult(boardId, view, jsonOpts);
}


@GetMapping("/{boardId}/newCardsView/{viewId}")
public JSONViewResult getNewCardsView(int boardId,String viewIdStr,int firstRow,int pageLength,String orderBysListStr){
    userSession.assertLoggedIn();
    final JSONOptions jsonOpts = newJSONOptions();
    final ViewRowsOrderBy orderBys = ViewRowsOrderBy.parseString(orderBysListStr, jsonOpts);
    final ViewResult viewResult = viewsRepo.getView(viewIdStr).getPageWithRowIdsOnly(firstRow, pageLength, orderBys);
    final List<Integer> boardCardIds = boardsRepo.retrieveCardIds(boardId);
    return toJSONCardsViewResult(boardId, viewResult, jsonOpts, // filter out cards which already exist in our board
    cardId -> !boardCardIds.contains(cardId));
}


public JSONDocumentLayoutOptions newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.of(userSession);
}


@GetMapping("/{boardId}/card/{cardId}")
public JSONBoardCard getCard(int boardId,int cardId){
    userSession.assertLoggedIn();
    final BoardCard card = boardsRepo.getCard(boardId, cardId);
    return JSONBoardCard.of(card, userSession.getAD_Language());
}


@PostMapping("/{boardId}/newCardsView/{viewId}/filter")
public JSONViewResult filterNewCardsView(int boardId,String viewIdStr,JSONFilterViewRequest jsonRequest){
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    final IView newView = viewsRepo.filterView(viewId, jsonRequest);
    final JSONOptions jsonOpts = newJSONOptions();
    return toJSONCardsViewResult(boardId, newView, jsonOpts);
}


}