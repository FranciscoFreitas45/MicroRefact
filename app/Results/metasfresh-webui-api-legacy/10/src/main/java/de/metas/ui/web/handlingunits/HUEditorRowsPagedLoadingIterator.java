package de.metas.ui.web.handlingunits;
 import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import de.metas.cache.CCache;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.GuavaCollectors;
import de.metas.util.collections.IteratorUtils;
import lombok.Builder;
import lombok.NonNull;
public class HUEditorRowsPagedLoadingIterator implements Iterator<HUEditorRow>{

 private  int DEFAULT_BUFFERSIZE;

 private  HUEditorViewRepository huEditorRepo;

 private  CCache<DocumentId,HUEditorRow> cache;

 private  int bufferSize;

 private  Iterator<HUEditorRowId> rowIds;

 private  HUEditorRowFilter filter;

 private  Predicate<HUEditorRow> filterPredicate;

 private  Iterator<HUEditorRow> currentPageIterator;

 private  boolean finished;


@Override
public HUEditorRow next(){
    if (currentPageIterator == null) {
        throw new NoSuchElementException();
    }
    return currentPageIterator.next();
}


public Stream<HUEditorRow> stream(){
    return IteratorUtils.stream(this);
}


@Override
public boolean hasNext(){
    if (finished) {
        return false;
    }
    if (currentPageIterator == null || !currentPageIterator.hasNext()) {
        currentPageIterator = getNextPageIterator();
        if (!currentPageIterator.hasNext()) {
            finished = true;
            return false;
        } else {
            return true;
        }
    } else {
        return currentPageIterator.hasNext();
    }
}


public Iterator<HUEditorRow> getNextPageIterator(){
    // the result; part of it will be taken from cache, the, rest will be loaded
    final HUEditorRow[] rows = new HUEditorRow[bufferSize];
    // HUEditorRowIds that we don't have in the cache and that therefore need to be loaded
    final Map<HUEditorRowId, Integer> rowIdToLoad2index = new HashMap<>();
    // Get from cache as much as possible
    {
        int idx = 0;
        while (rowIds.hasNext() && idx < bufferSize) {
            final HUEditorRowId rowId = rowIds.next();
            final HUEditorRowId topLevelRowId = rowId.toTopLevelRowId();
            final HUEditorRow topLevelRow = cache.get(topLevelRowId.toDocumentId());
            if (topLevelRow == null) {
                // to be loaded
                rowIdToLoad2index.put(rowId, idx);
            } else {
                if (rowId.equals(topLevelRowId)) {
                    rows[idx] = topLevelRow;
                } else {
                    rows[idx] = topLevelRow.getIncludedRowById(rowId.toDocumentId()).orElse(null);
                }
            }
            idx++;
        }
    }
    // 
    // Load missing rows (which were not found in cache)
    if (!rowIdToLoad2index.isEmpty()) {
        final ListMultimap<HUEditorRowId, HUEditorRowId> topLevelRowId2rowIds = rowIdToLoad2index.keySet().stream().map(rowId -> GuavaCollectors.entry(rowId.toTopLevelRowId(), rowId)).collect(GuavaCollectors.toImmutableListMultimap());
        final Set<HuId> topLevelHUIds = topLevelRowId2rowIds.keys().stream().map(HUEditorRowId::getTopLevelHUId).collect(ImmutableSet.toImmutableSet());
        huEditorRepo.retrieveHUEditorRows(topLevelHUIds, filter).forEach(topLevelRow -> {
            final HUEditorRowId topLevelRowId = topLevelRow.getHURowId();
            for (final HUEditorRowId includedRowId : topLevelRowId2rowIds.get(topLevelRowId)) {
                final Integer idx = rowIdToLoad2index.remove(includedRowId);
                if (idx == null) {
                    // wtf?! shall not happen
                    continue;
                }
                if (topLevelRowId.equals(includedRowId)) {
                    rows[idx] = topLevelRow;
                    cache.put(topLevelRow.getId(), topLevelRow);
                } else {
                    rows[idx] = topLevelRow.getIncludedRowById(includedRowId.toDocumentId()).orElse(null);
                }
            }
        });
    }
    return Stream.of(rows).filter(// IMPORTANT: just to make sure we won't stream some empty gaps (e.g. missing rows because HU was not a top level one)
    Objects::nonNull).filter(filterPredicate).iterator();
}


}