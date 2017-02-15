package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Print X-report
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintXReport extends Command {
    /**
     * Main command for print
     */
    public CommandPrintXReport() {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandPrintDailyReport(CommandPrintDailyReport.X_REPORT));
    }
}
