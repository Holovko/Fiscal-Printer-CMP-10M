package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get day counters using for print Z report
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandGetCounters extends Command {
    private static final int CODE = 0xE2;

    /**
     * @param idx value index
     */
    public CommandGetCounters(int idx) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter(String.valueOf(idx));
        super.mCommandList.add(this);
    }
}
