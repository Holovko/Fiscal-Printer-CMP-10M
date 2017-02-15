package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get Device date and time
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandGetTaxIdNumber extends Command {
    private static final int CODE = 0x46;
    /**
     * Main construct needed data for command
     */
    public CommandGetTaxIdNumber() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
