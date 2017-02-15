package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Print Z-report
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintZReport extends Command {
    /**
     * Main commands for print zero report
     */
    public CommandPrintZReport() {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandGetCounters(0));
        super.mCommandList.add(new CommandGetCounters(3));
        super.mCommandList.add(new CommandPrintDailyReport(CommandPrintDailyReport.Z_REPORT_WITH_ANULATE_CONTROL_TAPE));
        super.mCommandList.add(this);
    }
}
