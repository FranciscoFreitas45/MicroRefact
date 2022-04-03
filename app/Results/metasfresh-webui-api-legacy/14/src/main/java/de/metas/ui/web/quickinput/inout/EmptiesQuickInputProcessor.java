package de.metas.ui.web.quickinput.inout;
 import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.edi.model.I_M_InOut;
import de.metas.handlingunits.empties.EmptiesInOutLinesProducer;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
public class EmptiesQuickInputProcessor implements IQuickInputProcessor{


@Override
public Set<DocumentId> process(QuickInput quickInput){
    final I_M_InOut inout = quickInput.getRootDocumentAs(I_M_InOut.class);
    final IEmptiesQuickInput emptiesQuickInput = quickInput.getQuickInputDocumentAs(IEmptiesQuickInput.class);
    final Set<Integer> affectedDocumentIds = EmptiesInOutLinesProducer.newInstance(inout).addSource(emptiesQuickInput.getM_HU_PackingMaterial(), emptiesQuickInput.getQty()).create().getAffectedInOutLinesId();
    return affectedDocumentIds.stream().map(DocumentId::of).collect(ImmutableSet.toImmutableSet());
}


}