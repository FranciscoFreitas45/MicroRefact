package utilities.internal;
 import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import utilities.DatabaseConfig;
public class DatabaseEnquirer {


public boolean interpretLine(DatabaseUtil databaseUtil,String line){
    boolean result;
    String command;
    List<?> objects;
    int affected;
    result = false;
    try {
        command = StringUtils.substringBefore(line, " ");
        switch(command) {
            case "quit":
            case "exit":
                result = true;
                break;
            case "begin":
            case "open":
            case "start":
                databaseUtil.startTransaction();
                System.out.println("Transaction started");
                break;
            case "end":
            case "close":
            case "commit":
                databaseUtil.commitTransaction();
                System.out.println("Transaction committed");
                break;
            case "rollback":
                databaseUtil.rollbackTransaction();
                System.out.println("Transaction rollbacked");
                break;
            case "update":
            case "delete":
                affected = databaseUtil.executeUpdate(line);
                System.out.printf("%d objects %sd%n", affected, command);
                break;
            case "select":
                objects = databaseUtil.executeSelect(line);
                System.out.printf("%d object%s selected%n", objects.size(), (objects.size() == 1 ? "" : "s"));
                SchemaPrinter.print(objects);
                break;
            default:
                System.err.println("Command not understood");
        }
    } catch (final Throwable oops) {
        ThrowablePrinter.print(oops);
    }
    return result;
}


public void run(String title){
    DatabaseUtil databaseUtil;
    ConsoleReader reader;
    String line;
    boolean quit;
    EclipseConsole.fix();
    LogManager.getLogger("org.hibernate").setLevel(Level.OFF);
    databaseUtil = null;
    try {
        System.out.println(title);
        System.out.println(String.format("%1$" + title.length() + "s", "").replace(" ", "-"));
        System.out.println();
        System.out.printf("Initialising persistence context `%s'.%n", DatabaseConfig.PersistenceUnit);
        databaseUtil = new DatabaseUtil();
        databaseUtil.initialise();
        databaseUtil.setReadUncommittedIsolationLevel();
        System.out.println();
        reader = new ConsoleReader();
        do {
            line = reader.readCommand();
            quit = DatabaseEnquirer.interpretLine(databaseUtil, line);
        } while (!quit);
    } catch (final Throwable oops) {
        ThrowablePrinter.print(oops);
    } finally {
        if (databaseUtil != null) {
            System.out.println("Closing persistence context.");
            databaseUtil.shutdown();
        }
    }
}


}