package de.metas.ui.web.pporder.process;
 import java.util.stream.Stream;
import java.util.Objects;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
public class WEBUI_PP_Order_Template extends ViewBasedProcessTemplate{


public Stream<PPOrderLineRow> streamPPOrderLineRows(){
    return streamSelectedRows().map(PPOrderLineRow::cast).filter(Objects::nonNull);
}


@Override
public PPOrderLineRow getSingleSelectedRow(){
    return PPOrderLineRow.cast(super.getSingleSelectedRow());
}


@Override
public PPOrderLinesView getView(){
    return super.getView(PPOrderLinesView.class);
}


}