package Interface;
public interface HUEditorView {

   public boolean matchesAnyRowRecursive(HUEditorRowFilter filter);
   public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter);
}