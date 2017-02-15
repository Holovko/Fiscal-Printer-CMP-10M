package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Print summary data
 * Created by Andrey Holovko on 1/24/17.
 */
class CommandPrintSummaryData extends Command {
    private static final int CODE = 0x6D;

    /**
     * @param isPrintOnIndicator write on indicator
     */
    CommandPrintSummaryData(boolean isPrintOnIndicator) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        if (isPrintOnIndicator) {
            addTextParameter("1");
        } else {
            addTextParameter("0");
        }
        super.mCommandList.add(this);
    }
}
