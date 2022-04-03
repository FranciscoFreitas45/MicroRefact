package de.metas.ui.web.handlingunits.report;
 import java.util.List;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.report.HUToReport;
import de.metas.ui.web.handlingunits.HUEditorRow;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode(of = "huId")
@ToString(exclude = "row")
public class HUEditorRowAsHUToReport implements HUToReport{

 private  HUEditorRow row;

 private  HuId huId;

 private  BPartnerId partnerId;

 private  String huUnitType;

 private  boolean topLevel;


@Override
public HuId getHUId(){
    return huId;
}


public HUEditorRowAsHUToReport of(HUEditorRow row){
    return new HUEditorRowAsHUToReport(row);
}


@Override
public List<HUToReport> getIncludedHUs(){
    return row.getIncludedRows().stream().map(HUEditorRow::getAsHUToReportOrNull).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());
}


@Override
public boolean isTopLevel(){
    return topLevel;
}


@Override
public String getHUUnitType(){
    return huUnitType;
}


@Override
public BPartnerId getBPartnerId(){
    return partnerId;
}


}