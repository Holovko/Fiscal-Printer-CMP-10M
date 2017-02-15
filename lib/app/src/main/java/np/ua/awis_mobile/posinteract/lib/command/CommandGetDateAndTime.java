package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get device date and time
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandGetDateAndTime extends Command {
    private static final int CODE = 0x21;
    /**
     * Main construct needed data for command
     */
    public CommandGetDateAndTime() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
