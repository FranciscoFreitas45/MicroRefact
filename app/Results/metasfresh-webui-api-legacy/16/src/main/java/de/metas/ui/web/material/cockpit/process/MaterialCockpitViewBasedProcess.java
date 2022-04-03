package de.metas.ui.web.material.cockpit.process;
 import java.util.stream.Stream;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.material.cockpit.MaterialCockpitView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
public class MaterialCockpitViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{


@Override
public Stream<MaterialCockpitRow> streamSelectedRows(){
    return super.streamSelectedRows().map(MaterialCockpitRow::cast);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable()


@Override
public MaterialCockpitView getView(){
    return MaterialCockpitView.cast(super.getView());
}


}