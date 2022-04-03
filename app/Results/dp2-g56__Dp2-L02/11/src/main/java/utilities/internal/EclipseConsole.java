package utilities.internal;
 import java.io.PrintStream;
public class EclipseConsole {

 private  boolean isFixed;


public void fix(){
    // Inserts a 200ms delay into the System.err or System.out OutputStreams
    // every time the output switches from one to the other. This prevents the
    // Eclipse console from showing the output of the two streams out of order.
    EclipseStream out, err;
    try {
        if (!EclipseConsole.isFixed) {
            EclipseConsole.isFixed = true;
            out = new EclipseStream(System.out);
            err = new EclipseStream(System.err);
            System.setOut(new PrintStream(out, true, "iso-8859-1"));
            System.setErr(new PrintStream(err, true, "iso-8859-1"));
        }
    } catch (final Throwable oops) {
        throw new RuntimeException(oops);
    }
}


}