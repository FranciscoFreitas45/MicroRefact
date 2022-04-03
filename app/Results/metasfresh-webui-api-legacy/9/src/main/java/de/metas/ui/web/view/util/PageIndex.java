package de.metas.ui.web.view.util;
 import de.metas.printing.esb.base.util.Check;
import lombok.Value;
@Value
public class PageIndex {

 private int firstRow;

 private int pageLength;


public PageIndex getPageContainingRow(int rowIndex,int pageLength){
    Check.assume(rowIndex >= 0, "rowIndex >= 0");
    Check.assume(pageLength > 0, "pageLength > 0");
    final int page = rowIndex / pageLength;
    final int firstRow = page * pageLength;
    return ofFirstRowAndPageLength(firstRow, pageLength);
}


public PageIndex ofFirstRowAndPageLength(int firstRow,int pageLength){
    return new PageIndex(firstRow, pageLength);
}


}